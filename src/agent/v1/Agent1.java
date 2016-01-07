package agent.v1;

import java.util.ArrayList;
import java.util.HashMap;

// TODO: nombre temporal o definitivo?

/**
 *  Clase basada en la teoría de agentes.
 *  
 *  Representación abstracta de como actúa un agente.
 */
public abstract class Agent1 {
    
    /*
     * Datos: sensors, actuators. perceptions, intentions.
     */
    
    /**
     * Sensores que permiten la percepción del entorno.
     */
    ArrayList<Sensor1> sensors;
    
    /**
     * Actuadores que permiten la interacción con el entorno (cambiar el mundo).
     */
    ArrayList<Actuator1> actuators;
    
    /**
     * Información que se actualiza con la recibida de los sensores en el método perception().
     * Conjunto de percepciones.
     */
    HashMap<String,String> perceptions;
    
    /**
     * Información que se actualiza con la salida del sistema planificador en el método planification().
     * Conjunto de acciones a tomar.
     */
    HashMap<String,String> intentions;
    
    /*
     * Métodos: perception(), planification(), action().
     */
    
    /**
     * El agente recibe las percepciones de los sensores.
     * No hay salida porque se actualiza el HashMap perceptions como efecto secundario.
     */
    protected abstract void perception();
    
    /**
     * El agente recibe los planes del sistema planificador.
     * No hay salida porque se actualiza el HashMap intentions como efecto secundario.
     */
    protected abstract void planification();
    
    /**
     * El agente activa sus actuadores de acuerdo con sus intenciones.
     * Para ello leera el HashMap intentions.
     */
    protected abstract void action();
}

