/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import entidades.Persona;
import entidades.Empresa;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Robert
 */
public class dbempresa {
    Conexion conexion;
    
    public dbempresa(){
        conexion = new Conexion();
    }
    
    public int insertEmpresa( String nombre_em){
        int rptaRegistro=0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("INSERT INTO public.empresa(nom_empresa) VALUES (?);");
            cs.setString(1, nombre_em);
           
            
            int numFAfectas = cs.executeUpdate();
            
            if(numFAfectas>0){
                JOptionPane.showMessageDialog(null, "registro exitoso");
            }
        } catch (Exception e) {
        }
        return rptaRegistro;
    }
    
    public ArrayList<Empresa> listEmpresa(){
        ArrayList listaEmpresa = new ArrayList();
        Empresa emp;
        try {
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("select * from empresa");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                emp = new Empresa();
                emp.setId(rs.getInt(1));
                emp.setEmpresa(rs.getString(2));
                
                listaEmpresa.add(emp);
            }
        } catch (Exception e) {
        }
        return listaEmpresa;
    }
    
    public int deleteEmpresa(int id){
        int filAfectadas= 0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_deleteEmpresa(?)}");
            cs.setInt(1, id);
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        
        return filAfectadas;
    }
    
    public int editEmpresa(int id, String nombre_em){
        int filAfectadas=0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_editEmpresa(?,?)}");
            cs.setInt(1, id);
            cs.setString(2, nombre_em);
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
        }
        return filAfectadas;  
    }
    
    public ArrayList<Empresa> buscaEmpresa(String nombre){
        ArrayList listaEmpresa = new ArrayList();
        Empresa emp;
        try {
            Connection acceDB = conexion.getConexion();
            CallableStatement cs = acceDB.prepareCall("SELECT id_empresa, nom_empresa\n" +"  FROM public.empresa\n" +
"\n" +"  WHERE nom_empresa =?;");
            cs.setString(1, nombre);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                emp = new Empresa();
                emp.setId(rs.getInt(1));
                emp.setEmpresa(rs.getString(2));
                listaEmpresa.add(emp);
            }
        } catch (Exception e) {
        }
        return listaEmpresa;
    }
}
