package es.udc.sistemasinteligentes.gA_42.ejemplo;

import es.udc.sistemasinteligentes.gA_42.Accion;
import es.udc.sistemasinteligentes.gA_42.Estado;
import es.udc.sistemasinteligentes.gA_42.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.gA_42.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    public Nodo[] reconstruyeSol(Nodo n) {
        Nodo actual;
        List<Nodo> revSol = new ArrayList<>();

        actual = n;
        while (actual != null) {
            revSol.add(actual);
            actual = actual.getPadre();
        }

        Collections.reverse(revSol);

        return revSol.toArray(new Nodo[0]);
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        List<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        Nodo nodo = new Nodo(estadoActual, null, null);
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)) {
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;

            for (Accion acc : accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + "," + acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    estadoActual = sc;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    nodo = new Nodo(estadoActual, nodo, acc);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                } else {
                    System.out.println((i++) + " - " + sc + " ya explorado");
                }
            }
            if (!modificado) {
                throw new Exception("No se ha podido encontrar una solución");
            }
        }
        System.out.println((i) + " - FIN - " + estadoActual);

        return reconstruyeSol(nodo);
    }
}
