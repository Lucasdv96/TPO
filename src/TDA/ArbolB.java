package TDA;

import Interfaces.iArbolB;

import java.util.ArrayList;
import java.util.List;

public class ArbolB <T extends Comparable<T>> implements iArbolB <T>{

    private static class NodoB<T>{
        int n;
        List<T> claves;
        List<NodoB<T>> hijos;
        boolean esHoja;

        public NodoB(boolean esHoja) {
            this.n = 0;
            this.claves = new ArrayList<>();
            this.hijos = new ArrayList<>();
            this.esHoja = esHoja;
        }
    }

    private final int t;
    private NodoB<T> raiz;

    public ArbolB() {
        this.t = 2;
        this.raiz = new NodoB<>(true);
    }

    @Override
    public void insertar(T clave) {
        NodoB<T> r = this.raiz;

        if (r.claves.size() == (2 * t - 1)) {
            NodoB<T> s = new NodoB<>(false); // Nueva raíz provisional (nodo interno)
            this.raiz = s;
            s.hijos.add(r);

            dividirHijo(s, 0, r);
            insertarNoLleno(s, clave);
        } else {
            insertarNoLleno(r, clave);
        }

    }

    public void insertarNoLleno(NodoB<T> nodo, T clave){ // para ordenar las claves se empieza a recorrer la lista de atras hacia adelante :)
        /*
        Inserta las claves de manera ordenada mientras un Nodo no este lleno.
        Pre:
            - nodo: inserta el nodo donde se van a insertar las claves (2t)
            - clave: se inserta valor de tipo generico
         */
        int i = nodo.claves.size() - 1;

        if (nodo.esHoja){
            /*
        EJEMPLO: si tengo [20|30] y la clave nueva es: 25;
        empiezo a recorrer desde el 30 y comparo si 25 es menor a 30.
        Si es asi, desplazo el 30 a la derecha y lo "clono" -> [20|30|30].
        Y sigo recorriendo...
         */

            while (i >= 0 && clave.compareTo(nodo.claves.get(i)) < 0){
                nodo.claves.set(i + 1, nodo.claves.get(i));
                i--;
            }

        /*
        Aca se inserta el 25 en la lista de claves.
        [20|25|30] (lo que me costo entender esto chicos 😩)
        */
            nodo.claves.set(i + 1, clave);
            nodo.n++;

        } else{
                while (i >= 0 && clave.compareTo(nodo.claves.get(i)) < 0){
                    i--;
                }
                i++;
                NodoB<T> hijoAsociado = nodo.hijos.get(i);
                if (hijoAsociado.claves.size() == (2 * t - 1)) {
                    dividirHijo(nodo, i, hijoAsociado);

                    if (clave.compareTo(nodo.claves.get(i)) > 0) {
                        i++;
                    }
                }
                insertarNoLleno(nodo.hijos.get(i), clave);
        }
    }

    public void dividirHijo(NodoB<T> padre, int i, NodoB<T> hijoLleno) {
        /*
        Divide un nodo hijo que ha superado el limite de claves (Grado 3)
        PRE:
            - padre: el nodo superior que recibira la clave mediana.
            - i: El indice donde se encuentra el hijo lleno en la lista del padre.
            - hijoLleno: el nodo que se va a dividir.
         */

        if (padre == null || hijoLleno == null) { //esta parte se la pedi a la ia porque me dio paja hacer los throws jij.
            throw new IllegalArgumentException("Los nodos no pueden ser nulos.");
        }
        if (hijoLleno.claves.size() != 3) {
            throw new IllegalArgumentException("Precondición fallida: El hijo debe tener exactamente 3 claves para dividirse.");
        }
        if (padre.claves.size() >= 3) {
            throw new IllegalArgumentException("Precondición fallida: El padre está lleno y no puede recibir la mediana.");
        }
        if (i < 0 || i > padre.hijos.size()) {
            throw new IndexOutOfBoundsException("Índice i fuera de los límites de los hijos del padre.");
        }

        NodoB<T> hijoNuevo = new NodoB<>(hijoLleno.esHoja);

        T claveMasGrande = hijoLleno.claves.remove(2);
        hijoNuevo.claves.add(claveMasGrande); // este nodo es el que va a ir a la derecha del nuevo padre, ya que es el mayor del nodo lleno.
        hijoNuevo.n++;

        if (!hijoLleno.esHoja) { //si es hoja quiere decir que tiene 4 nodos hijos por ende, los ultimos dos (que son los mas grandes los tengo que pasar a el nuevo nodo derecho del nuevo padre) un chino
            hijoNuevo.hijos.add(hijoLleno.hijos.remove(2));
            hijoNuevo.hijos.add(hijoLleno.hijos.remove(2));
        }

        hijoLleno.n = 1;

        T claveDelMedio = hijoLleno.claves.remove(1);

        padre.claves.add(i, claveDelMedio);
        padre.n++;

        padre.hijos.add(i + 1, hijoNuevo); //como el padre solo apunta al hijoLleno que quedo, falta apuntar al hijo nuevo que quedo colgado.
    }

    @Override
    public boolean buscar(T clave) {
        return buscarRec(raiz, clave);
    }

    private boolean buscarRec(NodoB<T> nodo, T clave) {
        if (nodo == null) return false;

        int i = 0;
        while (i < nodo.claves.size() && clave.compareTo(nodo.claves.get(i)) > 0) {
            i++;
        }

        if (i < nodo.claves.size() && clave.compareTo(nodo.claves.get(i)) == 0) {
            return true;
        }

        if (nodo.esHoja) {
            return false;
        }
        return buscarRec(nodo.hijos.get(i), clave);
    }

    @Override
    public void mostrar() {
        //me queda pendiente esta wey.
    }

    @Override
    public boolean esVacio() {
        return raiz == null || (raiz.claves.isEmpty() && raiz.esHoja);
    }
}