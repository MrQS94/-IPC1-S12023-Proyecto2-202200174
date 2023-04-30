/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import handlers.JPEGHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import imagehandler.JPEGImageCopy;
import imagehandler.JPEGImageHandlerBN;
import imagehandler.JPEGImageHandlerColors;
import imagehandler.JPEGImageHandlerRotator;
import imagehandler.JPEGtoBMPImage;
import javax.swing.JOptionPane;
import vista.Editor;

/**
 *
 * @author queza
 */
public class ControladorEditor implements ActionListener {

    private Editor edit;
    private File file;

    public ControladorEditor(Editor edit) {
        this.edit = edit;
        this.edit.jButtonSeleccionar.addActionListener(this);
        this.edit.jRadioButtonJPEGaBMP.addActionListener(this);
        this.edit.jRadioButtonCopiaJPEG.addActionListener(this);
        this.edit.jRadioButtonRojoVerdeAzulSepia.addActionListener(this);
        this.edit.jRadioButtonModificar.addActionListener(this);
        this.edit.jRadioButtonBlancoNegro.addActionListener(this);
    }

    private void SeleccionarImagen() {
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo JPEG & BMP", "jpeg", "bmp");
        jfc.setFileFilter(filter);
        int opcion = jfc.showOpenDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            file = jfc.getSelectedFile();
            String url = file.getAbsolutePath();
            ImageIcon img = new ImageIcon(url);
            Icon icon = new ImageIcon(img.getImage().getScaledInstance(edit.jLabelImagen.getWidth(), edit.jLabelImagen.getHeight(), 1)); // width height scalate
            edit.jLabelImagen.setIcon(icon);
            edit.jLabelUrl.setText(url);
            HabilitarJComboBox();
        }
    }

    private void BlancoNegro() throws Exception {
        JPEGImageHandlerBN bn = new JPEGImageHandlerBN(file.getName(), file);
        JPEGHandler.runHandler(bn);
        JOptionPane.showMessageDialog(null, "Imagen convertida a Blanco y Negro con exito!", "Blanco y Negro!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void RotarImagen() throws Exception {
        JPEGImageHandlerRotator rotator = new JPEGImageHandlerRotator(file.getName(), file);
        JPEGHandler.runHandler(rotator);
         JOptionPane.showMessageDialog(null, "Imagen rotada con exito!", "ROTATION!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void RojoVerdeAzulSepia() throws Exception {
        JPEGImageHandlerColors handlerColor = new JPEGImageHandlerColors(file.getName(), file);
        JPEGHandler.runHandler(handlerColor);
        JOptionPane.showMessageDialog(null, "La imágen ha sido convertida con exito!", "CONVERTIDO!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void CopyJPEG() throws Exception {
        JPEGImageCopy copy = new JPEGImageCopy(file.getName(), file);
        JPEGHandler.runHandler(copy);
        JOptionPane.showMessageDialog(null, "La imágen ha sido copiada con exito!", "COPIADO!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void JPEG_BMP() throws Exception {
        JPEGtoBMPImage jpeg_BMP = new JPEGtoBMPImage(file.getName(), file);
        JPEGHandler.runHandler(jpeg_BMP);
        JOptionPane.showMessageDialog(null, "La imágen ha sido convertida con exito!", "CONVERTIDO!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void HabilitarJComboBox() {
        edit.jRadioButtonJPEGaBMP.setEnabled(true);
        edit.jRadioButtonCopiaJPEG.setEnabled(true);
        edit.jRadioButtonRojoVerdeAzulSepia.setEnabled(true);
        edit.jRadioButtonModificar.setEnabled(true);
        edit.jRadioButtonBlancoNegro.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == edit.jButtonSeleccionar) {
            SeleccionarImagen();
        } else if (e.getSource() == edit.jRadioButtonJPEGaBMP) {
            try {
                JPEG_BMP();
            } catch (Exception ex) {
                Logger.getLogger(ControladorEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == edit.jRadioButtonCopiaJPEG) {
            try {
                CopyJPEG();
            } catch (Exception ex) {
                Logger.getLogger(ControladorEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == edit.jRadioButtonRojoVerdeAzulSepia) {
            try {
                RojoVerdeAzulSepia();
            } catch (Exception ex) {
                Logger.getLogger(ControladorEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == edit.jRadioButtonModificar) {
            try {
                RotarImagen();
            } catch (Exception ex) {
                Logger.getLogger(ControladorEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == edit.jRadioButtonBlancoNegro) {
            try {
                BlancoNegro();
            } catch (Exception ex) {
                Logger.getLogger(ControladorEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
