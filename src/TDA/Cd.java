package TDA;

import Interfaces.iCd;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementación del Diccionario usando HashMap interno.
 * Uso: StreamDictionary — streamId (String) -> Stream activo.
 * Complejidad: O(1) amortizado para put/get/remove/contains.
 * Espacio: O(n) donde n = cantidad de streams activos.
 */

//aca lo mismo, me lo hizo claudio despues me lo acomodo bien
public class Cd<K, V> implements iCd<K, V> {

    private HashMap<K, V> mapa;

    public Cd() {
        this.mapa = new HashMap<>();
    }

    @Override
    public void put(K clave, V valor) {
        mapa.put(clave, valor);
    }

    @Override
    public V get(K clave) {
        return mapa.get(clave);
    }

    @Override
    public void remove(K clave) {
        mapa.remove(clave);
    }

    @Override
    public boolean contains(K clave) {
        return mapa.containsKey(clave);
    }

    @Override
    public int size() {
        return mapa.size();
    }

    @Override
    public boolean isEmpty() {
        return mapa.isEmpty();
    }

    /**
     * Retorna todas las claves. Útil para listar streams activos.
     */
    public Set<K> claves() {
        return mapa.keySet();
    }

    /**
     * Muestra todo el contenido del diccionario por consola.
     */
    public void mostrar() {
        if (mapa.isEmpty()) {
            System.out.println("  (diccionario vacío)");
            return;
        }
        for (Map.Entry<K, V> entry : mapa.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}
