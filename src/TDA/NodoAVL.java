package TDA;

public class NodoAVL<T> {
    T dato;
    NodoAVL<T> izq;
    NodoAVL<T> der;
    int alt;

    NodoAVL(T dato){
        this.dato = dato;
        this.izq = null;
        this.der = null;
        this.alt = 1; // todo nodo nuevo empieza con altura 1
    }

}
