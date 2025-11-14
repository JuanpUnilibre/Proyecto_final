package proyectofinal.java;

import java.util.Random;

public class GeneradorImagen {

    public static int[][] generarImagenSimulada(int N) {
        int[][] imagen = new int[N][N];
        Random rnd = new Random(123);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                imagen[i][j] = 80 + rnd.nextInt(50);

        for (int i = N/4; i < 3*N/4; i++)
            for (int j = N/4; j < 3*N/4; j++)
                imagen[i][j] = 220;

        return imagen;
    }
}

