package es.udc.sistemasinteligentes;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public ProblemaCuadradoMagico(Estado estadoInicial) {
        super(estadoInicial);
    }

    @Override
    public boolean esMeta(Estado es) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Accion[] acciones(Estado es) {
        // TODO Auto-generated method stub
        return null;
    }
}