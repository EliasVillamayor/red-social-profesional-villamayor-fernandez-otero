package redsocialprofesional;

public class Main {
    public static void main(String[] args) {

        GrafoRed red = new GrafoRed(10, false);

        // Crear usuarios
        Usuario juan   = new Usuario("Juan");
        Usuario ana    = new Usuario("Ana");
        Usuario carlos = new Usuario("Carlos");
        Usuario maria  = new Usuario("Maria");  // es contacto de Ana, no de Juan, lo uso para el ejemplo de recomendaciones

        // Insertar en el grafo
        red.insertarVertice(juan);
        red.insertarVertice(ana);
        red.insertarVertice(carlos);
        red.insertarVertice(maria);

        // Conectar usuarios
        // Juan conecta con Ana, Juan conecta con Carlos, Ana conecta con Maria
        // Maria queda como "amiga de amiga" de Juan
        red.insertarArista(juan, ana);
        red.insertarArista(juan, carlos);
        red.insertarArista(ana, maria);

        // Mostrar estado del grafo
        System.out.println("Vertices existentes y su matriz de adyacencia:");
        red.mostrarVertices();
        red.mostrarMatriz();
        System.out.println("Contactos de Juan:");
        red.mostrarContactos(juan);


        // BFS: recomendaciones para Juan
        // Espera recomendar a Maria (contacto de Ana, no de Juan)
        System.out.println();
        red.recomendarContactos(juan);

        // Crear y publicar ofertas
        Oferta oferta1 = new Oferta("Dev Java",   "Trabajo remoto",   ana);
        Oferta oferta2 = new Oferta("Tester QA",  "Presencial CABA",  carlos);

        ana.publicarOferta(oferta1);
        carlos.publicarOferta(oferta2);

        System.out.println();


        // Postulaciones
        juan.postularOferta(oferta1);   
        juan.postularOferta(oferta2);   
        maria.postularOferta(oferta1);  


        // Validaciones: casos que deben rechazarse
        System.out.println("\nValidaciones de postulaciones, un usuario no debe poder postularse a su propia oferta o a una que ya está postulada:");
        ana.postularOferta(oferta1);    // Ana no puede postularse a su propia oferta, no deberia dejarla postularse
        juan.postularOferta(oferta1);   // Juan ya está postulado a esta oferta, no deberia dejarlo postularse de nuevo


        // Mostrar historial de postulaciones de Juan
        System.out.println("\nPostulaciones de Juan:");
        for (int i = 0; i < juan.getCantPostuladas(); i++) {
            System.out.println("  " + juan.getOfertasPostuladas()[i].getTitulo());
        }

        // Mostrar ofertas publicadas por Ana
        System.out.println("\nOfertas publicadas por Ana:");
        for (int i = 0; i < ana.getCantPublicadas(); i++) {
            System.out.println("  " + ana.getOfertasPublicadas()[i].getTitulo());
        }


        // Atender postulantes de oferta1 por orden de llegada
        // Orden esperado: Juan → Maria
        System.out.println("\nAtendiendo postulantes de 'Dev Java':");
        oferta1.atenderPostulante();  // Juan (llegó primero)
        oferta1.atenderPostulante();  // Maria
        oferta1.atenderPostulante();  // Cola vacía → mensaje de aviso
    }
}
