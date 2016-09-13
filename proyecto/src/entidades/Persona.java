/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.ByteArrayInputStream;

/**
 *
 * @author PC_QUIQUI
 */
public class Persona {
    private String idCliente;
    private String nombre;
    private String apellido;
    
   
    private String estado;
    private ByteArrayInputStream huella;
    private String empresa;
    private String cargo;

    public Persona(String idCliente, String nombre, String apellido, String estado, ByteArrayInputStream huella, String idempresa, String idcargo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
       
        this.estado = estado;
        this.huella = huella;
        this.empresa = idempresa;
        this.cargo = idcargo;
    }
    

    public Persona(String idCliente, String nombre, String apellido, String estado) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
     
        
        this.estado = estado;
    }

    public Persona(String Cedula, String Nombre, String Apellidos, ByteArrayInputStream huella ,String estado) {
        this.idCliente = Cedula;
        this.nombre = Nombre;
        this.apellido = Apellidos;
      
     
        this.huella=huella;
        this.estado=estado;
     
    }
   

    public Persona() {
    }

     public void setHuella(ByteArrayInputStream huella) {
        this.huella = huella;
    }

       public ByteArrayInputStream getHuella() {
        return huella;
    }
       

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


  

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
