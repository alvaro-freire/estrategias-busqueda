package es.udc.sistemasinteligentes;

import java.util.ArrayList;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public static class EstadoCuadrado extends Estado {
        private int[][] cuadrado;
        private int n;
        private ArrayList<Integer> numerosUtilizados = new ArrayList<>();

        public EstadoCuadrado(int[][] cuadrado, int n) {
            if (cuadrado.length != n) {
                throw new IllegalArgumentException();
            }

            for (int[] fila : cuadrado) {
                if (fila.length != n) {
                    throw new IllegalArgumentException();
                }

                for (int casilla : fila) {
                    if (numerosUtilizados.contains(casilla)) {
                        throw new IllegalArgumentException();
                    }
                    if (casilla != 0) {
                        numerosUtilizados.add(casilla);
                    }
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

    public static class AccionCuadrado extends Accion {
        private int fila;
        private int columna;
        private int numero;

        public AccionCuadrado(int fila, int columna, int n) {
            this.fila = fila;
            this.columna = columna;
            this.numero = n;
        }

        @Override
        public String toString() {
            return numero + "in [" + fila + ", " + columna + "]";
        }

        @Override
        public boolean esAplicable(Estado es) {
            return true;
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadrado esCua = (EstadoCuadrado) es;
            int[][] nuevoCuadrado = esCua.cuadrado;
            nuevoCuadrado[fila][columna] = numero;

            EstadoCuadrado nuevoEstadoCuadrado = new EstadoCuadrado(nuevoCuadrado, esCua.n);

            return nuevoEstadoCuadrado;
        }
    }

    public ProblemaCuadradoMagico(EstadoCuadrado estadoInicial) {
        super(estadoInicial);
    }

    public Accion[] acciones(Estado estado){
        EstadoCuadrado es = (EstadoCuadrado) estado;
        ArrayList<Integer> numerosPosibles = new ArrayList<>();
        ArrayList<Integer> numerosTotales = new ArrayList<>();
        Accion[] listaAcciones = new Accion[]{};
        int tamanoLista = 0;

        // Rango de posibles números en una casilla:
        for (int i = 1; i <= es.n; i++) {
            numerosTotales.add(i);
        }

        // Guardamos los números que todavía no han sido utilizados:
        for (int n : numerosTotales) {
            if (!es.numerosUtilizados.contains(n)) {
                numerosPosibles.add(n);
            }
        }

        // Guardamos las acciones posibles para cada casilla
        for (int i = 0; i < es.cuadrado.length; i++) {
            for (int j = 0; j < es.cuadrado[i].length; i++) {
                if (es.cuadrado[i][j] == 0) {
                    for (int numero : numerosPosibles) {
                        listaAcciones[tamanoLista] = new AccionCuadrado(i, j, numero);
                        tamanoLista++;
                    }
                }
            }
        }

        return listaAcciones;
    }

    @Override
    public boolean esMeta(Estado estado) {
        EstadoCuadrado es = (EstadoCuadrado) estado;
        return sumanFila(es) && sumanColumna(es) && sumanDiagonal(es);
    }

    public int sumaTotal(EstadoCuadrado es) {
        return es.n * (es.n * es.n + 1) / 2;
    }

    public boolean sumanFila(EstadoCuadrado es) {
        boolean cumpleSuma = true;
        int sumaFila = 0;
        int constanteMagica = sumaTotal(es);
        int f = 0;

        while (f < es.n && cumpleSuma) {
            for (int c = 0; c < es.n; c++) {
                sumaFila += es.cuadrado[f][c];
            }

            if (sumaFila != constanteMagica) {
                cumpleSuma = false;
            }
            f++;
            sumaFila = 0;
        }
        return cumpleSuma;
    }

    public boolean sumanColumna(EstadoCuadrado es) {
        boolean cumpleSuma = true;
        int sumaColumna = 0;
        int constanteMagica = sumaTotal(es);
        int c = 0;
        while (c < es.n && cumpleSuma) {
            for (int f = 0; f < es.n; f++) {
                sumaColumna += es.cuadrado[f][c];
            }

            if (sumaColumna != constanteMagica) {
                cumpleSuma = false;
            }
            c++;
            sumaColumna = 0;
        }
        return cumpleSuma;
    }

    public boolean sumanDiagonal(EstadoCuadrado es) {
        boolean cumpleSuma = true;
        int constanteMagica = sumaTotal(es);
        int sumaDiag1 = 0;
        int f = 0, c = 0;
        while (f < es.n && c < es.n) {
            sumaDiag1 += es.cuadrado[f][c];
            f++;
            c++;
        }
        if (sumaDiag1 != constanteMagica) {
            cumpleSuma = false;
        } else {
            int sumaDiag2 = 0;
            f = 0;
            c = es.n - 1;
            while (f < es.n && c >= 0) {
                sumaDiag2 += es.cuadrado[f][c];
                f++;
                c--;
            }
            if (sumaDiag2 != constanteMagica) {
                cumpleSuma = false;
            }
        }
        return cumpleSuma;
    }
}