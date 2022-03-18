package es.udc.sistemasinteligentes.gA_42;

import es.udc.sistemasinteligentes.gA_42.ProblemaCuadradoMagico.EstadoCuadrado;

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

        for (int[] fila : cuadrado) {
            int casillasVacias = 0;
            for (int casilla : fila) {
                if (casilla == 0) {
                    casillasVacias++;
                }
            }
            int suma = 0;
            for (int num : fila) {
                suma += num;
            }

            if (suma > numMagico) {
                return 0;
            }
            if (casillasVacias > 0 && suma >= numMagico) {
                return 0;
            }
            if (casillasVacias == 1 && contains(cuadrado, n, numMagico - suma)) {
                return 0;
            }
            if (casillasVacias == 0 && suma != numMagico) {
                return 0;
            }

            h *= (double) (n - casillasVacias) / n;
        }

        return h;
    }
}
