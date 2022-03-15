package es.udc.sistemasinteligentes;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public static class EstadoCuadrado extends Estado {
        public enum PosicionRobot {IZQ, DER};
        public enum PosicionBasura {AMBAS, DER, IZQ, NINGUNA};

        private PosicionRobot posicionRobot;
        private PosicionBasura posicionBasura;

        public EstadoCuadrado(PosicionRobot posicionRobot, PosicionBasura posicionBasura) {
            this.posicionRobot = posicionRobot;
            this.posicionBasura = posicionBasura;
        }

        @Override
        public String toString() {
            return "(" + posicionRobot + "," + posicionBasura + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EstadoCuadrado that = (EstadoCuadrado) o;

            if (posicionRobot != that.posicionRobot) return false;
            return posicionBasura == that.posicionBasura;
        }

        @Override
        public int hashCode() {
            int result = posicionRobot.hashCode();
            result = 31 * result + posicionBasura.hashCode();
            return result;
        }
    }

    public static class AccionCuadrado extends Accion{
        public enum Tipo {IZQ, DER, ASP};

        private Tipo tipo;

        public AccionCuadrado(Tipo tipo) {
            this.tipo = tipo;
        }

        @Override
        public String toString() {
            return tipo.name();
        }

        @Override
        public boolean esAplicable(Estado es) {
            return true;
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadrado esAs = (EstadoCuadrado)es;
            EstadoCuadrado.PosicionRobot nuevaPosicionRobot=esAs.posicionRobot;
            EstadoCuadrado.PosicionBasura nuevaPosicionBasura=esAs.posicionBasura;

            if (tipo==Tipo.IZQ)
                nuevaPosicionRobot = EstadoCuadrado.PosicionRobot.IZQ;
            else if (tipo==Tipo.DER)
                nuevaPosicionRobot = EstadoCuadrado.PosicionRobot.DER;
            else if (tipo==Tipo.ASP) {
                if (esAs.posicionRobot==EstadoCuadrado.PosicionRobot.IZQ) { //Aspiramos izquierda
                    if ((esAs.posicionBasura==EstadoCuadrado.PosicionBasura.DER) ||
                            (esAs.posicionBasura==EstadoCuadrado.PosicionBasura.AMBAS)) {
                        nuevaPosicionBasura = EstadoCuadrado.PosicionBasura.DER;
                    }
                    else
                        nuevaPosicionBasura = EstadoCuadrado.PosicionBasura.NINGUNA;
                }
                else{ //Aspiramos derecha
                    if ((esAs.posicionBasura==EstadoCuadrado.PosicionBasura.IZQ) ||
                            (esAs.posicionBasura==EstadoCuadrado.PosicionBasura.AMBAS)) {
                        nuevaPosicionBasura = EstadoCuadrado.PosicionBasura.IZQ;
                    }
                    else
                        nuevaPosicionBasura = EstadoCuadrado.PosicionBasura.NINGUNA;
                }
            }
            return new EstadoCuadrado(nuevaPosicionRobot, nuevaPosicionBasura);
        }
    }

    //Como toda las acciones se pueden aplicar en cualquier estado y son pocas,
    //podemos mantenerlas en un array para cuando nos las pidan con el método acciones.
    private Accion[] listaAcciones;

    public ProblemaCuadradoMagico(EstadoCuadrado estadoInicial) {
        super(estadoInicial);
        //Inicializamos la lista de acciones
        listaAcciones = new Accion[]{new AccionCuadrado(AccionCuadrado.Tipo.IZQ),
                new AccionCuadrado(AccionCuadrado.Tipo.DER),
                new AccionCuadrado(AccionCuadrado.Tipo.ASP)};
    }

    public Accion[] acciones(Estado es){
        //No es necesario generar las acciones dinámicamente a partir del estado porque todas las acciones se pueden
        //aplicar a todos los estados
        return listaAcciones;
    }

    @Override
    public boolean esMeta(Estado es) {
        return ((EstadoCuadrado)es).posicionBasura == EstadoCuadrado.PosicionBasura.NINGUNA;
    }
}