import Clases.*;
import TDA.Diccionario;
import TDA.ArbolB;
import TDA.ArbolGenerico;
import TDA.Cola;
import TDA.Pila;
// import TDA.Abb;   ← descomentar cuando Lucas entregue
// import TDA.Avl;   ← descomentar cuando Lucas entregue
import TDA.Grafo;

import java.util.List;
import java.util.Scanner;

/**
 * Main — Sistema de Streaming de Audio (Espotifai 🎵)
 *
 * P1 La cabra        → Arquitectura, Diccionario, Main, consultas complejas
 * P2 Lucas-Chan      → ABB + AVL
 * P3 Brichota        → ArbolB + ArbolGenerico
 * P4 Tobias tkm      → Pila + Cola + ColaConPrioridad (SoporteTecnico)
 * P5 Nestor-Kawai    → Grafo
 */
public class Main {

    // ── TDA P1: Diccionario usuarios → prioridad (1=PREMIUM, 0=GRATUITO) ─────
    static Diccionario<Integer, Integer> diccionarioUsuarios = new Diccionario<>();

    // ── TDA P2: descomentar cuando Lucas-Chan entregue ────────────────────────
    // static Abb<CreadorCont> catalogoCreadores = new Abb<>();
    // static Avl<Usuario>     usuariosActivos   = new Avl<>();

    // ── TDA P3: ArbolB y ArbolGenerico ────────────────────────────────────────
    static ArbolB<Cancion>       catalogoHistorico = new ArbolB<>();
    static ArbolGenerico<String> arbolGeneros      = new ArbolGenerico<>();

    // ── TDA P4: Pila, Cola y SoporteTecnico (usa ColaConPrioridad internamente)
    static Pila<String>           pilaNavegacion  = new Pila<>();
    static Cola<Cancion>          colaReproduccion = new Cola<>();
    static SoporteTecnico<String> soporte          = new SoporteTecnico<>();

    // ── TDA P5: descomentar cuando Nestor-Kawai entregue ─────────────────────
    static Grafo<Servidor> redCDN = new Grafo<>();

    static Scanner scanner = new Scanner(System.in);

    // ── Categorías globales para reutilizar ───────────────────────────────────
    static Categoria rock   = new Categoria("Rock");
    static Categoria techno = new Categoria("Techno");
    static Categoria pop    = new Categoria("Pop");
    static Categoria metal  = new Categoria("Metal");
    static Categoria folk   = new Categoria("Folk");

    // ── Canciones globales para reutilizar en submenús ───────────────────────
    static Cancion c1 = new Cancion(1, "Bohemian Rhapsody",      "Queen",          rock,   354);
    static Cancion c2 = new Cancion(2, "Blinding Lights",         "The Weeknd",     pop,    200);
    static Cancion c3 = new Cancion(3, "Strobe",                  "deadmau5",       techno, 601);
    static Cancion c4 = new Cancion(4, "La Llorona",              "Chavela Vargas", folk,   218);
    static Cancion c5 = new Cancion(5, "Smells Like Teen Spirit", "Nirvana",        metal,  301);

