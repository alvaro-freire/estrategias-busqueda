package es.udc.sistemasinteligentes.gA_42;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

import es.udc.sistemasinteligentes.gA_42.ejemplo.Nodo;

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

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        List<Nodo> explorados = new ArrayList<>();
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);
        Nodo hijo;
        Stack<Nodo> frontera = new Stack<>();
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
