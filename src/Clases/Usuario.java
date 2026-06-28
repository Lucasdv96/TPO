package Clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un usuario de la plataforma.
 * Usado como elemento en: AVL (índice por ID), una opcion para el grafo pueden ser los amigos tambien.
 * Tiene su propia Pila de navegación y Cola de reproducción instanciadas en Main.
 */

public class Usuario implements Comparable<Usuario> {

    public Integer getPrioridad() {
    }

    // le pase el codigo a claudio para ver si estaba bien y me agrego esto, si les gusta lo sacamos y si no vuela
    public enum TipoCuenta { GRATUITO, PREMIUM }

    private int id;
    private String nombre;
    private String email;
    private TipoCuenta tipoCuenta;
    private List<Playlist> playlists;
    private boolean activo;      // true = sesión iniciada (usado en AVL de activos)

    public Usuario(String nombre, String email, TipoCuenta tipoCuenta) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.tipoCuenta = tipoCuenta;
        this.playlists = new ArrayList<>();
        this.activo = false;
    }

    public void agregarPlaylist(Playlist p) {
        playlists.add(p);
    }

    // Comparable por id — usado en AVL
    @Override
    public int compareTo(Usuario otro) {
        return Integer.compare(this.id, otro.id);
    }

    @Override
    public String toString() {
        return String.format("[ID:%d] %s (%s) - %s", id, nombre, email, tipoCuenta);
    }

    // Getters / Setters
    public int getId()                  { return id; }
    public String getNombre()           { return nombre; }
    public String getEmail()            { return email; }
    public TipoCuenta getTipoCuenta()   { return tipoCuenta; }
    public List<Playlist> getPlaylists(){ return playlists; }
    public boolean isActivo()           { return activo; }
    public void setActivo(boolean b)    { this.activo = b; }
}