    // ── Datos de prueba ───────────────────────────────────────────────────────
    static void cargarDatosPrueba() {

        // Usuarios — el constructor con ID actualiza el contador automáticamente
        // así el próximo usuario creado desde el menú arranca desde el 106
        Usuario u1 = new Usuario("Gabriel", "gaby@mail.com",   Usuario.TipoCuenta.PREMIUM);
        Usuario u2 = new Usuario("Lucas",   "lucas@mail.com",  Usuario.TipoCuenta.GRATUITO);
        Usuario u3 = new Usuario("Toto",    "tobias@mail.com", Usuario.TipoCuenta.GRATUITO);
        Usuario u4 = new Usuario("Nestor",  "nestor@mail.com", Usuario.TipoCuenta.PREMIUM);
        Usuario u5 = new Usuario("Brisa",   "brisa@mail.com",  Usuario.TipoCuenta.PREMIUM);

        // Diccionario: registramos a cada usuario con su prioridad
        // getPrioridad() devuelve 1 si es PREMIUM, 0 si es GRATUITO
        diccionarioUsuarios.put(u1.getId(), u1.getPrioridad());
        diccionarioUsuarios.put(u2.getId(), u2.getPrioridad());
        diccionarioUsuarios.put(u3.getId(), u3.getPrioridad());
        diccionarioUsuarios.put(u4.getId(), u4.getPrioridad());
        diccionarioUsuarios.put(u5.getId(), u5.getPrioridad());

        // ArbolB: catálogo histórico de canciones
        catalogoHistorico.insertar(c1);
        catalogoHistorico.insertar(c2);
        catalogoHistorico.insertar(c3);
        catalogoHistorico.insertar(c4);
        catalogoHistorico.insertar(c5);

        // ArbolGenerico: jerarquía de géneros musicales
        arbolGeneros.agregarHijo(null,     "Música");
        arbolGeneros.agregarHijo("Música", "Rock");
        arbolGeneros.agregarHijo("Música", "Pop");
        arbolGeneros.agregarHijo("Música", "Techno");
        arbolGeneros.agregarHijo("Rock",   "Nacional");
        arbolGeneros.agregarHijo("Rock",   "Internacional");
        arbolGeneros.agregarHijo("Techno", "House");
        arbolGeneros.agregarHijo("House",  "Deep House");

        // Pila: pantalla inicial de navegación
        pilaNavegacion.push("INICIO");

        // Cola: canciones en espera de reproducción
        colaReproduccion.enqueue(c1);
        colaReproduccion.enqueue(c2);
        colaReproduccion.enqueue(c3);

        // Creadores de contenido — pendiente P2
        // CreadorCont canal1 = new CreadorCont(1, "rockclasico",  "Rock de los 70s-90s");
        // catalogoCreadores.insertar(canal1);

        // Usuarios activos — pendiente P2
        // usuariosActivos.insertar(u1); usuariosActivos.insertar(u4); usuariosActivos.insertar(u5);

        // TODO P5: construir red CDN en Grafo
        // Red CDN — P5
        Servidor svBA = new Servidor("BA", "Buenos Aires");
        Servidor svMX = new Servidor("MX", "México");
        Servidor svNY = new Servidor("NY", "Nueva York");
        Servidor svMD = new Servidor("MD", "Madrid");
        Servidor svSP = new Servidor("SP", "São Paulo");

        redCDN.agregarVertice(svBA);
        redCDN.agregarVertice(svMX);
        redCDN.agregarVertice(svNY);
        redCDN.agregarVertice(svMD);
        redCDN.agregarVertice(svSP);

        redCDN.agregarArista(svBA, svMX, 120);  // BA - México: 120ms
        redCDN.agregarArista(svBA, svSP, 30);   // BA - São Paulo: 30ms
        redCDN.agregarArista(svMX, svNY, 50);   // México - NY: 50ms
        redCDN.agregarArista(svNY, svMD, 80);   // NY - Madrid: 80ms
        redCDN.agregarArista(svMD, svSP, 200);  // Madrid - São Paulo: 200ms

        System.out.println("Datos de prueba subidos perfectamente.");
    }

