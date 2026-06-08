package Interfaces;

public interface iColaConPrioridad <T>{
    void encolar(T elemento, int prioridad);

    T desencolar();

    T frente();

    boolean estaVacia();

    boolean estaLlena();

    int cantidad();
}

