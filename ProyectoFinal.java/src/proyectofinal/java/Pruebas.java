package proyectofinal.java;

public class Pruebas {

    public static void main(String[] args) {

        int[] tamaños = {128, 256, 512, 1024};
        int tamVentana = 7;
        double umbral = 1.5;

        for (int N : tamaños) {
            System.out.println("\n--- N = " + N + " ---");

            int[][] imagen = GeneradorImagen.generarImagenSimulada(N);

            long t1 = System.nanoTime();
            DetectorBordes.detectarBordesNaive(imagen, tamVentana, umbral);
            long t2 = System.nanoTime();

            long t3 = System.nanoTime();
            DetectorBordes.detectarBordesIntegral(imagen, tamVentana, umbral);
            long t4 = System.nanoTime();

            System.out.printf("Versión Naive:      %.4f s%n", (t2 - t1) / 1e9);
            System.out.printf("Versión Optimizada: %.4f s%n", (t4 - t3) / 1e9);
        }
    }
}

