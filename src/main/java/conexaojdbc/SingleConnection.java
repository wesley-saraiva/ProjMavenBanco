/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author wesle
 */
public class SingleConnection {
    
    private static String url = "jdbc:postgresql://localhost:5432/posjava";
    private static String password = "admin";
    private static String user = "postgres";
    private static Connection connection = null;
    
    static {
        conectar();
        
    }
    
    public SingleConnection() {
        conectar();
    }
    
    private static void conectar() {
        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
                System.out.println("Conectado com sucesso");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nao conectado");
        }
    }
    
    public static Connection getConnection() {
        return connection;
    }
}
