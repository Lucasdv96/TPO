package Interfaces;

//índice de usuarios activos ordenados por ID numérico.
// los usuarios se insertan/eliminan seguido.
public interface iAvl<T extends Comparable<T>> {

    //Inserta un elemento y rebalancea si es necesario. nashee
    void insertar(T dato);

    //Busca un elemento.
    //@return el elemento si existe, null si no
    T buscar(T dato);

    //Elimina un elemento y rebalancea si es necesario.
    void eliminar(T dato);

    //Retorna la altura del árbol.
    //O(1) si se guarda como atributo
    int mostrarAltura();

    //Retorna el factor de equilibrio de un nodo dado su dato.
    //Factor = altura(subárbol izq) - altura(subárbol der)
    int factorEquilibrio(T dato);

    // ❤️
    boolean esVacio();
}
