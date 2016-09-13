/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import SecuGen.FDxSDKPro.jni.JSGFPLib;
import SecuGen.FDxSDKPro.jni.SGDeviceInfoParam;
import SecuGen.FDxSDKPro.jni.SGFDxDeviceName;
import SecuGen.FDxSDKPro.jni.SGFDxErrorCode;
import SecuGen.FDxSDKPro.jni.SGFingerInfo;
import SecuGen.FDxSDKPro.jni.SGFingerPosition;
import SecuGen.FDxSDKPro.jni.SGImpressionType;
import SecuGen.FDxSDKPro.jni.SGPPPortAddr;
import entidades.Cargo;
import entidades.Empresa;

import base.PersonaDAO;
import java.awt.Image;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import util.ValidarCedula;
import vistas.EMPLEADOS;

/**
 *
 * @author Ricardo
 */
public final class ControladorCrud implements ActionListener, KeyListener {

    EMPLEADOS vistaCRUD = new EMPLEADOS();
    PersonaDAO modeloCRUD = new PersonaDAO();
    private SGDeviceInfoParam deviceInfo = new SGDeviceInfoParam();
    private long deviceName;
    private long devicePort;
    private JSGFPLib fplib = null;
    private long ret;
    byte[] regs = new byte[400];
    private BufferedImage imgRegistration1;
    ByteArrayInputStream huella;

    public ControladorCrud(EMPLEADOS vistaCRUD, PersonaDAO modeloCRUD) {
        this.modeloCRUD = modeloCRUD;
        this.vistaCRUD = vistaCRUD;
        this.vistaCRUD.btguardar.addActionListener(this);
        this.vistaCRUD.btncapturar.addActionListener(this);
        this.vistaCRUD.btnuevo.addActionListener(this);
      this.vistaCRUD.btncapturar.addActionListener(this);
        this.vistaCRUD.btnGedit.addActionListener(this);
        this.vistaCRUD.btmodificar.addActionListener(this);
        this.vistaCRUD.btneliminar.addActionListener(this);
        this.vistaCRUD.jcomboestado.addKeyListener(this);
        this.vistaCRUD.txtbuscar.addKeyListener(this);
        this.vistaCRUD.txtcedula.addKeyListener(this);
       
        bloquear();
        LLenarTabla(vistaCRUD.tablapersonas);
        //System.err.print(modeloCRUD.cargarComboCargo());
        CargarComboBoxCargo();
        CargarComboBoxEmpresa();
    }

    public void InicializarCrud() {
       // System.out.println("crud");
        //bloquear();
       // LLenarTabla(vistaCRUD.tablapersonas);

    }
    
     public void CargarComboBoxCargo()
      {
       
       
        for(int i=0;i<modeloCRUD.cargarComboCargo().size();i++)
          {
            Cargo oItem = new Cargo(modeloCRUD.cargarComboCargo().get(i).getId(),modeloCRUD.cargarComboCargo().get(i).getCargo());
         // System.out.println(modeloCRUD.cargarComboCargo().size()+ " "+ modeloCRUD.cargarComboCargo().get(i).getCargo());
           
         vistaCRUD.jcombocargo.addItem(oItem); 
         }
        
     }
      public void CargarComboBoxEmpresa()
      {
       
       
        for(int i=0;i<modeloCRUD.cargarComboEmpresa().size();i++)
          {
              Empresa eItem = new Empresa(modeloCRUD.cargarComboEmpresa().get(i).getId(),modeloCRUD.cargarComboEmpresa().get(i).getEmpresa());
            //ClaseObjetoParaComboBox oItem = new ClaseObjetoParaComboBox(oListaObjetoParaComboBox.get(i).getCodigo(),oListaObjetoParaComboBox.get(i).getNombreDeEjemplo());
          System.out.println(modeloCRUD.cargarComboEmpresa().size()+ " "+ modeloCRUD.cargarComboEmpresa().get(i).getEmpresa());
            vistaCRUD.jcomboempresa.addItem(eItem); 
         }
        }
      
