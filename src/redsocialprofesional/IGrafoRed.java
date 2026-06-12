package redsocialprofesional;

public interface IGrafoRed <Object>{
    void insertarVertice(Object vertice);

    void eliminarVertice(Object vertice);

    void insertarArista(Object origen, Object destino);

    void eliminarArista(Object origen, Object destino);

    boolean existeVertice(Object vertice);

    boolean existeArista(Object origen, Object destino);

    void mostrarMatriz();

    void mostrarVertices();
}
