package redsocialprofesional;

public interface IColaPostulantes {

    void encolar(Usuario usuario);

    Usuario desencolar();

    Usuario verFrente();

    boolean estaVacia();

    int getCantidad();

    // para ver si no hay duplicados
    boolean contiene(Usuario usuario);
}
