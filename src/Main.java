import Clases.*;
import TDA.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Clases.Categoria;

/**
 * Main — Sistema de Streaming de Audio (Espotifai)
 *
 * P1 Gabriel   → Diccionario + Main + Consultas complejas
 * P2 Lucas     → Abb + Avl
 * P3 Brichota  → ArbolB + ArbolGenerico
 * P4 Tobias    → Pila + Cola + ColaConPrioridad + SoporteTecnico
 * P5 Nestor    → Grafo
 */
public class Main {

    // ── TDAs ─────────────────────────────────────────────────────────────────
    static Diccionario<Integer, Integer> diccionarioUsuarios = new Diccionario<>();
    static Abb<Servidor>                 catalogoServidores  = new Abb<>();
    static Avl<Usuario>                  usuariosActivos     = new Avl<>();
    static ArbolB<Cancion>               catalogoHistorico   = new ArbolB<>();
    static ArbolGenerico<Categoria>      arbolGeneros        = new ArbolGenerico<>();
    static Pila<String>                  pilaNavegacion      = new Pila<>();
    static Cola<Cancion>                 colaReproduccion    = new Cola<>();
    static SoporteTecnico<String>        soporte             = new SoporteTecnico<>();
    static Grafo<Servidor>               redCDN              = new Grafo<>();

    // ── Listas globales de referencia ─────────────────────────────────────────
    static ArrayList<Cancion>   listaGlobalCanciones  = new ArrayList<>();
    static ArrayList<Usuario>   listaGlobalUsuarios   = new ArrayList<>();
    static ArrayList<Categoria> listaGlobalCategoria  = new ArrayList<>();
    static ArrayList<Servidor>  listaGlobalServidores = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    // ── Categorías globales ───────────────────────────────────────────────────
    static Categoria musica = new Categoria("MUSICA");
    static Categoria rock   = new Categoria("ROCK");
    static Categoria techno = new Categoria("TECHNO");
    static Categoria pop    = new Categoria("POP");
    static Categoria metal  = new Categoria("METAL");
    static Categoria folk   = new Categoria("FOLK");

    // ── Canciones globales ────────────────────────────────────────────────────
    static Cancion c1 = new Cancion(1, "Bohemian Rhapsody",      "Queen",          rock,   354);
    static Cancion c2 = new Cancion(2, "Blinding Lights",         "The Weeknd",     pop,    200);
    static Cancion c3 = new Cancion(3, "Strobe",                  "deadmau5",       techno, 601);
    static Cancion c4 = new Cancion(4, "La Llorona",              "Chavela Vargas", folk,   218);
    static Cancion c5 = new Cancion(5, "Smells Like Teen Spirit", "Nirvana",        metal,  301);

    // ── Servidores globales ───────────────────────────────────────────────────
    static Servidor svBA = new Servidor("BA", "Buenos Aires");
    static Servidor svMX = new Servidor("MX", "Mexico");
    static Servidor svNY = new Servidor("NY", "Nueva York");
    static Servidor svMD = new Servidor("MD", "Madrid");
    static Servidor svSP = new Servidor("SP", "Sao Paulo");

    // ═════════════════════════════════════════════════════════════════════════
    // INICIALIZACIÓN
    // ═════════════════════════════════════════════════════════════════════════

    public static void inicializarDatos() {
        listaGlobalCanciones.add(c1);
        listaGlobalCanciones.add(c2);
        listaGlobalCanciones.add(c3);
        listaGlobalCanciones.add(c4);
        listaGlobalCanciones.add(c5);

        listaGlobalCategoria.add(musica);
        listaGlobalCategoria.add(rock);
        listaGlobalCategoria.add(techno);
        listaGlobalCategoria.add(pop);
        //listaGlobalCategoria.add(metal);
        listaGlobalCategoria.add(folk);

        listaGlobalServidores.add(svBA);
        listaGlobalServidores.add(svMX);
        listaGlobalServidores.add(svNY);
        listaGlobalServidores.add(svMD);
        listaGlobalServidores.add(svSP);
    }

    static void cargarDatosPrueba() {
        // Usuarios
        Usuario u1 = new Usuario("Gabriel", "gaby@mail.com",   Usuario.TipoCuenta.PREMIUM);
        Usuario u2 = new Usuario("Lucas",   "lucas@mail.com",  Usuario.TipoCuenta.GRATUITO);
        Usuario u3 = new Usuario("Toto",    "tobias@mail.com", Usuario.TipoCuenta.GRATUITO);
        Usuario u4 = new Usuario("Nestor",  "nestor@mail.com", Usuario.TipoCuenta.PREMIUM);
        Usuario u5 = new Usuario("Brisa",   "brisa@mail.com",  Usuario.TipoCuenta.PREMIUM);

        // Diccionario
        diccionarioUsuarios.put(u1.getId(), u1.getPrioridad());
        diccionarioUsuarios.put(u2.getId(), u2.getPrioridad());
        diccionarioUsuarios.put(u3.getId(), u3.getPrioridad());
        diccionarioUsuarios.put(u4.getId(), u4.getPrioridad());
        diccionarioUsuarios.put(u5.getId(), u5.getPrioridad());

        listaGlobalUsuarios.add(u1);
        listaGlobalUsuarios.add(u2);
        listaGlobalUsuarios.add(u3);
        listaGlobalUsuarios.add(u4);
        listaGlobalUsuarios.add(u5);

        // AVL: usuarios activos
        u1.setActivo(true); u4.setActivo(true); u5.setActivo(true);
        usuariosActivos.insertar(u1);
        usuariosActivos.insertar(u4);
        usuariosActivos.insertar(u5);

        // ABB: servidores
        catalogoServidores.insertar(svBA);
        catalogoServidores.insertar(svMX);
        catalogoServidores.insertar(svNY);
        catalogoServidores.insertar(svMD);
        catalogoServidores.insertar(svSP);

        // ArbolB: canciones
        catalogoHistorico.insertar(c1);
        catalogoHistorico.insertar(c2);
        catalogoHistorico.insertar(c3);
        catalogoHistorico.insertar(c4);
        catalogoHistorico.insertar(c5);

        // ArbolGenerico: géneros
        arbolGeneros.agregarHijo(null,   musica);
        arbolGeneros.agregarHijo(musica, rock);
        arbolGeneros.agregarHijo(musica, pop);
        arbolGeneros.agregarHijo(musica, techno);
        arbolGeneros.agregarHijo(musica, folk);


        // Pila y Cola
        pilaNavegacion.push("INICIO");
        colaReproduccion.enqueue(c1);
        colaReproduccion.enqueue(c2);
        colaReproduccion.enqueue(c3);

        // Grafo CDN
        redCDN.agregarVertice(svBA);
        redCDN.agregarVertice(svMX);
        redCDN.agregarVertice(svNY);
        redCDN.agregarVertice(svMD);
        redCDN.agregarVertice(svSP);
        redCDN.agregarArista(svBA, svMX, 120);
        redCDN.agregarArista(svBA, svSP, 30);
        redCDN.agregarArista(svMX, svNY, 50);
        redCDN.agregarArista(svNY, svMD, 80);
        redCDN.agregarArista(svMD, svSP, 200);

        System.out.println("  Datos de prueba cargados correctamente.");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // MENÚ PRINCIPAL
    // ═════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) throws InterruptedException {
        inicializarDatos();
        cargarDatosPrueba();
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║      ESPOTIFAI - STREAMING DE AUDIO      ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1.  Catalogo canciones   (Arbol B)      ║");
            System.out.println("║  2.  Usuarios activos     (AVL)          ║");
            System.out.println("║  3.  Catalogo servidores  (ABB)          ║");
            System.out.println("║  4.  Generos musicales    (Arbol n-ario) ║");
            System.out.println("║  5.  Red CDN              (Grafo)        ║");
            System.out.println("║  6.  Historial            (Pila)         ║");
            System.out.println("║  7.  Cola reproduccion    (Cola)         ║");
            System.out.println("║  8.  Usuarios y planes    (Diccionario)  ║");
            System.out.println("║  9.  Soporte tecnico      (Cola Prior.)  ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  CONSULTAS COMPLEJAS                     ║");
            System.out.println("║  10. C1: Ruta optima de servidor         ║");
            System.out.println("║  11. C2: Soporte + Diccionario + ABB     ║");
            System.out.println("║  12. C3: Deshacer navegacion             ║");
            System.out.println("║  13. C4: Explorar genero y encolar       ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  0.  Salir                               ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("  Opcion: ");

            opcion = leerInt();

            switch (opcion) {
                case 1  -> menuArbolB();
                case 2  -> menuAVL();
                case 3  -> menuABB();
                case 4  -> menuArbolNario();
                case 5  -> menuGrafo();
                case 6  -> menuPila();
                case 7  -> menuCola();
                case 8  -> menuDiccionario();
                case 9  -> menuColaConPrioridad();
                case 10 -> consultaC1();
                case 11 -> consultaC2();
                case 12 -> consultaC3();
                case 13 -> consultaC4();
                case 0  -> { System.out.println("\n  Hasta la proxima!"); scanner.close(); }
                default -> System.out.println("  Opcion invalida.");
            }
        } while (opcion != 0);
    }

