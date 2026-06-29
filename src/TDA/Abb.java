package TDA;

import Interfaces.iAbb;

/**
 * Implementación del Árbol Binario de Búsqueda (ABB).
 * Uso en el sistema: catálogo de canales/creadores de contenido ordenados alfabéticamente por username.
 * Complejidad promedio: O(log n) | Peor caso (árbol degenerado): O(n)
 * Justificación: se usa ABB y no AVL porque el catálogo de canales es relativamente estático
 * (pocas inserciones/eliminaciones), por lo que el costo extra del balanceo automático no se justifica.
 */

public class Abb<T extends Comparable<T>> implements iAbb<T>{
    private NodoABB<T> raiz;  // raíz del árbol, null si está vacío



    private NodoABB<T> insertar (NodoABB<T> nodo, T dato) {
        if(nodo == null) return new NodoABB<>(dato); // encontré el lugar vacío
        int cmp = dato.compareTo(nodo.dato);
        if(cmp < 0){
            nodo.izq = insertar(nodo.izq, dato); // va a la izquierda
        } else if(cmp > 0){
            nodo.der = insertar(nodo.der, dato); // va a la derecha
        }
        // si cmp == 0, ya existe, no insertamos duplicados
        return nodo;
    }
    /**
     * Recorre el árbol en orden (izquierda → raíz → derecha) de forma recursiva.
     * Produce los elementos en orden ascendente según compareTo.
     * O(n) — visita todos los nodos exactamente una vez.
     */

    private void inorden (NodoABB<T> nodo){
        if(nodo == null){
            return;
        }
        inorden(nodo.izq);
        System.out.println(nodo.dato);
        inorden(nodo.der);
    }

    /**
     * Busca un dato en el ABB de forma recursiva.
     * Mismo recorrido que insertar pero sin modificar el árbol.
     * @return el dato si existe, null si no se encuentra
     */

    private T  buscar (NodoABB<T> nodo, T dato){
        if(nodo == null){ // no existe en el arbol
            return null;
        }
        int cmp = nodo.dato.compareTo(dato); // compara nodo contra dato
        if(cmp < 0) return buscar(nodo.der, dato); // el nodo es menor, busco a la derecha
        else if(cmp > 0){                          // el nodo es mayor, busco a la izquierda
            return buscar(nodo.izq, dato);
        }
        return nodo.dato; // encontrado
    }
    /**
     * Elimina un dato del ABB de forma recursiva.
     * Maneja tres casos:
     *   Caso 1 — nodo hoja (sin hijos): se elimina directamente retornando null.
     *   Caso 2 — un solo hijo: el hijo sube y ocupa el lugar del nodo eliminado.
     *   Caso 3 — dos hijos: se reemplaza con el sucesor inorden (mínimo del subárbol derecho)
     *            y se elimina el sucesor de su posición original.
     */
    private NodoABB<T> eliminar (NodoABB<T> nodo, T dato){
        if(nodo == null){  //no existe el dato
            return null;
        }
        //PRIMERO COMPARAMOS
        int cmp = dato.compareTo(nodo.dato);

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

    /**
     * Retorna el nodo con el valor mínimo dentro de un subárbol.
     * Siempre es el nodo más a la izquierda.
     * Usado en la eliminación con dos hijos para encontrar el sucesor inorden.
     */

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
