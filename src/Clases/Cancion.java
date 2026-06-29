package Clases;

/**
 *  Representa a una unidad (cancion) en la plataforma de streaming
 */

public class Cancion implements Comparable<Cancion> {

    private int id;
    private String titulo;
    private String artista;
    private Categoria categoria;       // ej: "Rock", "Pop", "Electrónica", "rkt"
    private int duracionSeg;     // duración en segundos para que el mogolico de tobias no se queje
    private int reproducciones;  // usado para prioridad en ColaConPrioridad

    public Cancion(int id, String titulo, String artista, Categoria categoria, int duracionSeg) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.categoria = categoria;
        this.duracionSeg = duracionSeg;
        this.reproducciones = 0;
    }

    public void reproducir() {
        this.reproducciones++;
    }

    // Comparable por id — usado en ArbolB y ABB (P2 y P3 arreglense jijiji)
    @Override
    public int compareTo(Cancion otra) {
        return Integer.compare(this.id, otra.id);
    }

    @Override
    public String toString() {
        return String.format("[ID:%d] %s - %s (%s, %ds)", id, titulo, artista, categoria, duracionSeg);
    }

    // Getters
    public int getId()              { return id; }
    public String getTitulo()       { return titulo; }
    public String getArtista()      { return artista; }
    public Categoria getCategoria()       { return categoria; }
    public int getDuracionSeg()     { return duracionSeg; }
    public int getReproducciones()  { return reproducciones; }
}