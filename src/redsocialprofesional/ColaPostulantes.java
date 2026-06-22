package redsocialprofesional;

public class ColaPostulantes implements IColaPostulantes {

    // Clase interna que representa cada nodo de la cola
    private class Nodo {
        Usuario usuario;
        Nodo siguiente;

        Nodo(Usuario usuario) {
            this.usuario = usuario;
            this.siguiente = null;
        }
    }

    private Nodo frente;   // primer elemento (el que se atiende primero)
    private Nodo fin;      // ultimo elemento (donde se encola el siguiente)
    private int cantidad;

    public ColaPostulantes() {
        this.frente = null;
        this.fin = null;
        this.cantidad = 0;
    }

    @Override
    public void encolar(Usuario usuario) {
        Nodo nuevo = new Nodo(usuario);

        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }

        cantidad++;
    }

    @Override
    public Usuario desencolar() {
        if (estaVacia()) {
            System.out.println("La cola de postulantes está vacía.");
            return null;
        }

        Usuario atendido = frente.usuario;
        frente = frente.siguiente;

        if (frente == null) {
            fin = null;
        }

        cantidad--;
        return atendido;
    }

    @Override
    public Usuario verFrente() {
        if (estaVacia()) {
            System.out.println("La cola de postulantes está vacía.");
            return null;
        }

        return frente.usuario;
    }

    @Override
    public boolean estaVacia() {
        return frente == null;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    @Override
    public boolean contiene(Usuario usuario) {
        Nodo actual = frente;

        while (actual != null) {
            if (actual.usuario.equals(usuario)) {
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }
}
