package TDA;

import Interfaces.iArbolGenerico;

import java.util.LinkedList;
import java.util.Queue;

/*para entenderlo lo mejor es pensar es carpetad o directorios y subcarpetas.
tenemos distintas carpetas basadas en egeneros musicales: carpeta de ROCK, POP, REGGAETON, ETC.
cada carpeta puede tener subcarpetas: ROCK -> NACIONAL
                                      POP -> LATINO
                                      REGGEATON -> VIEJO
Las "carpetas" son NODOS y cada "subcarpeta" son LISTAS DE NODOS.
EJEMPLO, NodoRock -> [NodoNacional, NodoInternacional,...]

Ejemplo de jerarquía:
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


public class ArbolGenerico <T> implements iArbolGenerico <T>{

    private NodoNario<T> raiz;

    public ArbolGenerico() {
        this.raiz = null;
    }

    @Override
    public void agregarHijo(T datoPadre, T datoHijo) {
        /*
        PRE:
            datoPadre: recibe un datoPadre, como bie
         */

        if (esVacio()){
            this.raiz = new NodoNario<>(datoPadre);
            this.raiz.hijos.add(new NodoNario<>(datoHijo));
        }

        // prevenir duplicados
        if (buscarNodo(raiz, datoHijo) != null){ //se fija en toda la raiz si el dato hijo ya existe, si no da nula no hagas mas nada.
            return;
        }
        // buscamos el nodo del padre en todo el arbol empezando desde la raiz.
        NodoNario<T> nodoPadreEncontrado = buscarNodo(this.raiz, datoPadre);

        // si lo encontramos le agregamos el datoHijo a la lista del padre encontrado.
        if (nodoPadreEncontrado != null){
            nodoPadreEncontrado.hijos.add(new NodoNario<>(datoHijo));
        } else {
            System.out.println("El genero "+ nodoPadreEncontrado + "no existe.");
        }
    }

    private NodoNario<T> buscarNodo(NodoNario<T> nodoBuscar, T datoBuscar){
        if (nodoBuscar == null) return null;
        if (nodoBuscar.dato != null && nodoBuscar.dato.equals(datoBuscar)) return nodoBuscar;

        for (NodoNario<T> hijo : nodoBuscar.hijos){
            NodoNario<T> nodoEncontrado = buscarNodo(hijo, datoBuscar);
            if (nodoEncontrado != null) return nodoEncontrado;
        }
        return null;
    }
    @Override
    public void recorridoProfundidad() {
        /*
        el recorrido a profundidad va hasta al fondo de la rama.
         */
        imprimirProfundidad(this.raiz);
        System.out.println();
    }

    private void imprimirProfundidad(NodoNario<T> nodo) {
        /*
        PRE:
            nodo: recibe el nodo a imprimir y recorrer.
         */
        if (nodo == null) return;

        System.out.print(nodo.dato + " -> "); //imprime el dato del nodo que se le paso

        for (NodoNario<T> hijo : nodo.hijos) { //verificamos si ese nodo tiene hijos.
            imprimirProfundidad(hijo); //si los tiene llamamos de nuevo a la funcion y lo imprimimos.
        }
    }

    @Override
    public void recorridoAmplitud() {
        /*
        Este recorrido se realiza de nivel por nivel. Esto quiere decir que el primero que se agrego
        es el primero que se mostrara (FIFO)
         */
        if (esVacio()) return;

        //agrego una cola auxiliar para ir guardando los niveles
        Queue<NodoNario<T>> cola = new LinkedList<>();
        //primero agregamos la raiz.
        cola.add(this.raiz);

        while (!cola.isEmpty()) {
            //el bucle termina cuando la cola esta vacia.

            NodoNario<T> actual = cola.poll();//guardamos el primer elemento de la raiz.
            System.out.print(actual.dato + " -> ");

            for (NodoNario<T> hijo : actual.hijos) {
                cola.add(hijo); // si tiene hijos los agrega al final de la cola
            }
        }
        System.out.println();
    }

    @Override
    public boolean esVacio() {
        return this.raiz == null || this.raiz.dato == null;
    }
}
