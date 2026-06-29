package Interfaces;

/**
 * Interfaz para la Pila (LIFO).
 * Uso en el sistema: historial de navegación del usuario con respecto a cada cancion visitada. Estas se apilan
 * LIFO (Last in first out)
 * Cada pantalla visitada se apila; "atrás" hace pop.
 */
public interface iPila<T> {

    //Apila un elemento
    void push(T dato);

    //Desapila y retorna el elemento del tope.
    T pop();

    //Retorna el elemento del tope sin desapilarlo.
    T peek();


    boolean isEmpty();
}
