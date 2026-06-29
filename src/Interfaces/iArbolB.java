package Interfaces;


public interface iArbolB<T extends Comparable<T>> {
    //Inserta una clave en el Árbol B.
    void insertar(T clave);

    //Busca una clave en el Árbol B.
    boolean buscar(T clave);

    //Muestra el contenido del árbol nivel por nivel.
    void mostrar();

    //Te dice que el arbol esta vacio
    boolean esVacio();
}
