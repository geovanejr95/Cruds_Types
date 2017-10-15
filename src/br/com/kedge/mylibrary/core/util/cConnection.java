package br.com.kedge.mylibrary.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class cConnection {

    public static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/MyLibrary";
        String user = "root";
        String password = "Junior95@";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro de CNF ao tentar conectar no banco!");
        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println("Erro de SQL ao tentar conectar no banco!");
        }

        return conn;
    }
}
