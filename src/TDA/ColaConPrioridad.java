package TDA;

import Interfaces.iColaConPrioridad;

/**
 * Implementación dinámica y genérica de una Cola con Prioridad.
 *
 * Los elementos Premium (prioridad = 1) se ubican antes que los
 * Gratuitos (prioridad = 0), manteniendo el orden de llegada
 * entre elementos con la misma prioridad.
 *
 * @param <T> Tipo de dato almacenado en la cola.
 */
public class ColaConPrioridad <T> implements iColaConPrioridad<T> {
    /**
     * Primer elemento de la cola.
     */
    private NodoPrioridad<T> frente;
    /**
     * Ultimo elemento de la cola.
     */
    private NodoPrioridad<T> fin;

    /**
     * Crea una cola vacía.
     */
    public ColaConPrioridad() {
        this.frente = null;
        this.fin = null;
    }

    /**
     * Inserta un elemento según su prioridad.
     *
     * Si la prioridad es Premium (1), se coloca detrás del último
     * Premium existente. Si es Gratuito (0), se agrega al final
     * de la cola.
     *
     * @param dato Elemento a insertar.
     * @param prioridad Prioridad del elemento (0 = Gratuito, 1 = Premium).
     */
    @Override
    public void insert(T dato, int prioridad) {
        NodoPrioridad<T> anterior = null;
        NodoPrioridad<T> actual;

        NodoPrioridad<T> nuevo = new NodoPrioridad<>(dato, prioridad);
        if(isEmpty()){
            frente = nuevo;
            fin = frente;
            return;
        }
        actual = frente;
        if(prioridad == 1){
            while(actual != null && actual.prioridad == 1){
                anterior = actual;
                actual = anterior.sig;
            }
            if(anterior != null){
                nuevo.sig = actual;
                anterior.sig = nuevo;
                return;
            }
            nuevo.sig = frente;
            frente = nuevo;
        }else if(prioridad == 0){
            fin.sig = nuevo;
            fin = fin.sig;
        }else{
            System.out.println("Error: Prioridad invalida");
        }
    }

    /**
     * Elimina y devuelve el elemento con mayor prioridad.
     *
     * @return Elemento ubicado al frente de la cola o null si está vacia.
     */
    @Override
    public T extractMax() {
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
     * Devuelve el elemento que será atendido primero sin eliminarlo.
     *
     * @return Primer elemento de la cola o null si está vacia.
     */
    @Override
    public T peek() {
        if (isEmpty()) return null;

        return frente.dato;
    }

    /**
     * se fija si esta vacia la cola, no es mucha ciencia
     *
     * @return true si esta vacia o false en caso contrario
     * */
    @Override
    public boolean isEmpty() {
        return frente == null;
    }
}
