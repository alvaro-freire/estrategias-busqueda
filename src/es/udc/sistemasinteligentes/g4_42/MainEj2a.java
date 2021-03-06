package es.udc.sistemasinteligentes.g4_42;

import java.util.Arrays;

public class MainEj2a {
    public static void main(String[] args) throws Exception {
        int n = 3;
        int[][] cuadrado = {{4, 9, 0}, {0, 0, 0}, {0, 0, 0}};

        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial = new ProblemaCuadradoMagico.EstadoCuadrado(cuadrado, n);
        ProblemaBusqueda cuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusquedaAnchura buscador = new EstrategiaBusquedaAnchura();
        // EstrategiaBusquedaProfundidad buscador = new EstrategiaBusquedaProfundidad();

        System.out.println(Arrays.toString(buscador.soluciona(cuadradoMagico)));
    }
}
