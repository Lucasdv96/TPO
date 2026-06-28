package TDA;

import Interfaces.iDiccionario;
import java.util.HashMap;
import java.util.Set;

//Los comentarios los hizo claude xdd

/**
 * ============================================================
 * CLASE: Cd — Diccionario de usuarios y su tipo de cuenta
 * ============================================================
 *
 * Implementación del TDA Diccionario usando un HashMap interno.
 *
 * EN NUESTRO SISTEMA:
 * Almacena la relación entre el ID de un usuario y su nivel de acceso:
 *   - clave: idUsuario (Integer)
 *   - valor: prioridad (Integer) → 1 = PREMIUM, 0 = GRATUITO
 *
 * Ejemplo del contenido del diccionario:
 *   101 → 1   (Gabriel, PREMIUM)
 *   102 → 0   (Lucía, GRATUITO)
 *   103 → 1   (Matías, PREMIUM)
 *
 * USO EN CONSULTA C2:
 * Antes de encolar un usuario en la ColaConPrioridad, consultamos
 * el Diccionario para saber qué prioridad asignarle:
 *   cd.get(idUsuario) → 1 (PREMIUM) o 0 (GRATUITO)
 *
 * COMPLEJIDAD: O(1) amortizado para todas las operaciones.
 * ESPACIO: O(n) donde n = cantidad de usuarios registrados.
 */
public class Diccionario<K, V> implements iDiccionario<K, V> {

    // HashMap interno — estructura que hace el trabajo real
    private HashMap<K, V> mapa;

    /** Crea un diccionario vacío. */
    public Diccionario() {
        this.mapa = new HashMap<>();
    }

    /**
     * Registra o actualiza la prioridad de un usuario.
     * Si ya existe, actualiza. Si no, lo agrega.
     * Ejemplo: put(101, 1) → Gabriel es PREMIUM
     */
    @Override
    public void put(K clave, V valor) {
        mapa.put(clave, valor);
    }

    /**
     * Retorna la prioridad del usuario con ese ID.
     * Ejemplo: get(101) → 1 | get(102) → 0 | get(999) → null
     */
    @Override
    public V get(K clave) {
        return mapa.get(clave);
    }

    /**
     * Elimina un usuario del diccionario (ej: se da de baja).
     */
    @Override
    public void remove(K clave) {
        mapa.remove(clave);
    }

    /**
     * Verifica si un usuario está registrado.
     * Ejemplo: contains(101) → true | contains(999) → false
     */
    @Override
    public boolean contains(K clave) {
        return mapa.containsKey(clave);
    }

    /** Retorna la cantidad de usuarios registrados. */
    @Override
    public int size() {
        return mapa.size();
    }

    /** Indica si no hay usuarios registrados. */
    @Override
    public boolean isEmpty() {
        return mapa.isEmpty();
    }

    /** Retorna todos los IDs registrados. Útil para iterar. */
    public Set<K> claves() {
        return mapa.keySet();
    }

    /**
     * Muestra el contenido completo del diccionario.
     * Recorre las claves con un for-each simple y busca
     * el valor de cada una con get(). Sin Map.Entry.
     */
    public void mostrar() {
        if (mapa.isEmpty()) {
            System.out.println("  (diccionario vacío)");
            return;
        }
        System.out.println("  ID Usuario | Plan");
        System.out.println("  -----------+----------");
        // Recorremos las claves y buscamos su valor con get()
        for (K clave : mapa.keySet()) {
            V valor = mapa.get(clave);
            // Traducimos 1 → PREMIUM y 0 → GRATUITO para que sea legible
            String plan;
            if (valor instanceof Integer && (Integer) valor == 1) {
                plan = "PREMIUM";
            } else {
                plan = "GRATUITO";
            }
            System.out.println("  " + clave + "         | " + plan);
        }
    }

    /**
     * Cuenta cuántos usuarios tienen plan PREMIUM (valor == 1).
     * Recorre los valores con un for-each y suma manualmente.
     * O(n)
     */
    public int cantPremium() {
        int cantidad = 0;
        // Recorremos todos los valores del mapa
        for (V valor : mapa.values()) {
            if (valor instanceof Integer && (Integer) valor == 1) {
                cantidad = cantidad + 1;
            }
        }
        return cantidad;
    }

    /** Cuenta cuántos usuarios tienen plan GRATUITO (valor == 0). */
    public int cantGratuitos() {
        // Total menos los premium = los gratuitos
        return size() - cantPremium();
    }
}