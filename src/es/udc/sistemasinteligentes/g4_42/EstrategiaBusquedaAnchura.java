package es.udc.sistemasinteligentes.g4_42;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import es.udc.sistemasinteligentes.g4_42.ejemplo.Nodo;

public class EstrategiaBusquedaAnchura implements EstrategiaBusqueda {

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
        List<Nodo> explorados = new ArrayList<>();
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);
        Nodo hijo;
        Queue<Nodo> frontera = new LinkedList<>();
        frontera.add(nodoActual);
        int i = 1, n_cr = 1, n_exp = 0;

        while (true) {
            if (frontera.isEmpty()) {
                throw new Exception("No se ha podido encontrar solución");
            }

            nodoActual = frontera.remove();

            if (p.esMeta(nodoActual.getEstado())) {
                break;
            }

            System.out.println((i++) + " - " + nodoActual.getEstado() + " no es meta");

            explorados.add(nodoActual);
            n_exp++;

            for (Accion acc : p.acciones(nodoActual.getEstado())) {
                hijo = new Nodo(p.result(nodoActual.getEstado(), acc), nodoActual, acc);
                n_cr++;

                if (!frontera.contains(hijo) && !explorados.contains(hijo)) {
                    frontera.add(hijo);
                }
            }
        }

        System.out.println((i) + " - FIN - " + nodoActual.getEstado());
        System.out.println("Nodos creados: " + n_cr);
        System.out.println("Nodos expandidos: " + n_exp);


        return reconstruyeSol(nodoActual);
    }
}
