package TDA;
/**
 * Nodo interno del AVL.
 * A diferencia del NodoABB, almacena también la altura del subárbol que encabeza.
 * La altura se usa para calcular el factor de equilibrio y decidir si hay que rotar.
 */
public class NodoAVL<T> {
    T dato;
    NodoAVL<T> izq;    // hijo izquierdo
    NodoAVL<T> der;    // hijo derecho
    int alt;        // altura del subárbol con raíz en este nodo

    NodoAVL(T dato){
        this.dato = dato;
        this.izq = null;
        this.der = null;
        this.alt = 1; // todo nodo nuevo empieza con altura 1
    }

}
