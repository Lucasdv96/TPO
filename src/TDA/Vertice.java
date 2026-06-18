package TDA;

import java.util.ArrayList;
import java.util.List;

public class Vertice <T> {
    T dato;
    List<Arista<T>> vecinos;

    public Vertice(T dato) {
        this.dato = dato;
        this.vecinos = new ArrayList<>();
    }

}