       void desbloquear(){
    
    vistaCRUD. txtcedula.setEnabled(true);
     vistaCRUD.txtnombre.setEnabled(true);
     vistaCRUD.txtapellido.setEnabled(true);
     
//     calendario.setEnabled(true);
     //sexo.setEnabled(true);
     vistaCRUD.btnuevo.setEnabled(false);
     vistaCRUD.btguardar.setEnabled(true);
    vistaCRUD.btncapturar.setEnabled(true);
    vistaCRUD.btneliminar.setEnabled(true);
     //vistaCRUD.txtbuscar.setEnabled(true);
     vistaCRUD.btnGedit.setEnabled(true);
     vistaCRUD.btmodificar.setEnabled(true);
    }  

void bloquear(){
    
     vistaCRUD.txtcedula.setEnabled(false);
     vistaCRUD.txtnombre.setEnabled(false);
     vistaCRUD.txtapellido.setEnabled(false);
     //txtdireccion.setEnabled(false);
     //calendario.setEnabled(false);
     //sexo.setEnabled(false);
     vistaCRUD.btncapturar.setEnabled(false);
     vistaCRUD.btguardar.setEnabled(false);
    // vistaCRUD.btmodificar.setEnabled(false);
     //vistaCRUD.btneliminar.setEnabled(false);
     vistaCRUD.btnGedit.setEnabled(false);
    
    }  
    public void LLenarTabla(JTable tablaD) {
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);

        modeloT.addColumn("CEDULA");
        modeloT.addColumn("NOMBRES");
        modeloT.addColumn("APELLID0S");
        modeloT.addColumn("ESTADO");
        modeloT.addColumn("EMPRESA");
        modeloT.addColumn("CARGO");
        

        Object[] columna = new Object[6];

