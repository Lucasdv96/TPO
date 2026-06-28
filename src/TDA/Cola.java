package TDA;

import Interfaces.iCola;

/**
 * Implementación dinámica y genérica de una Cola (FIFO).
 *
 * El primer elemento que ingresa a la cola es el primero en salir.
 * Complejidad: O(1) para todas las operaciones.
 *
 * @param <T> Tipo de dato almacenado en la cola.
 */
public class Cola<T> implements iCola<T> {
    /**
     * Primer nodo de la cola.
     */
    private Nodo<T> frente;
    /**
     * Ultimo nodo de la cola.
     */
    private Nodo<T> fin;

    /**
     * Crea una cola vacía.
     */
    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    /**
     * Agrega un nuevo elemento al final de la cola.
     *
     * @param dato Elemento que se desea encolar.
     */
    @Override
    public void enqueue(T dato) {
        if (dato == null) return;
        if(frente == null){
            frente = new Nodo<>(dato);
            fin = frente;
            return;
        }
        fin.sig = new Nodo<>(dato);
        fin = fin.sig;
    }

    /**
     * Elimina y devuelve el primer elemento de la cola.
     *
     * @return Elemento ubicado al frente de la cola o null
     * si la cola está vacía.
     */
    @Override
    public T dequeue() {
        if(isEmpty()){
            System.out.println("Error: no hay elementos para desencolar");
            return null;
        }
        T desencolado = frente.dato;
        frente = frente.sig;
        if(frente == null) fin = frente;
        return desencolado;
    }

    /**
     * Devuelve el primer elemento de la cola sin eliminarlo.
     *
     * @return Elemento ubicado al frente o null
     * si la cola está vacía.
     */
    @Override
    public T front() {
        if(isEmpty()) return null;
        return frente.dato;
    }

    /**
     * se fija si esta vacia la cola, no es mucha ciencia
     *
     * @return true si esta vacia o false en caso contrario
     * */
    @Override
    public boolean isEmpty() {
        return frente==null;
    }
}
