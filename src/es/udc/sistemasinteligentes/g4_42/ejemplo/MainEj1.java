package es.udc.sistemasinteligentes.g4_42.ejemplo;

import es.udc.sistemasinteligentes.g4_42.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.g4_42.ProblemaBusqueda;

import java.util.Arrays;

public class MainEj1 {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaGrafo();
        // EstrategiaBusqueda buscador = new Estrategia4();

        System.out.println(Arrays.toString(buscador.soluciona(aspiradora)));
    }
}
