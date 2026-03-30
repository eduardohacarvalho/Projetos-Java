package org.jdbc1.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection(){
        if (conn == null) {
            try{
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
                System.out.println("Conectado ao banco de dados com sucesso!");
            } catch (SQLException e) {
                throw  new DbException(e.getMessage());
            }

        }
        return conn;
    }

    public  static void closeConnection(){
        if(conn != null){
            try {
                conn.close();
                System.out.println("Desconexão do banco de dados realizada com sucesso!");
            } catch (SQLException e) {
                throw  new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties(){
        try (FileInputStream fs = new FileInputStream("src/main/db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e){
            throw  new DbException(e.getMessage());
        }
    }
}
