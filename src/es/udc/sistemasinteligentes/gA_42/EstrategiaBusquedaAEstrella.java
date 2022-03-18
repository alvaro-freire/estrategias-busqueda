package es.udc.sistemasinteligentes.gA_42;

import es.udc.sistemasinteligentes.gA_42.ejemplo.Nodo;

import java.util.*;

public class EstrategiaBusquedaEstrella implements EstrategiaBusquedaInformada {

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
    public Estado soluciona(ProblemaBusqueda p, Heuristica h) throws Exception {
        List<NodoHeuristica> explorados = new ArrayList<>();
        NodoHeuristica nodoActual = new NodoHeuristica(p.getEstadoInicial(), null, null,
                0, h.evalua(p.getEstadoInicial()));
        NodoHeuristica hijo;
        Queue<NodoHeuristica> frontera = new PriorityQueue<>();
        frontera.add(nodoActual);
        int i = 1;

        while (true) {
            if (frontera.isEmpty()) {
                throw new Exception("No se ha podido encontrar solución");
            }

            nodoActual = frontera.pop();

            if (p.esMeta(nodoActual.getEstado())) {
                break;
            }

            System.out.println((i++) + " - " + nodoActual.getEstado() + " no es meta");

            explorados.add(nodoActual);

            for (Accion acc : p.acciones(nodoActual.getEstado())) {
                hijo = new Nodo(p.result(nodoActual.getEstado(), acc), nodoActual, acc);

                if (!frontera.contains(hijo) && !explorados.contains(hijo)) {
                    frontera.add(hijo);
                }
            }
        }

        System.out.println((i) + " - FIN - " + nodoActual.getEstado());

        return reconstruyeSol(nodoActual);
    }
}
