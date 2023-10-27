import arbolInfijo.convertInfxPostfx;
import estructurasExpresion.Arbol;
import estructurasExpresion.Nodo;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        ArbolExpresion ex = new ArbolExpresion();
        Arbol arbol = new Arbol();
        convertInfxPostfx cnv = new convertInfxPostfx();
        double resultado = ex.evaluarRecursivo(arbol.construct(cnv.convertirPQ("16+57*34*8+29")));
        System.out.println("Resultado: " + resultado);
    }
}

class ArbolExpresion {
    Nodo raiz = null;

    public ArbolExpresion() {
    }

    public double evaluar() {
        return this.evaluarRecursivo(this.raiz);
    }

    public double evaluarRecursivo(Nodo nodoDelete) {
        if (nodoDelete == null) {
            return 0.0;
        } else if (this.esNumero(nodoDelete.valor)) {
            return Double.parseDouble(nodoDelete.valor);
        } else {
            double izquierda = this.evaluarRecursivo(nodoDelete.left);
            double derecha = this.evaluarRecursivo(nodoDelete.right);
            switch (nodoDelete.valor) {
                case "+":
                    return izquierda + derecha;
                case "-":
                    return izquierda - derecha;
                case "*":
                    return izquierda * derecha;
                case "%":
                    return (izquierda * derecha)/100;
                case "/":
                    if (derecha != 0.0) {
                        return izquierda / derecha;
                    }
                    else{
                        throw new ArithmeticException("División por cero");
                    }
                case "^":
                    return Math.pow(izquierda, derecha);
                default:
                    throw new IllegalArgumentException("Operador inválido: " + nodoDelete.valor);
            }
        }
    }

    private boolean esNumero(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException var3) {
            return false;
        }
    }
}

