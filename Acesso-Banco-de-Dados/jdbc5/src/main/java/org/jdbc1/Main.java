package org.jdbc1;

import org.jdbc1.db.DB;
import org.jdbc1.db.DbException;
import org.jdbc1.db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static void main() {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);
            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
            int x = 1;
//            if (x<2){
//                throw new SQLException("Fake error");
//            }

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println(rows1 + "rows1");
            System.out.println(rows2 + "rows2");


        } catch (SQLException e){
            try {
                conn.rollback();
                throw new DbException("Operação reiniciada, por causa de: "+ e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Erro no rollback, por causa de: "+ e.getMessage());
            }
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
