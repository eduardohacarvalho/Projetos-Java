package org.jdbc1;

import org.jdbc1.db.DB;

import java.sql.*;

public class Main {
    static void main() {

        Connection conn = null;
        Statement st = null;
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


    }
}
