package estructurasExpresion;
import java.util.Stack;

public class Arbol {

    public Arbol(){

    }
    public boolean isOperator(String c) {
        return (c == "+" || c == "-" || c == "*" || c == "/" || c == "^");
    }
    public void inorder(Nodo root)
    {
        if (root == null) {
            return;
        }

        // si el token actual es un operador, imprime un paréntesis abierto
        if (isOperator(root.data)) {
            System.out.print("(");
        }

        inorder(root.left);
        System.out.print(root.data);
        inorder(root.right);

        // si el token actual es un operador, imprime cerrar paréntesis
        if (isOperator(root.data)) {
            System.out.print(")");
        }
    }
    public Nodo construct(Queue postfix)
    {
        // caso base
        if (postfix == null) {
            return null;
        }

        // crea una stack vacía para almacenar punteros de árbol
        Stack<Nodo> s = new Stack<>();

        String c = "";
        // recorrer la expresión de sufijo
        while (postfix.getFirst() !=null)
        {
            // si el token actual es un operador
            c += postfix.dequeue();
            if (isOperator(c))
            {
                // extrae dos nodos `x` e `y` de la stack
                Nodo x = s.pop();
                Nodo y = s.pop();

                // construye un nuevo árbol binario cuya raíz es el operador y cuyo
                // los niños izquierdo y derecho apuntan a `y` y `x`, respectivamente
                Nodo node = new Nodo(c, y, x);

                // inserta el nodo actual en la stack
                s.add(node);
            }
            // si el token actual es un operando, crea un nuevo nodo de árbol binario
            // cuya raíz es el operando y lo empuja a la stack
            else {
                s.add(new Nodo(c));
            }
            c="";
        }

        // un puntero a la raíz del árbol de expresión permanece en la stack
        return s.peek();
    }
}
