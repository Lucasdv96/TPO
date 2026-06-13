package TDA;

import Interfaces.iColaConPrioridad;

public class ColaConPrioridad <T> implements iColaConPrioridad<T> {
    private NodoPrioridad<T> frente;
    private NodoPrioridad<T> fin;

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

    @Override
    public T peek() {
        if (isEmpty()) return null;

        return frente.dato;
    }

    @Override
    public boolean isEmpty() {
        return frente == null;
    }
}
