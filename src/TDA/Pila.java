package TDA;

import Interfaces.iPila;

public class Pila<T> implements iPila<T> {
    private Nodo<T> tope;

    public Pila() {
        this.tope = null;
    }
    /**
     * Se agrega el dato al tope de la pila
     * en caso de que sea nulo el dato directamente no agrega nada y se va a la casa
     *
     * @param dato elemento a apilar
     * */
    @Override
    public void push(T dato) {
        if(dato == null) return;
        Nodo<T> nuevo = new Nodo<>(dato);
        if(isEmpty()){
            tope = nuevo;
            return;
        }
        nuevo.sig = tope;
        tope = nuevo;
    }

    /**
     * Se saca el ultimo dato agregado, se retorna y se borra de chill
     * en caso de que este vacia te tira corte que haces flaquito
     *
     * @return Ultimo dato agregado o null si esta vacia
     * */
    @Override
    public T pop() {
        if(isEmpty()){
            System.out.println("Error: no hay elementos para retirar");
            return null;
        }
        T retirado = tope.dato;
        tope = tope.sig;
        return retirado;
    }

    /**
     * Devuelve el ultimo dato agregado sin eliminarlo
     *
     * @return Ultimo elemento agregado o null si esta vacia
     * */
    @Override
    public T peek() {
        if(isEmpty()) return null;
        return tope.dato;
    }
    /**
     * se fija si esta vacia la pila, no es mucha ciencia
     *
     * @return true si esta vacia o false en caso contrario
     * */
    @Override
    public boolean isEmpty() {
        return tope==null;
    }
}