    // ── Menú principal ────────────────────────────────────────────────────────
    public static void main(String[] args) throws InterruptedException {
        cargarDatosPrueba();
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║   SISTEMA DE STREAMING DE AUDIO          ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1.  Catálogo canciones  (Árbol B)       ║");
            System.out.println("║  2.  Usuarios activos    (AVL) ⏳         ║");
            System.out.println("║  3.  Catálogo creadores  (ABB) ⏳         ║");
            System.out.println("║  4.  Géneros musicales   (Árbol n-ario)  ║");
            System.out.println("║  5.  Red CDN             (Grafo) ✅       ║");
            System.out.println("║  6.  Historial           (Pila)           ║");
            System.out.println("║  7.  Cola reproducción   (Cola)           ║");
            System.out.println("║  8.  Usuarios y planes   (Diccionario)    ║");
            System.out.println("║  9.  Soporte técnico     (Cola Prior.)    ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  CONSULTAS COMPLEJAS                     ║");
            System.out.println("║  10. C1: Ruta óptima de servidor         ║");
            System.out.println("║  11. C2: Soporte + Diccionario + ABB     ║");
            System.out.println("║  12. C3: Deshacer navegación             ║");
            System.out.println("║  13. C4: Explorar género y encolar       ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  0.  Salir                               ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("  Opción: ");

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
                case 0  -> { System.out.println("\nHasta la próxima! 🎵"); scanner.close(); }
                default -> System.out.println("  ⚠ Opción inválida.");
            }
        } while (opcion != 0);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SUBMENÚS POR TDA
    // ══════════════════════════════════════════════════════════════════════════

