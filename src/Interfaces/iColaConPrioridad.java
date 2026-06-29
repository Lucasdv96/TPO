package Interfaces;

/**
 * si es premium tiene prioridad, si es gratuito siempre va ir despues.
 */
public interface iColaConPrioridad<T> {
    //Inserta un elemento con una prioridad numérica.
    void insert(T dato, int prioridad);

    //Elimina y retorna el elemento de mayor prioridad.
    T extractMax();

    //Retorna el elemento de mayor prioridad sin eliminarlo.
    T peek();

    boolean isEmpty();
}