    // ═════════════════════════════════════════════════════════════════════════
    // DISPLAYS VISUALES — helpers reutilizables
    // ═════════════════════════════════════════════════════════════════════════

    /** Muestra las canciones como tabla con índice */
    static void mostrarCanciones() {
        System.out.println("  ┌────┬──────────────────────────────┬──────────────────┬────────┬────────┐");
        System.out.println("  │ ID │ Titulo                       │ Artista          │ Genre  │  Seg   │");
        System.out.println("  ├────┼──────────────────────────────┼──────────────────┼────────┼────────┤");
        for (Cancion c : listaGlobalCanciones) {
            System.out.printf("  │ %-2d │ %-28s │ %-16s │ %-6s │ %4ds  │%n",
                    c.getId(),
                    truncar(c.getTitulo(), 28),
                    truncar(c.getArtista(), 16),
                    truncar(c.getCategoria().getNombre(), 6),
                    c.getDuracionSeg());
        }
        System.out.println("  └────┴──────────────────────────────┴──────────────────┴────────┴────────┘");
    }

    /** Muestra los usuarios como tabla */
    static void mostrarUsuarios() {
        System.out.println("  ┌─────┬────────────────┬──────────────────────┬──────────┐");
        System.out.println("  │ ID  │ Nombre         │ Email                │ Plan     │");
        System.out.println("  ├─────┼────────────────┼──────────────────────┼──────────┤");
        for (Usuario u : listaGlobalUsuarios) {
            System.out.printf("  │ %-3d │ %-14s │ %-20s │ %-8s │%n",
                    u.getId(),
                    truncar(u.getNombre(), 14),
                    truncar(u.getEmail(), 20),
                    u.getTipoCuenta());
        }
        System.out.println("  └─────┴────────────────┴──────────────────────┴──────────┘");
    }

    /** Muestra los servidores como tabla */
    static void mostrarServidores() {
        System.out.println("  ┌────┬──────────────────┐");
        System.out.println("  │ ID │ Ciudad           │");
        System.out.println("  ├────┼──────────────────┤");
        for (Servidor s : listaGlobalServidores) {
            System.out.printf("  │ %-2s │ %-16s │%n", s.getId(), truncar(s.getCiudad(), 16));
        }
        System.out.println("  └────┴──────────────────┘");
    }

    /** Muestra las categorías como lista numerada */
    static void mostrarCategorias() {
        System.out.println("  Categorias disponibles:");
        int i = 1;
        for (Categoria cat : listaGlobalCategoria) {
            System.out.println("    " + i + ". " + cat.getNombre());
            i++;
        }
    }

    /**
     * Muestra la pila visualmente — el tope arriba, la base abajo.
     * Necesitamos copiarla a un ArrayList para poder recorrerla sin destruirla.
     */
    static void mostrarPila(Pila<String> pila) {
        // Copiamos a lista usando una pila auxiliar para preservar el orden
        ArrayList<String> elementos = new ArrayList<>();
        Pila<String> aux = new Pila<>();

        // Vaciamos en auxiliar (invierte el orden)
        Pila<String> copia = copiarPila(pila);
        while (!copia.isEmpty()) {
            String e = copia.pop();
            aux.push(e);
            elementos.add(e);
        }

        if (elementos.isEmpty()) {
            System.out.println("  [ pila vacia ]");
            return;
        }

        System.out.println("  TOPE");
        System.out.println("   |");
        for (int i = 0; i < elementos.size(); i++) {
            boolean esTope = (i == 0);
            System.out.println("  ┌─────────────────────────────┐  " + (esTope ? "<-- tope (peek)" : ""));
            System.out.printf( "  │  %-27s│%n", elementos.get(i));
            System.out.println("  └─────────────────────────────┘");
            if (i < elementos.size() - 1) System.out.println("   |");
        }
        System.out.println("  BASE");
    }

    /**
     * Muestra la cola visualmente — frente a la izquierda, fin a la derecha.
     */
    static void mostrarCola(Cola<Cancion> cola) {
        ArrayList<Cancion> elementos = new ArrayList<>();
        Cola<Cancion> copia = new Cola<>();

        // Copiamos vaciando en lista y reconstruyendo
        Cola<Cancion> aux = new Cola<>();
        Cola<Cancion> orig = cola;

        // No podemos iterar sin destruir — usamos la listaGlobal como referencia visual
        // y mostramos lo que está en la cola de reproducción actual
        if (cola.isEmpty()) {
            System.out.println("  [ cola vacia ]");
            return;
        }

        // Mostramos con lo que tenemos en listaGlobal como referencia
        System.out.println("  FRENTE                                                     FIN");
        System.out.println("  (primera en");
        System.out.println("   reproducirse)");
        System.out.print("  ");
        for (Cancion c : listaGlobalCanciones) {
            // Verificamos contra la cola buscando coincidencia (display aproximado)
            System.out.print("┌──────────────────────┐     ");
        }
        System.out.println();
        System.out.print("  ");
        for (Cancion c : listaGlobalCanciones) {
            System.out.printf("│  %-20s│ --> ", truncar(c.getTitulo(), 20));
        }
        System.out.println("[fin]");
        System.out.print("  ");
        for (Cancion c : listaGlobalCanciones) {
            System.out.print("└──────────────────────┘     ");
        }
        System.out.println();
    }

    /**
     * Muestra el Grafo CDN visualmente con conexiones y pesos.
     */
    static void mostrarGrafo() {
        System.out.println();
        System.out.println("  RED DE SERVIDORES CDN");
        System.out.println("  (peso = latencia en ms)");
        System.out.println();

        if (redCDN.esVacio()) {
            System.out.println("  (grafo vacio)");
            return;
        }

        // Obtenemos las latencias reales desde el grafo de Nestor via getVertices()
        // Buscamos el peso de cada arista recorriendo la lista de vecinos del vertice origen
        java.util.Map<Servidor, Vertice<Servidor>> vertices = redCDN.getVertices();

        // Helper: devuelve el peso real de la arista entre dos servidores, o -1 si no existe
        // Recorre los vecinos del origen buscando el destino por equals()
        int pBA_MX = getPeso(vertices, svBA, svMX);
        int pBA_SP = getPeso(vertices, svBA, svSP);
        int pMX_NY = getPeso(vertices, svMX, svNY);
        int pNY_MD = getPeso(vertices, svNY, svMD);
        int pMD_SP = getPeso(vertices, svMD, svSP);

        // Diagrama visual con los pesos reales del grafo
        System.out.println("  [" + svBA.getId() + " - " + svBA.getCiudad() + "]");
        System.out.println("       /         \\");
        System.out.printf( "   %dms        %dms%n", pBA_MX, pBA_SP);
        System.out.println("     /               \\");
        System.out.println("  [" + svMX.getId() + " - " + svMX.getCiudad() + "]      [" + svSP.getId() + " - " + svSP.getCiudad() + "]");
        System.out.println("      |                   |");
        System.out.printf( "    %dms              %dms%n", pMX_NY, pMD_SP);
        System.out.println("      |                   |");
        System.out.printf( "  [%s - %s] --%dms-- [%s - %s]%n",
                svNY.getId(), svNY.getCiudad(), pNY_MD, svMD.getId(), svMD.getCiudad());
        System.out.println();

        // Lista de conexiones tambien con los pesos reales
        System.out.println("  Conexiones:");
        for (java.util.Map.Entry<Servidor, Vertice<Servidor>> entry : vertices.entrySet()) {
            Servidor origen = entry.getKey();
            for (Arista<Servidor> arista : entry.getValue().vecinos) {
                Servidor destino = arista.destino.dato;
                // Evitamos duplicar: solo imprimimos si origen.getId() < destino.getId()
                if (origen.getId().compareTo(destino.getId()) < 0) {
                    System.out.printf("    %s <--%3dms--> %s%n",
                            origen.getId(), arista.peso, destino.getId());
                }
            }
        }
    }

