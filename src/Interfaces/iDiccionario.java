package Interfaces;

/**
 * Uso en el sistema: StreamDictionary — mapa de streamId -> estado del stream.
 * Permite acceso O(1) promedio a cualquier stream activo por su ID.
 * Justificación: HashMap interno, O(1) amortizado para put/get/remove.
 * Este es la mia despues lo hago, lo dejo asi para ya pushear
 * Le pedi a claude que lo comente todo, despues cuando vaya a usarlo le edito las cosas 😈
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
