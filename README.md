# Sistema de Streaming de Audio

> Trabajo Práctico Obligatorio — Algoritmos y Estructuras de Datos II  
> Universidad Argentina de la Empresa (UADE)

---

## 📌 Descripción

**Espotifai** es una plataforma de streaming de audio simulada por consola que integra **9 TDAs** vistos en la materia. El sistema gestiona usuarios, canciones, géneros musicales, servidores CDN y soporte técnico, demostrando la aplicación práctica de cada estructura de datos en un dominio coherente.

---

## 👥 Equipo

| Integrante | Rol | TDAs a cargo |
|---|---|---|
| Gabriel | Arquitecto / Integrador (P1) | Diccionario, Main, Consultas complejas |
| Lucas | P2 | ABB, AVL |
| Brisa | P3 | Árbol B, Árbol Genérico |
| Tobias | P4 | Pila, Cola, Cola con Prioridad, SoporteTecnico |
| Nestor | P5 | Grafo |

---

## 🗂️ Estructura del proyecto

```
src/
├── Clases/
│   ├── Cancion.java
│   ├── Categoria.java
│   ├── Playlist.java
│   ├── Servidor.java
│   ├── SoporteTecnico.java
│   ├── Stream.java
│   └── Usuario.java
├── Interfaces/
│   ├── iAbb.java
│   ├── iArbolB.java
│   ├── iArbolGenerico.java
│   ├── iAvl.java
│   ├── iCola.java
│   ├── iColaConPrioridad.java
│   ├── iDiccionario.java
│   ├── iGrafo.java
│   └── iPila.java
├── TDA/
│   ├── Abb.java
│   ├── ArbolB.java
│   ├── ArbolGenerico.java
│   ├── Arista.java
│   ├── Avl.java
│   ├── Cola.java
│   ├── ColaConPrioridad.java
│   ├── Diccionario.java
│   ├── Grafo.java
│   ├── Nodo.java
│   ├── NodoABB.java
│   ├── NodoAVL.java
│   ├── NodoNario.java
│   ├── NodoPrioridad.java
│   ├── Pila.java
│   └── Vertice.java
└── Main.java
```

---

## 🧩 Mapeo de TDAs al dominio

| TDA | Uso en Espotifai | Complejidad clave |
|---|---|---|
| **ABB** | Catálogo de servidores CDN ordenado por ID alfabético | O(log n) promedio |
| **AVL** | Índice de usuarios con sesión activa | O(log n) garantizado |
| **Árbol B** (t=2) | Catálogo histórico de canciones | O(log n) |
| **Árbol Genérico** | Jerarquía de géneros musicales (Música → Rock → Nacional) | O(n) recorridos |
| **Grafo** (no dirigido, con pesos) | Red de servidores CDN con latencia en ms | O(V+E) BFS/DFS |
| **Pila** | Historial de navegación del usuario (LIFO) | O(1) |
| **Cola** | Cola de reproducción (FIFO) | O(1) |
| **Cola con Prioridad** | Soporte técnico: PREMIUM antes que GRATUITO | O(n) insert, O(1) extract |
| **Diccionario** | idUsuario → prioridad (1=PREMIUM, 0=GRATUITO) | O(1) amortizado |

---

## 🔍 Consultas complejas

El sistema implementa 4 consultas que combinan mínimo 3 TDAs cada una:

### C1 — Sesión activa → plan → servidor óptimo
`AVL + Diccionario + Grafo`

Cuando el usuario presiona **play**: verifica sesión en el AVL (O log n), consulta su plan en el Diccionario (O 1), y hace BFS + `vecinoMenorPeso` en el Grafo para asignarle el servidor con menor latencia.

### C2 — Ticket → plan → servidor en mantenimiento
`ColaConPrioridad + Diccionario + ABB`

Soporte atiende el ticket más urgente (`extractMax`), consulta el plan del usuario en el Diccionario, y da de baja al servidor fallido del ABB.

### C3 — Atrás → canción → valida género
`Pila + ArbolB + ArbolGenérico`

El usuario presiona atrás: `pop` de la Pila para obtener la pantalla anterior, busca la canción en el Árbol B, y verifica que su género esté registrado en la jerarquía del Árbol Genérico.

### C4 — Género → encolar → verificar sesión
`ArbolGenérico + Cola + AVL`

El usuario elige un género: el Árbol Genérico valida que existe en la jerarquía, la Cola encola todas las canciones de ese género, y el AVL verifica que el usuario tenga sesión activa.

---

## ▶️ Cómo ejecutar

**Requisitos:** Java 17+ y cualquier IDE (IntelliJ IDEA recomendado)

```bash
# Clonar el repositorio
git clone https://github.com/<usuario>/espotifai.git

# Abrir en IntelliJ y ejecutar Main.java
# O desde terminal:
javac -d out src/**/*.java src/Main.java
java -cp out Main
```

---

## 🖥️ Menú principal

```
╔══════════════════════════════════════════╗
║      ESPOTIFAI - STREAMING DE AUDIO      ║
╠══════════════════════════════════════════╣
║  1.  Catalogo canciones   (Arbol B)      ║
║  2.  Usuarios activos     (AVL)          ║
║  3.  Catalogo servidores  (ABB)          ║
║  4.  Generos musicales    (Arbol n-ario) ║
║  5.  Red CDN              (Grafo)        ║
║  6.  Historial            (Pila)         ║
║  7.  Cola reproduccion    (Cola)         ║
║  8.  Usuarios y planes    (Diccionario)  ║
║  9.  Soporte tecnico      (Cola Prior.)  ║
╠══════════════════════════════════════════╣
║  CONSULTAS COMPLEJAS                     ║
║  10. C1: Ruta optima de servidor         ║
║  11. C2: Soporte + Diccionario + ABB     ║
║  12. C3: Deshacer navegacion             ║
║  13. C4: Explorar genero y encolar       ║
╚══════════════════════════════════════════╝
```

---

## 📊 Red CDN (Grafo)

```
       [BA - Buenos Aires]
           /         \
        120ms         30ms
         /               \
  [MX - Mexico]      [SP - Sao Paulo]
       |                   |
     50ms               200ms
       |                   |
  [NY - Nueva York] --80ms-- [MD - Madrid]
```

---

## 🎼 Jerarquía de géneros (Árbol Genérico)

```
  [MUSICA]
  ├── [ROCK]
  ├── [POP]
  ├── [TECHNO]
  ├── [FOLK]
  └── [METAL]
```

---

## 📄 Licencia

Proyecto académico — UADE 2025. No redistribuir.
