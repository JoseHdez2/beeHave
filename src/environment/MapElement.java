package environment;

/*
 * Interfaz que implementarán todas las clases que representen a un objeto en el entorno (Environment).
 */
public interface MapElement {

    // TODO: conviene usar Float para mayor flexibilidad, y redondear cuando sea necesario?
    
    /**
     * El elemento devuelve su tamaño en el eje horizontal.
     * La unidad equivale a una cuadrícula del entorno (Environment).
     * @return Su tamaño en el eje horizontal.
     */
    float getSizeX();
    
    /**
     * El elemento devuelve su tamaño en el eje vertical.
     * La unidad equivale a una cuadrícula del entorno (Environment).
     * @return Su tamaño en el eje vertical.
     */
    float getSizeY();
    
    /**
     * El elemento devuelve su tamaño en el eje de profundidad.
     * La unidad equivale a una cuadrícula del entorno (Environment).
     * Esta es la altura del elemento con respecto al suelo del entorno.
     * @return Su tamaño en el eje de profundidad.
     */
    float getSizeZ();
    
    // TODO: conviene usar Float para mayor flexibilidad, y redondear cuando sea necesario?
    
    /**
     * El elemento devuelve la posición horizontal de su origen con respecto al origen del entorno.
     * La unidad equivale a una cuadrícula del entorno (Environment).
     * @return La posición horizontal de su origen con respecto al origen del entorno.
     */
    float getPosX();
    
    /**
     * El elemento devuelve la posición vertical de su origen con respecto al origen del entorno.
     * La unidad equivale a una cuadrícula del entorno (Environment).
     * @return La posición vertical de su origen con respecto al origen del entorno.
     */
    float getPosY();
    
    /**
     * El elemento devuelve la profundidad de su origen con respecto al origen del entorno.
     * La unidad equivale a una cuadrícula del entorno (Environment).
     * Esta es la altura del elemento con respecto al suelo del entorno.
     * @return La profundidad de su origen con respecto al origen del entorno.
     */
    float getPosZ();
}
