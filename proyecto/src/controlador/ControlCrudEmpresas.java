/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import base.dbempresa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vistas.EMPRESAS;

/**
 *
 * @author Robert
 */
public class ControlCrudEmpresas implements ActionListener, KeyListener {

    EMPRESAS vistaCRUD = new EMPRESAS();
    dbempresa modeloCRUD = new dbempresa();

    public ControlCrudEmpresas(EMPRESAS vistaCRUD, dbempresa modeloCRUD) {
        this.modeloCRUD = modeloCRUD;
        this.vistaCRUD = vistaCRUD;
        this.vistaCRUD.btguardar.addActionListener(this);
        this.vistaCRUD.btnuevo.addActionListener(this);
        this.vistaCRUD.btmodificar.addActionListener(this);
       
       // this.vistaCRUD.txtempresa.addKeyListener(this);
        this.vistaCRUD.txtbuscar.addKeyListener(this);
        LLenarTabla(vistaCRUD.tablaempresa);

    }

    public ControlCrudEmpresas() {
        
        
    }

    public void LLenarTabla(JTable tablaD) {
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);

        modeloT.addColumn("CODIGO");
        modeloT.addColumn("EMPRESA");

        Object[] columna = new Object[5];

        int numRegistros = modeloCRUD.listEmpresa().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloCRUD.listEmpresa().get(i).getId();
            columna[1] = modeloCRUD.listEmpresa().get(i).getEmpresa();
            modeloT.addRow(columna);
        }
    }

    public void LimpiarCampos() {
        vistaCRUD.txtempresa.setText("");
        vistaCRUD.txtempresa.setEditable(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vistaCRUD.btguardar) {
            System.out.println("guardar");
            String empresa = vistaCRUD.txtempresa.getText();
            int rptaRegistro = modeloCRUD.insertEmpresa(empresa);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
   

        if (e.getSource() == vistaCRUD.txtempresa || e.getSource() == vistaCRUD.txtbuscar ) {
            char c = e.getKeyChar();
            if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != (char) KeyEvent.VK_SPACE)) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
      
    }

    @Override
    public void keyReleased(KeyEvent e) {
if (e.getSource() == vistaCRUD.txtbuscar) {

            String apellidos = vistaCRUD.txtbuscar.getText();

            DefaultTableModel modeloT = new DefaultTableModel();
            vistaCRUD.tablaempresa.setModel(modeloT);

            modeloT.addColumn("ID");
            modeloT.addColumn("NOMBRE EMPRESA");
         

            Object[] columna = new Object[2];

            int numRegistros = modeloCRUD.buscaEmpresa(apellidos).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = modeloCRUD.buscaEmpresa(apellidos).get(i).getId();
                columna[1] = modeloCRUD.buscaEmpresa(apellidos).get(i).getEmpresa();
               
                modeloT.addRow(columna);
            }
        }

    
    }
}
