package es.udc.sistemasinteligentes;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

import es.udc.sistemasinteligentes.ejemplo.Nodo;

public class EstrategiaBusquedaProfundidad implements EstrategiaBusqueda {

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

    public boolean nodoEnLista(List<Nodo> list, Nodo nodo) {
        for (Nodo nodoLista : list) {
            if (nodoLista.getEstado() == nodo.getEstado()) {
                return true;
            }
        }

        return false;
    }

    public boolean nodoEnPila(Stack<Nodo> list, Nodo nodo) {
        for (Nodo nodoLista : list) {
            if (nodoLista.getEstado() == nodo.getEstado()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) {
        List<Nodo> explorados = new ArrayList<>();
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);
        Nodo nodoSiguiente;
        Stack<Nodo> frontera = new Stack<>();
        frontera.add(nodoActual);

        while (true) {
            if (frontera.isEmpty()) {
                throw new IllegalArgumentException();
            }

            nodoActual = frontera.pop();

            if (p.esMeta(nodoActual.getEstado())) {
                break;
            }

            explorados.add(nodoActual);

            for (Accion acc : p.acciones(nodoActual.getEstado())) {
                nodoSiguiente =
                        new Nodo(p.result(nodoActual.getEstado(), acc), nodoActual, acc);

                if (!nodoEnPila(frontera, nodoSiguiente) && !nodoEnLista(explorados, nodoSiguiente)) {
                    frontera.add(nodoSiguiente);
                }
            }
        }

        return reconstruyeSol(nodoActual);
    }
    
}