    // ── 1. ÁRBOL B ────────────────────────────────────────────────────────────
    static void menuArbolB() {
        System.out.println("\n── Catálogo Histórico (Árbol B) ──");
        System.out.println("  1. Buscar canción por ID");
        System.out.println("  2. Insertar nueva canción");
        System.out.println("  3. Mostrar catálogo completo (inorden)");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                System.out.print("  ID de canción a buscar: ");
                int idBusqueda = leerInt();
                // Creamos una canción "fantasma" solo con el ID para que compareTo funcione
                boolean encontrada = catalogoHistorico.buscar(new Cancion(idBusqueda, "", "", rock, 0));
                System.out.println(encontrada
                        ? "  ✅ Canción ID " + idBusqueda + " encontrada en el catálogo."
                        : "  ❌ Canción no encontrada.");
            }
            case 2 -> {
                System.out.print("  ID: ");
                int id = leerInt();
                scanner.nextLine();
                System.out.print("  Título: ");
                String titulo = scanner.nextLine();
                System.out.print("  Artista: ");
                String artista = scanner.nextLine();
                catalogoHistorico.insertar(new Cancion(id, titulo, artista, rock, 0));
                System.out.println("  ✅ Canción insertada.");
            }
            case 3 -> {
                System.out.println("\n  --- CATÁLOGO (inorden) ---");
                catalogoHistorico.mostrar();
            }
        }
    }

    // ── 2. AVL ────────────────────────────────────────────────────────────────
    static void menuAVL() {
        System.out.println("\n── Usuarios Activos (AVL) ── [⏳ Lucas-Chan pendiente]");
        System.out.println("  Falta: insertar/buscar/eliminar/mostrarAltura/factorEquilibrio.");
    }

    // ── 3. ABB ────────────────────────────────────────────────────────────────
    static void menuABB() {
        System.out.println("\n── Catálogo de Creadores (ABB) ── [⏳ Lucas-Chan pendiente]");
        System.out.println("  Falta: insertar/buscar/eliminar/inorden.");
    }

    // ── 4. ÁRBOL N-ARIO ───────────────────────────────────────────────────────
    static void menuArbolNario() {
        System.out.println("\n── Géneros Musicales (Árbol n-ario) ──");
        System.out.println("  1. Agregar subgénero");
        System.out.println("  2. Recorrido en amplitud  (BFS — nivel por nivel)");
        System.out.println("  3. Recorrido en profundidad (DFS — rama por rama)");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                scanner.nextLine();
                System.out.print("  Género padre (ej: Rock): ");
                String padre = scanner.nextLine();
                System.out.print("  Nuevo subgénero: ");
                String hijo = scanner.nextLine();
                arbolGeneros.agregarHijo(padre, hijo);
                System.out.println("  ✅ '" + hijo + "' agregado bajo '" + padre + "'.");
            }
            case 2 -> {
                System.out.println("\n  --- RECORRIDO EN AMPLITUD (nivel por nivel) ---");
                arbolGeneros.recorridoAmplitud();
            }
            case 3 -> {
                System.out.println("\n  --- RECORRIDO EN PROFUNDIDAD (rama por rama) ---");
                arbolGeneros.recorridoProfundidad();
            }
        }
    }

    // ── 5. GRAFO ──────────────────────────────────────────────────────────────
    static void menuGrafo() {
        System.out.println("\n── Red CDN (Grafo) ──");
        System.out.println("  1. Mostrar red completa (BFS)");
        System.out.println("  2. Explorar red en profundidad (DFS)");
        System.out.println("  3. Servidor más cercano desde un origen");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                System.out.println("  Seleccioná el servidor origen:");
                System.out.println("    1. Buenos Aires (BA)");
                System.out.println("    2. México (MX)");
                System.out.println("    3. Nueva York (NY)");
                System.out.println("    4. Madrid (MD)");
                System.out.println("    5. São Paulo (SP)");
                System.out.print("  Opción: ");

                String id;
                switch (leerInt()) {
                    case 1 -> id = "BA";
                    case 2 -> id = "MX";
                    case 3 -> id = "NY";
                    case 4 -> id = "MD";
                    case 5 -> id = "SP";
                    default -> { System.out.println("  Opción inválida."); return; }
                }

                Servidor origen = new Servidor(id, "");
                List<Servidor> recorrido = redCDN.BFS(origen);
                System.out.println("  Recorrido BFS:");
                for (Servidor s : recorrido) System.out.println("    → " + s);
            }

            case 2 -> {
                System.out.println("  Seleccioná el servidor origen:");
                System.out.println("    1. Buenos Aires (BA)");
                System.out.println("    2. México (MX)");
                System.out.println("    3. Nueva York (NY)");
                System.out.println("    4. Madrid (MD)");
                System.out.println("    5. São Paulo (SP)");
                System.out.print("  Opción: ");

                String id;
                switch (leerInt()) {
                    case 1 -> id = "BA";
                    case 2 -> id = "MX";
                    case 3 -> id = "NY";
                    case 4 -> id = "MD";
                    case 5 -> id = "SP";
                    default -> { System.out.println("  Opción inválida."); return; }
                }

                Servidor origen = new Servidor(id, "");
                List<Servidor> recorrido = redCDN.DFS(origen);
                System.out.println("  Recorrido DFS:");
                for (Servidor s : recorrido) System.out.println("    → " + s);
            }

            case 3 -> {
                System.out.println("  Seleccioná el servidor origen:");
                System.out.println("    1. Buenos Aires (BA)");
                System.out.println("    2. México (MX)");
                System.out.println("    3. Nueva York (NY)");
                System.out.println("    4. Madrid (MD)");
                System.out.println("    5. São Paulo (SP)");
                System.out.print("  Opción: ");

                String id;
                switch (leerInt()) {
                    case 1 -> id = "BA";
                    case 2 -> id = "MX";
                    case 3 -> id = "NY";
                    case 4 -> id = "MD";
                    case 5 -> id = "SP";
                    default -> { System.out.println("  Opción inválida."); return; }
                }

                Servidor origen = new Servidor(id, "");
                Servidor cercano = redCDN.vecinoMenorPeso(origen);
                if (cercano != null)
                    System.out.println("Servidor más cercano: " + cercano);
                else
                    System.out.println("Sin vecinos.");
            }

            default -> System.out.println("Opción inválida.");
        }
    }

    // ── 6. PILA ───────────────────────────────────────────────────────────────
    static void menuPila() {
        System.out.println("\n── Historial de Navegación (Pila) ──");
        System.out.println("  Pantalla actual: " + (pilaNavegacion.isEmpty() ? "ninguna" : pilaNavegacion.peek()));
        System.out.println("  1. Ir a nueva pantalla (push)");
        System.out.println("  2. Volver atrás        (pop)");
        System.out.println("  3. Ver pantalla actual (peek)");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                scanner.nextLine();
                System.out.print("  Nombre de pantalla: ");
                String pantalla = scanner.nextLine().toUpperCase();
                pilaNavegacion.push(pantalla);
                System.out.println("  ✅ Navegaste a: " + pantalla);
            }
            case 2 -> {
                if (pilaNavegacion.isEmpty()) {
                    System.out.println("  ⚠ No hay historial para volver.");
                } else {
                    String saliste = pilaNavegacion.pop();
                    System.out.println("  ↩ Saliste de: " + saliste);
                    System.out.println("  Ahora en: " + (pilaNavegacion.isEmpty() ? "INICIO" : pilaNavegacion.peek()));
                }
            }
            case 3 -> {
                System.out.println("  Pantalla actual: " + (pilaNavegacion.isEmpty() ? "ninguna" : pilaNavegacion.peek()));
            }
        }
    }

    // ── 7. COLA ───────────────────────────────────────────────────────────────
    static void menuCola() {
        System.out.println("\n── Cola de Reproducción (Cola) ──");
        System.out.println("  Próxima: " + (colaReproduccion.isEmpty() ? "cola vacía" : colaReproduccion.front()));
        System.out.println("  1. Encolar canción (por ID)");
        System.out.println("  2. Reproducir siguiente (dequeue)");
        System.out.println("  3. Ver próxima canción  (front)");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                System.out.print("  ID de canción a encolar (1-5): ");
                int id = leerInt();
                Cancion[] todas = {c1, c2, c3, c4, c5};
                boolean encolada = false;
                for (Cancion c : todas) {
                    if (c.getId() == id) {
                        colaReproduccion.enqueue(c);
                        System.out.println("  ✅ Encolada: " + c);
                        encolada = true;
                        break;
                    }
                }
                if (!encolada) System.out.println("  ❌ ID no encontrado (usá un ID del 1 al 5).");
            }
            case 2 -> {
                if (colaReproduccion.isEmpty()) {
                    System.out.println("  ⚠ La cola está vacía.");
                } else {
                    Cancion c = colaReproduccion.dequeue();
                    c.reproducir();
                    // Guardamos la canción reproducida en el historial de navegación
                    pilaNavegacion.push("CANCION:" + c.getId());
                    System.out.println("  ▶ Reproduciendo: " + c);
                    System.out.println("  Próxima: " + (colaReproduccion.isEmpty() ? "cola vacía" : colaReproduccion.front()));
                }
            }
            case 3 -> {
                System.out.println("  Próxima: " + (colaReproduccion.isEmpty() ? "cola vacía" : colaReproduccion.front()));
            }
        }
    }

    // ── 8. DICCIONARIO ────────────────────────────────────────────────────────
    static void menuDiccionario() {
        System.out.println("\n── Usuarios y Planes (Diccionario) ──");
        System.out.println("  1. Ver todos los usuarios");
        System.out.println("  2. Buscar plan de un usuario por ID");
        System.out.println("  3. Registrar nuevo usuario");
        System.out.println("  4. Actualizar plan de un usuario");
        System.out.println("  5. Dar de baja un usuario");
        System.out.println("  6. Estadísticas (PREMIUM vs GRATUITO)");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                System.out.println("  Usuarios registrados (" + diccionarioUsuarios.size() + "):");
                diccionarioUsuarios.mostrar();
            }
            case 2 -> {
                System.out.print("  ID de usuario: ");
                int id = leerInt();
                if (diccionarioUsuarios.contains(id)) {
                    int p = diccionarioUsuarios.get(id);
                    System.out.println("  Usuario " + id + " → " + (p == 1 ? "PREMIUM" : "GRATUITO"));
                } else {
                    System.out.println("  ❌ Usuario no encontrado.");
                }
            }
            case 3 -> registrarUsuario();
            case 4 -> {
                System.out.print("  ID de usuario a actualizar: ");
                int id = leerInt();
                if (!diccionarioUsuarios.contains(id)) {
                    System.out.println("  ❌ Usuario no encontrado.");
                } else {
                    System.out.print("  Nuevo plan (1=PREMIUM, 0=GRATUITO): ");
                    int plan = leerInt();
                    if (plan != 0 && plan != 1) {
                        System.out.println("  ⚠ Plan inválido.");
                    } else {
                        diccionarioUsuarios.put(id, plan);
                        System.out.println("  ✅ Plan actualizado a " + (plan == 1 ? "PREMIUM" : "GRATUITO") + ".");
                    }
                }
            }
            case 5 -> {
                System.out.print("  ID de usuario a dar de baja: ");
                int id = leerInt();
                if (!diccionarioUsuarios.contains(id)) {
                    System.out.println("  ❌ Usuario no encontrado.");
                } else {
                    diccionarioUsuarios.remove(id);
                    System.out.println("  ✅ Usuario " + id + " eliminado del sistema.");
                }
            }
            case 6 -> {
                System.out.println("  Total usuarios:   " + diccionarioUsuarios.size());
                System.out.println("  Usuarios PREMIUM: " + diccionarioUsuarios.cantPremium());
                System.out.println("  Usuarios GRATIS:  " + diccionarioUsuarios.cantGratuitos());
            }
        }
    }

    /**
     * Registra un nuevo usuario pidiendo nombre, email y plan.
     * El ID se asigna automáticamente (autoincremental en Usuario).
     * Se llama desde la opción 3 del menuDiccionario().
     */
    static void registrarUsuario() {
        System.out.println("\n  ── Registrar nuevo usuario ──");

        scanner.nextLine(); // limpia el buffer que deja leerInt()

        System.out.print("  Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("  Email: ");
        String email = scanner.nextLine();

        System.out.println("  Plan:");
        System.out.println("  1. PREMIUM");
        System.out.println("  0. GRATUITO");
        System.out.print("  Opción: ");
        int opcionPlan = leerInt();

        if (opcionPlan != 0 && opcionPlan != 1) {
            System.out.println("  ⚠ Plan inválido. Usá 1 (PREMIUM) o 0 (GRATUITO).");
            return;
        }

        // Determinamos el tipo de cuenta según la opción elegida
        Usuario.TipoCuenta plan = (opcionPlan == 1)
                ? Usuario.TipoCuenta.PREMIUM
                : Usuario.TipoCuenta.GRATUITO;

        // Usamos el constructor SIN id — el ID se asigna solo con el contador estático
        Usuario nuevo = new Usuario(nombre, email, plan);

        // Registramos en el Diccionario: id → prioridad
        diccionarioUsuarios.put(nuevo.getId(), nuevo.getPrioridad());

        System.out.println("\n  ✅ Usuario registrado exitosamente!");
        System.out.println("  " + nuevo);
        System.out.println("  Plan: " + (nuevo.getPrioridad() == 1 ? "PREMIUM 🌟" : "GRATUITO"));
    }

    // ── 9. COLA CON PRIORIDAD (SoporteTecnico) ────────────────────────────────
    static void menuColaConPrioridad() throws InterruptedException {
        System.out.println("\n── Soporte Técnico (Cola con Prioridad) ──");
        System.out.println("  1. Reportar problema");
        System.out.println("  2. Ver próximo problema (peek)");
        System.out.println("  3. Solucionar próximo problema (extractMax)");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                scanner.nextLine(); // limpia buffer
                System.out.print("  Describí el problema: ");
                String problema = leerString();
                System.out.print("  Prioridad (1=PREMIUM, 0=GRATUITO): ");
                int prioridad = leerInt();
                soporte.reportarProblema(problema, prioridad);
                System.out.println("  ✅ Problema recibido. Lo resolveremos pronto.");
            }
            case 2 -> {
                Object prox = soporte.proxProblema();
                System.out.println(prox != null
                        ? "  Próximo a resolver: " + prox
                        : "  ✅ No hay problemas pendientes.");
            }
            case 3 -> soporte.arreglarProblema();
            default -> System.out.println("  ⚠ Opción inválida.");
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // CONSULTAS COMPLEJAS
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * C1: Ruta óptima de servidor para un usuario activo.
     * TDAs: AVL + Diccionario + Grafo
     *
     * Flujo:
     * 1. Buscar el usuario en el AVL (verifica que esté activo).
     * 2. Consultar su plan en el Diccionario.
     * 3. BFS en el Grafo CDN para encontrar el servidor con menor latencia.
     */
    static void consultaC1() {
        System.out.println("\n── C1: Ruta óptima de servidor ──");
        System.out.println("  [AVL + Diccionario + Grafo]");
        System.out.print("  ID de usuario: ");
        int idUsuario = leerInt();

        // PASO 1 — AVL: verificar que el usuario esté activo
        // TODO P2 → descomentar cuando Lucas-Chan entregue Avl.java:
        // Usuario fake = new Usuario(idUsuario, "", "", Usuario.TipoCuenta.GRATUITO);
        // Usuario encontrado = (Usuario) usuariosActivos.buscar(fake);
        // if (encontrado == null) { System.out.println("Usuario no activo."); return; }
        System.out.println("  [AVL] Buscando usuario ID " + idUsuario + "... ⏳ P2 pendiente");

        // PASO 2 — Diccionario: consultar su plan
        if (diccionarioUsuarios.contains(idUsuario)) {
            int p = diccionarioUsuarios.get(idUsuario);
            System.out.println("  [Diccionario] ✅ Usuario " + idUsuario
                    + " → " + (p == 1 ? "PREMIUM" : "GRATUITO"));
        } else {
            System.out.println("  [Diccionario] ❌ Usuario no registrado.");
            return;
        }

        // PASO 3 — Grafo: BFS para ruta óptima y servidor con menor latencia
        Servidor svBA = new Servidor("BA", "Buenos Aires");
        System.out.println("  [Grafo] BFS desde BA: " + redCDN.BFS(svBA));
        Servidor optimo = redCDN.vecinoMenorPeso(svBA);
        System.out.println("  [Grafo] ✅ Servidor con menor latencia desde BA: " + optimo);
    }

    /**
     * C2: Atender problema de soporte y verificar plan del usuario.
     * TDAs: ColaConPrioridad + Diccionario + ABB
     *
     * Flujo:
     * 1. Extraer el problema de mayor prioridad del SoporteTecnico.
     * 2. Verificar en el Diccionario el plan del usuario que reportó.
     * 3. Buscar el creador de contenido relacionado en el ABB.
     */
    static void consultaC2() throws InterruptedException {
        System.out.println("\n── C2: Atender soporte y verificar plan ──");
        System.out.println("  [ColaConPrioridad + Diccionario + ABB]");

        // PASO 1 — ColaConPrioridad: extraer el problema de mayor prioridad
        Object proximoProblema = soporte.proxProblema();
        if (proximoProblema == null) {
            System.out.println("  [ColaConPrioridad] ⚠ No hay problemas pendientes.");
            System.out.println("  → Reportá un problema desde el menú 9 primero.");
            return;
        }
        System.out.println("  [ColaConPrioridad] ✅ Resolviendo: " + proximoProblema);
        soporte.arreglarProblema();

        // PASO 2 — Diccionario: verificar qué plan tiene el usuario afectado
        System.out.print("  ID del usuario que reportó el problema: ");
        int idUsuario = leerInt();
        if (diccionarioUsuarios.contains(idUsuario)) {
            int p = diccionarioUsuarios.get(idUsuario);
            System.out.println("  [Diccionario] ✅ Usuario " + idUsuario
                    + " → " + (p == 1 ? "PREMIUM (fue atendido primero ✅)" : "GRATUITO (esperó su turno)"));
        } else {
            System.out.println("  [Diccionario] ❌ Usuario no encontrado en el diccionario.");
        }

        // PASO 3 — ABB: buscar el creador de contenido relacionado
        // TODO P2 → descomentar cuando Lucas-Chan entregue Abb.java:
        // CreadorCont fake = new CreadorCont(0, nombreCreador, "");
        // CreadorCont encontrado = (CreadorCont) catalogoCreadores.buscar(fake);
        System.out.println("  [ABB] Buscando creador relacionado... ⏳ P2 pendiente");
    }

    /**
     * C3: Deshacer navegación y verificar canción en catálogo.
     * TDAs: Pila + ArbolB + AVL
     *
     * Flujo:
     * 1. Pop de la Pila para volver a la pantalla anterior.
     * 2. Si era una canción, buscarla en el ArbolB.
     * 3. Actualizar el estado del usuario en el AVL.
     */
    static void consultaC3() {
        System.out.println("\n── C3: Deshacer navegación ──");
        System.out.println("  [Pila + ArbolB + AVL]");

        // PASO 1 — Pila: volver a la pantalla anterior
        if (pilaNavegacion.isEmpty()) {
            System.out.println("  [Pila] ⚠ No hay historial de navegación.");
            return;
        }
        String pantallaAnterior = pilaNavegacion.pop();
        System.out.println("  [Pila] ✅ Saliste de: " + pantallaAnterior);
        System.out.println("         Ahora en: " + (pilaNavegacion.isEmpty() ? "INICIO" : pilaNavegacion.peek()));

        // PASO 2 — ArbolB: si la pantalla era una canción, buscarla en el catálogo
        // El menuCola() apila "CANCION:ID" cuando reproducís una canción
        if (pantallaAnterior.startsWith("CANCION:")) {
            int idCancion = Integer.parseInt(pantallaAnterior.split(":")[1]);
            boolean encontrada = catalogoHistorico.buscar(new Cancion(idCancion, "", "", rock, 0));
            System.out.println("  [ArbolB] Canción ID " + idCancion
                    + (encontrada ? " ✅ encontrada en el catálogo." : " ❌ no encontrada."));
        } else {
            System.out.println("  [ArbolB] La pantalla '" + pantallaAnterior + "' no tiene canción asociada.");
        }

        // PASO 3 — AVL: actualizar estado del usuario en sesión
        // TODO P2 → descomentar cuando Lucas-Chan entregue Avl.java:
        // usuariosActivos.buscar(usuarioEnSesion).setActivo(true);
        System.out.println("  [AVL] Actualizando estado del usuario... ⏳ P2 pendiente");
    }

    /**
     * C4: Explorar género musical y encolar canciones.
     * TDAs: ArbolGenerico + ArbolB + Cola
     *
     * Flujo:
     * 1. Mostrar la jerarquía de géneros con recorridoAmplitud().
     * 2. Buscar una canción por ID en el ArbolB.
     * 3. Encolarla en la Cola de reproducción.
     */
    static void consultaC4() {
        System.out.println("\n── C4: Explorar género y encolar ──");
        System.out.println("  [ArbolGenerico + ArbolB + Cola]");

        // PASO 1 — ArbolGenerico: mostrar géneros disponibles
        System.out.println("  [ArbolGenerico] Géneros disponibles:");
        arbolGeneros.recorridoAmplitud();

        // PASO 2 — ArbolB: buscar canción por ID en el catálogo
        System.out.print("  ID de canción a encolar (1-5): ");
        int id = leerInt();
        boolean encontrada = catalogoHistorico.buscar(new Cancion(id, "", "", rock, 0));

        if (!encontrada) {
            System.out.println("  [ArbolB] ❌ Canción ID " + id + " no encontrada en el catálogo.");
            return;
        }

        // PASO 3 — Cola: encolar la canción encontrada
        Cancion[] todas = {c1, c2, c3, c4, c5};
        for (Cancion c : todas) {
            if (c.getId() == id) {
                colaReproduccion.enqueue(c);
                System.out.println("  [ArbolB] ✅ Encontrada: " + c);
                System.out.println("  [Cola]   ✅ Encolada.");
                System.out.println("  Próxima en cola: " + colaReproduccion.front());
                break;
            }
        }
    }

    // ── Utilidades ────────────────────────────────────────────────────────────

    static int leerInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("  ⚠ Ingresá un número: ");
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
}