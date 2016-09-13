/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fabri
 */
public class Dbcargo {
    
    public static String user="postgres";
   public static String pass ="root";
 // public static String user="uxngpwci";
  // public static String pass ="AauBv_da_n6sViXybfT0P9ANID4bJfNf";
   //static String cadenaConexion = "jdbc:postgresql://elmer-01.db.elephantsql.com:5432/uxngpwci";
    static String cadenaConexion = "jdbc:postgresql://localhost:5432/restaurante";
    public static Connection conet = null;

    public static Connection conexion() {
        try {
            Class.forName("org.postgresql.Driver");

            conet = DriverManager.getConnection(cadenaConexion, user, pass);
            System.out.println("conexion establecida");
        } catch (ClassNotFoundException | SQLException e) {

            JOptionPane.showMessageDialog(null, "error de conexion " + e);
        }
        return conet;
    }

    public static ResultSet consulta(String sql) {

        ResultSet rs = null;
        try {
            Statement st = conexion().createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return rs;
    }

   /*public static void InsertarP(Persona p) {

        Connection conn = null;
        Calendar calendar = Calendar.getInstance();
        try {
            Connection con = DriverManager.getConnection(cadenaConexion, user, pass);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO persona VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, p.getCedula());
            stmt.setString(2, p.getNombre());
            stmt.setString(3, p.getApellidos());
            stmt.setString(4, p.getDireccion());
            stmt.setBinaryStream(5, p.getHuella());
            stmt.setString(6, p.getEstado());
            stmt.setString(7, p.getComedor());
            int res = stmt.executeUpdate();
            System.out.println(res);
          //  ResultSet rs = stmt.executeQuery();

            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
            System.out.println("Ocurrio un error : " + e.getSQLState());
        }
        System.out.println("La conexión se realizo sin problemas! =) ");

    }
    
     public static void mostrar(String valor){ 
   
       DefaultTableModel modelo = new DefaultTableModel();

       modelo.addColumn("CEDULA");
       modelo.addColumn("NOMBRE");
       modelo.addColumn("APELLIDO");
       modelo.addColumn("DIRECCION");

       modelo.addColumn("ESTADO");

       tablapersona.setModel(modelo);
       String sql = "";

       if (valor.equals("")) {
           sql = "SELECT * FROM persona";
           //System.out.println(Visualizar());
       } else {
           System.out.println(valor);
           sql = "SELECT * FROM persona WHERE idcliente='" + valor + "'";
           //sqls="select b.cupo from escuela.curso b ='"+valor+"'";
       }

       String[] datos = new String[6];

       int a = 0;
       try {

           ResultSet rs = consulta(sql);

           while (rs.next()) {
               a = a + 1;

               datos[0] = rs.getString(1);
               datos[1] = rs.getString(2);
               datos[2] = rs.getString(3);
               datos[3] = rs.getString(4);
             datos[4] = rs.getString(6);
               //datos[5] = rs.getString(6);

               modelo.addRow(datos);

           }
           tablapersona.setModel(modelo);

       } catch (SQLException ex) {

       }

    }
public static  int actualizarRegistro(Persona p){
        System.out.println("dddddd");
        int bandera=-1;
            try {
            PreparedStatement stmt=conexion().prepareStatement("UPDATE public.persona\n" +
"   SET  nom_cliente='"+p.getNombre()+"', ape_cliente='"+p.getApellidos()+"', dir_cliente='"+p.getDireccion()+"', estado='"+p.getEstado()+"', comedor='"+p.getComedor() +"' WHERE idcliente=?");
                 stmt.setString(1, p.getCedula());
            
           // stmt.setString(6, p.getEstado());
                int data = stmt.executeUpdate();
                if(data > 0 ){
                    bandera=1;
                   stmt.close();
                } 
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
                System.out.println(ex.getMessage());
            }
            
            return bandera;
    }
    public static Persona buscarP(String id) {
        Persona p = new Persona();
        {
            try {
                Connection con = DriverManager.getConnection(cadenaConexion, "postgres", "root");
                PreparedStatement stmt = con.prepareStatement("SELECT * from persona where idcliente=?");
                stmt.setString(1, id);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    p.setCedula(id);
                    p.setNombre(rs.getString("nom_cliente"));
                    p.setApellidos(rs.getString("ape_cliente"));
                    p.setDireccion(rs.getString("dir_cliente"));
                   // p.setHuella(rs.getBytes("huella"));
                    p.setEstado(rs.getString("estado"));
                    p.setComedor(rs.getString("comedor"));
                }
                stmt.close();
                con.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
            return p;
        }

    }

    public static void deletePersona(String cod) {
        try {
            Connection con = DriverManager.getConnection(cadenaConexion, "postgres", "root");
            PreparedStatement pstm = con.prepareStatement("delete from  persona  where idcliente = ?");
            pstm.setString(1, cod);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }*/


    
}
