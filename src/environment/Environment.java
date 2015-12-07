package environment;

import java.util.ArrayList;

/**
 *  Clase que modela el entorno de una simulación.
 *  Contiene una matriz y una lista de elementos.
 */
public class Environment {
    
    // TODO: ¿Conviene tener a los elementos dinámicos separados de los estáticos?
    
    ArrayList<MapElement> elems;
    // Elementos dinámicos en el entorno.
    // Los elementos dinámicos no se guardan en la matriz sino aparte,
    // puesto que actúan y se mueven constantemente y es necesario
    // conocer en todo momento dónde se encuentra cada uno.
    
    Matrix<MapElementType> mat;
    // Esta matriz nos permite, además de guardar las dimensiones del entorno,
    // guardar para cada posición un posible elemento estático, del que no necesitemos
    // conocer su posición en cada momento, y cuyos atributos no cambian a lo largo
    // de la simulación.

    
}
