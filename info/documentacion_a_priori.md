
# Clase Simulación

## Dependencias

* clase `Simulation`.

## ¿Qué?

Clase que modela una simulación.

## Atributos

* `Environment env`
  * Entorno de la simulación, a su vez con los elementos (agentes y objetos).
* `int turn`
  * Número de turno actual.
  * Empieza en 1 y acaba como mucho en `turnMax`.
* `int turnMax`
  * Máximo de turnos que correrá la simulación, en caso de no llegar a otra condición de parada.
* `bool interactive`
  * Permite que se muestre
  * Si `false`, corre la simulación lo más rápido posible y sólo muestra los resultados finales.
* `float turnTime`
  * Tiempo en segundos que se verá cada turno en pantalla (en caso de `interactive`).

## Métodos

* `initialize()`
  * Inicializa la simulación (`turn = 0`, etc.) en estado pausado.
  * Se llama cuando 
* `pause()`
  * Pausa la simulación.
* `continue()`
  * Corre una simulación pausada.
* `stop()`
  * Finaliza la simulación y realiza y muestra los cálculos pertinentes.
