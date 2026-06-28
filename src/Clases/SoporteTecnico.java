package Clases;

import TDA.ColaConPrioridad;

/**
 * Simula un sistema de soporte técnico que administra los problemas
 * reportados por los usuarios utilizando una Cola con Prioridad.
 *
 * Los usuarios Premium (prioridad = 1) tienen prioridad sobre los
 * usuarios Gratuitos (prioridad = 0).
 *
 * @param <T> Tipo de dato que representa un problema o incidencia.
 */
public class SoporteTecnico<T> {
    /**
     * Cola con prioridad que almacena los problemas reportados.
     */
    private ColaConPrioridad<T> quejas;

    /**
     * Crea un nuevo sistema de soporte técnico con una cola vacía.
     */
    public SoporteTecnico() {
        this.quejas = new ColaConPrioridad<>();
    }

    /**
     * Registra un nuevo problema en el sistema.
     *
     * Si el dato es nulo o la prioridad no es válida (solo se aceptan 0 o 1), el problema no será registrado.
     *
     * @param dato Problema reportado por el usuario.
     * @param prioridadUser Prioridad del usuario:
     *   0 = Usuario Gratuito
     *   1 = Usuario Premium
     */
    public void reportarProblema(T dato, int prioridadUser){
        if (dato == null || prioridadUser < 0 || prioridadUser > 1){
            System.out.println("No hemos podido ver su problema, por favor comuniquenos el problema y al lado su tipo de usuario. Gracias");
            return;
        }
        quejas.insert(dato, prioridadUser);
    }

    /**
     * Extrae el problema con mayor prioridad de la cola y simula
     * su resolución mediante una barra de progreso.
     *
     * @throws InterruptedException Si el hilo es interrumpido durante
     *                              la simulación del proceso.
     */
    public void arreglarProblema() throws InterruptedException {
        T problema = quejas.extractMax();

        System.out.print("\nArreglando problema");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }
        System.out.println();
        for (int i = 0; i <= 20; i++) {
            System.out.print("\r[");

            for (int j = 0; j < i; j++) {
                System.out.print("=");
            }

            for (int j = i; j < 20; j++) {
                System.out.print(" ");
            }

            System.out.print("] " + (i * 5) + "%");

            Thread.sleep(100);
        }

        System.out.println("\nProblema solucionado: " + problema);
    }

    /**
     * Obtiene el próximo problema que será atendido sin eliminarlo
     * de la cola.
     *
     * @return El problema con mayor prioridad o null
     *         si la cola está vacía.
     */
    public T proxProblema(){
        return quejas.peek();
    }

    @Override
    public String toString() {
        return "SoporteTecnico{" +
                "quejas=" + quejas +
                '}';
    }
}
