package Interfaces;

/**
El diccionario se usa para ver si el usuario tiene un plan gratuito o  premiun
 y asi determinar su prioridad
 1 premium 0 gratuito
 premium tiene mas prioridad.
 */
public interface iDiccionario<K, V> {

    /**
     * Asocia una clave con un valor. O(1) amortizado
     * Si la clave ya existe, actualiza el valor.
     */
    void put(K clave, V valor);

    /**
     * Retorna el valor asociado a la clave. O(1) amortizado
     * @return el valor, o null si no existe
     */
    V get(K clave);

    /**
     * Elimina la entrada con esa clave. O(1) amortizado
     */
    void remove(K clave);

    /**
     * Indica si la clave existe en el diccionario.
     */
    boolean contains(K clave);

    /**
     * Retorna la cantidad de entradas.
     */
    int size();

    /**
     * Indica si el diccionario está vacío.
     */
    boolean isEmpty();
}
