package TDA;

import Interfaces.iAbb;

public class Abb<T extends Comparable<T>> implements iAbb<T>{
    private NodoABB<T> raiz;

    private NodoABB<T> insertar (NodoABB<T> nodo, T dato) {
        if(nodo == null){
            nodo = new NodoABB<>(dato); // encontre el lugar
        }
        int cmp = dato.compareTo(nodo.dato);
        if(cmp < 0){
            nodo.izq = insertar(nodo.izq, dato); // va a la izquierda
        } else if(cmp > 0){
            nodo.der = insertar(nodo.der, dato); // va a la derecha
        }
        // si cmp == 0, ya existe, no insertamos duplicados
        return nodo;
    }


    @Override
    public void insertar(T dato) {
        raiz = insertar(raiz, dato);

    }


    @Override
    public T buscar(T dato) {
        return null;
    }

    @Override
    public void eliminar(T dato) {

    }

    @Override
    public void inorden() {

    }

    @Override
    public boolean esVacio() {
        return false;
    }
}
