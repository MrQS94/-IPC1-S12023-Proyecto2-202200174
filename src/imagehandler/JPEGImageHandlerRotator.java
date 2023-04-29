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
public class JPEGImageHandlerRotator extends ImageHandler {

    private File nombre;
    private String converted;
    private FileInputStream input;
    private FileOutputStream output;

    public JPEGImageHandlerRotator(String filename, File file) {
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
        String url = "temporal/temporal-" + converted + ".bmp";
        output = new FileOutputStream(url);
        BufferedImage imagen = ImageIO.read(input);
        ImageIO.write(imagen, "bmp", output);
        output.close();
        input.close();

        FileInputStream inputH = new FileInputStream(url);
        FileInputStream inputV = new FileInputStream(url);
        
        BufferedImage imageH = ImageIO.read(inputH);
        BufferedImage imageV = ImageIO.read(inputV);

        int[][] imgHoriz = new int[imageH.getWidth()][imageH.getHeight()];
        int[][] imgVert = new int[imageV.getWidth()][imageV.getHeight()];

        for (int x = 0; x < imageH.getWidth(); x++) {
            for (int y = 0; y < imageH.getHeight(); y++) {
                int pixl = imageH.getRGB(x, y);

                imgHoriz[x][(imageH.getHeight() - 1) - y] = pixl;
                imgVert[(imageV.getWidth() - 1) - x][y] = pixl;
            }
        }

        //Horizontal
        FileOutputStream fotoH = new FileOutputStream("temporal/Hrotation-" + converted + ".jpeg");
        for (int x = 0; x < imageH.getWidth(); x++) {
            for (int y = 0; y < imageH.getHeight(); y++) {
                imageH.setRGB(x, y, imgHoriz[x][y]);
            }
        }
        ImageIO.write(imageH, "jpeg", fotoH);
        fotoH.close();
        inputH.close();
        
        //Vertical
        FileOutputStream fotoV = new FileOutputStream("temporal/Vrotation-" + converted + ".jpeg");
        for (int x = 0; x < imageV.getWidth(); x++) {
            for (int y = 0; y < imageV.getHeight(); y++) {
                imageH.setRGB(x, y, imgVert[x][y]);
            }
        }
        ImageIO.write(imageH, "jpeg", fotoV);
        fotoV.close();
        inputV.close();
        
        File fileH = new File("temporal/Hrotation-" + converted + ".jpeg");
        File fileV = new File("temporal/Vrotation-" + converted + ".jpeg");
        System.out.println(fileH.getName());
        System.out.println(fileV.getName());
        
        // Eliminado BMP
        File file = new File(url);
        file.delete();
        JOptionPane.showMessageDialog(null, "Imagen rotada con exito!", "ROTATION!", JOptionPane.INFORMATION_MESSAGE);
    }

}
