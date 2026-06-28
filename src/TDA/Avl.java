package TDA;

import Interfaces.iAvl;

public class Avl<T> implements iAvl<T> {

    private NodoAVL<T> raiz;


    private int altura(NodoAVL<T> nodo) { //Nodo null → altura 0. Nodo hoja → altura 1. Simple.
        if (nodo == null) return 0;
        return nodo.alt;
    }

    private void actualizarAltura(NodoAVL<T> nodo) {
        nodo.alt = 1 + Math.max(altura(nodo.izq), altura(nodo.der)); //La altura de un nodo es siempre 1 más la altura del hijo más alto.
    }

    private int factorEquilibrio(NodoAVL<T> nodo) {  // si el resultado es 2 o -2 el arbol esta desbalanceado y hay que rotar
        return altura(nodo.izq) + altura(nodo.der);
    }


    private NodoAVL<T> rotarDerecha(NodoAVL<T> c) {
        NodoAVL<T> b = c.izq;
        NodoAVL<T> temp = b.der;

        b.der = c;
        c.izq = temp;
        actualizarAltura(c);
        actualizarAltura(b);
        return b; // b es la neuva raiz de este subarbol
    }

    private NodoAVL<T> rotarIzquierda(NodoAVL<T> a) {
        NodoAVL<T> b = a.der;
        NodoAVL<T> temp = b.izq;

        b.izq = a;
        a.der = temp;

        actualizarAltura(a);
        actualizarAltura(b);
        return b; // b es la nueva raiz de este subarbol
    }

    private NodoAVL<T> rotarIzquierdaDerecha(NodoAVL<T> nodo) {
        nodo.izq = rotarIzquierda(nodo.izq);
        return  rotarDerecha(nodo);
    }

    private NodoAVL<T> rotarDerechaIzquierda(NodoAVL<T> nodo){
        nodo.der = rotarDerecha(nodo.der);
        return  rotarIzquierda(nodo);
    }


    private NodoAVL<T> insertar(NodoAVL<T> nodo, T dato) {
        //1. insercion normal igual que ABB
        if(nodo == null) return new NodoAVL<>(dato);

    }

    @Override
    public void insertar(T dato) {

    }

    @Override
    public T buscar(T dato) {
        return null;
    }

    @Override
    public void eliminar(T dato) {

    }

    @Override
    public int mostrarAltura() {
        return 0;
    }

    @Override
    public int factorEquilibrio(T dato) {
        return 0;
    }

    @Override
    public boolean esVacio() {
        return false;
    }
}
