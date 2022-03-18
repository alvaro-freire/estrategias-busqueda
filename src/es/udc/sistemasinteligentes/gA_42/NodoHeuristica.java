package es.udc.sistemasinteligentes.gA_42;

import java.util.Objects;

public class NodoHeuristica implements Comparable<NodoHeuristica> {

    private final Estado estado;
    private final NodoHeuristica padre;
    private final Accion accion;
    private final int coste;
    private final float f;

    public NodoHeuristica(Estado e, NodoHeuristica p, Accion a, int c, float f) {
        this.estado = e;
        this.padre = p;
        this.accion = a;
        this.coste = c;
        this.f = f;
    }

    public int getCoste() {
        return coste;
    }

    public float getF() {
        return f;
    }

    public Estado getEstado() {
        return estado;
    }

    public NodoHeuristica getPadre() {
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
        NodoHeuristica nodo = (NodoHeuristica) o;

        return Objects.equals(estado, nodo.estado);
    }

    @Override
    public int compareTo(NodoHeuristica nodo) {
        if (this.f < nodo.f) {
            return -1;
        } else if (this.f > nodo.f) {
            return 1;
        }

        return 0;
    }
}
