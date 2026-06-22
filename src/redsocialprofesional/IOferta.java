package redsocialprofesional;

public interface IOferta {

    String getTitulo();

    String getDescripcion();

    Usuario getPublicadaPor();

    // Intenta agregar un usuario a la cola de postulantes
    void postular(Usuario usuario);

    // Atiende (desencola) al primer postulante
    Usuario atenderPostulante();

    // Devuelve cuántos postulantes hay en la cola
    int getCantPostulantes();
}
