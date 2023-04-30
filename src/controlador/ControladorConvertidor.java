/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import handlers.JPEGHandler;
import imagehandler.JPEGImageCopy;
import imagehandler.JPEGImageHandlerBN;
import imagehandler.JPEGImageHandlerColors;
import imagehandler.JPEGImageHandlerRotator;
import imagehandler.JPEGtoBMPImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import listas.ListaDoble;
import listas.ListaSimple;
import vista.Convertidor;

/**
 *
 * @author queza
 */
public class ControladorConvertidor implements ItemListener, ActionListener {

    private Convertidor convert;
    private ListaSimple listSimple;
    private ListaDoble listDoble;
    private DefaultListModel listModel = new DefaultListModel();

    public ControladorConvertidor(Convertidor convert, ListaSimple listSimple, ListaDoble listDoble) {
        this.convert = convert;
        this.listSimple = listSimple;
        this.listDoble = listDoble;
        this.convert.jComboBoxUsuario.addItemListener(this);
        this.convert.jButtonAgregar.addActionListener(this);
        this.convert.jButtonEjecutar.addActionListener(this);
        CargarUsuarios();
    }

    private void CargarUsuarios() {
        for (int i = 0; i < listSimple.getSize(); i++) {
            this.convert.jComboBoxUsuario.addItem(String.valueOf(listSimple.get(i)));
        }
    }

    private void CargarCategorias() {
        if (convert.jComboBoxUsuario.getSelectedItem() != null) {
            convert.jComboBoxCategoria.removeAllItems();
            String user = convert.jComboBoxUsuario.getSelectedItem().toString();

            String[] userCat = new String[2];
            userCat[0] = user;

            String[] categorias = (String[]) listDoble.find(userCat);
            for (String categoria : categorias) {
                convert.jComboBoxCategoria.addItem(categoria);
            }
        }
    }

    private void EjecutarUnaImagen() throws Exception {
        String imgUrl = convert.jList.getSelectedValue();
        File file = new File(imgUrl);

        if (convert.jCheckBoxJPEGTOBMP.isSelected()) {
            JPEGtoBMPImage jpegToBMP = new JPEGtoBMPImage("", file);
            JPEGHandler.runHandler(jpegToBMP, convert.jTextArea);
        }

        if (convert.jCheckBoxCopia.isSelected()) {
            JPEGImageCopy copy = new JPEGImageCopy("", file);
            JPEGHandler.runHandler(copy, convert.jTextArea);
        }

        if (convert.jCheckBoxRojoVerde.isSelected()) {
            JPEGImageHandlerColors handlerColor = new JPEGImageHandlerColors("", file);
            JPEGHandler.runHandler(handlerColor, convert.jTextArea);
        }

        if (convert.jCheckBoxModificar.isSelected()) {
            JPEGImageHandlerRotator rotator = new JPEGImageHandlerRotator("", file);
            JPEGHandler.runHandler(rotator, convert.jTextArea);
        }

        if (convert.jCheckBoxBN.isSelected()) {
            JPEGImageHandlerBN bn = new JPEGImageHandlerBN("", file);
            JPEGHandler.runHandler(bn, convert.jTextArea);
        }
        
        System.out.flush();
    }

    private void CargarUserCatImg() {
        if (convert.jComboBoxUsuario.getSelectedItem() != null
                && convert.jComboBoxCategoria.getSelectedItem() != null) {

            String user = convert.jComboBoxUsuario.getSelectedItem().toString();
            String cat = convert.jComboBoxCategoria.getSelectedItem().toString();

            String[] userCat = new String[2];
            userCat[0] = user;
            userCat[1] = cat;

            String[] categorias = (String[]) listDoble.find(userCat);
            for (String categoria : categorias) {
                listModel.addElement(categoria);
            }
            convert.jList.setModel(listModel);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == convert.jComboBoxUsuario) {
            CargarCategorias();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convert.jButtonAgregar) {
            CargarUserCatImg();
        } else if (e.getSource() == convert.jButtonEjecutar) {
            try {
                EjecutarUnaImagen();
            } catch (Exception ex) {
                Logger.getLogger(ControladorConvertidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
