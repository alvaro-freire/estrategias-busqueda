package es.udc.sistemasinteligentes.g4_42;

import es.udc.sistemasinteligentes.g4_42.ProblemaCuadradoMagico.EstadoCuadrado;

public class HeuristicaCuadradoMagico extends Heuristica {

    public boolean contains(int[][] cuadrado, int length, int num) {
        for (int[] fila : cuadrado) {
            for (int casilla : fila) {
                if (casilla == num) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public float evalua(Estado e) {
        EstadoCuadrado ec = (EstadoCuadrado) e;
        int[][] cuadrado = ec.getCuadrado();
        int n = ec.getN();
        int numMagico = n * (n * n + 1) / 2;
        float h = 1;
        int sumaDiag1 = 0;
        int sumaDiag2 = 0;

        for (int i = 0; i < n; i++) {
            int casillasVacias = 0;
            for (int j = 0; j < n; j++) {
                if (cuadrado[i][j] == 0) {
                    casillasVacias++;
                }

                if (cuadrado[j][i] == 0) {
                    casillasVacias++;
                }
            }
            int sumaFila = 0;
            int sumaCol = 0;
            sumaDiag1 += cuadrado[i][i];
            sumaDiag2 += cuadrado[i][n - i - 1];
            for (int j = 0; j < n; j++) {
                sumaFila += cuadrado[i][j];
                sumaCol += cuadrado[j][i];
            }

            if (sumaFila > numMagico || sumaCol > numMagico) {
                return 0;
            }
            if (casillasVacias > 0 && (sumaFila >= numMagico) || sumaCol >= numMagico) {
                return 0;
            }
            if (casillasVacias == 1 && (contains(cuadrado, n, numMagico - sumaFila)
                    || (contains(cuadrado, n, numMagico - sumaCol)))) {
                return 0;
            }
            if (casillasVacias == 0 && (sumaFila != numMagico)) {
                return 0;
            }
        }

        return h;
    }
}
