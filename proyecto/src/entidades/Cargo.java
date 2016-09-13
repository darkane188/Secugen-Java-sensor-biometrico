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
public class Cargo {
      private int id;
    private String cargo;
  

    public Cargo() {
    }

    public Cargo(int id, String cargo) {
        this.id = id;
        this.cargo = cargo;
        
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
     public String toString()
    {
     return id +" "+ cargo;
    }
    
}
