package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {

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

    public boolean nodoEnLista(ArrayList<Nodo> list, Nodo nodo) {
        for (Nodo nodoLista : list) {
            if (nodoLista.getEstado().equals(nodo.getEstado())) {
                return true;
            }
        }

        return false;
    }

    public boolean nodoEnCola(Queue<Nodo> list, Nodo nodo) {
        for (Nodo nodoLista : list) {
            if (nodoLista.getEstado().equals(nodo.getEstado())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Nodo> explorados = new ArrayList<>();
        Queue<Nodo> frontera = new LinkedList<>();
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);
        frontera.add(nodoActual);
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + nodoActual.getEstado());

        while (true) {
            if (frontera.isEmpty()) {
                throw new Exception("No se ha podido encontrar solución");
            }
            nodoActual = frontera.poll();
            System.out.println((i++) + " - Estado actual cambiado a " + nodoActual.getEstado());
            if (p.esMeta(nodoActual.getEstado())) {
                break;
            }
            System.out.println((i++) + " - " + nodoActual.getEstado() + " no es meta");
            explorados.add(nodoActual);

            Accion[] accionesDisponibles = p.acciones(nodoActual.getEstado());
            for (Accion acc : accionesDisponibles) {
                Estado e = p.result(nodoActual.getEstado(), acc);
                System.out.println((i++) + " - RESULT(" + nodoActual.getEstado() + "," + acc + ")=" + e);
                Nodo hijo = new Nodo(e, nodoActual, acc);
                if (!nodoEnLista(explorados, hijo) && !nodoEnCola(frontera, hijo)) {
                    System.out.println((i++) + " - " + hijo.getEstado() + " NO explorado");
                    frontera.add(hijo);
                } else {
                    System.out.println((i++) + " - " + hijo.getEstado() + " YA explorado o YA en frontera");
                }
            }
        }

        System.out.println((i) + " - FIN - " + nodoActual.getEstado());

        return reconstruyeSol(nodoActual);
    }
}
