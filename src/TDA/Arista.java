package TDA;


public class Arista <T>{
    public Vertice<T> destino;
    public int peso;

    public Arista(Vertice<T> destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}