        int numRegistros = modeloCRUD.listPersona().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloCRUD.listPersona().get(i).getIdCliente();
            columna[1] = modeloCRUD.listPersona().get(i).getNombre();
            columna[2] = modeloCRUD.listPersona().get(i).getApellido();
            columna[3] = modeloCRUD.listPersona().get(i).getEstado();
            columna[4] = modeloCRUD.listPersona().get(i).getCargo();
            columna[5] = modeloCRUD.listPersona().get(i).getEmpresa();
            modeloT.addRow(columna);
        }
    }

    public void LimpiarCampos() {
        vistaCRUD.txtcedula.setText("");
        //vistaCRUD.txtempresa.setEditable(true);
        vistaCRUD.txtnombre.setText("");
        vistaCRUD.txtapellido.setText("");
             

       
    }

    @Override
    public void actionPerformed(ActionEvent e) {

  if (e.getSource() == vistaCRUD.btguardar) {
            System.err.println("holaaa");
            String cedula = vistaCRUD.txtcedula.getText();
         String apellidos = vistaCRUD.txtapellido.getText();
         String nombres = vistaCRUD.txtnombre.getText();
         String empresa = vistaCRUD.jcomboempresa.getSelectedItem().toString();
          String cargo = vistaCRUD.jcombocargo.getSelectedItem().toString();
      if(vistaCRUD.txtcedula.getText().isEmpty()||vistaCRUD.txtnombre.getText().isEmpty()||vistaCRUD.txtapellido.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"No deje campos en blanco","error",JOptionPane.ERROR_MESSAGE);
        }else
       
       if(vistaCRUD.lblcaptura.getIcon()==null){
            JOptionPane.showMessageDialog(null,"Registre la huella","error",JOptionPane.ERROR_MESSAGE);
        } 
     
     else {
           
    if(ValidarCedula.valida(vistaCRUD.txtcedula.getText())==false) { 
 
        JOptionPane.showMessageDialog(null,"cedula incorrecta");
                }  
    else{ 
          
   huella = new ByteArrayInputStream(regs);
   Cargo c = (Cargo) vistaCRUD.jcombocargo.getSelectedItem();
   System.out.println(c.getId());
   Empresa em =  (Empresa) vistaCRUD.jcomboempresa.getSelectedItem();
          
    System.out.println(em.getId());
   int rptaRegistro = modeloCRUD.insertPersona(cedula, nombres, apellidos,c.getId(),em.getId(),vistaCRUD.jcomboestado.getSelectedItem().toString(),huella);
     // System.out.println(rptaRegistro);
        vistaCRUD.lblcaptura.setIcon(null);
        LLenarTabla(vistaCRUD.tablapersonas);
        LimpiarCampos();
   
    }
       }
        
        }

      
 if (e.getSource() == vistaCRUD.btncapturar) {
            int[] quality = new int[1];
        SGFingerInfo fingerInfo = new SGFingerInfo();
        fingerInfo.FingerNumber = SGFingerPosition.SG_FINGPOS_LI;
        fingerInfo.ImageQuality = quality[0];
        fingerInfo.ImpressionType = SGImpressionType.SG_IMPTYPE_LP;
        fingerInfo.ViewNumber = 1;
        long iError = SGFDxErrorCode.SGFDX_ERROR_NONE;

            BufferedImage img1gray = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);
            byte[] imageBuffer1 = ((java.awt.image.DataBufferByte) imgRegistration1.getRaster().getDataBuffer()).getData();
        if (fplib != null) {
                ret = fplib.GetImageEx(imageBuffer1, 1000, 0, 50);
            if (ret == SGFDxErrorCode.SGFDX_ERROR_NONE) {
                   vistaCRUD.lblcaptura.setIcon(new ImageIcon(imgRegistration1.getScaledInstance(200, 230, Image.SCALE_DEFAULT)));

                     long ret2 = fplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer1, quality);
                    iError = fplib.CreateTemplate(fingerInfo, imageBuffer1, regs);

            } else {

            }
        } else {
            vistaCRUD.lblestado.setText("JSGFPLib is not Initialized---");
        }
        System.out.println();          
            
        }
 
 
 
 
 
 
 
        if (e.getSource() == vistaCRUD.btmodificar) {
            int filaEditar = vistaCRUD.tablapersonas.getSelectedRow();
            int numfilas = vistaCRUD.tablapersonas.getSelectedRowCount();

            if (filaEditar >= 0 && numfilas == 1) {
                vistaCRUD.txtcedula.setText(String.valueOf(vistaCRUD.tablapersonas.getValueAt(filaEditar, 0)));
                vistaCRUD.txtnombre.setText(String.valueOf(vistaCRUD.tablapersonas.getValueAt(filaEditar, 1)));
                vistaCRUD.txtapellido.setText(String.valueOf(vistaCRUD.tablapersonas.getValueAt(filaEditar, 2)));
                vistaCRUD.txtcedula.setEditable(false);
                vistaCRUD.btnGedit.setEnabled(true);
                 vistaCRUD.btguardar.setEnabled(false);
                vistaCRUD.btmodificar.setEnabled(false);
                vistaCRUD.btneliminar.setEnabled(false);
                //vistaCRUD.btnRegistrar.setEnabled(false);
                
               // vistaCRUD.btnListar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione registro a editar");
            }

        }

        if (e.getSource() == vistaCRUD.btnGedit) {
            System.out.println("botne edit");
            String cedula = vistaCRUD.txtcedula.getText();
            String apellidos = vistaCRUD.txtapellido.getText();
            String nombres = vistaCRUD.txtnombre.getText();
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            //String fecha = formatoFecha.format(vistaCRUD.jdFechaN.getDate());
           // String  = vistaCRUD.txtLugar.getText();
           Cargo c = (Cargo) vistaCRUD.jcombocargo.getSelectedItem();
             System.out.println(vistaCRUD.jcomboestado.getSelectedItem().toString());
         Empresa em =  (Empresa) vistaCRUD.jcomboempresa.getSelectedItem();

            int rptEdit = modeloCRUD.editPersona( cedula,nombres,apellidos,c.getId(),em.getId(),vistaCRUD.jcomboestado.getSelectedItem().toString());
            if (rptEdit > 0) {
                LimpiarCampos();
                JOptionPane.showMessageDialog(null, "Edicion exitosa.");
                LLenarTabla(vistaCRUD.tablapersonas);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo realizar edicion.");
            }
            vistaCRUD.txtcedula.setEditable(true);
            vistaCRUD.btnGedit.setEnabled(false);
            vistaCRUD.btmodificar.setEnabled(true);
            vistaCRUD.btneliminar.setEnabled(true);
            vistaCRUD.btguardar.setEnabled(true);
            
        }
    if (e.getSource() == vistaCRUD.btnuevo) {
        System.err.print("SENSOR");
              int selectedDevice = 0;
        switch (selectedDevice) {
            case 0: //USB
            default:
                this.deviceName = SGFDxDeviceName.SG_DEV_AUTO;
                break;

        }
        fplib = new JSGFPLib();
        ret = fplib.Init(this.deviceName);
        if ((fplib != null) && (ret == SGFDxErrorCode.SGFDX_ERROR_NONE)) {
            //lblestado.setText("JSGFPLib Initialization Success");
            this.devicePort = SGPPPortAddr.AUTO_DETECT;

            ret = fplib.OpenDevice(this.devicePort);
            if (ret == SGFDxErrorCode.SGFDX_ERROR_NONE) {
                 vistaCRUD.lblestado.setText("Dispositivo Iniciado");       
                ret = fplib.GetDeviceInfo(deviceInfo);
                imgRegistration1 = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);

            } else {
                vistaCRUD.lblestado.setText("OpenDevice() Error [" + ret + "]");
            }
        } else {
            vistaCRUD.lblestado.setText("JSGFPLib Initialization Failed");
        }
        
        
          desbloquear();
       // txtcedula.setText("");
        //txtnombre.setText("");
        //txtapellido.setText("");
       // txtdireccion.setText("");
       // calendario.setDate("");
       
       
              
          }

        if (e.getSource() == vistaCRUD.btneliminar) {
            int filInicio = vistaCRUD.tablapersonas.getSelectedRow();
            int numfilas = vistaCRUD.tablapersonas.getSelectedRowCount();
            ArrayList<String> listaDni = new ArrayList<>();
            String dni;
            if (filInicio >= 0) {
                for (int i = 0; i < numfilas; i++) {
                    dni = String.valueOf(vistaCRUD.tablapersonas.getValueAt(i + filInicio, 0));
                    listaDni.add(i, dni);
                }

                for (int j = 0; j < numfilas; j++) {
                    int rpta = JOptionPane.showConfirmDialog(null, "Desea eliminar registro con dni: " + listaDni.get(j) + "? ");
                    if (rpta == 0) {
                        modeloCRUD.deletePersona(listaDni.get(j));
                    }
                }
                LLenarTabla(vistaCRUD.tablapersonas);
            } else {
                JOptionPane.showMessageDialog(null, "Elija al menos un registro para eliminar.");
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vistaCRUD.txtcedula) {
            char c = e.getKeyChar();
            if (c < '0' || c > '9') {
                e.consume();
            }
        }

        if (e.getSource() == vistaCRUD.txtapellido || e.getSource() == vistaCRUD.txtnombre) {
            char c = e.getKeyChar();
            if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != (char) KeyEvent.VK_SPACE)) {
                e.consume();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vistaCRUD.txtbuscar) {
System.out.println("presion");
            String apellidos = vistaCRUD.txtbuscar.getText();

            DefaultTableModel modeloT = new DefaultTableModel();
            vistaCRUD.tablapersonas.setModel(modeloT);

            modeloT.addColumn("CEDULA");
            modeloT.addColumn("NOMBRES");
            modeloT.addColumn("APELLIDOS");
            modeloT.addColumn("CARGO");
            modeloT.addColumn("EMPRESA");
            modeloT.addColumn("ESTADO");

            Object[] columna = new Object[6];

            int numRegistros = modeloCRUD.buscaPersona(apellidos).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = modeloCRUD.buscaPersona(apellidos).get(i).getIdCliente();
                columna[1] = modeloCRUD.buscaPersona(apellidos).get(i).getNombre();
                columna[2] = modeloCRUD.buscaPersona(apellidos).get(i).getApellido();
                columna[3] = modeloCRUD.buscaPersona(apellidos).get(i).getCargo();
                columna[4] = modeloCRUD.buscaPersona(apellidos).get(i).getEmpresa();
                columna[5] = modeloCRUD.buscaPersona(apellidos).get(i).getEstado();
                modeloT.addRow(columna);
            }
        }

    }

}
