package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import es.udc.sistemasinteligentes.ejemplo.Nodo;

public class EstrategiaBusquedaAnchura implements EstrategiaBusqueda {

    public Nodo[] reconstruyeSol(Nodo n) {
        Nodo actual;
        ArrayList<Nodo> revSol = new ArrayList<>();

        actual = n;
        while (actual != null) {
            revSol.add(actual);
            actual = actual.getPadre();
        }

        Collections.reverse(revSol);

        return revSol.toArray(new Nodo[0]);
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) {
        List<Nodo> explorados = new ArrayList<>();
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);
        Nodo hijo;
        Queue<Nodo> frontera = new LinkedList<>();
        frontera.add(nodoActual);

        while (true) {
            if (frontera.isEmpty()) {
                throw new IllegalArgumentException("No se ha podido encontrar solución");
            }

            nodoActual = frontera.remove();

            if (p.esMeta(nodoActual.getEstado())) {
                break;
            }

            explorados.add(nodoActual);

            for (Accion acc : p.acciones(nodoActual.getEstado())) {
                hijo = new Nodo(p.result(nodoActual.getEstado(), acc), nodoActual, acc);

                if (!frontera.contains(hijo) && !explorados.contains(hijo)) {
                    frontera.add(hijo);
                }
            }
        }

        return reconstruyeSol(nodoActual);
    }
}
