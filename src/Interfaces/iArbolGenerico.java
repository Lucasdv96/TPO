package Interfaces;

/**
 * Aca va a ordenar y dar jerarquia a los generos, en la clase esta dibujado.
 */
public interface iArbolGenerico<T> {

    //Agrega un hijo que tiene los datos del padre
    void agregarHijo(T datopadre, T datoHijo);

    //Visita primera la Raiz para despues recursivamente cada arbol
    void recorridoProfundidad();

    /**
     * Recorrido en amplitud (BFS / por niveles).
     * Visita todos los nodos nivel por nivel. O(n)
     */
    void recorridoAmplitud();

    boolean esVacio();
}
