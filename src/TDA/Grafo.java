package TDA;

import Interfaces.iGrafo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List BFS(T origen) {
        return List.of();
    }

    @Override
    public List DFS(T origen) {
        return List.of();
    }

    @Override
    public T vecinoMenorPeso(T origen) {
        return null;
    }

    @Override
    public boolean esVacio() {
        return false;
    }
}
