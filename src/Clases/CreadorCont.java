package Clases;

/**
 * Esto es lo que hablamos de tipos y permisos de usuarios, por ejemplo este seria un usuario "admin" ->
 * -> o creador de contenido
 * Usado como elemento en: ABB (catálogo de canales ordenado por username).
 * Comparable por username alfabético — clave de búsqueda en el ABB.
 */
public class CreadorCont implements Comparable<CreadorCont> {

    private int id;
    private String username;     // clave de búsqueda en el ABB
    private String descripcion;
    private int suscriptores;

    public CreadorCont(int id, String username, String descripcion) {
        this.id = id;
        this.username = username;
        this.descripcion = descripcion;
        this.suscriptores = 0;
    }

    // Comparable por username alfabético — clave del ABB
    @Override
    public int compareTo(CreadorCont otro) {
        return this.username.compareToIgnoreCase(otro.username);
    }

    @Override
    public String toString() {
        return String.format("[ID:%d] @%s - %s (%d subs)", id, username, descripcion, suscriptores);
    }

    public int getId()              { return id; }
    public String getUsername()     { return username; }
    public String getDescripcion()  { return descripcion; }
    public int getSuscriptores()    { return suscriptores; }
    public void setSuscriptores(int n) { this.suscriptores = n; }
}
