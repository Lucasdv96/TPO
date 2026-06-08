package Interfaces;

public interface iColaPriodad<T> {
    void encolar(T elemento, int prioridad);

    T desencolar();

    T frente();

    boolean estaVacia();

    boolean estaLlena();

    int cantidad();
}