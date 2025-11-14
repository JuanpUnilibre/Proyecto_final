package proyectofinal.java;

import java.io.*;
import java.util.*;

public class PGMUtils {

    public static int[][] cargarPGM(String ruta) {
        try {
            Scanner sc = new Scanner(new File(ruta));

            String tipo = sc.next();
            if (!tipo.equals("P2"))
                throw new RuntimeException("Solo se admite PGM tipo P2.");

            int ancho = sc.nextInt();
            int alto = sc.nextInt();
            int maxVal = sc.nextInt();

            int[][] imagen = new int[alto][ancho];

            for (int i = 0; i < alto; i++)
                for (int j = 0; j < ancho; j++)
                    imagen[i][j] = sc.nextInt();

            sc.close();
            return imagen;

        } catch (Exception e) {
            throw new RuntimeException("Error leyendo PGM: " + e.getMessage());
        }
    }

    public static void guardarPGM(int[][] imagen, String ruta) {
        try {
            PrintWriter pw = new PrintWriter(new File(ruta));

            int alto = imagen.length;
            int ancho = imagen[0].length;

            pw.println("P2");
            pw.println(ancho + " " + alto);
            pw.println(255);

            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    pw.print((imagen[i][j] == 1 ? 255 : 0) + " ");
                }
                pw.println();
            }

            pw.close();

        } catch (Exception e) {
            throw new RuntimeException("Error guardando PGM: " + e.getMessage());
        }
    }
}
