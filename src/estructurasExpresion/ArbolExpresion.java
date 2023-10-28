package estructurasExpresion;

import estructurasExpresion.Nodo;

public class ArbolExpresion {
    public ArbolExpresion() {
    }

    public String evaluarExpresion(Nodo arbol) {
        if (arbol!=null){
        return String.valueOf(this.evaluarRecursivo(arbol));}
        else{
            return "Expresion invalida";
        }
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
                case "#":
                    return Math.pow(izquierda, derecha);
                case "|":
                    return ((int) izquierda | (int) derecha);
                case "&":
                    return ((int) izquierda & (int) derecha);
                case "^":
                    return ((int) izquierda ^ (int) derecha);
                case "~":
                    return ~ ((byte) derecha);
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

