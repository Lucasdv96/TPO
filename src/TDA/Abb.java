package TDA;

import Interfaces.iAbb;

public class Abb<T extends Comparable<T>> implements iAbb<T>{
    private NodoABB<T> raiz;

    private NodoABB<T> insertar (NodoABB<T> nodo, T dato) {
        if(nodo == null) return new NodoABB<>(dato);
        int cmp = dato.compareTo(nodo.dato);
        if(cmp < 0){
            nodo.izq = insertar(nodo.izq, dato); // va a la izquierda
        } else if(cmp > 0){
            nodo.der = insertar(nodo.der, dato); // va a la derecha
        }
        // si cmp == 0, ya existe, no insertamos duplicados
        return nodo;
    }


    private void inorden (NodoABB<T> nodo){
        if(nodo == null){
            return;
        }
        inorden(nodo.izq);
        System.out.println(nodo.dato);
        inorden(nodo.der);
    }

    private T  buscar (NodoABB<T> nodo, T dato){
        if(nodo == null){
            return null;
        }
        int cmp = nodo.dato.compareTo(dato); // compara nodo contra dato
        if(cmp < 0) return buscar(nodo.der, dato); // si nodo < dato
        else if(cmp > 0){
            return buscar(nodo.izq, dato);
        }
        return nodo.dato;
    }

    private NodoABB<T> eliminar (NodoABB<T> nodo, T dato){
        if(nodo == null){
            return null;
        }
        //PRIMERO COMPARAMOS
        int cmp = nodo.dato.compareTo(dato);

        if(cmp < 0){
            nodo.izq = eliminar(nodo.izq, dato);
        }
        else if(cmp > 0){
            nodo.der = eliminar(nodo.der, dato);
        }
        else{
            // ENCONTRE EL NODO A ELIMINAR, TIENE 3 CASOS:
            // Caso 1: no tiene hijos
            if (nodo.izq == null && nodo.der == null) return null;

            // Caso 2a: solo tiene hijo derecho
            if (nodo.izq == null) return nodo.der;

            // Caso 2b: solo tiene hijo izquierdo
            if (nodo.der == null) return nodo.izq;

            // Caso 3: tiene dos hijos — busco el sucesor inorden
            NodoABB<T> sucesor = minimoNodo(nodo.der);  // mínimo del subárbol derecho
            nodo.dato = sucesor.dato;                    // copio el valor del sucesor
            nodo.der = eliminar(nodo.der, sucesor.dato); // elimino el sucesor del subárbol derecho

        }

        return  nodo;
    }

    // metodo auxuiliar que encuentra el nodo a la izquierda
    private NodoABB<T> minimoNodo(NodoABB<T> nodo){
        while(nodo.izq != null){
            nodo = nodo.izq;
        }
        return nodo;
    }

    @Override
    public void insertar(T dato) {
        raiz = insertar(raiz, dato);

    }


    @Override
    public T buscar(T dato) {
        return buscar(raiz, dato);

    }

    @Override
    public void eliminar(T dato) {
        raiz = eliminar(raiz, dato);
    }

    @Override
    public void inorden() {
        inorden(raiz);
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }




}
