/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imagehandler;

import handlers.ImageHandler;
import java.awt.Color;
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
public class JPEGImageHandlerColors extends ImageHandler {

    private File nombre;
    private String converted;
    private FileInputStream input;
    private FileOutputStream output;

    public JPEGImageHandlerColors(String filename, File file) {
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

        FileInputStream inputRed = new FileInputStream(url);
        FileInputStream inputGreen = new FileInputStream(url);
        FileInputStream inputBlue = new FileInputStream(url);
        FileInputStream inputSepia = new FileInputStream(url);

        BufferedImage imagenRed = ImageIO.read(inputRed);
        BufferedImage imagenGreen = ImageIO.read(inputGreen);
        BufferedImage imagenBlue = ImageIO.read(inputBlue);
        BufferedImage imagenSepia = ImageIO.read(inputSepia);

        Color[][] redM = new Color[imagenRed.getWidth()][imagenRed.getHeight()];
        Color[][] greenM = new Color[imagenGreen.getWidth()][imagenGreen.getHeight()];
        Color[][] blueM = new Color[imagenBlue.getWidth()][imagenBlue.getHeight()];
        Color[][] sepiaM = new Color[imagenSepia.getWidth()][imagenSepia.getHeight()];

        for (int x = 0; x < imagenRed.getWidth(); x++) {
            for (int y = 0; y < imagenRed.getHeight(); y++) {
                int pixels = imagenRed.getRGB(x, y);
                String pixelB = Integer.toBinaryString(pixels);

                String redB = pixelB.substring(8, 16);
                String greenB = pixelB.substring(16, 24);
                String blueB = pixelB.substring(24, 32);

                int red = Integer.parseInt(redB, 2);
                int green = Integer.parseInt(greenB, 2);
                int blue = Integer.parseInt(blueB, 2);

                // ------------- Sepia -------------
                int redS = (int) ((0.393 * red) + (0.769 * green) + (0.189 * blue));
                int greenS = (int) ((0.349 * red) + (0.686 * green) + (0.168 * blue));
                int blueS = (int) ((0.272 * red) + (0.534 * green) + (0.131 * blue));

                if (redS > 255) {
                    redS = 255;
                }

                if (greenS > 255) {
                    greenS = 255;
                }

                if (blueS > 255) {
                    blueS = 255;
                }

                Color rojo = new Color(red, 0, 0);
                Color verde = new Color(0, green, 0);
                Color azul = new Color(0, 0, blue);
                Color sepia = new Color(redS, greenS, blueS);

                redM[x][y] = rojo;
                greenM[x][y] = verde;
                blueM[x][y] = azul;
                sepiaM[x][y] = sepia;
            }
        }

        // Color Rojo
        FileOutputStream outputRed = new FileOutputStream("temporal/Red-" + converted + ".jpeg");
        for (int i = 0; i < imagenRed.getWidth(); i++) {
            for (int j = 0; j < imagenRed.getHeight(); j++) {
                imagenRed.setRGB(i, j, redM[i][j].getRGB());
            }
        }
        ImageIO.write(imagenRed, "jpeg", outputRed);
        outputRed.close();

        // Color Verde
        FileOutputStream outputGreen = new FileOutputStream("temporal/Green-" + converted + ".jpeg");
        for (int x = 0; x < imagenGreen.getWidth(); x++) {
            for (int y = 0; y < imagenGreen.getHeight(); y++) {
                imagenGreen.setRGB(x, y, greenM[x][y].getRGB());
            }
        }
        ImageIO.write(imagenGreen, "jpeg", outputGreen);
        outputGreen.close();

        // Color Azul
        FileOutputStream outputBlue = new FileOutputStream("temporal/Blue-" + converted + ".jpeg");
        for (int i = 0; i < imagenBlue.getWidth(); i++) {
            for (int j = 0; j < imagenBlue.getHeight(); j++) {
                imagenBlue.setRGB(i, j, blueM[i][j].getRGB());
            }
        }
        ImageIO.write(imagenBlue, "jpeg", outputBlue);
        outputBlue.close();

        // Color Azul
        FileOutputStream outputSepia = new FileOutputStream("temporal/Sepia-" + converted + ".jpeg");
        for (int i = 0; i < imagenSepia.getWidth(); i++) {
            for (int j = 0; j < imagenSepia.getHeight(); j++) {
                imagenSepia.setRGB(i, j, sepiaM[i][j].getRGB());
            }
        }
        ImageIO.write(imagenSepia, "jpeg", outputSepia);
        outputSepia.close();

        inputRed.close();
        inputGreen.close();
        inputBlue.close();
        inputSepia.close();

        File fileRed = new File("temporal/Red-" + converted + ".jpeg");
        File fileGreen = new File("temporal/Green-" + converted + ".jpeg");
        File fileBlue = new File("temporal/Blue-" + converted + ".jpeg");
        File fileSepia = new File("temporal/Sepia-" + converted + ".jpeg");
        System.out.println(fileRed.getName());
        System.out.println(fileGreen.getName());
        System.out.println(fileBlue.getName());
        System.out.println(fileSepia.getName());

        File file = new File(url);
        file.delete();

        JOptionPane.showMessageDialog(null, "La imágen ha sido convertida con exito!", "CONVERTIDO!", JOptionPane.INFORMATION_MESSAGE);

    }

}
