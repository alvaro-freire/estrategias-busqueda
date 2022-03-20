## Memoria Prácticas - Estrategias-Búsqueda

### Ejercicio 1

Hemos creado una clase Nodo con los atributos `estado` (Estado), `padre` (Nodo) y `accion` (Accion).


En el método `soluciona()` vamos creando nuevos nodos a partir del inicial (sin padre ni acción, simplemente un estado) siempre indicando al nodo padre. De esta forma, cuando la función encuentra un nodo meta, el método `reconstruyeSol()` simplemente tiene que ir introducir los nodos en una lista desde el nodo meta hasta el nodo inicial y devolver la lista en orden inverso, para que el primer nodo sea el nodo inicial y el último sea la meta.


Para la clase `EstrategiaBúsquedaGrafo` hemos añadido una frontera (cola), que soluciona el problema de `Estrategia4`, en la que era posible que estados por los que se había transitado previamente todavía tuvieran sucesores sin explorar. Para ello, la cola almacena los estados sucesores encontrados mientras no son explorados.


### Ejercicio 2

#### Apartado A)

Hemos definido un estado con los atributos `cuadrado` como matriz con las casillas a completar
y `n` que describe la dimensión del cuadrado `NxN`. Las acciones que hemos concluido para un
estado se basan en, para cada casilla, colocar el menor número posible.

Ejemplo:

Estado:  
>[4, 9, 2]  
[3, 5, 7]  
[0, 0, 0]

Acciones:  
>[4, 9, 2] -- [4, 9, 2] -- [4, 9, 2]  
[3, 5, 7] -- [3, 5, 7] -- [3, 5, 7]  
[1, 0, 0] -- [0, 1, 0] -- [0, 0, 1]  

Se han implementado también dos estrategias de `búsqueda no informada`:
`EstrategiaBusquedaAnchura` y `EstrategiaBusquedaProfundidad`.

La idea principal de la búsqueda en anchura consiste en visitar todos 
los nodos que hay a profundidad `i` antes de pasar a visitar aquellos 
que hay a profundidad `i+1`. Es decir, tras visitar un nodo, pasamos 
a visitar a sus hermanos antes que a sus hijos.

En cambio, la búsqueda en profundidad consiste en ir expandiendo todos 
y cada uno de los nodos que va localizando, de forma recurrente, en un 
camino concreto. Cuando ya no quedan más nodos que visitar en dicho 
camino, regresa, de modo que repite el mismo proceso con cada uno de 
los hermanos del nodo ya procesado.

Para este caso, la búsqueda en anchura es completa (suponiendo un número
finito de estados los llega a expandir todos) y óptima (siempre encuentra
la solución que requiere menos pasos). En el caso de la búsqueda en 
profundidad, esta no ofrece una solución óptima, ya que puede encontrar la
meta en el nivel más bajo cuando esta podría estar en uno superior.

Por lo tanto, la estrategia más adecuada es la búsqueda en anchura.

#### Apartado B)

Hemos creado una función heurística con valores que oscilan entre 0 y 1,
siendo 0 la heurísitica más lejana a la meta y 1 la más cercana. Para 
ello, puesto que las acciones se basan en rellenar casillas vacías y no
se pueden sobreescribir, cuando un estado tiene algún valor incorrecto 
como por ejemplo:

> [9, 8, 0]  
[0, 0, 0]  
[0, 0, 0]  

Una vez se comprueba que el estado no es incorrecto, se comienza a
"penalizar" el valor de retorno en función de las casillas vacías
que haya en cada fila. De esta forma, cuantas más casillas rellenas
haya, mayor será el valor de la función heurística y más cerca estaremos
de la meta.

La heurística es admisible ya que nunca va a sobreestimar el coste real
de alcanzar la meta, y consistente, ya que su estimación es siempre menor 
o igual a la distancia estimada desde cualquier vértice vecino a la meta, 
más el costo de alcanzarla.

<!-- LICENSE -->
### License

Distributed under the MIT License. See `LICENSE` for more information.