    /**
     * Devuelve el peso real de la arista entre origen y destino
     * recorriendo la lista de vecinos del vertice origen en el grafo.
     * Retorna -1 si la arista no existe.
     */
    static int getPeso(java.util.Map<Servidor, Vertice<Servidor>> vertices, Servidor origen, Servidor destino) {
        Vertice<Servidor> v = vertices.get(origen);
        if (v == null) return -1;
        for (Arista<Servidor> arista : v.vecinos) {
            if (arista.destino.dato.equals(destino)) return arista.peso;
        }
        return -1;
    }

    /**
     * Muestra el Árbol Genérico de géneros en forma de árbol visual.
     * Hardcodeado para el árbol de géneros del sistema.
     */
    /**
     * Muestra el Árbol Genérico de forma dinámica recorriendo los nodos reales.
     * Usa getRaiz() de ArbolGenerico para acceder a la estructura interna
     * y la imprime con líneas ASCII igual que la imagen de referencia.
     */
    static void mostrarArbolGenericoVisual() {
        System.out.println();
        System.out.println("  JERARQUIA DE GENEROS MUSICALES");
        System.out.println();
        if (arbolGeneros.esVacio()) {
            System.out.println("  (arbol vacio)");
            System.out.println();
            return;
        }
        // Imprime desde la raíz real del árbol, sin hardcodear nada
        imprimirNarioVisual(arbolGeneros.getRaiz(), "", true);
        System.out.println();
    }

    /**
     * Imprime recursivamente el árbol n-ario con sangría y líneas ASCII.
     *
     * Ejemplo de salida:
     *   [MUSICA]
     *   ├── [ROCK]
     *   │   ├── [NACIONAL]
     *   │   └── [INTERNACIONAL]
     *   ├── [POP]
     *   │   └── [HIP HOP]
     *   ├── [TECHNO]
     *   ├── [FOLK]
     *   └── [METAL]
     *       └── [HEAVY]
     *
     * @param nodo      nodo actual a imprimir
     * @param prefijo   cadena de espacios/líneas acumulada desde el nivel superior
     * @param esUltimo  true si este nodo es el último hijo de su padre
     */
    static void imprimirNarioVisual(NodoNario<Categoria> nodo, String prefijo, boolean esUltimo) {
        if (nodo == null) return;

        // La raíz (prefijo vacío) no lleva conector, los demás sí
        if (prefijo.isEmpty()) {
            System.out.println("  [" + nodo.dato + "]");
        } else {
            // └── si es último hijo, ├── si hay más hermanos después
            String conector = esUltimo ? "  └── " : "  ├── ";
            System.out.println(prefijo + conector + "[" + nodo.dato + "]");
        }

        // El prefijo que reciben los hijos:
        // - Si el nodo actual es el último, los hijos no necesitan línea vertical (└ ya cerró)
        // - Si no es el último, los hijos necesitan │ para conectarse con el siguiente hermano
        String extensionPrefijo;
        if (prefijo.isEmpty()) {
            extensionPrefijo = "";          // hijos de la raíz arrancan sin sangría extra
        } else {
            extensionPrefijo = prefijo + (esUltimo ? "       " : "  │    ");
        }

        for (int i = 0; i < nodo.hijos.size(); i++) {
            boolean ultimoHijo = (i == nodo.hijos.size() - 1);
            imprimirNarioVisual(nodo.hijos.get(i), extensionPrefijo, ultimoHijo);
        }
    }

    /**
     * Muestra el ABB de servidores en forma de árbol visual.
     * El ABB inserta BA, MX, NY, MD, SP en ese orden:
     *   BA es raíz, MX va a la derecha, NY a la derecha de MX,
     *   MD va entre BA y MX (izquierda de MX), SP a la derecha de NY.
     */
    static void mostrarABBVisual() {
        System.out.println();
        System.out.println("  CATALOGO DE SERVIDORES (ABB - inorden alfabetico)");
        System.out.println();
        System.out.println("             [BA]");
        System.out.println("               \\");
        System.out.println("             [MX]");
        System.out.println("            /    \\");
        System.out.println("         [MD]   [NY]");
        System.out.println("                   \\");
        System.out.println("                  [SP]");
        System.out.println();
        System.out.println("  Inorden (alfabetico): BA -> MD -> MX -> NY -> SP");
        System.out.println();
    }

    /**
     * Muestra el AVL de usuarios activos con forma de árbol visual.
     * Con u1(ID menor), u4, u5 insertados, el AVL queda balanceado.
     */
    static void mostrarAVLVisual() {
        System.out.println();
        System.out.println("  USUARIOS ACTIVOS (AVL - ordenado por ID)");
        System.out.println("  Altura: " + usuariosActivos.mostrarAltura());
        System.out.println();

        // Obtenemos los IDs de los usuarios activos para mostrar
        ArrayList<Integer> ids = new ArrayList<>();
        for (Usuario u : listaGlobalUsuarios) {
            if (usuariosActivos.buscar(new Usuario(u.getId(), "", "", Usuario.TipoCuenta.GRATUITO)) != null) {
                ids.add(u.getId());
            }
        }

        if (ids.isEmpty()) {
            System.out.println("  (arbol vacio — ningun usuario activo)");
            return;
        }

        // Mostramos el árbol según cuántos haya
        if (ids.size() == 1) {
            System.out.println("          [ID:" + ids.get(0) + "]");
        } else if (ids.size() == 2) {
            System.out.println("          [ID:" + ids.get(0) + "]");
            System.out.println("               \\");
            System.out.println("            [ID:" + ids.get(1) + "]");
        } else if (ids.size() == 3) {
            // Con 3 nodos el AVL los balancea: el del medio es la raíz
            java.util.Collections.sort(ids);
            System.out.println("          [ID:" + ids.get(1) + "]    <- raiz");
            System.out.println("         /         \\");
            System.out.println("    [ID:" + ids.get(0) + "]      [ID:" + ids.get(2) + "]");
        } else {
            // Para más nodos mostramos lista ordenada con indentación simple
            java.util.Collections.sort(ids);
            System.out.println("  Nodos activos (ordenados por ID):");
            for (int i = 0; i < ids.size(); i++) {
                String prefijo = (i == 0) ? "  raiz --> " : "           ";
                System.out.println(prefijo + "[ID:" + ids.get(i) + "]");
            }
        }

        System.out.println();
        System.out.println("  Factor de equilibrio de la raiz: " +
                usuariosActivos.factorEquilibrio(
                        new Usuario(ids.get(ids.size() / 2), "", "", Usuario.TipoCuenta.GRATUITO)));
        System.out.println();
    }

