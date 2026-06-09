package TDA;

import Interfaces.iCola;
import Interfaces.iColaConPrioridad;

public class ColaConPrioridad <T> implements iColaConPrioridad {
    @Override
    public void encolar(Object elemento, int prioridad) {

    }

    @Override
    public Object desencolar() {
        return null;
    }

    @Override
    public Object frente() {
        return null;
    }

    @Override
    public boolean estaVacia() {
        return false;
    }

    @Override
    public boolean estaLlena() {
        return false;
    }

    @Override
    public int cantidad() {
        return 0;
    }

    private static class Nodo <T> {
        T dato;
        Nodo <T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo <T> frente;
    private Nodo <T> fin;
    private int tamanio;


    public ColaConPrioridad() {
        this.frente=null;
        this.fin = null;
        this.tamanio =0;
    }
}
