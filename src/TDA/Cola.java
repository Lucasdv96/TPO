package TDA;

public class Cola <T> {
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


    public Cola() {
        fin = null;
        tamanio = null;
        tamanio =0;
    }

}
