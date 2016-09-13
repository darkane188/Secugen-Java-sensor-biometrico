/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;
import base.Conexion;
import entidades.Cargo;
import entidades.Empresa;
import entidades.Persona;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo
 */
public class PersonaDAO {
    Conexion conexion;
    
    public PersonaDAO(){
        conexion = new Conexion();
    }
    
    public int  insertPersona(String cedula, String nombres, String apellidos, int cargo, int empresa,String estado,ByteArrayInputStream huella){
        String rptaRegistro=null;
       int res=0;
           
              try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement stmt = accesoDB.prepareStatement("INSERT INTO persona VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, cedula);
            stmt.setString(2,nombres);
            stmt.setString(3, apellidos);
            stmt.setInt(4, cargo);
            stmt.setInt(5, empresa);
            stmt.setString(6, estado);
            stmt.setBinaryStream(7, huella);
             res = stmt.executeUpdate();
            System.out.println(res);
          //  ResultSet rs = stmt.executeQuery();

            stmt.close();
            accesoDB.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
            System.out.println("Ocurrio un error : " + e.getSQLState());
        }
        System.out.println("La conexión se realizo sin problemas! =) ");
        return res;
    }
    
    public ArrayList<Persona> listPersona(){
        ArrayList listaPersona = new ArrayList();
        Persona persona;
        try {
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("SELECT cedula, nombres, apellidos,nom_empresa,nom_cargo,estado\n" +
"  FROM public.persona inner join empresa on persona.id_empresa1= empresa.id_empresa\n" +
"inner join cargos on persona.idcargo_1=cargos.id_cargo");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                persona = new Persona();
                persona.setIdCliente(rs.getString(1));
                persona.setNombre(rs.getString(2));
                persona.setApellido(rs.getString(3));
                
                persona.setCargo(rs.getString(4));
                persona.setEmpresa(rs.getString(5));
                persona.setEstado(rs.getString(6));
                listaPersona.add(persona);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaPersona;
    }
    
    public int deletePersona(String dni){
        int filAfectadas= 0;
        try {
            Connection accesoDB = conexion.getConexion();
            PreparedStatement cs = accesoDB.prepareStatement("DELETE FROM public.persona\n" +
" WHERE cedula=?");
            cs.setString(1, dni);
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return filAfectadas;
    }
    
    public int editPersona( String cedula,String nombres, String apellidos,int cargo,int empresa,String estado){
        int filAfectadas=0;
        System.out.println("editar");
        try {
            Connection accesoDB = conexion.getConexion();
           PreparedStatement cs = accesoDB.prepareStatement("UPDATE public.persona\n" +
"   SET  nombres=?, apellidos=?, idcargo_1=?, id_empresa1=?, \n" +
"       estado=?\n" +
" WHERE cedula=?;");
            cs.setString(1, nombres);
            cs.setString(2, apellidos);
            cs.setInt(3, cargo);
            cs.setInt(4, empresa);
            cs.setString(5, estado);
            cs.setString(6, cedula);
            filAfectadas = cs.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return filAfectadas;  
    }
    
    public ArrayList<Persona> buscaPersona(String cedula){
        ArrayList listaPersona = new ArrayList();
        Persona persona;
        try {
            Connection acceDB = conexion.getConexion();
           PreparedStatement stmt = acceDB.prepareStatement("SELECT cedula, nombres, apellidos,nom_empresa,nom_cargo,estado\n" +
"  FROM public.persona inner join empresa on persona.id_empresa1= empresa.id_empresa\n" +
"inner join cargos on persona.idcargo_1=cargos.id_cargo\n" +
"WHERE cedula=?");
                stmt.setString(1, cedula);
            //cs.setString(1, apellido);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                persona = new Persona();
                persona.setIdCliente(rs.getString(1));
                persona.setNombre(rs.getString(2));
                persona.setApellido(rs.getString(3));
               
                persona.setCargo(rs.getString(4));
                persona.setEmpresa(rs.getString(5));
                 persona.setEstado(rs.getString(6));
                listaPersona.add(persona);
            }
        } catch (Exception e) {
        }
        return listaPersona;
    }
    public int buscaEmpresa(String empresa){
        
        int r=0;
        try {
            Connection acceDB = conexion.getConexion();
           PreparedStatement stmt = acceDB.prepareStatement("SELECT id_empresa" +
"  FROM public.empresa\n" +
"  WHERE nom_empresa = ?");
                stmt.setString(1, empresa);
            //cs.setString(1, apellido);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                r=rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;
    }
     public int buscaCargo(String empresa){
        
        int r=0;
        try {
            Connection acceDB = conexion.getConexion();
           PreparedStatement stmt = acceDB.prepareStatement("SELECT id_empresa" +
"  FROM public.empresa\n" +
"  WHERE nom_empresa = ?");
                stmt.setString(1, empresa);
            //cs.setString(1, apellido);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                r=rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;
    }
      public Vector<Cargo> cargarComboCargo(){
         Vector<Cargo> datos=new Vector<Cargo>(); 
        int r=0;
        try {
             
            Connection acceDB = conexion.getConexion();
           PreparedStatement stmt = acceDB.prepareStatement("SELECT  id_cargo,nom_cargo FROM public.cargos");
                //stmt.setString(1, empresa);
            //cs.setString(1, apellido);
            ResultSet rs = stmt.executeQuery();
           
            while(rs.next()){
                ///System.err.println(rs.getString(2));
                 Cargo c = new Cargo(rs.getInt(1),rs.getString(2));
                datos.addElement(c); 
                
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
      return datos;  
    }
      public Vector<Empresa> cargarComboEmpresa(){
         Vector<Empresa> datos=new Vector<Empresa>(); 
        int r=0;
        try {
             
            Connection acceDB = conexion.getConexion();
           PreparedStatement stmt = acceDB.prepareStatement("SELECT id_empresa, nom_empresa\n" +
"  FROM public.empresa");
            ResultSet rs = stmt.executeQuery();
           
            while(rs.next()){
                ///System.err.println(rs.getString(2));
                 Empresa e= new Empresa(rs.getInt(1),rs.getString(2));
                datos.addElement(e); 
                
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
      return datos;  
    }
}
