package app;

import environment.Environment;

/**
 *  Clase que modela una simulación.
 */
public abstract class Simulation {
    
    Environment env;
    // Entorno de la simulación, a su vez con los elementos (agentes y objetos).

    int turn;
    // Número de turno actual.
    // Empieza en 1 y acaba como mucho en "turnMax".

    int turnMax;
    // Número máximo de turnos que correrá la simulación, en caso de no llegar a otra condición de parada. 
    
    boolean paused;
    // Si la simulación se encuentra pausada. Esto se revisa antes de empezar a ejecutar un nuevo turno.
    
    boolean interactive;
    // Controla el modo en que se muestra la simulación.
    // Si true, muestra cada paso en pantalla durante "interactiveTurnSeconds" segundos.
    
    float interactiveTurnSeconds;
    // Tiempo en segundos durante el cual se mostrará cada turno en pantalla.
    
    abstract void initialize();
    // Inicializa la simulacion ("turn" = 0, etc) en estado pausado.
    // Llama al initialize() del "env"(Entorno) en cascada.
    
    abstract void pause();
    // Pausa la simulación.
    
    abstract void run();
    // Ejecuta el bucle inicial de la simulación.
    // Ejecuta cada turno de la simulación, y además...
    // Si "interactive" es true, muestra el entorno por pantalla durante "interactiveTurnSeconds".
    // Si "interactive" es true, revisa si ha llegado una peticion de parada y no sigue.
    
    abstract void finish();
    // Para la simulación de manera definitiva.
    // Se diferencia de pause() en que además calculamos y mostramos estadísticas.
}
