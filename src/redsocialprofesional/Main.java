package redsocialprofesional;

public class Main {
    public static void main(String[] args) {

        GrafoRed red = new GrafoRed(10, false);

        // crear usuarios
        Usuario juan   = new Usuario("Juan");
        Usuario ana    = new Usuario("Ana");
        Usuario carlos = new Usuario("Carlos");
        Usuario maria  = new Usuario("Maria");  // es contacto de Ana, no de Juan, lo uso para el ejemplo de recomendaciones

        // insertar en el grafo
        red.insertarVertice(juan);
        red.insertarVertice(ana);
        red.insertarVertice(carlos);
        red.insertarVertice(maria);

        // conectar usuarios
        // juan conecta con ana, juan conecta con Carlos, ana conecta con Maria
        // maria queda como "amiga de amiga" de juan
        red.insertarArista(juan, ana);
        red.insertarArista(juan, carlos);
        red.insertarArista(ana, maria);

        // estado del grafo
        System.out.println("Vertices existentes y su matriz de adyacencia:");
        red.mostrarVertices();
        red.mostrarMatriz();
        System.out.println("Contactos de Juan:");
        red.mostrarContactos(juan);


        // BFS: recomendaciones para juan
        // espera recomendar a Maria (contacto de ana, no de juan)
        System.out.println();
        red.recomendarContactos(juan);

        // crear y publicar ofertas
        Oferta oferta1 = new Oferta("Dev Java",   "Trabajo remoto",   ana);
        Oferta oferta2 = new Oferta("Tester QA",  "Presencial CABA",  carlos);

        ana.publicarOferta(oferta1);
        carlos.publicarOferta(oferta2);

        System.out.println();


        // postulaciones
        juan.postularOferta(oferta1);   
        juan.postularOferta(oferta2);   
        maria.postularOferta(oferta1);  


        // validaciones: casos a rechazar
        System.out.println("\nValidaciones de postulaciones, un usuario no debe poder postularse a su propia oferta o a una que ya está postulada:");
        ana.postularOferta(oferta1);    // ana no puede postularse a su propia oferta
        juan.postularOferta(oferta1);   // juan ya está postulado a esta oferta


        // mostrar historial de postulaciones de juan
        System.out.println("\nPostulaciones de Juan:");
        for (int i = 0; i < juan.getCantPostuladas(); i++) {
            System.out.println("  " + juan.getOfertasPostuladas()[i].getTitulo());
        }

        // mostrar ofertas publicadas por ana
        System.out.println("\nOfertas publicadas por Ana:");
        for (int i = 0; i < ana.getCantPublicadas(); i++) {
            System.out.println("  " + ana.getOfertasPublicadas()[i].getTitulo());
        }


        // atender postulantes de oferta1 por orden de llegada
        // orden esperado: juan -> maria
        System.out.println("\nAtendiendo postulantes de 'Dev Java':");
        oferta1.atenderPostulante();  // juan (llego primero)
        oferta1.atenderPostulante();  // Maria
        oferta1.atenderPostulante();  // cola vacía -> mensaje de aviso
    }
}
