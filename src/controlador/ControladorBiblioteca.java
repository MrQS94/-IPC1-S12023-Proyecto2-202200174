/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import listas.ListaDoble;
import listas.ListaSimple;
import modelo.ModeloUsuario;
import vista.Biblioteca;

/**
 *
 * @author queza
 */
public class ControladorBiblioteca implements ActionListener, ItemListener, ListSelectionListener {

    private Biblioteca menu;
    private ListaSimple listSimple;
    private ListaDoble listDoble;
    private String user;
    private DefaultListModel listModel = new DefaultListModel();

    public ControladorBiblioteca(Biblioteca menu, ListaSimple listSimple, ListaDoble listDoble) {
        this.menu = menu;
        this.listSimple = listSimple;
        this.listDoble = listDoble;
        IngresarUsuario();
        this.menu.jLabelUsuario.setText(user);
        this.menu.jButtonAgregarImagen.addActionListener(this);
        this.menu.jButtonEliminarImagen.addActionListener(this);
        this.menu.jComboBoxImagen.addItemListener(this);
        this.menu.jButtonAgregarCategoria.addActionListener(this);
        this.menu.jList.addListSelectionListener(this);
        this.menu.jButtonEliminarCategoria.addActionListener(this);
        this.menu.jButtonNext.addActionListener(this);
        this.menu.jButtonPrev.addActionListener(this);
        CargarCategorias();
    }

    private void AgregarImagen() throws IOException {
        if (menu.jList.getSelectedValue() != null) {
            JFileChooser jfc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JPG & JPEG", "jpg", "jpeg");
            jfc.setFileFilter(filter);
            int opcion = jfc.showOpenDialog(null);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                ImageIcon img = new ImageIcon(file.getAbsolutePath());
                Icon icon = new ImageIcon(img.getImage().getScaledInstance(menu.jLabelImg.getWidth(), menu.jLabelImg.getHeight(), 1)); // width height scalate
                menu.jLabelImg.setIcon(icon);
                String cat = menu.jList.getSelectedValue();
                listDoble.add(new ModeloUsuario(user, file.getAbsolutePath(), cat));
                menu.jComboBoxImagen.addItem(file.getAbsolutePath());
                menu.jComboBoxImagen.setSelectedItem(file.getAbsolutePath());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione o agregue una categoria.", "ADVERTENCIA!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void AgregarCategorias() {
        String cat;
        do {
            cat = JOptionPane.showInputDialog(null, "Ingrese el nombre de la categoria: ");
        } while (cat.isBlank());

        listModel.addElement(cat);
        menu.jList.setModel(listModel);

    }

    private void SiguienteImagen() {
        int limite = menu.jComboBoxImagen.getItemCount();
        int index = menu.jComboBoxImagen.getSelectedIndex() + 1;
        if (index < limite) {
            menu.jComboBoxImagen.setSelectedIndex(index);
        } else if (index == limite) {
            menu.jComboBoxImagen.setSelectedIndex(0);
        }
    }

    private void AnteriorImagen() {
        int limite = menu.jComboBoxImagen.getItemCount();
        int index = menu.jComboBoxImagen.getSelectedIndex() - 1;
        if (index < limite && index >= 0) {
            menu.jComboBoxImagen.setSelectedIndex(index);
        }
    }

    private void CargarCategorias() {
        String[] userCat = new String[2];
        userCat[0] = user;

        String[] object = (String[]) listDoble.find(userCat);
        for (String object1 : object) {
            if (object1 != null) {
                listModel.addElement(object1);
            }
        }

        if (listModel.isEmpty()) {
            listModel.addElement("General");
        }
        menu.jList.setModel(listModel);
    }

    private void CargarImagenesCategorias() {
        menu.jComboBoxImagen.removeAllItems();
        menu.jComboBoxImagen.addItem("Seleccionar...");

        String[] userCat = new String[2];
        userCat[0] = user;
        userCat[1] = menu.jList.getSelectedValue();

        String[] file = (String[]) listDoble.find(userCat);
        for (String file1 : file) {
            if (file1 != null) {

                menu.jComboBoxImagen.addItem(file1);
            }
        }
    }

    private void SeleccionarImagenComboBox() {
        String url = null;

        if (menu.jComboBoxImagen.getSelectedItem() != null) {
            url = menu.jComboBoxImagen.getSelectedItem().toString();
        }

        Icon icon = null;
        if (menu.jComboBoxImagen.getSelectedIndex() != 0) {
            ImageIcon img = new ImageIcon(url);
            icon = new ImageIcon(img.getImage().getScaledInstance(menu.jLabelImg.getWidth(), menu.jLabelImg.getHeight(), 1)); // width height scalate
            menu.jLabelImg.setIcon(icon);
        } else {
            menu.jLabelImg.setIcon(icon);
        }
    }

    private void EliminarImagenes() {
        String[] eliminar = new String[2];
        eliminar[0] = user;
        eliminar[1] = menu.jComboBoxImagen.getSelectedItem().toString();

        if (menu.jComboBoxImagen.getSelectedIndex() != 0) {
            listDoble.delete(eliminar);
            JOptionPane.showMessageDialog(null, "La imágen con url\n\"" + eliminar[1] + "\"\nHa sido eliminada exitosamente.", "INFORMACIÓN!", JOptionPane.INFORMATION_MESSAGE);
            menu.jComboBoxImagen.removeItem(eliminar[1]);
            menu.jComboBoxImagen.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione o agregue una imagen.", "ADEVERTENCIA!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void EliminarCategorias() {
        String[] eliminar = new String[2];

        if (menu.jList.getSelectedValue() != null) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar esta categoria?\nSi lo elimina no podrá volver a ver sus imágenes guardadas.", "ADEVERTENCIA!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opcion == 0) {
                eliminar[0] = user;
                eliminar[1] = menu.jList.getSelectedValue();
                int index = menu.jList.getSelectedIndex();
                listDoble.delete(eliminar);
                listModel.remove(index);
                menu.jList.setModel(listModel);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una categoria para eliminar.", "ADEVERTENCIA!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void IngresarUsuario() {
        String user = "";
        do {
            user = JOptionPane.showInputDialog(null, "Ingrese el usuario que desea ingresar: ");
        } while (user.isBlank());

        if (listSimple.getSize() == 0) {
            listSimple.add(user);
        } else {
            if (!isUserExist(user)) {
                listSimple.add(user);
            }
        }
        this.user = user;
    }

    private boolean isUserExist(String nombre) {
        for (int i = 0; i < listSimple.getSize(); i++) {
            if (listSimple.get(i).equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == menu.jList) {
            CargarImagenesCategorias();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == menu.jComboBoxImagen) {
            SeleccionarImagenComboBox();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.jButtonAgregarImagen) {
            try {
                AgregarImagen();
            } catch (IOException ex) {
                Logger.getLogger(ControladorBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == menu.jButtonEliminarImagen) {
            EliminarImagenes();
        } else if (e.getSource() == menu.jButtonAgregarCategoria) {
            AgregarCategorias();
        } else if (e.getSource() == menu.jButtonEliminarCategoria) {
            EliminarCategorias();
        } else if (e.getSource() == menu.jButtonNext) {
            SiguienteImagen();
        } else if (e.getSource() == menu.jButtonPrev) {
            AnteriorImagen();
        }
    }

}
