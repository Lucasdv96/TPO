package TDA;

import Interfaces.iGrafo;

import java.util.*;

/**
 * Implementación genérica de un Grafo no dirigido utilizando
 * listas de adyacencia.
 *
 * Permite agregar vértices y aristas con peso, además de realizar
 * recorridos BFS y DFS sobre el grafo.
 *
 * @param <T> Tipo de dato almacenado en cada vértice.
 */
public class Grafo<T> implements iGrafo<T> {

    /**
     * Colección de vértices del grafo.
     *
     * La clave representa el dato almacenado y el valor
     * el vértice correspondiente.
     */
    private Map<T, Vertice<T>> vertices;

    /**
     * Crea un grafo vacío.
     */
    public Grafo() {
        this.vertices = new HashMap<>();
    }

    /**
     * Agrega un nuevo vértice al grafo.
     *
     * Si el vértice ya existe, no se agrega nuevamente.
     *
     * @param dato Dato que representará el nuevo vértice.
     */
    @Override
    public void agregarVertice(T dato) {
        if (dato == null) return;
        vertices.putIfAbsent(dato, new Vertice<>(dato));
    }

    /**
     * Agrega una arista entre dos vértices existentes.
     *
     * Al tratarse de un grafo no dirigido, ambos vértices
     * se agregan mutuamente como vecinos.
     *
     * @param v1 Primer vértice.
     * @param v2 Segundo vértice.
     * @param peso Peso asociado a la arista.
     */
    @Override
    public void agregarArista(T v1, T v2, int peso) {
        Vertice<T> dato1 = vertices.get(v1);
        Vertice<T> dato2 = vertices.get(v2);

        if (dato1 == null || dato2 == null) {
            System.out.println("Error: ambos vértices deben existir.");
            return;
        }

        dato1.vecinos.add(new Arista<>(dato2, peso));
        dato2.vecinos.add(new Arista<>(dato1, peso));


    }

    /**
     * Realiza un recorrido Breadth First Search (BFS)
     * comenzando desde el vértice indicado.
     *
     * El recorrido visita los vértices por niveles utilizando
     * una Cola.
     *
     * @param origen Vértice desde donde comienza el recorrido.
     * @return Lista con el orden en que fueron visitados los vértices.
     */
    @Override
    public List<T> BFS(T origen) {

        List<T> resultado = new ArrayList<>();
        Vertice<T> inicio = vertices.get(origen);

        if (inicio == null)
            return resultado;

        Set<T> visitados = new HashSet<>();
        Cola<Vertice<T>> cola = new Cola<>();

        cola.enqueue(inicio);
        visitados.add(inicio.dato);

        while (!cola.isEmpty()) {

            Vertice<T> actual = cola.dequeue();
            resultado.add(actual.dato);

            for (Arista<T> arista : actual.vecinos) {

                if (!visitados.contains(arista.destino.dato)) {

                    visitados.add(arista.destino.dato);
                    cola.enqueue(arista.destino);

                }

            }

        }

        return resultado;
    }

    /**
     * Realiza un recorrido Depth First Search (DFS)
     * comenzando desde el vértice indicado.
     *
     * El recorrido se implementa mediante recursividad,
     * utilizando implícitamente la pila de llamadas.
     *
     * @param origen Vértice desde donde comienza el recorrido.
     * @return Lista con el orden en que fueron visitados los vértices.
     */
    @Override
    public List<T> DFS(T origen) {

        List<T> resultado = new ArrayList<>();
        Vertice<T> inicio = vertices.get(origen);

        if (inicio == null)
            return resultado;

        Set<T> visitados = new HashSet<>();

        dfsRecursivo(inicio, visitados, resultado);

        return resultado;
    }

    /**
     * Método auxiliar utilizado por DFS para recorrer
     * recursivamente el grafo.
     *
     * @param actual Vértice que se está procesando.
     * @param visitados Conjunto de vértices ya recorridos.
     * @param resultado Lista con el recorrido realizado.
     */
    private void dfsRecursivo(Vertice<T> actual,
                              Set<T> visitados,
                              List<T> resultado) {

        visitados.add(actual.dato);
        resultado.add(actual.dato);

        for (Arista<T> arista : actual.vecinos) {

            if (!visitados.contains(arista.destino.dato)) {

                dfsRecursivo(arista.destino, visitados, resultado);

            }

        }

    }

    /**
     * Obtiene el vecino conectado mediante la arista
     * de menor peso.
     *
     * @param origen Vértice de origen.
     * @return Dato del vecino con menor peso o {@code null}
     * si el vértice no existe o no posee vecinos.
     */
    @Override
    public T vecinoMenorPeso(T origen) {

        Vertice<T> inicio = vertices.get(origen);

        if (inicio == null || inicio.vecinos.isEmpty())
            return null;

        Arista<T> menor = null;

        for (Arista<T> arista : inicio.vecinos) {

            if (menor == null || arista.peso < menor.peso) {

                menor = arista;

            }

        }

        return menor.destino.dato;
    }

    /**
     * Indica si el grafo contiene vértices.
     *
     * @return {@code true} si el grafo está vacío,
     * {@code false} en caso contrario.
     */
    @Override
    public boolean esVacio() {
        return vertices.isEmpty();
    }

    public Map<T, Vertice<T>> getVertices() {
        return vertices;
    }
}