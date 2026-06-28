import Clases.*;
import TDA.Cd;

// Cuando los terminan descomentan el TDA que hicieron
// import TDA.Abb;
// import TDA.Avl;
import TDA.ArbolB;
import TDA.ArbolGenerico;
// import TDA.Grafo;
import TDA.Cola;
import TDA.ColaConPrioridad;
import TDA.Pila;

import java.util.Scanner;

/**
 * Main — Sistema de Streaming de Audio espotifai
 * P1 La cabra / Arquitectura y diccionario.
 * P2 Lucas-Chan
 * P3 Brichota
 * P4 El mogolico de tobias tkm amigo
 * P5 Nestor-Kawai
 * Pt El profe
 */
///
    // TDA de P1

    static Cd<String, Stream> streamDictionary = new Cd<>();

    // TDAs de P2 (descomentar P2 entregues 😏)
    // static Abb<Canal>   catalogoCanales = new Abb<>();
    // static Avl<Usuario> usuariosActivos = new Avl<>();

    // TDAs de P3 (descomentar P3 cuando lo subas)
    static ArbolB<Cancion> catalogoHistorico = new ArbolB<>();
    static ArbolGenerico<String> arbolGeneros  = new ArbolGenerico<>();

    // TDAs de P4 (Descomentar cuando este lo del Autista de toto 🎻)
    static Pila<String> pilaNavegacion = new Pila<>();
    static Cola<Cancion> colaReproduccion = new Cola<>();
    static ColaConPrioridad<Stream> colaTranscoding = new ColaConPrioridad<>();

    // TDA de P5 (Nada lo mismo que lo de arriba ya me da paja escribir)
    // static Grafo<Servidor> redCDN = new Grafo<>();

    //Scanner ya habiamos usado con toto, esta medio rustico y medio con Ia pero se puede mejorar.
    static Scanner scanner = new Scanner(System.in);
    static final SoporteTecnico<Object> soporte = new SoporteTecnico<>();

    // Datos para probar de ejemplo
    static void cargarDatosPrueba() {

        SoporteTecnico soporteTecnico = new SoporteTecnico<>();

        // le pedi los ejemplos la IA ya me daba paja pensar
        Cancion c1 = new Cancion(1, "Bohemian Rhapsody", "Queen",       "Rock",        354);
        Cancion c2 = new Cancion(2, "Blinding Lights",   "The Weeknd",  "Pop",         200);
        Cancion c3 = new Cancion(3, "Strobe",            "deadmau5",    "Electrónica", 601);
        Cancion c4 = new Cancion(4, "La Llorona",        "Chavela Vargas","Folk",      218);
        Cancion c5 = new Cancion(5, "Smells Like Teen Spirit","Nirvana", "Rock",       301);

        // Usuarios
        Usuario u1 = new Usuario(101, "Gabriel",  "gaby@mail.com",   Usuario.TipoCuenta.PREMIUM);
        Usuario u2 = new Usuario(102, "Lucas",    "lucas@mail.com",  Usuario.TipoCuenta.GRATUITO);
        Usuario u3 = new Usuario(103, "Toto",    "tobias@mail.com",  Usuario.TipoCuenta.GRATUITO);
        Usuario u4 = new Usuario(104, "Nestor",   "nestor@mail.com",   Usuario.TipoCuenta.PREMIUM);
        Usuario u5 = new Usuario(105, "brisa",   "brichota@mail.com",   Usuario.TipoCuenta.PREMIUM);

        // Canales
        CreadorCont canal1 = new CreadorCont(1, "rockclasico",  "Rock de los 70s-90s");
        CreadorCont canal2 = new CreadorCont(2, "electronicos", "Música electrónica y house");
        CreadorCont canal3 = new CreadorCont(3, "latinpop",     "Pop en español");

        // Streams activos en el Diccionario
        streamDictionary.put("STR-001", new Stream("STR-001", 101, 1));
        streamDictionary.put("STR-002", new Stream("STR-002", 102, 3));
        streamDictionary.put("STR-003", new Stream("STR-003", 103, 2));

        // TODO P2: insertar usuarios en AVL y canales en ABB
        // usuariosActivos.insertar(u1); usuariosActivos.insertar(u2); ...
        // catalogoCanales.insertar(canal1); ...

        // TODO P3: insertar canciones en ArbolB y géneros en ArbolGenerico
        // catalogoHistorico.insertar(c1); ...
        // arbolGeneros.agregarHijo(raiz, subgenero); ...

        // TODO P4: apilar pantalla inicial en Pila
        pilaNavegacion.push("INICIO");

        // TODO P5: construir red CDN en Grafo
        // Servidor sBA = new Servidor("BA","Buenos Aires"); ...
        // redCDN.agregarVertice(sBA); redCDN.agregarArista(sBA, sMX, 120);

        System.out.println("Datos de prueba subidos perfectamente.");
    }

    // menu principal para el "usuario"
    void main(String[] args) throws InterruptedException {
        cargarDatosPrueba();
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║   SISTEMA DE STREAMING DE AUDIO          ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Gestión de canciones (Árbol B)       ║");
            System.out.println("║  2. Gestión de usuarios (AVL)            ║");
            System.out.println("║  3. Catálogo de canales (ABB)            ║");
            System.out.println("║  4. Géneros musicales (Árbol n-ario)     ║");
            System.out.println("║  5. Red CDN (Grafo)                      ║");
            System.out.println("║  6. Historial navegación (Pila)          ║");
            System.out.println("║  7. Cola de reproducción (Cola)          ║");
            System.out.println("║  8. Streams activos (Diccionario)        ║");
            System.out.println("║  9. Solucion de Problemas (Cola Prior.)  ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  CONSULTAS COMPLEJAS                     ║");
            System.out.println("║  10. C1: Ruta óptima de servidor         ║");
            System.out.println("║  11. C2: Procesar transcodificación      ║");
            System.out.println("║  12. C3: Deshacer navegación             ║");
            System.out.println("║  13. C4: Explorar género y encolar       ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  0. Salir                                ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("  Opción: ");

            // no se es esto, lo hizo claudio
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
                case 10 -> {
                    System.out.println("\n── C1: Ruta óptima de servidor ──");
                    System.out.print("  Ingresá el ID de usuario: ");
                    int idUsuario = leerInt();

                    // Paso 1: buscar usuario en AVL
                    // TODO P2: Usuario u = usuariosActivos.buscar(new Usuario(idUsuario,...));
                    System.out.println("  [AVL] Buscando usuario ID " + idUsuario + "... ⏳ P2 pendiente");

                    // Paso 2: buscar su stream activo en el Diccionario
                    Stream streamActivo = null;
                    for (String key : streamDictionary.claves()) {
                        Stream s = streamDictionary.get(key);
                        if (s.getIdUsuario() == idUsuario && s.getEstado() == Stream.Estado.ACTIVO) {
                            streamActivo = s;
                            break;
                        }
                    }
                    if (streamActivo == null) {
                        System.out.println("  [Diccionario] ❌ No hay stream activo para ese usuario.");
                    } else {
                        System.out.println("  [Diccionario] ✅ Stream encontrado: " + streamActivo);

                        // Paso 3: BFS en el Grafo CDN para encontrar servidor con menor latencia
                        // TODO P5: List<Servidor> ruta = redCDN.BFS(servidorOrigen);
                        System.out.println("  [Grafo] Calculando ruta BFS... ⏳ P5 pendiente");
                        System.out.println("  ─ Resultado esperado: servidor más cercano con menor latencia.");
                    }
                }
                case 11 -> consultaC2();
                case 12 -> consultaC3();
                case 13 -> consultaC4();
                case 0  -> {
                    System.out.println("\nHasta la proximaa.");
                    scanner.close();
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    //sub menus por los TDA

    static void menuDiccionario() {
        System.out.println("\n── Streams Activos (Diccionario) ──");
        System.out.println("  1. Ver todos los streams");
        System.out.println("  2. Buscar stream por ID");
        System.out.println("  3. Iniciar nuevo stream");
        System.out.println("  4. Finalizar stream");
        System.out.print("  Opción: ");
        switch (leerInt()) {
            case 1 -> {
                System.out.println("Streams activos (" + streamDictionary.size() + "):");
                streamDictionary.mostrar();
            }
            case 2 -> {
                System.out.print("  StreamId: ");
                String id = scanner.next();
                Stream s = streamDictionary.get(id);
                System.out.println(s != null ? "  " + s : "   No encontrado.");
            }
            case 3 -> {
                System.out.print("  StreamId (ej: STR-004): ");
                String sid = scanner.next();
                System.out.print("  ID Usuario: ");
                int uid = leerInt();
                System.out.print("  ID Canción: ");
                int cid = leerInt();
                streamDictionary.put(sid, new Stream(sid, uid, cid));
                System.out.println("  Stream iniciado.");
            }
            case 4 -> {
                System.out.print("  StreamId a finalizar: ");
                String sid = scanner.next();
                if (streamDictionary.contains(sid)) {
                    streamDictionary.get(sid).setEstado(Stream.Estado.FINALIZADO);
                    streamDictionary.remove(sid);
                    System.out.println("  Stream finalizado y removido.");
                } else {
                    System.out.println("  Stream no encontrado.");
                }
            }
        }
    }

    static void menuArbolB() {
        System.out.println("\n── Catálogo Histórico (Árbol B) ──");
        System.out.println("  1. Cargar canciones de prueba");
        System.out.println("  2. Buscar canción por ID");
        System.out.println("  3. Mostrar catálogo completo (Ordenado)");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                catalogoHistorico.insertar(new Cancion(20, "Bohemian Rhapsody", "Queen", "Rock", 354));
                catalogoHistorico.insertar(new Cancion(40, "Blinding Lights", "The Weeknd", "Pop", 200));
                catalogoHistorico.insertar(new Cancion(30, "Strobe", "deadmau5", "Electrónica", 601));
                catalogoHistorico.insertar(new Cancion(25, "La Llorona", "Chavela Vargas", "Folk", 218));
                System.out.println(" Canciones insertadas con éxito!");
            }
            case 2 -> {
                System.out.print("  Ingresá el ID de la canción a buscar: ");
                int idBusqueda = leerInt();
                boolean encontrada = catalogoHistorico.buscar(new Cancion(idBusqueda, "", "", "", 0));
                if (encontrada) {
                    System.out.println("Cancion "+ idBusqueda + " encontrada en el Árbol B!");
                } else {
                    System.out.println(" Canción no encontrada.");
                }
            }
            case 3 -> {
                System.out.println("\n--- CONTENIDO DEL ÁRBOL B (Inorden) ---");
                catalogoHistorico.mostrar();
            }
        }
    }

    static void menuAVL() {
        System.out.println("\n── Usuarios Activos (AVL) ── [P2]");
        // TODO P2: insertar/buscar/eliminar/altura/factorEquilibrio
        System.out.println("  ⏳ Pendiente — P2 entrega esta semana.");
    }

    static void menuABB() {
        System.out.println("\n── Catálogo de Canales (ABB) ── [P2]");
        // TODO P2: insertar/buscar/eliminar/inorden
        System.out.println("  ⏳ Pendiente — P2 entrega esta semana.");
    }

    static void menuArbolNario() {
        System.out.println("\n── Géneros Musicales (Árbol n-ario) ── ");
        System.out.println("  1. Inicializar y cargar árbol de géneros");
        System.out.println("  2. Mostrar recorrido en Amplitud (BFS)");
        System.out.println("  3. Mostrar recorrido en Profundidad (DFS)");
        System.out.print("  Opción: ");

        switch (leerInt()) {
            case 1 -> {
                arbolGeneros = new ArbolGenerico<>();
                arbolGeneros.agregarHijo(null, "Música");

                arbolGeneros.agregarHijo("Música", "Rock");
                arbolGeneros.agregarHijo("Música", "Pop");
                arbolGeneros.agregarHijo("Música", "Trap");
                arbolGeneros.agregarHijo("Rock", "Nac");
                arbolGeneros.agregarHijo("Rock", "Int");
                System.out.println(" Jerarquía de géneros cargada con éxito.");
            }
            case 2 -> {
                System.out.println("\n--- RECORRIDO EN AMPLITUD ---");
                arbolGeneros.recorridoAmplitud();
            }
            case 3 -> {
                System.out.println("\n--- RECORRIDO EN PROFUNDIDAD ---");
                arbolGeneros.recorridoProfundidad();
            }
        }
    }

    static void menuGrafo() {
        System.out.println("\n── Red CDN (Grafo) ── [P5]");
        // TODO P5: agregarVertice/agregarArista/BFS/DFS
        System.out.println("  ⏳ Pendiente — P5 entrega esta semana.");
    }

    static void menuPila() {
        System.out.println("\n── Historial de Navegación (Pila) ── [P4]");
        // TODO P4: push/pop/peek

        while(!pilaNavegacion.isEmpty()){
            System.out.println("\n Quien esta arriba? " + pilaNavegacion.peek());
            System.out.println("Se nos fue: " + pilaNavegacion.pop());

        }
        System.out.println("\n Ya no hay mas");


        System.out.println("  ⏳ Pendiente — P4 entrega esta semana.");
    }

    static void menuCola() {
        System.out.println("\n── Cola de Reproducción (Cola) ── [P4]");
        // TODO P4: enqueue/dequeue/front
        Cancion c1 = new Cancion(1, "Bohemian Rhapsody", "Queen",       "Rock",        354);
        Cancion c2 = new Cancion(2, "Blinding Lights",   "The Weeknd",  "Pop",         200);
        Cancion c3 = new Cancion(3, "Strobe",            "deadmau5",    "Electrónica", 601);
        colaReproduccion.isEmpty();
        colaReproduccion.enqueue(c1);
        colaReproduccion.enqueue(c2);
        colaReproduccion.enqueue(c3);

        while(!colaReproduccion.isEmpty()){
            System.out.println("\n Siguente Cancion: " + colaReproduccion.front().getTitulo());
            String cancion = colaReproduccion.dequeue().getTitulo();
            System.out.println("\n Escuchando... " + cancion);
            pilaNavegacion.push(cancion);
        }

        System.out.println("  ⏳ Pendiente — P4 entrega esta semana.");
    }

    static void menuColaConPrioridad() throws InterruptedException {
        System.out.println("\n── Solucion de Problemas (Cola con Prioridad) ── [P4]");


        // TODO P4: insert/extractMax/peek
        System.out.println("  1. Reportar Problema/Queja");
        System.out.println("  2. Visualizar siguiente Problema");
        System.out.println("  3. Solucionar Problema");
        System.out.print("  Opción: ");
        switch (leerInt()){
            case 1 ->{
                scanner.nextLine();
                System.out.print("Problema: ");
                String problema = leerString();

                System.out.print("Prioridad (0 o 1): ");
                int prioridad = leerInt();

                soporte.reportarProblema(problema, prioridad);
                System.out.println("Hemos recibido su inconveniente, pronto lo solucionaremos");
                System.out.println();

            }
            case 2 ->{
                Object problema = soporte.proxProblema();
                System.out.println("El proximo problema a resolver es: " + problema);
            }
            case 3 ->{
                soporte.arreglarProblema();
            }
            default -> throw new IllegalStateException("Unexpected value: " + leerInt());
        }

    }

    // Los submenus los hizo la IA, igual los mire y creo que estan bastante bien


    //Procesar lote de transcodificación y registrar resultado.
    //Combina: ColaConPrioridad + ABB + Diccionario
    // aca flasho la Ia podemos intentar hacerlo asi o despues vemos como lo cambiamos.
    static void consultaC2() {
        System.out.println("\n── C2: Procesar transcodificación ──");

        // Paso 1: extraer canal con mayor prioridad de la Cola
        // TODO P4: Canal canal = colaTranscoding.extractMax();
        Stream canal = colaTranscoding.extractMax();
        System.out.println("  [ColaConPrioridad] Extrayendo canal de mayor prioridad... ⏳ P4 pendiente");

        // Paso 2: buscar ese canal en el ABB por username
        // TODO P2: Canal encontrado = catalogoCanales.buscar(canal);
        System.out.println("  [ABB] Buscando canal en catálogo... ⏳ P2 pendiente");

        // Paso 3: registrar en el Diccionario que se procesó
        String streamIdProcesado = "STR-PROC-" + System.currentTimeMillis();
        // streamDictionary.put(streamIdProcesado, new Stream(...));
        System.out.println("  [Diccionario] ✅ Se registraría con ID: " + streamIdProcesado);
    }

    /**
     * C3: Deshacer navegación y actualizar sesión.
     * Combina: Pila + Árbol B + AVL
     */
    static void consultaC3() {
        System.out.println("\n── C3: Deshacer navegación ──");

        // Paso 1: pop de la Pila de navegación
        // TODO P4: String pantallaAnterior = pilaNavegacion.pop();
        System.out.println("  [Pila] Pop de historial de navegación... ⏳ P4 pendiente");

        // Paso 2: buscar la canción correspondiente en el Árbol B
        // TODO P3: Cancion c = catalogoHistorico.buscar(cancionAnterior);
        System.out.println("  [ArbolB] Buscando canción en catálogo histórico... ⏳ P3 pendiente");

        // Paso 3: actualizar estado del usuario en el AVL
        // TODO P2: usuariosActivos.buscar(u).setActivo(true);
        System.out.println("  [AVL] Actualizando estado del usuario... ⏳ P2 pendiente");
    }

    /**
     * C4: Explorar categoría y encolar playlist.
     * Combina: Árbol n-ario + Árbol B + Cola
     */
    static void consultaC4() {
        System.out.println("\n── C4: Explorar género y encolar ──");
        System.out.print("  Género a explorar (ej: Rock): ");
        String genero = scanner.next();

        // Paso 1: recorrer el Árbol n-ario para listar subgéneros
        // TODO P3: arbolGeneros.recorridoAmplitud(); (filtrado por nodo genero)
        System.out.println("  [ArbolGenerico] Subgéneros de '" + genero + "'... ⏳ P3 pendiente");

        // Paso 2: buscar canciones de ese género en el Árbol B
        // TODO P3: List<Cancion> resultados = catalogoHistorico.buscarPorGenero(genero);
        System.out.println("  [ArbolB] Canciones encontradas en catálogo... ⏳ P3 pendiente");

        // Paso 3: encolar en la Cola de reproducción del usuario
        // TODO P4: for (Cancion c : resultados) colaReproduccion.enqueue(c);
        System.out.println("  [Cola] Encolando canciones del género '" + genero + "'... ⏳ P4 pendiente");
        System.out.println("  ─ Resultado esperado: playlist del género lista para reproducir.");
    }

    //

    static int leerInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("  ⚠ Ingresá un número: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    static String leerString() {
        while (!scanner.hasNextLine()) {
            System.out.print("  ⚠ Ingresá un número: ");
            scanner.next();
        }
        return scanner.nextLine();
    }
