package Interfaces;

import java.util.List;

/**
 * Interfaz para el Grafo (dirigido o no dirigido, con pesos).
 * Uso en el sistema: red de servidores CDN con latencia como peso de arista.
 * Ejemplo: Servidor_BA --[20ms]--> Servidor_MX --[15ms]--> Servidor_NY
 * Complejidad BFS/DFS: O(V + E) donde V = vértices, E = aristas.
 */

//aca se lo pedi entero a claudio es lo de los servidores, yo digo que lo cambiemos, despues debatimos que hacemos con este.
public interface iGrafo<T> {

    /**
     * Agrega un vértice al grafo.
     * O(1) con lista de adyacencia
     */
    void agregarVertice(T dato);

    /**
     * Agrega una arista entre dos vértices con un peso (latencia en ms).
     * O(1) con lista de adyacencia
     */
    void agregarArista(T origen, T destino, int peso);

    /**
     * Recorrido BFS desde un vértice origen.
     * Retorna lista de vértices visitados en orden BFS. O(V + E)
     */
    List<T> BFS(T origen);

    /**
     * Recorrido DFS desde un vértice origen.
     * Retorna lista de vértices visitados en orden DFS. O(V + E)
     */
    List<T> DFS(T origen);

    /**
     * Retorna el vértice vecino con menor peso (menor latencia).
     * Usado en la consulta compleja C1.
     */
    T vecinoMenorPeso(T origen);

    /**
     * Indica si el grafo no tiene vértices.
     */
    boolean esVacio();
}
