package redsocialprofesional;

public class Oferta implements IOferta {

    private String titulo;
    private String descripcion;
    private Usuario publicadaPor;
    private ColaPostulantes postulantes;

    public Oferta(String titulo, String descripcion, Usuario publicadaPor) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.publicadaPor = publicadaPor;
        this.postulantes = new ColaPostulantes();
    }

    @Override
    public void postular(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Usuario inválido.");
            return;
        }

        // Validación: no puede postularse quien publicó la oferta
        if (usuario.equals(publicadaPor)) {
            System.out.println(usuario.getNombre() + " no puede postularse a su propia oferta (" + titulo + ").");
            return;
        }

        // Validación: no puede postularse dos veces a la misma oferta
        if (postulantes.contiene(usuario)) {
            System.out.println(usuario.getNombre() + " ya está postulado a la oferta: " + titulo);
            return;
        }

        postulantes.encolar(usuario);
        System.out.println(usuario.getNombre() + " se postuló a: " + titulo);
    }

    @Override
    public Usuario atenderPostulante() {
        if (postulantes.estaVacia()) {
            System.out.println("No hay postulantes para la oferta: " + titulo);
            return null;
        }

        Usuario atendido = postulantes.desencolar();
        System.out.println("Atendiendo postulante: " + atendido.getNombre() + " para la oferta: " + titulo);
        return atendido;
    }

    @Override
    public int getCantPostulantes() {
        return postulantes.getCantidad();
    }

    // no borrar
    public ColaPostulantes getPostulantes() {
        return postulantes;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public Usuario getPublicadaPor() {
        return publicadaPor;
    }

    @Override
    public String toString() {
        return titulo + " - " + publicadaPor.getNombre();
    }
}
