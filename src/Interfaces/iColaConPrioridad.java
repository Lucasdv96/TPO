package Interfaces;

/**
 * Este es el que puse en la clase que me propuso clauio, la cola va a decidir quien tiene prioridad
 * si es premium tiene prioridad, si sos un seco como toto no,
 */
public interface iColaConPrioridad<T> {
    //Inserta un elemento con una prioridad numérica.
    void insert(T dato, int prioridad);

    //Elimina y retorna el elemento de mayor prioridad.
    T extractMax();

    //Retorna el elemento de mayor prioridad sin eliminarlo.
    T peek();

    //🎻
    boolean isEmpty();
}
