package TDA;

import Interfaces.iGrafo;

import java.util.*;

public class Grafo <T> implements iGrafo<T> {
    private Map<T, Vertice<T>> vertices;

    public Grafo() {
        this.vertices = new HashMap<>();
    }

    @Override
    public void agregarVertice(T dato) {
        if (dato == null) return;
        vertices.putIfAbsent(dato, new Vertice<>(dato));
    }

    @Override
    public void agregarArista(T v1, T v2, int peso) {
        Vertice<T> dato1 = vertices.get(v1);
        Vertice<T> dato2 = vertices.get(v2);

        dato1.vecinos.add(new Arista<>(dato2, peso));
        dato2.vecinos.add(new Arista<>(dato1, peso));
    }

    @Override
    public List<T> BFS(T origen) {
        // Lista en orden de los visitados
        List<T> resultado = new ArrayList<>();
        Vertice<T> inicio = vertices.get(origen);

        if (inicio == null) return resultado;// Si no existe retorna null para no romper
        Set<T> visitados = new HashSet<>();  // Set para marcar qué vértices ya fueron visitados (evita repeticiones)

        Cola<Vertice<T>> cola = new Cola<>();
        cola.enqueue(inicio);
        visitados.add(inicio.dato);

        // Mientras queden vértices pendientes de procesar...
        while (!cola.isEmpty()) {

            // saca el vértice que está al frente de la cola y lo guarda en la variable actual
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
    @Override
    public List<T> DFS(T origen) {
        List<T> resultado = new ArrayList<>();
        Vertice<T> inicio = vertices.get(origen);
        if (inicio == null) return resultado;

        Set<T> visitados = new HashSet<>();
        dfsRecursivo(inicio, visitados, resultado);
        return resultado;
    }

    private void dfsRecursivo(Vertice<T> actual, Set<T> visitados, List<T> resultado) {
        visitados.add(actual.dato);
        resultado.add(actual.dato);
        for (Arista<T> arista : actual.vecinos) {
            if (!visitados.contains(arista.destino.dato)) {
                dfsRecursivo(arista.destino, visitados, resultado);
            }
        }
    }

    @Override
    public T vecinoMenorPeso(T origen) {
        Vertice<T> inicio = vertices.get(origen);
        if (inicio == null || inicio.vecinos.isEmpty()) return null;

        Arista<T> menor = null;
        for (Arista<T> arista : inicio.vecinos) {
            if (menor == null || arista.peso < menor.peso) {
                menor = arista;
            }
        }
        return menor.destino.dato;
    }

    @Override
    public boolean esVacio() {
        return vertices.isEmpty();
    }
}