package estructurasExpresion;
import java.util.Objects;
import java.util.Stack;

/**
 * Clase de estructura de la clase arbol
 * */
public class Arbol {


    public Arbol(){

    }
    
    /**
     * Meotodo para saber si es un operador
     * @param c como caracter de la cadena
     * @return boolean
     */
    public boolean isOperator(String c) {
        return Objects.equals(c, "+") || Objects.equals(c, "-") || Objects.equals(c, "*") || Objects.equals(c, "/") || Objects.equals(c, "#") ||Objects.equals(c, "|" ) ||Objects.equals(c, "&" ) ||Objects.equals(c, "^"  ) ||Objects.equals(c, "~" );
    }

    /**
     * Meotodo para construitr el arbol
     * @param postfix como la cola postfija
     * @return Nodo que es la raiz del arbol
     */
    public Nodo construct(Cola postfix)
    {
        // caso base
        if (postfix == null) {
            return null;
        }

        // crea una stack vacía para almacenar punteros de árbol
        Pila s = new Pila();

        String c = "";
        // recorrer la expresión de sufijo
        while (!postfix.empty()) {
            // si el token actual es un operador
            c += postfix.dequeue();
            if (isOperator(c)) {
                // extrae dos nodos `x` e `y` de la stack
                Nodo x = (Nodo) s.pop();
                Nodo y = null;
                if (!s.empty()) {
                    y = (Nodo) s.pop();
                }

                // construye un nuevo árbol binario cuya raíz es el operador y cuyo
                // los niños izquierdo y derecho apuntan a `y` y `x`, respectivamente
                Nodo node = new Nodo(c, y, x);

                // inserta el nodo actual en la stack
                s.push(node);
            }
            // si el token actual es un operando, crea un nuevo nodo de árbol binario
            // cuya raíz es el operando y lo empuja a la stack
            else {
                s.push(new Nodo(c));
            }
            c = "";
        }

        // un puntero a la raíz del árbol de expresión permanece en la stack
        return (Nodo) s.peek();
    }
}
