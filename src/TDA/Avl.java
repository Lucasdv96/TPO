package TDA;

import Interfaces.iAvl;

/**
 * Implementación del Árbol AVL (Árbol Binario de Búsqueda Autobalanceado).
 * Uso en el sistema: índice de usuarios activos (sesiones) ordenados por ID numérico.
 * Complejidad garantizada: O(log n) para insertar, buscar y eliminar en todos los casos.
 * Justificación: los usuarios se conectan y desconectan constantemente (inserciones y
 * eliminaciones masivas). Un ABB podría degenerarse a O(n). El AVL garantiza siempre
 * O(log n) mediante rotaciones automáticas que mantienen el árbol balanceado.
 */

public class Avl<T  extends Comparable<T>> implements iAvl<T> {

    private NodoAVL<T> raiz;  // raíz del árbol, null si está vacío


    /**
     * Retorna la altura de un nodo.
     * Si el nodo es null retorna 0 para evitar NullPointerException.
     */
    private int altura(NodoAVL<T> nodo) {
        if (nodo == null) return 0;
        return nodo.alt;
    }

    /**
     * Actualiza la altura de un nodo basándose en la altura de sus hijos.
     * La altura es 1 + la altura del hijo más alto.
     */
    private void actualizarAltura(NodoAVL<T> nodo) {
        nodo.alt = 1 + Math.max(altura(nodo.izq), altura(nodo.der)); //La altura de un nodo es siempre 1 más la altura del hijo más alto.
    }

    /**
     * Calcula el factor de equilibrio de un nodo.
     * Factor = altura(izq) - altura(der)
     * Si está entre -1 y 1: balanceado.
     * Si es 2: subárbol izquierdo demasiado alto → rotar derecha o izquierda-derecha.
     * Si es -2: subárbol derecho demasiado alto → rotar izquierda o derecha-izquierda.
     */
    private int factorEquilibrio(NodoAVL<T> nodo) {  // si el resultado es 2 o -2 el arbol esta desbalanceado y hay que rotar
        return altura(nodo.izq) + altura(nodo.der);
    }


    /**
     * Rotación simple a la derecha.
     * Se aplica cuando el desbalance es hacia la izquierda en línea recta (factor = 2
     * y el hijo izquierdo tiene factor >= 0).
     * El hijo izquierdo (b) sube y el nodo actual (c) baja a la derecha de b.
     */
    private NodoAVL<T> rotarDerecha(NodoAVL<T> c) {
        NodoAVL<T> b = c.izq;
        NodoAVL<T> temp = b.der;

        b.der = c;
        c.izq = temp;
        actualizarAltura(c);
        actualizarAltura(b);
        return b; // b es la neuva raiz de este subarbol
    }

    /**
     * Rotación simple a la izquierda.
     * Espejo de rotarDerecha. Se aplica cuando el desbalance es hacia la derecha
     * en línea recta (factor = -2 y el hijo derecho tiene factor <= 0).
     */
    private NodoAVL<T> rotarIzquierda(NodoAVL<T> a) {
        NodoAVL<T> b = a.der;
        NodoAVL<T> temp = b.izq;

        b.izq = a;
        a.der = temp;

        actualizarAltura(a);
        actualizarAltura(b);
        return b; // b es la nueva raiz de este subarbol
    }
    /**
     * Rotación doble izquierda-derecha.
     * Se aplica cuando el desbalance es en zigzag hacia la izquierda
     * (factor = 2 y el hijo izquierdo tiene factor < 0).
     * Primero rota izquierda sobre el hijo, después derecha sobre el nodo.
     */

    private NodoAVL<T> rotarIzquierdaDerecha(NodoAVL<T> nodo) {
        nodo.izq = rotarIzquierda(nodo.izq);
        return  rotarDerecha(nodo);
    }

    /**
     * Rotación doble derecha-izquierda.
     * Espejo de izquierda-derecha. Se aplica cuando el desbalance es en zigzag
     * hacia la derecha (factor = -2 y el hijo derecho tiene factor > 0).
     * Primero rota derecha sobre el hijo, después izquierda sobre el nodo.
     */

    private NodoAVL<T> rotarDerechaIzquierda(NodoAVL<T> nodo){
        nodo.der = rotarDerecha(nodo.der);
        return  rotarIzquierda(nodo);
    }

    /**
     * Inserta un dato en el AVL de forma recursiva.
     * Igual que el ABB, pero al volver de la recursión:
     *   1. Actualiza la altura del nodo.
     *   2. Calcula el factor de equilibrio.
     *   3. Aplica la rotación correspondiente si el árbol se desbalanceó.
     */
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
        // Rotación derecha simple — desbalance izquierda en línea recta
        if (fe == 2 && factorEquilibrio(nodo.izq) >= 0)  return rotarDerecha(nodo);
        // Rotación izquierda-derecha — desbalance izquierda en zigzag
        if (fe == 2 && factorEquilibrio(nodo.izq) < 0)   return rotarIzquierdaDerecha(nodo);
        // Rotación izquierda simple — desbalance derecha en línea recta
        if (fe == -2 && factorEquilibrio(nodo.der) <= 0) return rotarIzquierda(nodo);
        // Rotación derecha-izquierda — desbalance derecha en zigzag
        if (fe == -2 && factorEquilibrio(nodo.der) > 0)  return rotarDerechaIzquierda(nodo);

        return nodo;
    }

    /**
     * Busca un dato en el AVL. Mismo algoritmo que el ABB.
     * @return el dato si existe, null si no se encuentra
     */

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

    /**
     * Busca y retorna el nodo que contiene el dato.
     * Usado internamente por factorEquilibrio(T dato).
     */

    private NodoAVL<T> buscarNodo(NodoAVL<T> nodo, T dato) {
        if (nodo == null) return null;
        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) return buscarNodo(nodo.izq, dato);
        if (cmp > 0) return buscarNodo(nodo.der, dato);
        return nodo;
    }

    /**
     * Elimina un dato del AVL de forma recursiva.
     * Mismos tres casos que el ABB, pero al volver de la recursión
     * actualiza la altura y aplica rotaciones si el árbol se desbalanceó.
     */
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
    /**
     * Retorna el nodo con el valor mínimo de un subárbol.
     * Siempre es el nodo más a la izquierda.
     * Usado en la eliminación con dos hijos.
     */

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
