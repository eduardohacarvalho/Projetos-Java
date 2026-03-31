package org.jdbc1;

import org.jdbc1.db.DB;
import org.jdbc1.db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    static void main() {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "DELETE FROM department " +
                            "WHERE " +
                            "Id = ?"
            );
            st.setInt(1, 2);

            int rowsAffected = st.executeUpdate();
            System.out.println("Dados alterados! Linhas alteradas: " + rowsAffected);
        } catch (SQLException e){
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
