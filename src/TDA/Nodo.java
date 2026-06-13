package TDA;

/**
 * Aca creamos el nodo que seria el tope y marca el siguiente
 * AL inicial queda como dato = null y sig = null
 * */
public class Nodo<T> {
    T dato;
    Nodo<T> sig;

    public Nodo(T dato) {
        this.dato = dato;
        this.sig = null;
    }
}
