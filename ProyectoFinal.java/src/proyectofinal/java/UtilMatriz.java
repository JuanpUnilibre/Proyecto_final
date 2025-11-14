package proyectofinal.java;

public class UtilMatriz {

    public static void imprimir(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila)
                System.out.print(valor + " ");
            System.out.println();
        }
    }
}

