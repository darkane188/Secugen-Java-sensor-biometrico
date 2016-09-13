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
public class Empresa {
    private int id;
    private String empresa;
    

    public Empresa(int id, String empresa) {
        this.id = id;
        this.empresa = empresa;
        
    }

    public Empresa() {
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
       public String toString()
    {
     return id +" "+ empresa;
   } 
    
}
