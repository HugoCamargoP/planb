/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Hugo Camargo
 */
public class Connection {
    //com.mysql.jdbc.Driver -> Clase para realizar la conexion a mysql
     private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
     //Especificamos la base de datos. userSSL=false es para evitar el warning      
     //que nos indica que no estamos usando el protocolo seguro SSL (Secure Sockets Layer)
     private static final String JDBC_URL = "jdbc:mysql://localhost/planB?userSSL=false";
     private static final String JDBC_USER = "root";//usuario de la db
     private static final String JDBC_PASS = "";//contraseña de la db
     private static Driver driver = null;
     
     //synchronized es para que solamente un hilo a la vez realice la conexion
     public static synchronized java.sql.Connection getConnection() throws SQLException{
         if(driver == null){ 
            try{
              //levantar y cargar en memoria la clase del driver de mysql
              Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
              //Instancear la clase del driver
              driver = (Driver) jdbcDriverClass.newInstance();
              //Registrar el driver
              DriverManager.registerDriver(driver);

            }catch(Exception e){
                System.out.println("Failed to load the JDBC driver");
                e.printStackTrace();
            }
         }
       return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);//Hacer la conexion
     }
     
    public static void close(ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
 
    public static void close(PreparedStatement stmt){
        try{
            if(stmt != null){
                stmt.close();
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
 
    public static void close(java.sql.Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
}