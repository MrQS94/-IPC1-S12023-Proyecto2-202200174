/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imagehandler;

import handlers.ImageHandler;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author queza
 */
public class JPEGtoBMPImage extends ImageHandler {

    private File nombre;
    private String converted;
    private FileInputStream entrada;

    public JPEGtoBMPImage(String filename, File file) {
        super(filename);
        this.nombre = file;
    }

    @Override
    public void readFile() throws Exception {
        int cantidad = nombre.getName().lastIndexOf(".");
        converted = nombre.getName().substring(0, cantidad);
        entrada = new FileInputStream(nombre);
    }

    @Override
    public void generateFiles() throws Exception {
        String filename = nombre.getName();
        String extension = "";
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex >= 0 && dotIndex < filename.length() - 1) {
            extension = filename.substring(dotIndex + 1);
        }

        String ext = "";
        if (extension.equals("jpeg") || extension.equals("jpg")) {
            ext = "bmp";
        } else if (extension.equals("bmp")) {
            ext = "jpeg";
        }

        String url = "temporal/converted-" + converted + "." + ext;
        FileOutputStream salida = new FileOutputStream(url);
        BufferedImage imagen = ImageIO.read(entrada);
        ImageIO.write(imagen, ext, salida);
        salida.close();
        entrada.close();
        
        File file = new File(url);
        System.out.println(file.getName());
        
        JOptionPane.showMessageDialog(null, "La imágen ha sido convertida con exito!", "CONVERTIDO!", JOptionPane.INFORMATION_MESSAGE);
        
    }

}
