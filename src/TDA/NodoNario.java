package TDA;

import java.util.ArrayList;
import java.util.List;
// esta clase va a guardar las "cajitas" que guardan el genero -> "ROCK", "POP", "LATINO"
public class NodoNario <T> {
    public T dato; // en este dato vamos a guardar el nombre del genero.
    public List<NodoNario<T>> hijos; //y en la lista de hijos vendrian a ser todos sus subgeneros de ese genero.

    public NodoNario( T dato) {
        // y bueno cada vez que se crea un Nodo (GENERO) va a tener su lista de subgeneros vacia.
        this.hijos = new ArrayList<>();
        this.dato = dato;
    }
}
