package Interfaces;

/**
 * Interfaz para el Árbol Binario de Búsqueda (ABB).
 * Uesto seria el catalogo de creadores de contenido ordenados por username (alfabético).
 * Complejidad promedio: O(log n) | Peor caso (degenerado): O(n)
 * en el tercer reglon del comentario tiro magia claudio, no soy quien para cuestionarlo
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
