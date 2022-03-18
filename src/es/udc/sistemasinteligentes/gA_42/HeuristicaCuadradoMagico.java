package es.udc.sistemasinteligentes.gA_42;

import es.udc.sistemasinteligentes.gA_42.ProblemaCuadradoMagico.EstadoCuadrado;

public class HeuristicaCuadradoMagico extends Heuristica{
    @Override
    public float evalua(Estado e) {
        EstadoCuadrado ec = (EstadoCuadrado) e;
        int[][] cuadrado = ec.getCuadrado();
        int result = ec.getN() * (ec.getN() * ec.getN() + 1) / 2;
        int sumaFila = 0;
        int sumaColumna = 0;

        for (int i=0; i < ec.getN(); i++) {
            for (int j=0; j < ec.getN(); j++) {
                sumaFila += cuadrado[i][j];
            }

        }

        return 0;
    }
}
