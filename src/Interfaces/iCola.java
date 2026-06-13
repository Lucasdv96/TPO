package Interfaces;

/**
 * Interfaz para la Cola (FIFO).
 * Uso en el sistema: playlist en reproducción actual del usuario.
 * La canción que entra primero es la primera en reproducirse.
 * Complejidad: O(1) para todas las operaciones.
 */

// haceme la cola profe 😁😁😁
public interface iCola<T> {

    // Encola el dato que quieras agregar
    void enqueue(T dato);

    // Desencola el primer dato agregado y lo retorna
    T dequeue();

    // Devuelve el dato que esta al frente de la Cola
    T front();

    boolean isEmpty();

//Toto comentalo vos tirame la goma vos sabes, you know baby
}
