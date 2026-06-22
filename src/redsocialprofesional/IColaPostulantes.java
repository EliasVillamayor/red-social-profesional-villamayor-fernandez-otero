package redsocialprofesional;

public interface IColaPostulantes {

    // Agrega un usuario al final de la cola
    void encolar(Usuario usuario);

    // Elimina y devuelve el usuario del frente de la cola
    Usuario desencolar();

    // Devuelve el usuario del frente sin eliminarlo
    Usuario verFrente();

    // Indica si la cola no tiene elementos
    boolean estaVacia();

    // Devuelve cuántos usuarios hay en la cola
    int getCantidad();

    // Indica si un usuario ya está en la cola (para evitar duplicados)
    boolean contiene(Usuario usuario);
}
