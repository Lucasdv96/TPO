package TDA;

import Interfaces.iAvl;

public class Avl<T  extends Comparable<T>> implements iAvl<T> {

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
        // 1. Inserción normal igual que ABB
        if (nodo == null) return new NodoAVL<>(dato);

        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) nodo.izq = insertar(nodo.izq, dato);
        else if (cmp > 0) nodo.der = insertar(nodo.der, dato);
        else return nodo; // duplicado, no insertamos

        // 2. Actualizar altura
        actualizarAltura(nodo);

        // 3. Calcular factor de equilibrio
        int fe = factorEquilibrio(nodo);

        // 4. Aplicar rotación si hace falta
        if (fe == 2 && factorEquilibrio(nodo.izq) >= 0)  return rotarDerecha(nodo);
        if (fe == 2 && factorEquilibrio(nodo.izq) < 0)   return rotarIzquierdaDerecha(nodo);
        if (fe == -2 && factorEquilibrio(nodo.der) <= 0)  return rotarIzquierda(nodo);
        if (fe == -2 && factorEquilibrio(nodo.der) > 0)   return rotarDerechaIzquierda(nodo);

        return nodo;
    }

    private T  buscar (NodoAVL<T> nodo, T dato){
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

    private NodoAVL<T> buscarNodo(NodoAVL<T> nodo, T dato) {
        if (nodo == null) return null;
        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) return buscarNodo(nodo.izq, dato);
        if (cmp > 0) return buscarNodo(nodo.der, dato);
        return nodo;
    }

    private NodoAVL<T> eliminar(NodoAVL<T> nodo, T dato) {
        // 1.eliminacion normal
        if(nodo == null) return null;

        int cmp = dato.compareTo(nodo.dato);
        if(cmp < 0) nodo.izq = eliminar(nodo.izq, dato);
        else if(cmp > 0) nodo.der =  eliminar(nodo.der, dato);
        else{
            // caso 1 no tiene hijos
            if(nodo.izq == null && nodo.der == null) return null;
            // caso 2 solo hijo derecho
            if(nodo.izq == null) return nodo.der;
            //caso 2b solo hijo izquierdo
            if(nodo.der == null) return nodo.izq;

            // caso 3: 2 hijos - suceor inorder
            NodoAVL<T> sucesor = minimoNodo(nodo.der);
            nodo.dato = sucesor.dato;
            nodo.der = eliminar(nodo.der, sucesor.dato);

        }

        //2. actualiazr altura
        actualizarAltura(nodo);

        //3. factor de equilibrio y rotaciones
        int fe = factorEquilibrio(nodo);
        if(fe == 2 && factorEquilibrio(nodo.izq) >= 0) return rotarDerecha(nodo);
        if(fe == 2 && factorEquilibrio(nodo.izq) < 0) return rotarIzquierdaDerecha(nodo);
        if(fe == -2 && factorEquilibrio(nodo.der) <= 0)  return rotarIzquierda(nodo);
        if(fe == -2 && factorEquilibrio(nodo.der) > 0)  return rotarDerechaIzquierda(nodo);

        return nodo;
    }

    private NodoAVL<T> minimoNodo(NodoAVL<T> nodo) {
        while (nodo.izq != null) nodo = nodo.izq;
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
    public int mostrarAltura() {
        return altura(raiz);
    }

    @Override
    public int factorEquilibrio(T dato) {
        NodoAVL<T> nodo = buscarNodo(raiz, dato);
        if(nodo == null) return 0;
        return factorEquilibrio(nodo);
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }
}
