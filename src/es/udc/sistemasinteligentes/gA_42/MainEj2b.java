package es.udc.sistemasinteligentes.gA_42;

public class MainEj2b {
    public static void main(String[] args) throws Exception {
        int n = 3;
        int[][] cuadrado = {{4, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial = new ProblemaCuadradoMagico.EstadoCuadrado(cuadrado, n);
        ProblemaBusqueda cuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusquedaInformada busquedaInformada = new EstrategiaBusquedaAEstrella();

        System.out.println(busquedaInformada.soluciona(cuadradoMagico, new HeuristicaCuadradoMagico()));
    }
}