package Clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una playlist de un usuario.
 * Las canciones dentro se encolan en la Cola de reproducción al reproducir.
 */

public class Playlist {

    private int id;
    private String nombre;
    private List<Cancion> canciones;

    public Playlist(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    public void agregarCancion(Cancion c) {
        canciones.add(c);
    }


    public void eliminarCancion(int idCancion) {
        canciones.removeIf(c -> c.getId() == idCancion);
    }

    public int cantCanciones() {
        return canciones.size();
    }

    @Override
    public String toString() {
        return String.format("[ID:%d] %s (%d canciones)", id, nombre, canciones.size());
    }

    public int getId()                  { return id; }
    public String getNombre()           { return nombre; }
    public List<Cancion> getCanciones() { return canciones; }
}
