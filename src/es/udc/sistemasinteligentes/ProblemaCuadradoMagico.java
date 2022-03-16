package es.udc.sistemasinteligentes;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public static class EstadoCuadrado extends Estado {
        private int[][] cuadrado;
        private int n;

        public EstadoCuadrado(int[][] cuadrado, int n) {
            if (cuadrado.length != n) {
                throw new IllegalArgumentException();
            }

            for (int[] fila : cuadrado) {
                if (fila.length != n) {
                    throw new IllegalArgumentException();
                }
            }

            this.cuadrado = cuadrado;
            this.n = n;
        }

        @Override
        public String toString() {
            return "(" + cuadrado + "," + n + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EstadoCuadrado that = (EstadoCuadrado) o;

            if (cuadrado != that.cuadrado) return false;
            return n == that.n;
        }

        @Override
        public int hashCode() {
            int result = cuadrado.hashCode();
            result = 31 * result + cuadrado.hashCode();
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