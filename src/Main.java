import edu.princeton.cs.introcs.StdDraw;

import java.util.Random;
import java.awt.event.KeyEvent;

/**
 * The Main.
 *
 * @author Misael Marcelo Zárate Narria
 */

public class Main {
    public static void main(String[] args) {
        // Se inicia el juego llamando al método iniciar().
        new JuegoComer().iniciar();
    }
}

/**
 * Clase que representa el juego.
 */

class JuegoComer {
    /**
     * Aquí es donde se crean la variables a usar.
     * Las variables "JugadorX" y "JugadorY" son las coordenadas x e y del punto azul.
     * Las variables "maxX" y "maxY" son las medidas del ancho y alto del lienzo.
     * La varible "Puntos" es el total de puntos rojos.
     * La variable "puntosComidos" es el contador de la cantidad de puntos que se come.
     * Las variables posX y posY es en donde de almacenan las coordenadas x e y de los puntos rojos.
     */
    // Coordenadas x e y del punto azul (Jugador).
    private double jugadorX = 250;
    private double jugadorY = 250;

    // Tamaño del lienzo
    private int maxX = 500;
    private int maxY = 500;

    // Contador de los puntos comidos y total de puntos rojos.
    private int puntosComidos = 0;
    private int Puntos = 12;

    // Arreglo para almacenar las coordenadas x e y de los puntos rojos.
    private double[] posX = new double[Puntos];
    private double[] posY = new double[Puntos];

    /**
     * Aqui se inicializa el programa con la creación del lienzo y el titulo.
     * También, se generan los puntos rojos y empieza el ciclo del juego.
     */
    public void iniciar() {
        // Tamaño del lienzo y Título.
        StdDraw.setCanvasSize(maxX, maxY);
        StdDraw.setXscale(0, maxX);
        StdDraw.setYscale(0, maxY);
        StdDraw.setTitle("Pickerd Apples");

        //Generar las coordenadas de los puntos rojos aleatoriamente.
        Random random = new Random();
        for (int i = 0; i < Puntos; i++) {
            posX[i] = random.nextDouble() * maxX;
            posY[i] = random.nextDouble() * maxY;
        }

        StdDraw.enableDoubleBuffering();
        base();

        while (puntosComidos < Puntos) {
            // Mover al jugador, actualizar los datos del juego y dibujar los elementos en el lienzo.
            moverJugador();
            actualizar();
            base();

            // Mostrar los cambios en el lienzo y controlar los "refrescos" de la interfaz grafica.
            StdDraw.show();
            StdDraw.pause(30);
        }

        // Mostrar mensaje de victoria a la hora de comerse todos los puntos rojos.
        MomentoVictoria();
    }

    /**
     * Mètodo para controlar el movimiento del jugador a través de las teclas y colocando los limites a su vez.
     */
    private void moverJugador() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            if (jugadorY < maxY) {
                jugadorY += 5;
            }
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            if (jugadorX > 0){
                jugadorX -= 5;
            }
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            if (jugadorY > 0){
                jugadorY -= 5;
            }
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_E)) {
            if (jugadorX < maxX){
                jugadorX += 5;
            }
        }
    }

    /**
     * Metodo para dibujar los elementos del juego en el lienzo.
     */
    private void base() {
        // Limpiar el lienzo, dibujar los puntos rojos, el jugador y el contador de puntos comidos.
        StdDraw.clear(StdDraw.WHITE);
        for (int i = 0; i < Puntos; i++) {
            if (posX[i] != -1) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledCircle(posX[i], posY[i], 4);
            }
        }
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(jugadorX, jugadorY, 4);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.textLeft(10, 10, ""+ puntosComidos);
    }

    /**
     * Método para actualizar el estado del juego, en donde, se comprueba si el jugador se ha comido un punto rojo o no.
     */
    private void actualizar() {

        // Se verifica si se comio un punto rojo y se actualiza el contador.
        for (int i = 0; i < Puntos; i++) {
            if (posX[i] != -1) {
                // Se usa formula matematica para calcular la distancia entre punto a punto para saber si se ha comido un punto rojo.
                if (Math.sqrt(Math.pow(jugadorX - posX[i], 2) + Math.pow(jugadorY - posY[i], 2)) <= 8) {
                        puntosComidos++;
                        posX[i] = -1;
                }
            }
        }
    }

    /**
     * Método para mostrar mensaje de victoria a la hora de ganar.
     */
    private void MomentoVictoria() {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(maxX / 2, maxY / 2, "JUEGO FINALIZADO, ¡GANASTE!");
        StdDraw.show();
    }
}