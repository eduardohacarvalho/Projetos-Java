package org.jdbc1;

import org.jdbc1.db.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    static void main() {

        Connection conn = DB.getConnection();
        DB.closeConnection();

        String url = "jdbc:postgresql://localhost:5432/jdbc"; // Se seu banco tiver outro nome, troque o "postgres" no final
        String usuario = "postgres";
        String senha = "1234567890"; // Coloque a senha que você configurou no banco

        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Sucesso! Conectado ao PostgreSQL.");

            // Aqui você faria seus comandos de banco (SELECT, INSERT, etc.)

            conexao.close(); // É boa prática sempre fechar a conexão depois de usar
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco de dados.");
            e.printStackTrace();
        }


    }
}
