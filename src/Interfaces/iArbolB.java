package Interfaces;

/**
 * Interfaz para el Árbol B de orden t.
 * Uso en el sistema: catálogo histórico completo de canciones/videos.
 * Grado mínimo t=2 (árbol B-2) o t=3 según implementación de P3.
 * Complejidad: O(log n) para insertar y buscar, con nodos que almacenan múltiples claves.
 * Justificación: simula almacenamiento en disco donde cada nodo es una "página".
 * Si eso
 */


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
