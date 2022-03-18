package es.udc.sistemasinteligentes.gA_42;

import es.udc.sistemasinteligentes.gA_42.ejemplo.Nodo;

import java.util.*;

public class EstrategiaBusquedaAEstrella implements EstrategiaBusquedaInformada {

    public NodoHeuristica[] reconstruyeSol(NodoHeuristica n) {
        NodoHeuristica actual;
        List<NodoHeuristica> revSol = new ArrayList<>();

        actual = n;
        while (actual != null) {
            revSol.add(actual);
            actual = actual.getPadre();
        }

        Collections.reverse(revSol);

        return revSol.toArray(new NodoHeuristica[0]);
    }

    @Override
    public Estado soluciona(ProblemaBusqueda p, Heuristica h) throws Exception {
        NodoHeuristica nodoActual = new NodoHeuristica(p.getEstadoInicial(), null, null,
                0, h.evalua(p.getEstadoInicial()));
        List<NodoHeuristica> explorados = new ArrayList<>();
        Queue<NodoHeuristica> frontera = new PriorityQueue<>();
        frontera.add(nodoActual);
        NodoHeuristica hijo;
        int i = 1;

        while (true) {
            if (frontera.isEmpty()) {
                throw new Exception("No se ha podido encontrar solución");
            }

            nodoActual = frontera.poll();

            if (p.esMeta(nodoActual.getEstado())) {
                break;
            }

            System.out.println((i++) + " - " + nodoActual.getEstado() + " no es meta");

            explorados.add(nodoActual);

            for (Accion acc : p.acciones(nodoActual.getEstado())) {
                Estado nuevoEstado = p.result(nodoActual.getEstado(), acc);
                int coste = nodoActual.getCoste() + 1;
                hijo = new NodoHeuristica(nuevoEstado, nodoActual, acc,
                        coste, h.evalua(nuevoEstado));

                if (explorados.contains(hijo)) {
                    continue;
                }

                if (!frontera.contains(hijo)) {
                    frontera.add(hijo);
                    continue;
                }

                if (hijo.getCoste() > coste)

                if (!frontera.contains(hijo) && !explorados.contains(hijo)) {
                    frontera.add(hijo);
                }
            }
        }

        System.out.println((i) + " - FIN - " + nodoActual.getEstado());

        return reconstruyeSol(nodoActual);
    }
}
