package Clases;

/**
 * Representa un nodo en el Árbol Genérico de géneros.
 * Ejemplo de jerarquía:
 *   Música
 *   ├── Rock
 *   │   ├── Nacional
 *   │   │   └── 80s
 *   │   └── Internacional
 *   ├── Pop
 *   └── Electrónica
 *       └── House
 *           └── Deep House
 */


// Este flujo y clase "categoria" la planteo claudio, pero banco, no es tanto mas y esta bueno, de ultima despues vemos
public class Categoria {

    private String nombre;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        return this.nombre.equalsIgnoreCase(((Categoria) o).nombre);
    }

    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }

    public String getNombre() { return nombre; }
}
