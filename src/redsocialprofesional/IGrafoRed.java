package redsocialprofesional;

public interface IGrafoRed {

    void insertarVertice(Usuario vertice);

    void eliminarVertice(Usuario vertice);

    void insertarArista(Usuario origen, Usuario destino);

    void eliminarArista(Usuario origen, Usuario destino);

    boolean existeVertice(Usuario vertice);

    boolean existeArista(Usuario origen, Usuario destino);

    void mostrarMatriz();

    void mostrarVertices();

    void mostrarContactos(Usuario usuario);

    // para ver contactos de contactos
    void recomendarContactos(Usuario usuario);
}
