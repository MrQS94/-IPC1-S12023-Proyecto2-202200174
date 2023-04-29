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
public class JPEGImageHandlerBN extends ImageHandler {

    private File nombre;
    private String converted;
    private FileInputStream input;
    private FileOutputStream output;

    public JPEGImageHandlerBN(String filename, File file) {
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

        FileInputStream inputBN = new FileInputStream(url);
        BufferedImage imageBN = ImageIO.read(inputBN);

        Color[][] BNColor = new Color[imageBN.getWidth()][imageBN.getHeight()];
        for (int x = 0; x < imageBN.getWidth(); x++) {
            for (int y = 0; y < imageBN.getHeight(); y++) {
                int pixs = imageBN.getRGB(x, y);
                String pixelBN = Integer.toBinaryString(pixs);

                String redBN = pixelBN.substring(8, 16);
                String greenBN = pixelBN.substring(16, 24);
                String blueBN = pixelBN.substring(24, 32);

                int red = (int) (Integer.parseInt(redBN, 2) * 0.299);
                int green = (int) (Integer.parseInt(greenBN, 2) * 0.599);
                int blue = (int) (Integer.parseInt(blueBN, 2) * 0.111);

                int sum = red + green + blue;
                if (sum > 255) {
                    sum = 255;
                }
                Color BN = new Color(sum, sum, sum);
                BNColor[x][y] = BN;
            }
        }

        FileOutputStream FotoBN = new FileOutputStream("temporal/BN-" + converted + ".jpeg");
        for (int x = 0; x < imageBN.getWidth(); x++) {
            for (int y = 0; y < imageBN.getHeight(); y++) {
                imageBN.setRGB(x, y, BNColor[x][y].getRGB());
            }
        }
        ImageIO.write(imageBN, "jpeg", FotoBN);
        File fileList = new File("temporal/BN-" + converted + ".jpeg");
        System.out.println(fileList.getName());
        FotoBN.close();
        inputBN.close();

        File file = new File(url);
        file.delete();
        JOptionPane.showMessageDialog(null, "Imagen convertida a Blanco y Negro con exito!", "Blanco y Negro!", JOptionPane.INFORMATION_MESSAGE);
    }

}
