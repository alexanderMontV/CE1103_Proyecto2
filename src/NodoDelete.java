public class NodoDelete {
    String valor;
    NodoDelete izquierda;
    NodoDelete derecha;

    public NodoDelete(String valor) {
        this.valor = valor;
        this.izquierda = this.derecha = null;
    }
}
