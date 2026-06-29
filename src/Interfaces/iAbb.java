package Interfaces;

/**
 * Interfaz para el Árbol Binario de Búsqueda (ABB).
 * Complejidad promedio: O(log n) | Peor caso (degenerado): O(n)
 */
public interface iAbb<T extends Comparable<T>> {

    //Inserta un nuevo elemento en el ABB
    void insertar(T dato);

    //Busca un elemento en el ABB
    T buscar(T dato);

    //Elimina un elemento del ABB.
    void eliminar(T dato);

    //Recorre el árbol en orden (izq - raíz - der).
    void inorden();

    //Indica si el árbol está vacío.
    boolean esVacio();
}
