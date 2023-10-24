package estructurasExpresion;

public class PruebaIN {
    public static void main(String[] args) {
        Evaluador myEvaluador = new Evaluador();
        /*Arbol ax = new Arbol();
        NodoDelete root= ax.construct(myEvaluador.Evaluar("((16+57)*(34*(8+29)))"));
        ax.inorder(root);*/

        Queue myQ = myEvaluador.Evaluar("+");

        System.out.println("Tamaño del queue: "+myQ.getList().size());
        if (myQ.getList().size() == 0){
            System.out.println("QUEUE VACIO");
        }
        else{
            while(myQ.getFirst() != null){
                System.out.println(myQ.dequeue());
            }
        }


    }
}
