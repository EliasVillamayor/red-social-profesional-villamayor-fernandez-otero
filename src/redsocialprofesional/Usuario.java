package redsocialprofesional;

public class Usuario implements IUsuario {

    private String nombre;
    private Oferta[] ofertasPublicadas;
    private Oferta[] ofertasPostuladas;
    private int cantPublicadas;
    private int cantPostuladas;
    private static final int MAX_OFERTAS = 50;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.ofertasPublicadas = new Oferta[MAX_OFERTAS];
        this.ofertasPostuladas = new Oferta[MAX_OFERTAS];
        this.cantPublicadas = 0;
        this.cantPostuladas = 0;
    }

    @Override
    public void publicarOferta(Oferta oferta) {
        if (oferta != null && cantPublicadas < MAX_OFERTAS) {
            ofertasPublicadas[cantPublicadas] = oferta;
            cantPublicadas++;
        }
    }

    @Override
    public void postularOferta(Oferta oferta) {
        if (oferta == null) {
            return;
        }

        // Delega en Oferta la lógica de validación y encolado.
        // Si la oferta rechaza la postulación (propia oferta o duplicado),
        // ella misma muestra el mensaje. Acá solo registramos si fue aceptada,
        // verificando si el usuario quedó en la cola después de llamar a postular.
        int cantAntes = oferta.getPostulantes().getCantidad();
        oferta.postular(this);
        int cantDespues = oferta.getPostulantes().getCantidad();

        // Solo registramos la oferta en el historial si la postulación fue aceptada
        if (cantDespues > cantAntes && cantPostuladas < MAX_OFERTAS) {
            ofertasPostuladas[cantPostuladas] = oferta;
            cantPostuladas++;
        }
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public Oferta[] getOfertasPublicadas() {
        return ofertasPublicadas;
    }

    @Override
    public Oferta[] getOfertasPostuladas() {
        return ofertasPostuladas;
    }

    @Override
    public int getCantPublicadas() {
        return cantPublicadas;
    }

    @Override
    public int getCantPostuladas() {
        return cantPostuladas;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Usuario)) {
            return false;
        }

        Usuario otro = (Usuario) obj;
        return this.nombre.equals(otro.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}
