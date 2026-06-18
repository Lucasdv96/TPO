package TDA;

public class Arista <T>{
    Vertice<T> destino;
    int peso;

    public Arista(Vertice<T> destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}
