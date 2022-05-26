package Modelo;

public class Lista<E>{

    protected Nodo<E> raiz;
    protected int nroNodos;

    public Lista(){
        this.raiz = null;
        this.nroNodos = 0;
    }

    public void add(E contenido){
        Nodo<E> nuevo = new Nodo<>(contenido);

        if (this.raiz == null){
            this.raiz = nuevo;
            nroNodos++;
            return;
        }

        Nodo<E> actual = this.raiz;

        while (actual.getSiguiente() != null){
            actual = actual.getSiguiente();
        }

        actual.setSiguiente(nuevo);
        nroNodos++;
    }

    public E get(int pos){
        if (pos < 0){
            throw new ArrayIndexOutOfBoundsException("No se puede obtener esa posicion, ya que es una posicion" +
                    " negativa");
        } else if (pos >= nroNodos) {
            throw new ArrayIndexOutOfBoundsException("No se puede obtener esa posicion, ya que es una posicion" +
                    " mayor a la cantidad de elementos en el array");
        }

        if (pos == 0){
            return raiz.getContenido();
        }

        Nodo<E> actual = this.raiz;
        int posActual = 0;

        while (actual != null && posActual < pos){
            actual = actual.getSiguiente();
            posActual++;
        }

        return actual.getContenido();
    }

    public void eliminar(int pos){

        if (pos < 0 || pos > nroNodos){
            throw new ArrayIndexOutOfBoundsException("No se pueden eliminar objetos que no existen");
        }

        if (pos == 0){
            this.raiz = raiz.getSiguiente();
            nroNodos--;
        }

        Nodo<E> actual = this.raiz;
        int posActual = 0;

        while (posActual < (pos-1)){
            actual = actual.getSiguiente();
            posActual++;
        }

        Nodo<E> aux = actual.getSiguiente().getSiguiente();
        actual.getSiguiente().setSiguiente(null);
        actual.setSiguiente(aux);
        nroNodos--;
    }

    public int size() {
        return nroNodos;
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<E> raiz) {
        this.raiz = raiz;
    }

    public void setNroNodos(int nroNodos) {
        this.nroNodos = nroNodos;
    }

    @Override
    public String toString() {
        if (raiz == null) {
            return "VACIA";
        }

        StringBuilder result = new StringBuilder();
        result.append("Lista: ");
        String separador = "";
        Nodo<E> actual = raiz;
        while(actual != null) {
            result.append(separador).append(actual);
            actual = actual.getSiguiente();
            separador = " -> ";
        }

        return result.toString();
    }

    static class Nodo<E>{
        private E contenido;
        private Nodo<E> siguiente;

        public Nodo(E objecto){
            this.contenido = objecto;
            this.siguiente = null;
        }

        public E getContenido() {
            return contenido;
        }

        public void setContenido(E contenido) {
            this.contenido = contenido;
        }

        public Nodo<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }

        @Override
        public String toString() {
            return "Nodo{" + contenido + '}';
        }
    }
}
