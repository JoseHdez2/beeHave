package agent.v2;

import java.util.HashMap;

import agent.v1.Agent1;

// TODO: nombre temporal o definitivo?

/**
 *  Clase basada en la teoría de agentes.
 *  
 *  
 */
public abstract class Agent2 extends Agent1 {
    
    /*
     * Datos: goals
     */
    
    /**
     * Metas del agente
     * Cada par representa una meta diferente.
     * La clave es el nombre de la meta, mientras que el valor es el estado de la misma. 
     */
    HashMap<String, Integer> goal_status;
    
    /**
     * Prioridades de las metas del agente
     * Cada par representa una meta diferente.
     * La clave es el nombre de la meta, mientras que el valor es la prioridad de la misma. 
     */
    HashMap<String, Integer> goal_priorities;
}

