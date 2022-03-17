package es.udc.sistemasinteligentes.ejemplo;

import java.util.Objects;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public class Nodo {

    private final Estado estado;

    private final Nodo padre;

    private final Accion accion;

    public Nodo(Estado e, Nodo p, Accion a) {
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

    @Override
    public String toString() {
        return "\nNodo {" +
                "estado=" + estado +
                ", accion=" + accion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        
        return Objects.equals(estado, nodo.estado);
    }
}
