package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.ejemplo.Nodo;

import java.util.ArrayList;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {

    @Override
    public ArrayList<Nodo> soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Estado> explorados = new ArrayList<Estado>();
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

        //return reconstruyeSol(nodo);
        return null;
    }
}
