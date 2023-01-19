import java.util.Scanner;

public class Pacman1 {

    static final int FILA = 0;
    static final int COLUMNA = 1;

    static final int ARRIBA = 1;
    static final int ABAJO = 2;
    static final int IZQUIERDA = 3;
    static final int DERECHA = 4;

    static final int[][] MOVIMIENTOS = {
            {0, 0},
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    static final char[] TECLAS = {'x', 'w', 's', 'a', 'd'};

    public static void main(String[] args) {

        int[][] elMapa = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0, 1, 3, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 2, 1, 0, 0, 1, 2, 1, 1, 0, 2, 2, 1},
                {1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        int[] elPersonaje = {1, 3};
        int[] fantasma = {7, 14};

        do {
            imprimeMundo(elMapa, elPersonaje, fantasma);
            definePosicion(elPersonaje, elMapa);
            definePosicionFantasma(fantasma, elMapa);
        } while (comprobar(elPersonaje, fantasma, elMapa) != 1);
    }


    private static void definePosicion(int[] elPersonaje, int[][] elMapa) {

        switch (capturaMovimiento()) {
            case ARRIBA:
                mueve(elPersonaje, ARRIBA, elMapa);
                break;
            case ABAJO:
                mueve(elPersonaje, ABAJO, elMapa);
                break;
            case IZQUIERDA:
                mueve(elPersonaje, IZQUIERDA, elMapa);
                break;
            case DERECHA:
                mueve(elPersonaje, DERECHA, elMapa);
                break;
        }
    }

    private static void definePosicionFantasma(int[] fantasma, int[][] elMapa) {
        switch (movimientoFantasma()) {
            case ARRIBA:
                mueveFantasma(fantasma, elMapa);
                break;
            case ABAJO:
                mueveFantasma(fantasma,elMapa);
                break;
            case IZQUIERDA:
                mueveFantasma(fantasma,  elMapa);
                break;
            case DERECHA:
                mueveFantasma(fantasma, elMapa);
                break;


        }
    }

    private static int capturaMovimiento() {

        switch (preguntaChar()) {
            case 's', 'S', '8':
                return ABAJO;
            case 'w', 'W', '2':
                return ARRIBA;
            case 'a', 'A', '4':
                return IZQUIERDA;
            case 'd', 'D', '6':
                return DERECHA;
        }
        return 0;
    }

    static void imprimeMundo(int[][] unMapa, int[] unPersonaje, int[] fantasma) {

        for (int fila = 0; fila < unMapa.length; fila++) {
            for (int columna = 0; columna < unMapa[fila].length; columna++) {
                if (fila == unPersonaje[FILA] && columna == unPersonaje[COLUMNA]) {
                    imprimePersonaje();

                } else if (fila == fantasma[FILA] && columna == fantasma[COLUMNA]) {

                    imprimeFantasma();
                } else {
                    imprimeTerreno(unMapa[fila][columna]);
                }
            }
            System.out.println();
        }
    }

    static void imprimeTerreno(int unTile) {

        String[] terreno = {" . ", "[#]", "   ", "---"};
        System.out.print(terreno[unTile]);
    }

    static void imprimePersonaje() {

        System.out.print("_O_");
    }

    static void imprimeFantasma() {
        System.out.print("|-|");
    }

    static void mueve(int[] elPersonaje, int movimiento, int[][] elMapa) {

        int nuevaFila = elPersonaje[FILA] + MOVIMIENTOS[movimiento][FILA];
        int nuevaColumna = elPersonaje[COLUMNA] + MOVIMIENTOS[movimiento][COLUMNA];
        if (elMapa[nuevaFila][nuevaColumna] != 1) {
            elPersonaje[FILA] = nuevaFila;
            elPersonaje[COLUMNA] = nuevaColumna;
        }


    }

    static void mueveFantasma(int[] fantasma, int[][] elMapa) {
        boolean desplazamiento=false;
        do {
            int movimiento=movimientoFantasma();
            int nuevaFila = fantasma[FILA] + MOVIMIENTOS[movimiento][FILA];
            int nuevaColumna = fantasma[COLUMNA] + MOVIMIENTOS[movimiento][COLUMNA];
            if (elMapa[nuevaFila][nuevaColumna] != 1) {
                fantasma[FILA] = nuevaFila;
                fantasma[COLUMNA] = nuevaColumna;
                desplazamiento=true;
            }
        }while (!desplazamiento);


    }

    static int preguntaInt() {

        Scanner entrada = new Scanner(System.in);
        return entrada.nextInt();
    }

    static String preguntaString() {

        Scanner entrada = new Scanner(System.in);
        return entrada.nextLine();
    }

    static char preguntaChar() {

        Scanner entrada = new Scanner(System.in);
        return entrada.next().charAt(0);
    }

    static int movimientoFantasma() {
        int asignacionMovimiento = (int) (((Math.random() * 100) % 4) + 1);

        return asignacionMovimiento;
    }

    static int comprobar(int elPersonaje[], int fantasma[], int elMapa[][]) {
        if (elPersonaje[FILA] == fantasma[FILA])
            if (fantasma[COLUMNA] == elPersonaje[COLUMNA]) {

                System.out.print("GAME OVER");
                return 1;
            }
        return 0;
    }
}
