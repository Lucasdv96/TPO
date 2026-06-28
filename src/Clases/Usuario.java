package Clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario implements Comparable<Usuario> {

    public enum TipoCuenta { GRATUITO, PREMIUM }

    // ── Contador estático para autoincrementar IDs ────────────────────────────
    // "static" significa que es compartido por TODOS los usuarios,
    // no pertenece a uno solo. Cada vez que se crea un usuario, sube 1.
    // Arranca en 100 para que los IDs queden lindos (101, 102, 103... y asi)
    private static int contadorId = 100;

    private int id;
    private String nombre;
    private String email;
    private TipoCuenta tipoCuenta;
    private List<Playlist> playlists;
    private boolean activo;

    // ── Constructor CON id manual (para cargarDatosPrueba dentro del maim) ───────────────────
    public Usuario(int id, String nombre, String email, TipoCuenta tipoCuenta) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.tipoCuenta = tipoCuenta;
        this.playlists = new ArrayList<>();
        this.activo = false;

        // Si el id manual es mayor al contador, se actualiza el contador
        // para que el próximo autoincremental no repita un id ya usado
        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    // ── Constructor SIN id (lo asigna solo) ──────────────────────────────────
    // Este es el que vas a usar cuando el usuario se registra desde el menú
    public Usuario(String nombre, String email, TipoCuenta tipoCuenta) {
        this.id = contadorId;   // toma el valor actual del contador
        contadorId++;           // sube el contador para el próximo
        this.nombre = nombre;
        this.email = email;
        this.tipoCuenta = tipoCuenta;
        this.playlists = new ArrayList<>();
        this.activo = false;
    }

    public int getPrioridad() {
        if (tipoCuenta == TipoCuenta.PREMIUM) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int compareTo(Usuario otro) {
        return Integer.compare(this.id, otro.id);
    }

    @Override
    public String toString() {
        return String.format("[ID:%d] %s (%s) - %s", id, nombre, email, tipoCuenta);
    }

    public void agregarPlaylist(Playlist p) { playlists.add(p); }
    public int getId()                   { return id; }
    public String getNombre()            { return nombre; }
    public String getEmail()             { return email; }
    public TipoCuenta getTipoCuenta()    { return tipoCuenta; }
    public List<Playlist> getPlaylists() { return playlists; }
    public boolean isActivo()            { return activo; }
    public void setActivo(boolean b)     { this.activo = b; }
}