package proyectofinal.java;

public class DetectorBordes {

    // ---------------------------------------------------
    // 1. VERSIÓN NAIVE (O(N^2 * k^2))
    // ---------------------------------------------------
    public static int[][] detectarBordesNaive(int[][] imagen, int tamVentana, double umbral) {
        int alto = imagen.length;           // Número de filas
        int ancho = imagen[0].length;       // Número de columnas
        int[][] bordes = new int[alto][ancho];
        int radio = tamVentana / 2;

        for (int i = radio; i < alto - radio; i++) {
            for (int j = radio; j < ancho - radio; j++) {

                double suma = 0;
                double sumaCuadrados = 0;
                int contador = 0;

                for (int x = i - radio; x <= i + radio; x++) {
                    for (int y = j - radio; y <= j + radio; y++) {
                        suma += imagen[x][y];
                        sumaCuadrados += imagen[x][y] * imagen[x][y];
                        contador++;
                    }
                }

                double promedio = suma / contador;
                double varianza = (sumaCuadrados / contador) - (promedio * promedio);
                double desviacion = Math.sqrt(Math.max(varianza, 0));

                bordes[i][j] = (Math.abs(imagen[i][j] - promedio) > umbral * desviacion) ? 1 : 0;
            }
        }
        return bordes;
    }

    // ---------------------------------------------------
    // 2. VERSIÓN OPTIMIZADA (O(N^2))
    // ---------------------------------------------------
    public static int[][] detectarBordesIntegral(int[][] imagen, int tamVentana, double umbral) {
        int alto = imagen.length;           // Número de filas
        int ancho = imagen[0].length;       // Número de columnas
        int[][] bordes = new int[alto][ancho];
        int radio = tamVentana / 2;

        long[][] sumaIntegral = new long[alto][ancho];
        long[][] sumaIntegralCuadrados = new long[alto][ancho];

        // Construir integrales
        for (int i = 0; i < alto; i++) {
            long sumaFila = 0;
            long sumaFilaCuadrados = 0;
            for (int j = 0; j < ancho; j++) {
                sumaFila += imagen[i][j];
                sumaFilaCuadrados += (long)imagen[i][j] * imagen[i][j];

                if (i == 0) {
                    sumaIntegral[i][j] = sumaFila;
                    sumaIntegralCuadrados[i][j] = sumaFilaCuadrados;
                } else {
                    sumaIntegral[i][j] = sumaIntegral[i - 1][j] + sumaFila;
                    sumaIntegralCuadrados[i][j] = sumaIntegralCuadrados[i - 1][j] + sumaFilaCuadrados;
                }
            }
        }

        // Aplicar ventana usando integral
        for (int i = radio; i < alto - radio; i++) {
            for (int j = radio; j < ancho - radio; j++) {

                int x1 = i - radio;
                int y1 = j - radio;
                int x2 = i + radio;
                int y2 = j + radio;

                long suma = sumaIntegral[x2][y2]
                        - (y1 > 0 ? sumaIntegral[x2][y1 - 1] : 0)
                        - (x1 > 0 ? sumaIntegral[x1 - 1][y2] : 0)
                        + (x1 > 0 && y1 > 0 ? sumaIntegral[x1 - 1][y1 - 1] : 0);

                long sumaCuadrados = sumaIntegralCuadrados[x2][y2]
                        - (y1 > 0 ? sumaIntegralCuadrados[x2][y1 - 1] : 0)
                        - (x1 > 0 ? sumaIntegralCuadrados[x1 - 1][y2] : 0)
                        + (x1 > 0 && y1 > 0 ? sumaIntegralCuadrados[x1 - 1][y1 - 1] : 0);

                int cantidadPixeles = tamVentana * tamVentana;

                double promedio = (double) suma / cantidadPixeles;
                double varianza = (double) sumaCuadrados / cantidadPixeles - (promedio * promedio);
                double desviacion = Math.sqrt(Math.max(varianza, 0));

                bordes[i][j] = (Math.abs(imagen[i][j] - promedio) > umbral * desviacion) ? 1 : 0;
            }
        }

        return bordes;
    }
}

