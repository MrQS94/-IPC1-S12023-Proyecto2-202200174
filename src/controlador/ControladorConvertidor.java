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
import javax.swing.JOptionPane;
import javax.swing.Timer;
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
    int x = 0;
    Timer timer;

    private void EjecutarUnaImagen() throws Exception {

        if (convert.jList.getSelectedValue() != null) {
            String imgUrl = convert.jList.getSelectedValue();
            File file = new File(imgUrl);

            String texto = "";
            int tiempo = 0;

            if (convert.jCheckBoxJPEGTOBMP.isSelected()) {
                JPEGtoBMPImage jpegToBMP = new JPEGtoBMPImage(file.getName(), file);
                texto += convert.jTextArea.getText();
                texto += JPEGHandler.runHandler(jpegToBMP, texto, convert.jTextArea);
                tiempo++;
            }

            if (convert.jCheckBoxCopia.isSelected()) {
                JPEGImageCopy copy = new JPEGImageCopy(file.getName(), file);
                texto += convert.jTextArea.getText();
                texto += JPEGHandler.runHandler(copy, texto, convert.jTextArea);
                tiempo++;
            }

            if (convert.jCheckBoxRojoVerde.isSelected()) {
                JPEGImageHandlerColors handlerColor = new JPEGImageHandlerColors(file.getName(), file);
                texto += convert.jTextArea.getText();
                texto += JPEGHandler.runHandler(handlerColor, texto, convert.jTextArea);
                tiempo++;
            }

            if (convert.jCheckBoxModificar.isSelected()) {
                JPEGImageHandlerRotator rotator = new JPEGImageHandlerRotator(file.getName(), file);
                texto += convert.jTextArea.getText();
                texto += JPEGHandler.runHandler(rotator, texto, convert.jTextArea);
                tiempo++;
            }

            if (convert.jCheckBoxBN.isSelected()) {
                JPEGImageHandlerBN bn = new JPEGImageHandlerBN(file.getName(), file);
                texto += convert.jTextArea.getText();
                texto += JPEGHandler.runHandler(bn, texto, convert.jTextArea);
                tiempo++;
            }

            ActionListener ac = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    x++;
                    convert.jProgressBar.setValue(x);
                    if (convert.jProgressBar.getValue() == 100) {
                        timer.stop();
                        JOptionPane.showMessageDialog(null, "Ha finalizado. Puede ver sus imagenes en la carpeta temporal.", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            };
            timer = new Timer(75 / tiempo, ac);
            timer.start();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione o agregue una imagen.", "ADVERTENCIA!", JOptionPane.WARNING_MESSAGE);
        }

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
