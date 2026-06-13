package TDA;

public class NodoPrioridad<T> {
    T dato;
    int prioridad;
    NodoPrioridad<T> sig;

    public NodoPrioridad(T dato, int prioridad) {
        this.dato = dato;
        this.prioridad = prioridad;
        this.sig = null;
    }
}
