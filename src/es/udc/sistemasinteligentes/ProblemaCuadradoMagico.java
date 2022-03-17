package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public static class EstadoCuadrado extends Estado {
        private int[][] cuadrado;
        private int n;

        public EstadoCuadrado(int[][] cuadrado, int n) {
            this.cuadrado = cuadrado;
            this.n = n;
        }

        @Override
        public String toString() {
            return "(" + Arrays.deepToString(cuadrado) + "," + n + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EstadoCuadrado that = (EstadoCuadrado) o;
            return Arrays.deepEquals(cuadrado, that.cuadrado);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(cuadrado);
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
            int[][] nuevoCuadrado = new int[esCua.n][];

            for (int i = 0; i < esCua.n; i++) {
                nuevoCuadrado[i] = esCua.cuadrado[i].clone();
            }

            nuevoCuadrado[fila][columna] = numero;

            return new EstadoCuadrado(nuevoCuadrado, esCua.n);
        }
    }

    public ProblemaCuadradoMagico(EstadoCuadrado estadoInicial) {
        super(estadoInicial);
    }

    public Accion[] acciones(Estado estado) {
        EstadoCuadrado es = (EstadoCuadrado) estado;
        List<Integer> valoresPosibles = new ArrayList<>();
        List<Integer> valoresTotales = new ArrayList<>();
        List<Integer> valoresUtilizados = new ArrayList<>();
        List<Accion> listaAcciones = new ArrayList<>();

        // Guardamos los números que ya han sido utilizados:
        for (int[] fila : es.cuadrado) {
            for (int casilla : fila) {
                if (casilla != 0) {
                    valoresUtilizados.add(casilla);
                }
            }
        }

        // Posibles números en una casilla:
        for (int i = 1; i <= es.n * es.n; i++) {
            valoresTotales.add(i);
        }

        // Guardamos los números que todavía no han sido utilizados:
        for (int n : valoresTotales) {
            if (!valoresUtilizados.contains(n)) {
                valoresPosibles.add(n);
            }
        }

        // Guardamos las acciones posibles para cada casilla
        for (int i = 0; i < es.n; i++) {
            for (int j = 0; j < es.n; j++) {
                if (es.cuadrado[i][j] == 0) {
                    for (int valor : valoresPosibles) {
                        listaAcciones.add(new AccionCuadrado(i, j, valor));
                    }
                }
            }
        }

        return listaAcciones.toArray(new Accion[0]);
    }

    @Override
    public boolean esMeta(Estado estado) {
        EstadoCuadrado es = (EstadoCuadrado) estado;
        int result = es.n * (es.n * es.n + 1) / 2;
        int sumaDiag1 = 0;
        int sumaDiag2 = 0;

        for (int i = 0; i < es.n; i++) {
            int sumaFila = 0;
            int sumaColumna = 0;
            sumaDiag1 += es.cuadrado[i][i];
            sumaDiag2 += es.cuadrado[i][es.n - i - 1];

            for (int j = 0; j < es.n; j++) {
                sumaFila += es.cuadrado[i][j];
                sumaColumna += es.cuadrado[j][i];
            }

            if (sumaFila != result || sumaColumna != result) {
                return false;
            }
        }

        return sumaDiag1 == result && sumaDiag2 == result;
    }
}