package Clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una playlist de un usuario.
 * Las canciones dentro se encolan en la Cola de reproducción al reproducir.
 */

//Si tienen una idea mejor a la de simular una "cola de reproduccion" me avisan, sono muy violento, pero les juro que lo decia bien

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

    //aca me cogio el cerebro el profe, me agarro disociando aca me ayudo claudio
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
