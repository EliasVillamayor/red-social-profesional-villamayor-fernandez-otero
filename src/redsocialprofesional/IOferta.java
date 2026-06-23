package redsocialprofesional;

public interface IOferta {

    String getTitulo();

    String getDescripcion();

    Usuario getPublicadaPor();

    // INTENTA agregar un usuario a la cola de postulantes
    void postular(Usuario usuario);

    // desencola al primer postulante
    Usuario atenderPostulante();

    int getCantPostulantes();
}
