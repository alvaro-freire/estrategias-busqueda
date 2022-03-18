## Memoria Prácticas - Estrategias-Búsqueda

### Ejercicio 1

Hemos creado una clase Nodo con los atributos `estado` (Estado), `padre` (Nodo) y `accion` (Accion).


En el método `soluciona()` vamos creando nuevos nodos a partir del inicial (sin padre ni acción, simplemente un estado) siempre indicando al nodo padre. De esta forma, cuando la función encuentra un nodo meta, el método `reconstruyeSol()` simplemente tiene que ir introducir los nodos en una lista desde el nodo meta hasta el nodo inicial y devolver la lista en orden inverso, para que el primer nodo sea el nodo inicial y el último sea la meta.


Para la clase `EstrategiaBúsquedaGrafo` hemos añadido una frontera (cola), que soluciona el problema de `Estrategia4`, en la que era posible que estados por los que se había transitado previamente todavía tuvieran sucesores sin explorar. Para ello, la cola almacena los estados sucesores encontrados mientras no son explorados.


### Ejercicio 2


