package estructurasExpresion;

public class PruebaIN {
    public static void main(String[] args) {
        Evaluador myEvaluador = new Evaluador();
        Arbol ax = new Arbol();
        Nodo root= ax.construct(myEvaluador.Evaluar("((a+b)*(c*(d+e)))"));
        ax.inorder(root);

    }
}
