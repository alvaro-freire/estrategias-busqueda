package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public class Nodo {

    private final Estado estado;

    private final Nodo padre;

    private final Accion accion;

    Nodo(Estado e, Nodo p, Accion a) {
        estado = e;
        padre = p;
        accion = a;
    }

    public Estado getEstado() {
        return estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public Accion getAccion() {
        return accion;
    }

}