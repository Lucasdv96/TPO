import Clases.*;
import TDA.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Clases.Categoria;

import javax.swing.*;

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
    static Abb<Servidor> catalogoServidores = new Abb<>();
    static Avl<Usuario> usuariosActivos = new Avl<>();

    // ── TDA P3: ArbolB y ArbolGenerico ────────────────────────────────────────
    static ArbolB<Cancion>       catalogoHistorico = new ArbolB<>();
    static ArbolGenerico<Categoria> arbolGeneros      = new ArbolGenerico<>();

    // ── TDA P4: Pila, Cola y SoporteTecnico (usa ColaConPrioridad internamente)
    static Pila<String>           pilaNavegacion  = new Pila<>();
    static Cola<Cancion>          colaReproduccion = new Cola<>();
    static SoporteTecnico<String> soporte          = new SoporteTecnico<>();
    static ArrayList<Cancion> listaGlobalCanciones = new ArrayList<>();

    // ── TDA P5: descomentar cuando Nestor-Kawai entregue ─────────────────────
    static Grafo<Servidor> redCDN = new Grafo<>();

    static Scanner scanner = new Scanner(System.in);

    // ── Categorías globales para reutilizar ───────────────────────────────────
    static Categoria musica   = new Categoria("Musica");
    static Categoria rock   = new Categoria("Rock");
    static Categoria techno = new Categoria("Techno");
    static Categoria pop    = new Categoria("Pop");
    static Categoria metal  = new Categoria("Metal");
    static Categoria folk   = new Categoria("Folk");
    static Categoria nacional   = new Categoria("Nacional");
    static Categoria internacional   = new Categoria("Internacional");
    static Categoria house   = new Categoria("House");
    static Categoria deepHouse   = new Categoria("Deep House");

    // ── Canciones globales para reutilizar en submenús ───────────────────────
    static Cancion c1 = new Cancion(1, "Bohemian Rhapsody",      "Queen",          rock,   354);
    static Cancion c2 = new Cancion(2, "Blinding Lights",         "The Weeknd",     pop,    200);
    static Cancion c3 = new Cancion(3, "Strobe",                  "deadmau5",       techno, 601);
    static Cancion c4 = new Cancion(4, "La Llorona",              "Chavela Vargas", folk,   218);
    static Cancion c5 = new Cancion(5, "Smells Like Teen Spirit", "Nirvana",        metal,  301);

    static Servidor svBA = new Servidor("BA", "Buenos Aires");
    static Servidor svMX = new Servidor("MX", "México");
    static Servidor svNY = new Servidor("NY", "Nueva York");
    static Servidor svMD = new Servidor("MD", "Madrid");
    static Servidor svSP = new Servidor("SP", "São Paulo");


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
        arbolGeneros.agregarHijo(null,     musica);
        arbolGeneros.agregarHijo(musica, rock);
        arbolGeneros.agregarHijo(musica, pop);
        arbolGeneros.agregarHijo(musica, techno);
        arbolGeneros.agregarHijo(rock,   nacional);
        arbolGeneros.agregarHijo(rock, internacional);
        arbolGeneros.agregarHijo(techno, house);
        arbolGeneros.agregarHijo(house, deepHouse );

        // Pila: pantalla inicial de navegación
        pilaNavegacion.push("INICIO");

        // Cola: canciones en espera de reproducción
        colaReproduccion.enqueue(c1);
        colaReproduccion.enqueue(c2);
        colaReproduccion.enqueue(c3);

        // ABB: Servidores - P2
        catalogoServidores.insertar(svBA);
        catalogoServidores.insertar(svMX);
        catalogoServidores.insertar(svNY);
        catalogoServidores.insertar(svMD);
        catalogoServidores.insertar(svSP);

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

        // Usuarios activos — P2 (¡Ahora funcionando!)
        u1.setActivo(true);
        u4.setActivo(true);
        u5.setActivo(true);
        usuariosActivos.insertar(u1);
        usuariosActivos.insertar(u4);
        usuariosActivos.insertar(u5);

        System.out.println("Datos de prueba subidos perfectamente.");
    }

    // ── Menú principal ────────────────────────────────────────────────────────
    public static void main(String[] args) throws InterruptedException {
        inicializarDatos();
        cargarDatosPrueba();
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║   SISTEMA DE STREAMING DE AUDIO          ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1.  Catálogo canciones  (Árbol B)        ║");
            System.out.println("║  2.  Usuarios activos    (AVL) ✅         ║");
            System.out.println("║  3.  Catálogo servidores (ABB) ✅         ║");
            System.out.println("║  4.  Géneros musicales   (Árbol n-ario)   ║");
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
        System.out.println("\n── Usuarios Activos (AVL) ──");
        System.out.println("  1. Iniciar sesión / Conectar (Insertar)");
        System.out.println("  2. Buscar usuario conectado");
        System.out.println("  3. Cerrar sesión / Desconectar (Eliminar)");
        System.out.println("  4. Métricas (Altura y Factor de Equilibrio)");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                System.out.print("  ID del usuario a conectar: ");
                int id = leerInt();

                // Verificamos si existe en nuestro sistema (Diccionario) primero
                if (diccionarioUsuarios.contains(id)) {
                    int plan = diccionarioUsuarios.get(id);
                    Usuario.TipoCuenta tipo = (plan == 1) ? Usuario.TipoCuenta.PREMIUM : Usuario.TipoCuenta.GRATUITO;

                    // Creamos el usuario con el constructor manual para mantener el ID
                    Usuario uActivo = new Usuario(id, "Usuario_" + id, "email@mail.com", tipo);
                    uActivo.setActivo(true);

                    usuariosActivos.insertar(uActivo);
                    System.out.println("  ✅ Usuario ID " + id + " conectado e insertado en el AVL.");
                } else {
                    System.out.println("  ❌ Usuario no registrado. Registrelo primero en el Diccionario (Menú 8).");
                }
            }
            case 2 -> {
                System.out.print("  ID del usuario a buscar: ");
                int id = leerInt();

                Usuario fantasma = new Usuario(id, "", "", Usuario.TipoCuenta.GRATUITO);
                Usuario encontrado = usuariosActivos.buscar(fantasma);

                if (encontrado != null) {
                    System.out.println("  ✅ El usuario ID " + id + " está ACTIVO en el sistema.");
                } else {
                    System.out.println("  ❌ El usuario ID " + id + " NO está activo.");
                }
            }
            case 3 -> {
                System.out.print("  ID del usuario a desconectar: ");
                int id = leerInt();

                Usuario fantasma = new Usuario(id, "", "", Usuario.TipoCuenta.GRATUITO);
                if (usuariosActivos.buscar(fantasma) != null) {
                    usuariosActivos.eliminar(fantasma);
                    System.out.println("  ✅ Usuario ID " + id + " desconectado (eliminado del AVL).");
                } else {
                    System.out.println("  ❌ El usuario ID " + id + " no estaba activo.");
                }
            }
            case 4 -> {
                System.out.println("  📈 Altura actual del árbol AVL: " + usuariosActivos.mostrarAltura());
                System.out.print("  Ingrese ID de un usuario activo para ver su Factor de Equilibrio: ");
                int id = leerInt();

                Usuario fantasma = new Usuario(id, "", "", Usuario.TipoCuenta.GRATUITO);
                if (usuariosActivos.buscar(fantasma) != null) {
                    int fe = usuariosActivos.factorEquilibrio(fantasma);
                    System.out.println("  ⚖ Factor de equilibrio del nodo (ID " + id + "): " + fe);
                } else {
                    System.out.println("  ❌ El usuario ID " + id + " no está en el árbol AVL.");
                }
            }
            default -> System.out.println("  ⚠ Opción inválida.");
        }
    }

    // ── 3. ABB ────────────────────────────────────────────────────────────────
    static void menuABB() {
        System.out.println("\n── Catálogo de Servidores Activos (ABB) ──");
        System.out.println("  1. Registrar / Insertar nuevo Servidor");
        System.out.println("  2. Buscar Servidor por ID");
        System.out.println("  3. Dar de baja / Eliminar Servidor");
        System.out.println("  4. Mostrar todos los Servidores (Inorden alfabético)");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                scanner.nextLine(); // Limpiar buffer
                System.out.print("  Ingrese ID del Servidor (ej: PT): ");
                String id = scanner.nextLine().toUpperCase();
                System.out.print("  Ingrese Nombre de la Ciudad: ");
                String ciudad = scanner.nextLine();

                Servidor nuevoSv = new Servidor(id, ciudad);
                catalogoServidores.insertar(nuevoSv);
                System.out.println("  ✅ Servidor " + id + " insertado correctamente en el ABB.");
            }
            case 2 -> {
                scanner.nextLine(); // Limpiar buffer
                System.out.print("  Ingrese el ID del Servidor a buscar: ");
                String idBusqueda = scanner.nextLine().toUpperCase();

                // Creamos un objeto fantasma con el ID para la comparación
                Servidor buscado = catalogoServidores.buscar(new Servidor(idBusqueda, ""));
                if (buscado != null) {
                    System.out.println("  ✅ Servidor encontrado: " + buscado.getCiudad() + " (" + buscado.getId() + ")");
                } else {
                    System.out.println("  ❌ El Servidor con ID " + idBusqueda + " no existe en el catálogo.");
                }
            }
            case 3 -> {
                scanner.nextLine(); // Limpiar buffer
                System.out.print("  Ingrese el ID del Servidor a eliminar: ");
                String idEliminar = scanner.nextLine().toUpperCase();

                Servidor objetivo = new Servidor(idEliminar, "");
                if (catalogoServidores.buscar(objetivo) != null) {
                    catalogoServidores.eliminar(objetivo);
                    System.out.println("  ✅ Servidor " + idEliminar + " eliminado del ABB.");
                } else {
                    System.out.println("  ❌ No se encontró el Servidor con ID " + idEliminar + ".");
                }
            }
            case 4 -> {
                System.out.println("\n  --- SERVIDORES REGISTRADOS (Inorden) ---");
                if (catalogoServidores.esVacio()) {
                    System.out.println("  (El árbol está vacío)");
                } else {
                    catalogoServidores.inorden();
                }
            }
            default -> System.out.println("  ⚠ Opción inválida.");
        }
    }

    // ── 4. ÁRBOL N-ARIO ───────────────────────────────────────────────────────
    static void menuArbolNario() {
        System.out.println("\n── Géneros Musicales (Árbol n-ario) ──");
        System.out.println("  1. Agregar subgénero");
        System.out.println("  2. Buscar generos");
        System.out.println("  3. Recorrido en amplitud  (BFS — nivel por nivel)");
        System.out.println("  4. Recorrido en profundidad (DFS — rama por rama)");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                scanner.nextLine();
                System.out.print("  Género padre (ej: Rock): ");
                String padre = scanner.nextLine();
                System.out.print("  Nuevo subgénero: ");
                String hijo = scanner.nextLine();


                Categoria categoriaPadre = new Categoria(padre);
                Categoria categoriaHijo = new Categoria(hijo);

                arbolGeneros.agregarHijo(categoriaPadre, categoriaHijo);

                System.out.println("  ✅ '" + hijo + "' agregado bajo '" + padre + "'.");
            }
            case 2 -> {
                System.out.println("\n --- BUSCAR GENEROS ---");

                scanner.nextLine();
                System.out.print("  🎵 Ingresá el género/categoría a buscar (ej: Rock): ");
                String nombreBuscar = scanner.nextLine();

                Categoria categoriaBuscada = new Categoria(nombreBuscar);

                if (arbolGeneros.existeCategoria(categoriaBuscada)) {
                    System.out.println("\n ¡Género '" + nombreBuscar + "' validado en el Árbol Genérico!");
                    System.out.println("Canciones encontradas en esta categoría:");
                    System.out.println("  ──────────────────────────────────────────");

                    boolean encontroAlguna = false;

                    for (Cancion c : listaGlobalCanciones) {
                        if (c.getCategoria().equals(categoriaBuscada)) {
                            System.out.println("  ID: " + c.getId() + " | " + c.getTitulo() + " - " + c.getArtista());
                            encontroAlguna = true;
                        }
                    }
                    if (!encontroAlguna) {
                        System.out.println("  (No hay canciones registradas bajo este género todavía).");
                    }

                } else {
                    System.out.println("  ❌ Error: El género '" + nombreBuscar + "' no existe en la jerarquía del Árbol.");
                }
                System.out.println("  ──────────────────────────────────────────");

            }
            case 3 -> {
                System.out.println("\n  --- RECORRIDO EN AMPLITUD (nivel por nivel) ---");
                arbolGeneros.recorridoAmplitud();
            }
            case 4 -> {
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
        System.out.println("  1. Ir a nueva cancion (push)");
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
                    pilaNavegacion.push(c.getTitulo());
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
     */
    static void consultaC1() {
        System.out.println("\n── C1: Ruta óptima de servidor ──");
        System.out.println("  [AVL + Diccionario + Grafo]");
        System.out.print("  ID de usuario: ");
        int idUsuario = leerInt();

        // PASO 1 — AVL: verificar que el usuario esté activo
        Usuario fake = new Usuario(idUsuario, "", "", Usuario.TipoCuenta.GRATUITO);
        Usuario encontrado = usuariosActivos.buscar(fake);
        if (encontrado == null) {
            System.out.println("  [AVL] ❌ Usuario no activo. No se puede calcular ruta.");
            return;
        }
        System.out.println("  [AVL] ✅ Usuario ID " + idUsuario + " validado como activo.");

        // PASO 2 — Diccionario: consultar su plan
        if (diccionarioUsuarios.contains(idUsuario)) {
            int p = diccionarioUsuarios.get(idUsuario);
            System.out.println("  [Diccionario] ✅ Plan: " + (p == 1 ? "PREMIUM" : "GRATUITO"));
        } else {
            System.out.println("  [Diccionario] ❌ Usuario no registrado.");
            return;
        }

        // PASO 3 — Grafo: BFS para ruta óptima y servidor con menor latencia
        System.out.println("  [Grafo] BFS desde BA: " + redCDN.BFS(svBA));
        Servidor optimo = redCDN.vecinoMenorPeso(svBA);
        System.out.println("  [Grafo] ✅ Servidor con menor latencia desde BA: " + optimo);
    }

    /**
     * C2: Atender problema de soporte y verificar servidor.
     * TDAs: ColaConPrioridad + Diccionario + ABB
     */
    static void consultaC2() throws InterruptedException {
        System.out.println("\n── C2: Atender soporte y verificar infraestructura ──");
        System.out.println("  [ColaConPrioridad + Diccionario + ABB]");

        // PASO 1 — ColaConPrioridad: extraer el problema
        Object proximoProblema = soporte.proxProblema();
        if (proximoProblema == null) {
            System.out.println("  [ColaConPrioridad] ⚠ No hay problemas pendientes. Reportá uno en el Menú 9.");
            return;
        }
        System.out.println("  [ColaConPrioridad] ✅ Resolviendo: " + proximoProblema);
        soporte.arreglarProblema();

        // PASO 2 — Diccionario: verificar qué plan tiene el afectado
        System.out.print("  ID del usuario que reportó el problema: ");
        int idUsuario = leerInt();
        if (diccionarioUsuarios.contains(idUsuario)) {
            int p = diccionarioUsuarios.get(idUsuario);
            System.out.println("  [Diccionario] ✅ Usuario " + idUsuario
                    + " → " + (p == 1 ? "PREMIUM (Atendido con prioridad ✅)" : "GRATUITO"));
        } else {
            System.out.println("  [Diccionario] ❌ Usuario no encontrado.");
        }

        // PASO 3 — ABB: verificar el servidor asignado al problema
        System.out.print("  [ABB] Ingrese ID del servidor que falló (ej. MX): ");
        String idSv = scanner.next().toUpperCase();
        Servidor svEncontrado = catalogoServidores.buscar(new Servidor(idSv, ""));
        if (svEncontrado != null) {
            System.out.println("  [ABB] ✅ Servidor " + svEncontrado.getCiudad() + " verificado en el catálogo para mantenimiento.");
        } else {
            System.out.println("  [ABB] ❌ El servidor " + idSv + " no figura en el árbol activo.");
        }
    }

    /**
     * C3: Deshacer navegación, verificar canción y sesión.
     * TDAs: Pila + ArbolB + AVL
     */
    static void consultaC3() {
        System.out.println("\n── C3: Deshacer navegación ──");
        System.out.println("  [Pila + ArbolB + AVL]");

        // PASO 1 — Pila: volver atrás
        if (pilaNavegacion.isEmpty()) {
            System.out.println("  [Pila] ⚠ No hay historial.");
            return;
        }
        String pantallaAnterior = pilaNavegacion.pop();
        System.out.println("  [Pila] ✅ Saliste de: " + pantallaAnterior);

        // PASO 2 — ArbolB: verificar canción
        if (pantallaAnterior.startsWith("CANCION:")) {
            int idCancion = Integer.parseInt(pantallaAnterior.split(":")[1]);
            boolean encontrada = catalogoHistorico.buscar(new Cancion(idCancion, "", "", rock, 0));
            System.out.println("  [ArbolB] Canción ID " + idCancion + (encontrada ? " ✅ encontrada." : " ❌ no encontrada."));
        }

        // PASO 3 — AVL: verificar sesión
        System.out.print("  [AVL] ID de usuario operando la app: ");
        int idUsuario = leerInt();
        Usuario fake = new Usuario(idUsuario, "", "", Usuario.TipoCuenta.GRATUITO);
        Usuario usuarioEnSesion = usuariosActivos.buscar(fake);
        if (usuarioEnSesion != null) {
            System.out.println("  [AVL] ✅ La sesión del usuario ID " + idUsuario + " sigue activa.");
        } else {
            System.out.println("  [AVL] ⚠ La sesión expiró o el usuario no está activo.");
        }
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
    public static void inicializarDatos() {
        listaGlobalCanciones.add(c1);
        listaGlobalCanciones.add(c2);
        listaGlobalCanciones.add(c3);
        listaGlobalCanciones.add(c4);
        listaGlobalCanciones.add(c5);
    }
}