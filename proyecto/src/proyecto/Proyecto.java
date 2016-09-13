/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto;

import vistas.MENU_PRINCIPAL;

/**
 *
 * @author Miguel
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        final MENU_PRINCIPAL frame = new MENU_PRINCIPAL();
 
        frame.setExtendedState(MENU_PRINCIPAL.MAXIMIZED_BOTH);
 
       frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
       frame.setVisible(true);
    }
    
    
}
