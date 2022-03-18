package es.udc.sistemasinteligentes.gA_42;

import java.util.Objects;

public class NodoEj2 {

    private final Estado estado;
    private final NodoEj2 padre;
    private final Accion accion;
    //private final float coste;

    public NodoEj2(Estado e, NodoEj2 p, Accion a) {
        estado = e;
        padre = p;
        accion = a;
        //coste = HeuristicaCuadradoMagico.evalua(e);
    }

    public Estado getEstado() {
        return estado;
    }

    public NodoEj2 getPadre() {
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
        NodoEj2 nodo = (NodoEj2) o;
        
        return Objects.equals(estado, nodo.estado);
    }
}
