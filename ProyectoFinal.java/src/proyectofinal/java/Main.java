
package proyectofinal.java;


public class Main {
    public static void main(String[] args) {
        String rutaEntrada = "input/CultivoV2.pgm";
        int tamVentana = 7;
        double umbral = 1.5;

        int[][] imagen = PGMUtils.cargarPGM(rutaEntrada);

        int[][] bordes = DetectorBordes.detectarBordesIntegral(imagen, tamVentana, umbral);

        PGMUtils.guardarPGM(bordes, "output/bordes_lena.pgm");

        System.out.println("Procesado. Archivo generado en output/bordes_lena.pgm");
    }
}


