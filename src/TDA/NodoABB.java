package TDA;

public class NodoABB <T>{
    T dato;
    NodoABB<T> izq;  // hijo izquierdo — valores menores
    NodoABB<T> der;  // hijo derecho — valores mayores

    NodoABB(T dato){
        this.dato = dato;
        this.izq =  null;
        this.der = null;

    }
}
