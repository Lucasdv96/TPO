package TDA;

import Interfaces.iCola;

public class Cola<T> implements iCola<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

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

    @Override
    public T front() {
        if(isEmpty()) return null;
        return frente.dato;
    }

    @Override
    public boolean isEmpty() {
        return frente==null;
    }
}
