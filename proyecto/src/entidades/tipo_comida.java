/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

/**
 *
 * @author Miguel
 */
public class tipo_comida {
    private int id_comida;
    private int desc_comida;
    private String fec_inicio;
    private String fec_fin;
    private String carac;
    private double precio;
    private String tipo_com;

    public tipo_comida(int id_comida, int desc_comida, String hora_inicio, String hora_fin, String carac, double precio, String tipo_com) {
        this.id_comida = id_comida;
        this.desc_comida = desc_comida;
        this.fec_inicio = hora_inicio;
        this.fec_fin = hora_fin;
        this.carac = carac;
        this.precio = precio;
        this.tipo_com = tipo_com;
    }

    
   
    public tipo_comida() {
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo_com() {
        return tipo_com;
    }

    public void setTipo_com(String tipo_com) {
        this.tipo_com = tipo_com;
    }

   

    public int getId_comida() {
        return id_comida;
    }

    public void setId_comida(int id_comida) {
        this.id_comida = id_comida;
    }

    public int getDesc_comida() {
        return desc_comida;
    }

    public void setDesc_comida(int desc_comida) {
        this.desc_comida = desc_comida;
    }

    public String getfec_inicio() {
        return fec_inicio;
    }

    public void setfec_inicio(String hora_inicio) {
        this.fec_inicio = hora_inicio;
    }

    public String getfec_fin() {
        return fec_fin;
    }

    public void setfec_fin(String hora_fin) {
        this.fec_fin = hora_fin;
    }

    public String getCarac() {
        return carac;
    }

    public void setCarac(String carac) {
        this.carac = carac;
    }
    
    
    
    
    
    
    
}
