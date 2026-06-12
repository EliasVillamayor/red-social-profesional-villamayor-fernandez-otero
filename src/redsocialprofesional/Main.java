package redsocialprofesional;

public class Main {
    public static void main(String[] args) {

        GrafoRed red = new GrafoRed(10, false);

        // Crear usuarios
        Usuario juan = new Usuario("Juan");
        Usuario ana = new Usuario("Ana");
        Usuario carlos = new Usuario("Carlos");

        // Insertar en el grafo
        red.insertarVertice(juan);
        red.insertarVertice(ana);
        red.insertarVertice(carlos);

        // Conectar usuarios
        red.insertarArista(juan, ana);
        red.insertarArista(juan, carlos);

        // Crear y publicar ofertas
        Oferta oferta1 = new Oferta("Dev Java", "Trabajo remoto", ana);
        Oferta oferta2 = new Oferta("Tester QA", "Presencial CABA", carlos);

        ana.publicarOferta(oferta1);
        carlos.publicarOferta(oferta2);

        // Postular a ofertas
        juan.postularOferta(oferta1);
        juan.postularOferta(oferta2);

        // Mostrar estado del grafo
        red.mostrarVertices();
        red.mostrarMatriz();
        red.mostrarContactos(juan);

        // Mostrar ofertas de cada usuario
        System.out.println("\nOfertas publicadas por Ana:");
        for (int i = 0; i < ana.getCantPublicadas(); i++) {
            System.out.println("  " + ana.getOfertasPublicadas()[i].getTitulo());
        }

        System.out.println("\nPostulaciones de Juan:");
        for (int i = 0; i < juan.getCantPostuladas(); i++) {
            System.out.println("  " + juan.getOfertasPostuladas()[i].getTitulo());
        }
    }
}