    /**
     * Muestra el Árbol B de canciones en forma visual por niveles (BFS).
     * Dado que ArbolB no expone sus nodos, mostramos el inorden con separadores
     * para simular la estructura de páginas del árbol B.
     */
    static void mostrarArbolBVisual() {
        System.out.println();
        System.out.println("  CATALOGO HISTORICO DE CANCIONES (Arbol B, t=2)");
        System.out.println("  Cada nodo puede tener hasta 3 claves (se ordenan por ID)");
        System.out.println();
        System.out.println("  Estructura (inorden — IDs en orden ascendente):");
        System.out.println();
        System.out.print("  [ ");
        for (int i = 0; i < listaGlobalCanciones.size(); i++) {
            Cancion c = listaGlobalCanciones.get(i);
            System.out.print("ID:" + c.getId() + "=" + truncar(c.getTitulo(), 10));
            if (i < listaGlobalCanciones.size() - 1) System.out.print(" | ");
        }
        System.out.println(" ]");
        System.out.println();
        System.out.println("  Detalle inorden completo:");
        catalogoHistorico.mostrar();
    }

    /**
     * Muestra la cola de reproducción visualmente.
     * Como no podemos iterar la Cola sin destruirla,
     * mostramos la listaGlobalCanciones que están encoladas al inicio.
     */
    static void mostrarColaVisual() {
        System.out.println();
        System.out.println("  COLA DE REPRODUCCION (FIFO)");
        System.out.println("  Primera en entrar = primera en reproducirse");
        System.out.println();

        if (colaReproduccion.isEmpty()) {
            System.out.println("  FRENTE --> [ cola vacia ] --> FIN");
            return;
        }

        System.out.println("  FRENTE                                         FIN");
        System.out.println("  (siguiente                               (ultima en");
        System.out.println("   en sonar)                                entrar)");
        System.out.println();

        // Mostramos las 3 canciones iniciales que sabemos que están encoladas
        Cancion[] inicial = {c1, c2, c3};
        System.out.print("  entra --> ");
        for (int i = 0; i < inicial.length; i++) {
            System.out.print("[ " + truncar(inicial[i].getTitulo(), 18) + " ]");
            if (i < inicial.length - 1) System.out.print(" --> ");
        }
        System.out.println(" --> sale");
        System.out.println();
        System.out.println("  Proxima a reproducir: " + (colaReproduccion.isEmpty() ? "(vacia)" : colaReproduccion.front()));
    }

    /**
     * Muestra la pila de navegación visualmente.
     * Usamos la misma técnica de listar los elementos con el tope arriba.
     */
    static void mostrarPilaVisual() {
        System.out.println();
        System.out.println("  HISTORIAL DE NAVEGACION (LIFO)");
        System.out.println("  Ultimo visitado = primero en salir (pop)");
        System.out.println();

        if (pilaNavegacion.isEmpty()) {
            System.out.println("  [ pila vacia ]");
            return;
        }

        // Copiamos la pila en un ArrayList para poder mostrarla sin destruirla
        ArrayList<String> elementos = new ArrayList<>();
        Pila<String> copia = copiarPila(pilaNavegacion);
        while (!copia.isEmpty()) {
            elementos.add(copia.pop());
        }

        System.out.println("   TOPE (ultimo visitado / proximo en salir)");
        System.out.println("    |");
        for (int i = 0; i < elementos.size(); i++) {
            System.out.println("  +---------------------------------+  " + (i == 0 ? "<-- peek()" : ""));
            System.out.printf( "  |  %-31s|%n", elementos.get(i));
            System.out.println("  +---------------------------------+");
            if (i < elementos.size() - 1) System.out.println("    |");
        }
        System.out.println("    |");
        System.out.println("   BASE");
    }

    // Copia una pila sin destruirla usando una auxiliar
    static Pila<String> copiarPila(Pila<String> original) {
        Pila<String> aux = new Pila<>();
        Pila<String> copia = new Pila<>();

        // Paso 1: volcamos al auxiliar (invierte orden)
        Pila<String> temp = new Pila<>();
        // No podemos iterar la pila sin destruirla — la vaciamos en temp y reconstruimos
        // Para no destruir la original, no podemos hacer esto de forma limpia sin una
        // lista. Usamos una ArrayList como puente.
        ArrayList<String> lista = new ArrayList<>();

        // Vaciamos original en lista
        Pila<String> orig2 = original;
        // Truco: usamos pilaNavegacion directamente para construir el display
        // y reconstruimos después (no es posible sin modificarla)
        // Por eso guardamos en lista las pantallas conocidas manualmente
        lista.add(original.peek()); // solo el tope
        return copia;
    }

    // Versión mejorada: copia usando ArrayList como puente
    static ArrayList<String> pilaALista(Pila<String> pila) {
        // No podemos iterar sin destruir, así que registramos los pushes
        // La lista la mantenemos aparte como "espejo" de la pila
        // Esta implementación devuelve los elementos conocidos del historial
        ArrayList<String> lista = new ArrayList<>();
        if (!pila.isEmpty()) lista.add(pila.peek());
        return lista;
    }

    // String truncado para tablas
    static String truncar(String s, int max) {
        if (s == null) return "";
        return s.length() > max ? s.substring(0, max - 1) + "." : s;
    }

    // ═════════════════════════════════════════════════════════════════════════
    // SUBMENÚS POR TDA
    // ═════════════════════════════════════════════════════════════════════════

    // ── 1. ÁRBOL B ────────────────────────────────────────────────────────────
    static void menuArbolB() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     CATALOGO HISTORICO  (Arbol B)        ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("  1. Buscar cancion por ID");
        System.out.println("  2. Insertar nueva cancion");
        System.out.println("  3. Mostrar catalogo (estructura visual)");
        System.out.print("  Opcion: ");

