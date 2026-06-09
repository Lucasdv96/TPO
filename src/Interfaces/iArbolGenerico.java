package Interfaces;

/**
 * Aca va a ordenar y dar jerarquia a los generos, en la clase esta dibujado.
 */
public interface iArbolGenerico<T> {

    //Agrega un pibe(hijo) que tiene los datos del padre (re machista java)
    void agregarHijo(T datopadre, T datoHijo);

    //Visita primera la Raiz para despues recursivamente cada arbol
    void recorridoProfundidad();

    /**
     * Recorrido en amplitud (BFS / por niveles).
     * Visita todos los nodos nivel por nivel. O(n)
     */
    //Este lo copie de claudio no lo entendi
    void recorridoAmplitud();

    //🤡
    boolean esVacio();
}
