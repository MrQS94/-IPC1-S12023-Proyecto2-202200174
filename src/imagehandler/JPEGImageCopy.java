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
public class JPEGImageCopy extends ImageHandler {

    private File nombre;
    private String converted;
    private FileInputStream input;
    private FileOutputStream output;

    public JPEGImageCopy(String filename, File file) {
        super(filename);
        nombre = file;
    }

    @Override
    public void readFile() throws Exception {
        int cantidad = nombre.getName().lastIndexOf(".");
        converted = nombre.getName().substring(0, cantidad);
        input = new FileInputStream(nombre);
    }

    @Override
    public void generateFiles() throws Exception {
        String url = "temporal/copy-" + converted + ".bmp";
        output = new FileOutputStream(url);
        BufferedImage imagen = ImageIO.read(input);
        ImageIO.write(imagen, "bmp", output);
        input.close();
        output.close();

        input = new FileInputStream(url);
        String url2 = "temporal/copy-" + converted + ".jpeg";
        output = new FileOutputStream(url2);
        BufferedImage imagen2 = ImageIO.read(input);
        ImageIO.write(imagen2, "jpeg", output);
        input.close();
        output.close();

        File fileBMP = new File(url);
        File fileJPEG = new File(url2);
        System.out.println(fileBMP.getName());
        System.out.println(fileJPEG.getName());
    }

}