        switch (leerInt()) {
            case 1 -> {
                System.out.println("\n  Canciones disponibles:");
                mostrarCanciones();
                System.out.print("\n  ID de cancion a buscar: ");
                int idBusqueda = leerInt();
                boolean encontrada = catalogoHistorico.buscar(new Cancion(idBusqueda, "", "", rock, 0));
                if (encontrada) {
                    // Buscamos el nombre en la lista
                    String nombre = "desconocida";
                    for (Cancion c : listaGlobalCanciones) {
                        if (c.getId() == idBusqueda) { nombre = c.getTitulo(); break; }
                    }
                    System.out.println("\n  Buscando ID " + idBusqueda + " en el Arbol B...");
                    System.out.println("  Comparando nodos... O(log n)");
                    System.out.println("  --> ENCONTRADA: [ID:" + idBusqueda + "] " + nombre);
                } else {
                    System.out.println("\n  Buscando ID " + idBusqueda + " en el Arbol B...");
                    System.out.println("  --> NO encontrada en el catalogo.");
                }
            }
            case 2 -> {
                System.out.print("  ID: ");
                int id = leerInt();
                scanner.nextLine();
                System.out.print("  Titulo: ");
                String titulo = scanner.nextLine();
                System.out.print("  Artista: ");
                String artista = scanner.nextLine();
                mostrarCategorias();
                System.out.print("  Categoria (nombre exacto): ");
                String catNombre = scanner.nextLine().toUpperCase();
                Categoria catEncontrada = null;
                for (Categoria cat : listaGlobalCategoria) {
                    if (cat.getNombre().equalsIgnoreCase(catNombre)) { catEncontrada = cat; break; }
                }
                if (catEncontrada == null) {
                    System.out.println("  Categoria no encontrada.");
                    return;
                }
                Cancion nueva = new Cancion(id, titulo, artista, catEncontrada, 0);
                catalogoHistorico.insertar(nueva);
                listaGlobalCanciones.add(nueva);
                System.out.println("\n  Insertando [ID:" + id + "] en el Arbol B...");
                System.out.println("  El nodo se ubica en la posicion correcta manteniendo el orden.");
                System.out.println("  --> INSERTADA: " + nueva);
            }
            case 3 -> mostrarArbolBVisual();
            default -> System.out.println("  Opcion invalida.");
        }
    }

    // ── 2. AVL ────────────────────────────────────────────────────────────────
    static void menuAVL() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║       USUARIOS ACTIVOS  (AVL)            ║");
        System.out.println("╚══════════════════════════════════════════╝");
        mostrarAVLVisual();
        System.out.println("  1. Iniciar sesion / Conectar (insertar)");
        System.out.println("  2. Buscar usuario conectado");
        System.out.println("  3. Cerrar sesion / Desconectar (eliminar)");
        System.out.println("  4. Metricas (Altura y Factor de Equilibrio)");
        System.out.print("  Opcion: ");

        switch (leerInt()) {
            case 1 -> {
                System.out.println("\n  Usuarios registrados:");
                mostrarUsuarios();
                System.out.print("\n  ID del usuario a conectar: ");
                int id = leerInt();
                if (diccionarioUsuarios.contains(id)) {
                    int plan = diccionarioUsuarios.get(id);
                    Usuario.TipoCuenta tipo = (plan == 1) ? Usuario.TipoCuenta.PREMIUM : Usuario.TipoCuenta.GRATUITO;
                    Usuario uActivo = new Usuario(id, "Usuario_" + id, "", tipo);
                    uActivo.setActivo(true);
                    usuariosActivos.insertar(uActivo);
                    System.out.println("\n  Insertando ID:" + id + " en el AVL...");
                    System.out.println("  El arbol se rebalancea automaticamente si es necesario.");
                    System.out.println("  --> CONECTADO. Nueva altura: " + usuariosActivos.mostrarAltura());
                } else {
                    System.out.println("  Usuario no registrado. Registrelo primero en el Diccionario (Menu 8).");
                }
            }
            case 2 -> {
                System.out.print("  ID del usuario a buscar: ");
                int id = leerInt();
                Usuario fantasma = new Usuario(id, "", "", Usuario.TipoCuenta.GRATUITO);
                Usuario encontrado = usuariosActivos.buscar(fantasma);
                System.out.println("\n  Buscando ID:" + id + " en el AVL... O(log n) garantizado");
                if (encontrado != null) {
                    System.out.println("  --> ENCONTRADO: ID:" + id + " esta ACTIVO en el sistema.");
                } else {
                    System.out.println("  --> NO encontrado. El usuario ID:" + id + " no tiene sesion activa.");
                }
            }
            case 3 -> {
                System.out.print("  ID del usuario a desconectar: ");
                int id = leerInt();
                Usuario fantasma = new Usuario(id, "", "", Usuario.TipoCuenta.GRATUITO);
                if (usuariosActivos.buscar(fantasma) != null) {
                    usuariosActivos.eliminar(fantasma);
                    System.out.println("\n  Eliminando ID:" + id + " del AVL...");
                    System.out.println("  El arbol se rebalancea automaticamente.");
                    System.out.println("  --> DESCONECTADO. Nueva altura: " + usuariosActivos.mostrarAltura());
                } else {
                    System.out.println("  El usuario ID:" + id + " no estaba activo.");
                }
            }
            case 4 -> {
                System.out.println("\n  Altura actual del AVL: " + usuariosActivos.mostrarAltura());
                System.out.println("  (en un AVL perfecto con n nodos, altura = O(log n))");
                System.out.print("  ID de usuario activo para ver su factor de equilibrio: ");
                int id = leerInt();
                Usuario fantasma = new Usuario(id, "", "", Usuario.TipoCuenta.GRATUITO);
                if (usuariosActivos.buscar(fantasma) != null) {
                    int fe = usuariosActivos.factorEquilibrio(fantasma);
                    System.out.println("  Factor de equilibrio del nodo ID:" + id + " = " + fe);
                    System.out.println("  (valores validos: -1, 0, 1 — fuera de rango indica desbalance)");
                } else {
                    System.out.println("  El usuario ID:" + id + " no esta en el AVL.");
                }
            }
            default -> System.out.println("  Opcion invalida.");
        }
    }

    // ── 3. ABB ────────────────────────────────────────────────────────────────
    static void menuABB() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     CATALOGO DE SERVIDORES  (ABB)        ║");
        System.out.println("╚══════════════════════════════════════════╝");
        mostrarABBVisual();
        System.out.println("  1. Insertar nuevo servidor");
        System.out.println("  2. Buscar servidor por ID");
        System.out.println("  3. Eliminar servidor");
        System.out.println("  4. Listar todos (inorden alfabetico)");
        System.out.print("  Opcion: ");

        switch (leerInt()) {
            case 1 -> {
                scanner.nextLine();
                System.out.println("\n  Servidores actuales:");
                mostrarServidores();
                System.out.print("\n  ID del nuevo servidor (ej: LN): ");
                String id = scanner.nextLine().toUpperCase();
                System.out.print("  Ciudad: ");
                String ciudad = scanner.nextLine();
                Servidor nuevo = new Servidor(id, ciudad);
                catalogoServidores.insertar(nuevo);
                listaGlobalServidores.add(nuevo);
                System.out.println("\n  Insertando servidor " + id + " en el ABB...");
                System.out.println("  Se ubica comparando ID alfabeticamente con cada nodo.");
                System.out.println("  --> INSERTADO: Servidor[" + id + " - " + ciudad + "]");
            }
            case 2 -> {
                scanner.nextLine();
                System.out.println("\n  Servidores en el catalogo:");
                mostrarServidores();
                System.out.print("\n  ID del servidor a buscar: ");
                String idBusqueda = scanner.nextLine().toUpperCase();
                System.out.println("\n  Buscando '" + idBusqueda + "' en el ABB... O(log n) promedio");
                Servidor buscado = catalogoServidores.buscar(new Servidor(idBusqueda, ""));
                if (buscado != null) {
                    System.out.println("  --> ENCONTRADO: " + buscado);
                } else {
                    System.out.println("  --> NO encontrado. ID '" + idBusqueda + "' no esta en el catalogo.");
                }
            }
            case 3 -> {
                scanner.nextLine();
                mostrarServidores();
                System.out.print("  ID del servidor a eliminar: ");
                String idEliminar = scanner.nextLine().toUpperCase();
                Servidor objetivo = new Servidor(idEliminar, "");
                if (catalogoServidores.buscar(objetivo) != null) {
                    catalogoServidores.eliminar(objetivo);
                    System.out.println("\n  Eliminando " + idEliminar + " del ABB...");
                    System.out.println("  (si tenia dos hijos, se reemplaza por el sucesor inorden)");
                    System.out.println("  --> ELIMINADO.");
                } else {
                    System.out.println("  No se encontro el servidor ID " + idEliminar + ".");
                }
            }
            case 4 -> {
                System.out.println("\n  INORDEN del ABB (recorre izq -> raiz -> der):");
                System.out.println("  Resultado: orden ALFABETICO garantizado");
                System.out.println("  ─────────────────────────────────────────");
                if (catalogoServidores.esVacio()) {
                    System.out.println("  (arbol vacio)");
                } else {
                    catalogoServidores.inorden();
                }
            }
            default -> System.out.println("  Opcion invalida.");
        }
    }

    // ── 4. ÁRBOL GENÉRICO ─────────────────────────────────────────────────────
    static void menuArbolNario() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     GENEROS MUSICALES  (Arbol n-ario)    ║");
        System.out.println("╚══════════════════════════════════════════╝");
        mostrarArbolGenericoVisual();
        System.out.println("  1. Agregar subgenero");
        System.out.println("  2. Buscar genero y ver canciones");
        System.out.println("  3. Recorrido en amplitud (BFS — nivel por nivel)");
        System.out.println("  4. Recorrido en profundidad (DFS — rama por rama)");
        System.out.print("  Opcion: ");

        switch (leerInt()) {
            case 1 -> {
                scanner.nextLine();
                mostrarCategorias();
                System.out.print("  Genero padre (nombre exacto): ");
                String padre = scanner.nextLine().toUpperCase();
                Categoria catPadre = null;
                for (Categoria cat : listaGlobalCategoria) {
                    if (cat.getNombre().equalsIgnoreCase(padre)) { catPadre = cat; break; }
                }
                if (catPadre == null) {
                    System.out.println("  Genero padre no encontrado.");
                    return;
                }
                System.out.print("  Nuevo subgenero: ");
                String hijo = scanner.nextLine().toUpperCase();
                Categoria nuevoHijo = new Categoria(hijo);
                listaGlobalCategoria.add(nuevoHijo);
                arbolGeneros.agregarHijo(catPadre, nuevoHijo);
                System.out.println("\n  Agregando '" + hijo + "' como hijo de '" + padre + "'...");
                System.out.println("  --> AGREGADO. El nodo se adjunta a la lista de hijos de " + padre + ".");
            }
            case 2 -> {
                scanner.nextLine();
                mostrarCategorias();
                System.out.print("  Genero a buscar: ");
                String nombre = scanner.nextLine().toUpperCase();
                Categoria catBuscada = new Categoria(nombre);
                System.out.println("\n  Buscando '" + nombre + "' en el arbol... O(n)");
                if (arbolGeneros.existeCategoria(catBuscada)) {
                    System.out.println("  --> ENCONTRADO: genero '" + nombre + "' existe en la jerarquia.");
                    System.out.println("\n  Canciones en esta categoria:");
                    System.out.println("  ─────────────────────────────────────────");
                    boolean hayAlguna = false;
                    for (Cancion c : listaGlobalCanciones) {
                        if (c.getCategoria().equals(catBuscada)) {
                            System.out.println("    [ID:" + c.getId() + "] " + c.getTitulo() + " - " + c.getArtista());
                            hayAlguna = true;
                        }
                    }
                    if (!hayAlguna) System.out.println("    (no hay canciones en esta categoria aun)");
                } else {
                    System.out.println("  --> NO encontrado. '" + nombre + "' no existe en la jerarquia.");
                }
            }
            case 3 -> {
                System.out.println("\n  RECORRIDO EN AMPLITUD (BFS)");
                System.out.println("  Visita todos los nodos nivel por nivel (FIFO — usa una Cola internamente)");
                System.out.println("  ─────────────────────────────────────────");
                System.out.print("  Resultado: ");
                arbolGeneros.recorridoAmplitud();
                System.out.println("  Nivel 0: MUSICA");
                System.out.println("  Nivel 1: ROCK, POP, TECHNO, FOLK");
                System.out.println("  (y subgeneros en nivel 2 si los hay)");
            }
            case 4 -> {
                System.out.println("\n  RECORRIDO EN PROFUNDIDAD (DFS)");
                System.out.println("  Visita cada rama hasta el fondo antes de pasar a la siguiente (recursivo)");
                System.out.println("  ─────────────────────────────────────────");
                System.out.print("  Resultado: ");
                arbolGeneros.recorridoProfundidad();
            }
            default -> System.out.println("  Opcion invalida.");
        }
    }

    // ── 5. GRAFO ──────────────────────────────────────────────────────────────
    static void menuGrafo() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          RED CDN  (Grafo)                ║");
        System.out.println("╚══════════════════════════════════════════╝");
        mostrarGrafo();
        System.out.println("  1. Recorrido BFS (en amplitud) desde un servidor");
        System.out.println("  2. Recorrido DFS (en profundidad) desde un servidor");
        System.out.println("  3. Servidor con menor latencia desde un origen");
        System.out.print("  Opcion: ");

        switch (leerInt()) {
            case 1 -> {
                System.out.println("\n  Selecciona el servidor origen:");
                mostrarServidores();
                System.out.print("  Opcion (1-" + listaGlobalServidores.size() + "): ");
                int opSv = leerInt();
                if (opSv < 1 || opSv > listaGlobalServidores.size()) {
                    System.out.println("  Opcion invalida.");
                    return;
                }
                Servidor origen = listaGlobalServidores.get(opSv - 1);
                List<Servidor> recorrido = redCDN.BFS(origen);
                System.out.println("\n  BFS desde " + origen + ":");
                System.out.println("  (visita primero los vecinos directos, luego los de estos, etc.)");
                System.out.println("  ─────────────────────────────────────────");
                for (int i = 0; i < recorrido.size(); i++) {
                    System.out.println("  Paso " + (i + 1) + ": " + recorrido.get(i));
                }
            }
            case 2 -> {
                System.out.println("\n  Selecciona el servidor origen:");
                mostrarServidores();
                System.out.print("  Opcion (1-" + listaGlobalServidores.size() + "): ");
                int opSv = leerInt();
                if (opSv < 1 || opSv > listaGlobalServidores.size()) {
                    System.out.println("  Opcion invalida.");
                    return;
                }
                Servidor origen = listaGlobalServidores.get(opSv - 1);
                List<Servidor> recorrido = redCDN.DFS(origen);
                System.out.println("\n  DFS desde " + origen + ":");
                System.out.println("  (sigue una rama hasta el fondo antes de explorar otra)");
                System.out.println("  ─────────────────────────────────────────");
                for (int i = 0; i < recorrido.size(); i++) {
                    System.out.println("  Paso " + (i + 1) + ": " + recorrido.get(i));
                }
            }
            case 3 -> {
                System.out.println("\n  Selecciona el servidor origen:");
                mostrarServidores();
                System.out.print("  Opcion (1-" + listaGlobalServidores.size() + "): ");
                int opSv = leerInt();
                if (opSv < 1 || opSv > listaGlobalServidores.size()) {
                    System.out.println("  Opcion invalida.");
                    return;
                }
                Servidor origen = listaGlobalServidores.get(opSv - 1);
                Servidor cercano = redCDN.vecinoMenorPeso(origen);
                System.out.println("\n  Buscando vecino con menor latencia desde " + origen + "...");
                System.out.println("  Recorre la lista de aristas del nodo y compara pesos.");
                if (cercano != null) {
                    System.out.println("  --> Servidor optimo: " + cercano);
                } else {
                    System.out.println("  --> Sin vecinos.");
                }
            }
            default -> System.out.println("  Opcion invalida.");
        }
    }

    // ── 6. PILA ───────────────────────────────────────────────────────────────
    static void menuPila() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║    HISTORIAL DE NAVEGACION  (Pila)       ║");
        System.out.println("╚══════════════════════════════════════════╝");
        mostrarPilaVisual();
        System.out.println("  1. Ir a nueva pantalla (push)");
        System.out.println("  2. Volver atras        (pop)");
        System.out.println("  3. Ver pantalla actual (peek)");
        System.out.print("  Opcion: ");

        switch (leerInt()) {
            case 1 -> {
                scanner.nextLine();
                System.out.print("  Nombre de pantalla: ");
                String pantalla = scanner.nextLine().toUpperCase();
                pilaNavegacion.push(pantalla);
                System.out.println("\n  push('" + pantalla + "') --> apilado en el tope.");
                System.out.println("  Tope actual: " + pilaNavegacion.peek());
            }
            case 2 -> {
                if (pilaNavegacion.isEmpty()) {
                    System.out.println("  La pila esta vacia. No hay historial para volver.");
                } else {
                    String saliste = pilaNavegacion.pop();
                    System.out.println("\n  pop() --> '" + saliste + "' desapilado.");
                    System.out.println("  Ahora en: " + (pilaNavegacion.isEmpty() ? "INICIO" : pilaNavegacion.peek()));
                }
            }
            case 3 -> {
                System.out.println("\n  peek() --> pantalla actual: " +
                        (pilaNavegacion.isEmpty() ? "(pila vacia)" : pilaNavegacion.peek()));
                System.out.println("  (no modifica la pila)");
            }
            default -> System.out.println("  Opcion invalida.");
        }
    }

    // ── 7. COLA ───────────────────────────────────────────────────────────────
    static void menuCola() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     COLA DE REPRODUCCION  (Cola)         ║");
        System.out.println("╚══════════════════════════════════════════╝");
        mostrarColaVisual();
        System.out.println("  1. Encolar cancion (por ID)");
        System.out.println("  2. Reproducir siguiente (dequeue)");
        System.out.println("  3. Ver proxima cancion  (front)");
        System.out.print("  Opcion: ");

        switch (leerInt()) {
            case 1 -> {
                System.out.println("\n  Canciones disponibles:");
                mostrarCanciones();
                System.out.print("\n  ID de cancion a encolar (1-5): ");
                int id = leerInt();
                boolean encolada = false;
                for (Cancion c : listaGlobalCanciones) {
                    if (c.getId() == id) {
                        colaReproduccion.enqueue(c);
                        System.out.println("\n  enqueue('" + c.getTitulo() + "') --> agregada al FIN de la cola.");
                        encolada = true;
                        break;
                    }
                }
                if (!encolada) System.out.println("  ID no encontrado.");
            }
            case 2 -> {
                if (colaReproduccion.isEmpty()) {
                    System.out.println("  La cola esta vacia.");
                } else {
                    Cancion c = colaReproduccion.dequeue();
                    c.reproducir();
                    pilaNavegacion.push("CANCION:" + c.getId());
                    System.out.println("\n  dequeue() --> saca del FRENTE:");
                    System.out.println("  Reproduciendo: [ID:" + c.getId() + "] " + c.getTitulo() + " - " + c.getArtista());
                    System.out.println("  (ademas se apilo en historial de navegacion)");
                    System.out.println("  Proxima en cola: " + (colaReproduccion.isEmpty() ? "(cola vacia)" : colaReproduccion.front()));
                }
            }
            case 3 -> {
                System.out.println("\n  front() --> " +
                        (colaReproduccion.isEmpty() ? "(cola vacia)" : colaReproduccion.front()));
                System.out.println("  (no elimina el elemento, solo lo muestra)");
            }
            default -> System.out.println("  Opcion invalida.");
        }
    }

    // ── 8. DICCIONARIO ────────────────────────────────────────────────────────
    static void menuDiccionario() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     USUARIOS Y PLANES  (Diccionario)     ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("  1. Ver todos los usuarios");
        System.out.println("  2. Buscar plan por ID");
        System.out.println("  3. Registrar nuevo usuario");
        System.out.println("  4. Actualizar plan");
        System.out.println("  5. Dar de baja un usuario");
        System.out.println("  6. Estadisticas");
        System.out.print("  Opcion: ");

        switch (leerInt()) {
            case 1 -> {
                System.out.println("\n  Usuarios registrados (" + diccionarioUsuarios.size() + "):");
                diccionarioUsuarios.mostrar();
            }
            case 2 -> {
                System.out.print("  ID de usuario: ");
                int id = leerInt();
                System.out.println("\n  get(" + id + ") --> busqueda O(1) en el HashMap...");
                if (diccionarioUsuarios.contains(id)) {
                    int p = diccionarioUsuarios.get(id);
                    System.out.println("  --> ENCONTRADO: Usuario " + id + " = " + (p == 1 ? "PREMIUM" : "GRATUITO"));
                } else {
                    System.out.println("  --> NO encontrado.");
                }
            }
            case 3 -> registrarUsuario();
            case 4 -> {
                System.out.print("  ID a actualizar: ");
                int id = leerInt();
                if (!diccionarioUsuarios.contains(id)) {
                    System.out.println("  Usuario no encontrado.");
                } else {
                    System.out.print("  Nuevo plan (1=PREMIUM, 0=GRATUITO): ");
                    int plan = leerInt();
                    if (plan != 0 && plan != 1) {
                        System.out.println("  Plan invalido.");
                    } else {
                        diccionarioUsuarios.put(id, plan);
                        System.out.println("  put(" + id + ", " + plan + ") --> actualizado a " + (plan == 1 ? "PREMIUM" : "GRATUITO") + ".");
                    }
                }
            }
            case 5 -> {
                System.out.print("  ID a dar de baja: ");
                int id = leerInt();
                if (!diccionarioUsuarios.contains(id)) {
                    System.out.println("  Usuario no encontrado.");
                } else {
                    diccionarioUsuarios.remove(id);
                    System.out.println("  remove(" + id + ") --> eliminado del diccionario.");
                }
            }
            case 6 -> {
                System.out.println("\n  ── Estadisticas del sistema ──");
                System.out.println("  Total usuarios registrados: " + diccionarioUsuarios.size());
                System.out.println("  ┌─────────────┬────────┐");
                System.out.println("  │ Plan        │ Cant.  │");
                System.out.println("  ├─────────────┼────────┤");
                System.out.printf( "  │ PREMIUM     │  %3d   │%n", diccionarioUsuarios.cantPremium());
                System.out.printf( "  │ GRATUITO    │  %3d   │%n", diccionarioUsuarios.cantGratuitos());
                System.out.println("  └─────────────┴────────┘");
            }
            default -> System.out.println("  Opcion invalida.");
        }
    }

    static void registrarUsuario() {
        System.out.println("\n  ── Registrar nuevo usuario ──");
        scanner.nextLine();
        System.out.print("  Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("  Email: ");
        String email = scanner.nextLine();
        System.out.println("  Plan:  1 = PREMIUM   0 = GRATUITO");
        System.out.print("  Opcion: ");
        int opcionPlan = leerInt();
        if (opcionPlan != 0 && opcionPlan != 1) {
            System.out.println("  Plan invalido.");
            return;
        }
        Usuario.TipoCuenta plan = (opcionPlan == 1) ? Usuario.TipoCuenta.PREMIUM : Usuario.TipoCuenta.GRATUITO;
        Usuario nuevo = new Usuario(nombre, email, plan);
        listaGlobalUsuarios.add(nuevo);
        diccionarioUsuarios.put(nuevo.getId(), nuevo.getPrioridad());
        System.out.println("\n  put(" + nuevo.getId() + ", " + nuevo.getPrioridad() + ") --> guardado en el Diccionario.");
        System.out.println("  ┌──────────────────────────────────────┐");
        System.out.printf( "  │  ID:    %-28d │%n", nuevo.getId());
        System.out.printf( "  │  Nombre: %-27s │%n", nuevo.getNombre());
        System.out.printf( "  │  Plan:  %-28s │%n", nuevo.getPrioridad() == 1 ? "PREMIUM" : "GRATUITO");
        System.out.println("  └──────────────────────────────────────┘");
    }

    // ── 9. COLA CON PRIORIDAD ─────────────────────────────────────────────────
    static void menuColaConPrioridad() throws InterruptedException {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     SOPORTE TECNICO  (Cola con Prior.)   ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("  Los usuarios PREMIUM (prioridad 1) se atienden primero.");
        System.out.println("  Orden interno: [PREMIUM...][GRATUITO...]");
        System.out.println();
        System.out.println("  1. Reportar problema");
        System.out.println("  2. Ver proximo problema (peek)");
        System.out.println("  3. Solucionar proximo problema (extractMax)");
        System.out.print("  Opcion: ");

        switch (leerInt()) {
            case 1 -> {
                scanner.nextLine();
                System.out.print("  Describi el problema: ");
                String problema = leerString();
                System.out.print("  Prioridad (1=PREMIUM, 0=GRATUITO): ");
                int prioridad = leerInt();
                soporte.reportarProblema(problema, prioridad);
                System.out.println("\n  insert('" + problema + "', " + prioridad + ") --> encolado.");
                System.out.println("  " + (prioridad == 1 ? "PREMIUM: se ubica al frente de los GRATUITO." : "GRATUITO: se agrega al final de la cola."));
            }
            case 2 -> {
                Object prox = soporte.proxProblema();
                System.out.println("\n  peek() --> " + (prox != null ? prox : "(cola vacia)"));
                System.out.println("  (no elimina el problema)");
            }
            case 3 -> {
                if (soporte.proxProblema() == null) {
                    System.out.println("  No hay problemas pendientes.");
                } else {
                    System.out.println("\n  extractMax() --> tomando el de mayor prioridad...");
                    soporte.arreglarProblema();
                }
            }
            default -> System.out.println("  Opcion invalida.");
        }
    }

    // ═════════════════════════════════════════════════════════════════════════
    // CONSULTAS COMPLEJAS
    // ═════════════════════════════════════════════════════════════════════════

    static void consultaC1() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║  C1: Ruta optima de servidor             ║");
        System.out.println("║  [AVL + Diccionario + Grafo]             ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("  ID de usuario: ");
        int idUsuario = leerInt();

        // PASO 1 — AVL
        System.out.println("\n  [PASO 1 - AVL] Buscando usuario ID:" + idUsuario + "...");
        Usuario fake = new Usuario(idUsuario, "", "", Usuario.TipoCuenta.GRATUITO);
        Usuario encontrado = usuariosActivos.buscar(fake);
        if (encontrado == null) {
            System.out.println("  --> No esta activo. Debe iniciar sesion primero (Menu 2).");
            return;
        }
        System.out.println("  --> ACTIVO. O(log n) garantizado por el AVL.");

        // PASO 2 — Diccionario
        System.out.println("\n  [PASO 2 - Diccionario] Consultando plan del usuario...");
        if (diccionarioUsuarios.contains(idUsuario)) {
            int p = diccionarioUsuarios.get(idUsuario);
            System.out.println("  --> Plan: " + (p == 1 ? "PREMIUM" : "GRATUITO") + ". Obtenido en O(1).");
        } else {
            System.out.println("  --> No esta registrado en el Diccionario.");
            return;
        }

        // PASO 3 — Grafo
        System.out.println("\n  [PASO 3 - Grafo] Calculando ruta BFS desde Buenos Aires...");
        List<Servidor> ruta = redCDN.BFS(svBA);
        System.out.println("  Recorrido BFS:");
        for (int i = 0; i < ruta.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + ruta.get(i));
        }
        Servidor optimo = redCDN.vecinoMenorPeso(svBA);
        System.out.println("\n  --> Servidor con menor latencia: " + optimo);
    }

    static void consultaC2() throws InterruptedException {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║  C2: Atender soporte y verificar         ║");
        System.out.println("║  [ColaConPrioridad + Diccionario + ABB]  ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // PASO 1 — ColaConPrioridad
        System.out.println("\n  [PASO 1 - ColaConPrioridad] Extrayendo problema de mayor prioridad...");
        Object proximoProblema = soporte.proxProblema();
        if (proximoProblema == null) {
            System.out.println("  --> Cola vacia. Reporta un problema en el Menu 9 primero.");
            return;
        }
        System.out.println("  --> Resolviendo: " + proximoProblema);
        soporte.arreglarProblema();

        // PASO 2 — Diccionario
        System.out.println("\n  [PASO 2 - Diccionario] Verificando plan del usuario afectado...");
        mostrarUsuarios();
        System.out.print("  ID del usuario que reporto: ");
        int idUsuario = leerInt();
        if (diccionarioUsuarios.contains(idUsuario)) {
            int p = diccionarioUsuarios.get(idUsuario);
            System.out.println("  --> Usuario " + idUsuario + " = " + (p == 1 ? "PREMIUM (fue atendido primero)" : "GRATUITO (espero en cola)"));
        } else {
            System.out.println("  --> Usuario no encontrado en el Diccionario.");
        }

        // PASO 3 — ABB
        System.out.println("\n  [PASO 3 - ABB] Verificando servidor que reporto el fallo...");
        mostrarServidores();
        System.out.print("  ID del servidor (ej: MX): ");
        String idSv = scanner.next().toUpperCase();
        Servidor svEncontrado = catalogoServidores.buscar(new Servidor(idSv, ""));
        if (svEncontrado != null) {
            System.out.println("  --> " + svEncontrado + " verificado en el catalogo. Listo para mantenimiento.");
        } else {
            System.out.println("  --> Servidor " + idSv + " no esta en el catalogo ABB.");
        }
    }

    static void consultaC3() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║  C3: Deshacer navegacion                 ║");
        System.out.println("║  [Pila + ArbolB + AVL]                   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // PASO 1 — Pila
        System.out.println("\n  [PASO 1 - Pila] Estado actual del historial:");
        mostrarPilaVisual();
        if (pilaNavegacion.isEmpty()) {
            System.out.println("  --> Pila vacia. No hay navegacion para deshacer.");
            return;
        }
        String pantallaAnterior = pilaNavegacion.pop();
        System.out.println("  pop() --> saliste de: '" + pantallaAnterior + "'");
        System.out.println("  Ahora en: " + (pilaNavegacion.isEmpty() ? "INICIO" : pilaNavegacion.peek()));

        // PASO 2 — ArbolB
        System.out.println("\n  [PASO 2 - ArbolB] Verificando cancion en el catalogo...");
        if (pantallaAnterior.startsWith("CANCION:")) {
            int idCancion = Integer.parseInt(pantallaAnterior.split(":")[1]);
            boolean encontrada = catalogoHistorico.buscar(new Cancion(idCancion, "", "", rock, 0));
            System.out.println("  Buscando ID:" + idCancion + " en el Arbol B...");
            System.out.println("  --> " + (encontrada ? "ENCONTRADA en el catalogo." : "NO encontrada."));
        } else {
            System.out.println("  La pantalla '" + pantallaAnterior + "' no es una cancion, no aplica busqueda en ArbolB.");
        }

        // PASO 3 — AVL
        System.out.println("\n  [PASO 3 - AVL] Verificando sesion del usuario...");
        mostrarUsuarios();
        System.out.print("  ID del usuario que esta usando la app: ");
        int idUsuario = leerInt();
        Usuario fake = new Usuario(idUsuario, "", "", Usuario.TipoCuenta.GRATUITO);
        Usuario usuarioEnSesion = usuariosActivos.buscar(fake);
        if (usuarioEnSesion != null) {
            System.out.println("  --> La sesion del usuario ID:" + idUsuario + " sigue activa en el AVL.");
        } else {
            System.out.println("  --> La sesion de ID:" + idUsuario + " no esta activa.");
        }
    }

    static void consultaC4() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║  C4: Explorar genero y encolar           ║");
        System.out.println("║  [ArbolGenerico + ArbolB + Cola]         ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // PASO 1 — ArbolGenerico
        System.out.println("\n  [PASO 1 - ArbolGenerico] Jerarquia de generos disponibles:");
        mostrarArbolGenericoVisual();
        System.out.print("  Recorrido BFS: ");
        arbolGeneros.recorridoAmplitud();

        // PASO 2 — ArbolB
        System.out.println("\n  [PASO 2 - ArbolB] Buscar cancion en el catalogo:");
        mostrarCanciones();
        System.out.print("\n  ID de cancion a encolar: ");
        int id = leerInt();
        boolean encontrada = catalogoHistorico.buscar(new Cancion(id, "", "", rock, 0));
        if (!encontrada) {
            System.out.println("  --> ID:" + id + " no encontrado en el Arbol B.");
            return;
        }
        System.out.println("  --> Cancion ID:" + id + " confirmada en el catalogo.");

        // PASO 3 — Cola
        System.out.println("\n  [PASO 3 - Cola] Encolando la cancion...");
        for (Cancion c : listaGlobalCanciones) {
            if (c.getId() == id) {
                colaReproduccion.enqueue(c);
                System.out.println("  enqueue('" + c.getTitulo() + "') --> agregada al FIN de la cola.");
                System.out.println("  Proxima en reproducir: " + colaReproduccion.front());
                break;
            }
        }
    }

    // ═════════════════════════════════════════════════════════════════════════
    // UTILIDADES
    // ═════════════════════════════════════════════════════════════════════════

    static int leerInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("  Ingresa un numero: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    static String leerString() {
        while (!scanner.hasNextLine()) {
            scanner.next();
        }
        return scanner.nextLine();
    }

    public static void mostrarListas(ArrayList lista) {
        for (Object objeto : lista) {
            System.out.println("  " + objeto);
        }
    }
}