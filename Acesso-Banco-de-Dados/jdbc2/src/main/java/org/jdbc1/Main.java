package org.jdbc1;

import org.jdbc1.db.DB;
import org.jdbc1.db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    static void main() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st= null;

        try {
            conn = DB.getConnection();
            /*
            st = conn.prepareStatement(
                    "INSERT INTO seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "Julia Silva");
            st.setString(2, "julia@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("25/05/1985").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);
*/
            st = conn.prepareStatement(
                    "insert into department (Name) values ('D1'),('D2')",
                    Statement.RETURN_GENERATED_KEYS);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0 ){
                ResultSet resultSet = st.getGeneratedKeys();
                while (resultSet.next()){
                    int id = resultSet.getInt(1);
                    System.out.println("Feito! Id registrado: " + id);

                }
            } else {
                System.out.println("Nenhuma linha foi alterada no banco de dados");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

/*        Statement st = null;
        ResultSet rs = null;

        try {
            conn =DB.getConnection();

            st = conn.createStatement();

            rs = st.executeQuery("select * from department");
            while (rs.next()){
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
*/

    }
}
