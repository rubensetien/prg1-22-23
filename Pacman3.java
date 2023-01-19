import java.util.Scanner;

public class Pacman3 {

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
static int puntos=0;
    public static void main(String[] args) {

        int[][] elMapa = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 2, 0, 1, 3, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 2, 2, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 2, 2, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        int[] elPersonaje = {1, 3};
        int[][] fantasma = {{7, 14}, {2, 1}, {3, 3}};

        do {
            imprimeMundo(elMapa, elPersonaje, fantasma);
            for (int i = 0; i < fantasma.length; i++) {
                definePosicionFantasma(fantasma[i], elMapa, elPersonaje);


            }
            definePosicion(elPersonaje, elMapa);
        } while ((comprobar(elPersonaje, fantasma) != 1)&&contador(elMapa)!=0);
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

    private static void definePosicionFantasma(int[] fan, int[][] elMapa, int[] elPersonaje) {
        switch (movimientoFantasma(seguimiento(fan, elPersonaje))) {
            case ARRIBA:
                mueveFantasma(fan, elMapa, elPersonaje);
                break;
            case ABAJO:
                mueveFantasma(fan, elMapa, elPersonaje);
                break;
            case IZQUIERDA:
                mueveFantasma(fan, elMapa, elPersonaje);
                break;
            case DERECHA:
                mueveFantasma(fan, elMapa, elPersonaje);
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

    static void imprimeMundo(int[][] unMapa, int[] unPersonaje, int[][] fantasma) {

        for (int fila = 0; fila < unMapa.length; fila++) {
            for (int columna = 0; columna < unMapa[fila].length; columna++) {
                if (fila == unPersonaje[FILA] && columna == unPersonaje[COLUMNA]) {
                    imprimePersonaje();


                } else if (existeFantasma(fila, columna, fantasma)) {
                    imprimeFantasma();
                } else {
                    imprimeTerreno(unMapa[fila][columna]);
                }


            }
            System.out.println();

        }System.out.println("Puntuacion: "+puntos);
    }

    static void imprimeTerreno(int unTile) {

        String[] terreno = {" . ", "[#]", "   ", "---"};
        System.out.print(terreno[unTile]);
    }

    static void imprimePersonaje() {

        System.out.print("_O_");
    }

    static void imprimeFantasma() {
        System.out.print("¬6¬");
    }

    static void mueve(int[] elPersonaje, int movimiento, int[][] elMapa) {

        int nuevaFila = elPersonaje[FILA] + MOVIMIENTOS[movimiento][FILA];
        int nuevaColumna = elPersonaje[COLUMNA] + MOVIMIENTOS[movimiento][COLUMNA];
        if (elMapa[nuevaFila][nuevaColumna] != 1) {
            if (elMapa[elPersonaje[FILA]][elPersonaje[COLUMNA]]==0){
                puntos+=10;
            }
            elMapa[elPersonaje[FILA]][elPersonaje[COLUMNA]] = 2;
            elPersonaje[FILA] = nuevaFila;
            elPersonaje[COLUMNA] = nuevaColumna;
        }


    }

    static void mueveFantasma(int[] fantasma, int[][] elMapa, int[] elPersonaje) {
        boolean desplazamiento = false;
        do {
            int pista = seguimiento(fantasma, elPersonaje);
            int movimiento = movimientoFantasma(pista);
            int nuevaFila = fantasma[FILA] + MOVIMIENTOS[movimiento][FILA];
            int nuevaColumna = fantasma[COLUMNA] + MOVIMIENTOS[movimiento][COLUMNA];
            if (elMapa[nuevaFila][nuevaColumna] != 1) {
                fantasma[FILA] = nuevaFila;
                fantasma[COLUMNA] = nuevaColumna;
                desplazamiento = true;
            }
        } while (!desplazamiento);


    }

  

    static char preguntaChar() {

        Scanner entrada = new Scanner(System.in);
        return entrada.next().charAt(0);
    }

    static int movimientoFantasma(int pista) {
        int asignacionMovimiento = (int) (((Math.random() * 100) % 4) + 1);

        int probabilidadPista = (int) (((Math.random() * 100) % 10) );
        if (probabilidadPista==0 ||probabilidadPista == 1 || probabilidadPista == 2 ||probabilidadPista==3 ||probabilidadPista==4||probabilidadPista==5||probabilidadPista==6||probabilidadPista==7||probabilidadPista==8) {
            return pista;
        } else if (probabilidadPista == 9) {
            return asignacionMovimiento;
        }

        return asignacionMovimiento;
    }

    static int comprobar(int elPersonaje[], int fantasma[][]) {
        if (existeFantasma(elPersonaje[FILA], elPersonaje[COLUMNA], fantasma)) {
            System.out.print("GAME OVER");
            return 1;
        }
        return 0;
    }
static int contador( int elMapa [][]) {
    int puntitos = 0;
    for (int i = 0; i < elMapa.length; i++) {
        for (int j = 0; j < elMapa.length; j++) {
            if (elMapa[i][j] == 1) {
                puntitos++;
            }

        }

    }return puntitos;
}



    static int seguimiento(int fantasma[], int[] elPersonaje) {
        if (elPersonaje[FILA] > fantasma[FILA]) {
            return ARRIBA;
        } else if (elPersonaje[FILA] < fantasma[FILA]) {
            return ABAJO;
        } else if (elPersonaje[COLUMNA] < fantasma[COLUMNA]) {
            return IZQUIERDA;
        } else if (elPersonaje[COLUMNA] > fantasma[COLUMNA]) {

            return DERECHA;


        }
        return 0;
    }


    static boolean existeFantasma(int f, int c, int[][] fantasma) {
        for (int i = 0; i < fantasma.length; i++) {


            if (f == fantasma[i][FILA] && c == fantasma[i][COLUMNA]) {

                return true;

            }

        }
        return false;

    }
}
