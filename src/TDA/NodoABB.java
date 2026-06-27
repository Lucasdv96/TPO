package TDA;

public class NodoABB <T>{
    T dato;
    NodoABB<T> izq;
    NodoABB<T> der;

    NodoABB(T dato){
        this.dato = dato;
        this.izq =  null;
        this.der = null;

    }
}
