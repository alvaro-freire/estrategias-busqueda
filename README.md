## Memoria Prácticas - Estrategias-Búsqueda

### Ejercicio 1

Hemos creado una clase Nodo con los atributos `estado` (Estado), `padre` (Nodo) y `accion` (Accion).


En el método `soluciona()` vamos creando nuevos nodos a partir del inicial (sin padre ni acción, simplemente un estado) siempre indicando al nodo padre. De esta forma, cuando la función encuentra un nodo meta, el método `reconstruyeSol()` simplemente tiene que ir introducir los nodos en una lista desde el nodo meta hasta el nodo inicial y devolver la lista en orden inverso, para que el primer nodo sea el nodo inicial y el último sea la meta.


Para la clase `EstrategiaBúsquedaGrafo` hemos añadido una frontera (cola), que soluciona el problema de `Estrategia4`, en la que era posible que estados por los que se había transitado previamente todavía tuvieran sucesores sin explorar. Para ello, la cola almacena los estados sucesores encontrados mientras no son explorados.


### Ejercicio 2

Hemos definido un estado con los atributos `cuadrado` como matriz con las casillas a completar
y `n` que describe la dimensión del cuadrado `NxN`. Las acciones que hemos concluido para un
estado se basan en, para cada casilla, colocar el menor número posible.

Ejemplo:

Estado:  
[4, 9, 2]  
[3, 5, 7]  
[0, 0, 0]


Acciones:  
[4, 9, 2] -- [4, 9, 2] -- [4, 9, 2]  
[3, 5, 7] -- [3, 5, 7] -- [3, 5, 7]  
[1, 0, 0] -- [0, 1, 0] -- [0, 0, 1]  


Se han implementado también dos estrategias:
`EstrategiaBusquedaAnchura` y `EstrategiaBusquedaProfundidad`.

La idea principal de la búsqueda en anchura consiste en visitar todos los nodos que hay a profundidad `i` antes de pasar a visitar aquellos que hay a profundidad `i+1`. Es decir, tras visitar un nodo, pasamos a visitar a sus hermanos antes que a sus hijos.

