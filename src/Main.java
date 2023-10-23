public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        ArbolExpresion arbol = new ArbolExpresion();
        arbol.raiz = new Nodo("-");
        arbol.raiz.izquierda = new Nodo("+");
        arbol.raiz.izquierda.izquierda = new Nodo("2");
        arbol.raiz.izquierda.derecha = new Nodo("*");
        arbol.raiz.izquierda.derecha.izquierda = new Nodo("3");
        arbol.raiz.izquierda.derecha.derecha = new Nodo("4");
        arbol.raiz.derecha = new Nodo("/");
        arbol.raiz.derecha.izquierda = new Nodo("5");
        arbol.raiz.derecha.derecha = new Nodo("0.0");
        double resultado = arbol.evaluar();
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

    private double evaluarRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0.0;
        } else if (this.esNumero(nodo.valor)) {
            return Double.parseDouble(nodo.valor);
        } else {
            double izquierda = this.evaluarRecursivo(nodo.izquierda);
            double derecha = this.evaluarRecursivo(nodo.derecha);
            switch (nodo.valor) {
                case "+":
                    return izquierda + derecha;
                case "-":
                    return izquierda - derecha;
                case "*":
                    return izquierda * derecha;
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
                    throw new IllegalArgumentException("Operador inválido: " + nodo.valor);
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

class Nodo {
    String valor;
    Nodo izquierda;
    Nodo derecha;

    public Nodo(String valor) {
        this.valor = valor;
        this.izquierda = this.derecha = null;
    }
}
