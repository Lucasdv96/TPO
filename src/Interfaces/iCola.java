package Interfaces;

/**
 * Interfaz para la Cola (FIFO).
 * Uso en el sistema: playlist en reproducción actual del usuario.
 * La canción que entra primero es la primera en reproducirse.
 * Complejidad: O(1) para todas las operaciones.
 */

// haceme la cola profe 😁😁😁
public interface iCola<T> {

    void enqueue(T dato);

    T dequeue();

    T front();

    boolean isEmpty();

//Toto comentalo vos tirame la goma vos sabes, you know baby
}
