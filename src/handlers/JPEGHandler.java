package handlers;

import javax.swing.JTextArea;

/**
 * @author Auxiliares
 * @version 1.0
 *
 */
public class JPEGHandler {

    public static void runHandler(ImageHandler imgh) throws Exception {
        /**
         * NO CAMBIE ESTE CODIGO *
         */
        System.out.println("--------------------------------------");
        System.out.println(imgh.getClass().getTypeName().toUpperCase() + ": ");
        System.out.println("\nLeyendo imagen : " + imgh.getFileName());
        imgh.readFile();
        System.out.println("Proceso de lectura de imagen terminado!");
        System.out.println("\nGenerando imagenes : ");
        imgh.generateFiles();
        System.out.println("Proceso de generacion de archivos terminado!");
        System.out.println("\n--------------------------------------");
        /**
         * --------------------- *
         */
    }

    public static String runHandler(ImageHandler imgh, String texto, JTextArea jta) throws Exception {
        texto += jta.getText();
        texto += "--------------------------------------";
        texto += "\n" + imgh.getClass().getTypeName().toUpperCase() + ": ";
        texto += "\nLeyendo imagen : " + imgh.getFileName();
        imgh.readFile();
        texto += "\nProceso de lectura de imagen terminado!";
        imgh.generateFiles();
        texto += "\nAplicando filtro: Copia JPEG";
        texto += "\nProceso de generacion de archivos terminado!";
        texto += "\n--------------------------------------";
        jta.setText(texto);
        return texto;
    }
}
