package es.udc.sistemasinteligentes.gA_42.ejemplo;

import es.udc.sistemasinteligentes.gA_42.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.gA_42.ProblemaBusqueda;

import java.util.Arrays;

public class Main1b {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaGrafo();
        System.out.println(Arrays.toString(buscador.soluciona(aspiradora)));
    }
}