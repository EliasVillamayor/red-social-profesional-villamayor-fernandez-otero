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

    // Recomienda usuarios que son contactos de contactos pero no contactos directos
    void recomendarContactos(Usuario usuario);
}
