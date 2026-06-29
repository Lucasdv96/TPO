package Clases;

public class Servidor implements Comparable<Servidor> {

    private String id;
    private String ciudad;

    public Servidor(String id, String ciudad) {
        this.id = id;
        this.ciudad = ciudad;
    }

    public String getId()     { return id; }
    public String getCiudad() { return ciudad; }

    @Override
    public int compareTo(Servidor otro) {
        // Ordenamiento alfabético por ID (ej: "BA" < "MX")
        return this.id.compareTo(otro.id);
    }

    @Override
    public String toString() {
        return "Servidor[" + id + " - " + ciudad + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servidor)) return false;
        Servidor s = (Servidor) o;
        return this.id.equals(s.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}