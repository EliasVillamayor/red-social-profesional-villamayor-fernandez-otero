package redsocialprofesional;

public class GrafoRed implements IGrafoRed {

    private Usuario[] vertices;
    private int[][] matriz;
    private int cantidad;
    private int capacidad;
    private boolean dirigido;

    public GrafoRed(int capacidad, boolean dirigido) {
        this.capacidad = capacidad;
        this.dirigido = dirigido;
        this.cantidad = 0;
        this.vertices = new Usuario[capacidad];
        this.matriz = new int[capacidad][capacidad];
    }

    @Override
    public void insertarVertice(Usuario vertice) {
        if (cantidad == capacidad) {
            System.out.println("No se pueden insertar más vértices.");
            return;
        }

        if (existeVertice(vertice)) {
            System.out.println("El vértice ya existe.");
            return;
        }

        vertices[cantidad] = vertice;
        cantidad++;
    }

    @Override
    public boolean existeVertice(Usuario vertice) {
        return obtenerIndice(vertice) != -1;
    }

    private int obtenerIndice(Usuario vertice) {
        for (int i = 0; i < cantidad; i++) {
            if (vertices[i].equals(vertice)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void insertarArista(Usuario origen, Usuario destino) {
        int posOrigen = obtenerIndice(origen);
        int posDestino = obtenerIndice(destino);

        if (posOrigen == -1 || posDestino == -1) {
            System.out.println("Uno de los vértices no existe.");
            return;
        }

        matriz[posOrigen][posDestino] = 1;

        if (!dirigido) {
            matriz[posDestino][posOrigen] = 1;
        }
    }

    @Override
    public void eliminarArista(Usuario origen, Usuario destino) {
        int posOrigen = obtenerIndice(origen);
        int posDestino = obtenerIndice(destino);

        if (posOrigen == -1 || posDestino == -1) {
            System.out.println("Uno de los vértices no existe.");
            return;
        }

        matriz[posOrigen][posDestino] = 0;

        if (!dirigido) {
            matriz[posDestino][posOrigen] = 0;
        }
    }

    @Override
    public boolean existeArista(Usuario origen, Usuario destino) {
        int posOrigen = obtenerIndice(origen);
        int posDestino = obtenerIndice(destino);

        if (posOrigen == -1 || posDestino == -1) {
            return false;
        }

        return matriz[posOrigen][posDestino] == 1;
    }

    @Override
    public void eliminarVertice(Usuario vertice) {
        int pos = obtenerIndice(vertice);

        if (pos == -1) {
            System.out.println("El vértice no existe.");
            return;
        }

        for (int i = pos; i < cantidad - 1; i++) {
            vertices[i] = vertices[i + 1];
        }

        for (int i = pos; i < cantidad - 1; i++) {
            for (int j = 0; j < cantidad; j++) {
                matriz[i][j] = matriz[i + 1][j];
            }
        }

        for (int j = pos; j < cantidad - 1; j++) {
            for (int i = 0; i < cantidad; i++) {
                matriz[i][j] = matriz[i][j + 1];
            }
        }

        cantidad--;
        vertices[cantidad] = null;

        for (int i = 0; i < capacidad; i++) {
            matriz[cantidad][i] = 0;
            matriz[i][cantidad] = 0;
        }
    }

    @Override
    public void mostrarVertices() {
        System.out.println("Vértices:");

        for (int i = 0; i < cantidad; i++) {
            System.out.print(vertices[i] + " ");
        }

        System.out.println();
    }

    @Override
    public void mostrarMatriz() {
        System.out.println("Matriz de adyacencia:");

        System.out.print("   ");

        for (int i = 0; i < cantidad; i++) {
            System.out.print(vertices[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < cantidad; i++) {
            System.out.print(vertices[i] + "  ");

            for (int j = 0; j < cantidad; j++) {
                System.out.print(matriz[i][j] + " ");
            }

            System.out.println();
        }
    }

    @Override
    public void mostrarContactos(Usuario usuario) {
        int pos = obtenerIndice(usuario);

        if (pos == -1) {
            System.out.println("El usuario no existe.");
            return;
        }

        System.out.print("Contactos de " + usuario.getNombre() + ": ");

        for (int i = 0; i < cantidad; i++) {
            if (matriz[pos][i] == 1) {
                System.out.print(vertices[i].getNombre() + " ");
            }
        }

        System.out.println();
    }

    // ---------------------------------------------------------------
    // BFS para recomendar contactos de contactos
    //
    // Idea:
    //   - visitados[]  → para no procesar el mismo nodo dos veces en el BFS
    //   - esDirecto[]  → para saber si ya es contacto directo del usuario
    //   - La cola del BFS maneja índices (int), por eso usamos un array
    //     circular simple en lugar de ColaPostulantes (que es de Usuario).
    //
    // Recorre nivel 1 (contactos directos) y nivel 2 (sus contactos).
    // Recomienda los del nivel 2 que no sean el propio usuario
    // ni ya contactos directos suyos.
    // ---------------------------------------------------------------
    @Override
    public void recomendarContactos(Usuario usuario) {
        int posOrigen = obtenerIndice(usuario);

        if (posOrigen == -1) {
            System.out.println("El usuario no existe.");
            return;
        }

        // Cola BFS con índices: usamos un array y dos punteros (cabeza y cola)
        int[] colaBFS = new int[capacidad];
        int cabeza = 0;
        int colaIdx = 0;

        // visitados[i] = true si ya fue procesado en el BFS
        boolean[] visitados = new boolean[capacidad];

        // esDirecto[i] = true si el nodo i es contacto directo del origen
        boolean[] esDirecto = new boolean[capacidad];

        // Marcar contactos directos (nivel 1)
        for (int i = 0; i < cantidad; i++) {
            if (matriz[posOrigen][i] == 1) {
                esDirecto[i] = true;
            }
        }

        // Arrancar BFS desde el nodo origen
        visitados[posOrigen] = true;
        colaBFS[colaIdx] = posOrigen;
        colaIdx++;

        System.out.print("Recomendaciones para " + usuario.getNombre() + ": ");
        boolean hayRecomendaciones = false;

        while (cabeza < colaIdx) {
            int actual = colaBFS[cabeza];
            cabeza++;

            // Recorrer vecinos del nodo actual
            for (int i = 0; i < cantidad; i++) {
                if (matriz[actual][i] == 1 && !visitados[i]) {
                    visitados[i] = true;
                    colaBFS[colaIdx] = i;
                    colaIdx++;

                    // Es recomendable si no es el propio usuario
                    // y no es ya contacto directo
                    if (i != posOrigen && !esDirecto[i]) {
                        System.out.print(vertices[i].getNombre() + " ");
                        hayRecomendaciones = true;
                    }
                }
            }
        }

        if (!hayRecomendaciones) {
            System.out.print("(ninguna por ahora)");
        }

        System.out.println();
    }
}
