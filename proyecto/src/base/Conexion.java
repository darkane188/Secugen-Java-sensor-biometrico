/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author Ricardo
 */
public class Conexion {
    public Conexion(){
        
    }
    
    public Connection getConexion(){
        Connection con = null;
        
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost/restaurante","postgres","root");
        } catch (Exception e) {
        }
        
        return con;
    }
}
