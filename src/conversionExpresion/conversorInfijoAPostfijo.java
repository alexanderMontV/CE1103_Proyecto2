package conversionExpresion;

import estructurasExpresion.Cola;
import estructurasExpresion.Pila;

public class conversorInfijoAPostfijo {
    public conversorInfijoAPostfijo(){}

    public Cola convertirPQ (String infijo){
        Cola colaSalidaPostfija = new Cola();
        StringBuilder temp = new StringBuilder();
        Pila pila = new Pila();
        for (int i=0;i<infijo.length();i++){
            Character C = infijo.charAt(i);
            if (isOperando(C)){
                temp.append(C);
            }
            else{
                if (!temp.toString().equals("")){
                colaSalidaPostfija.enqueue(temp.toString());
                temp = new StringBuilder();}
                if (C=='('){
                    pila.push(C);
                }else if (C==')'){
                    while(pila.empty() && (Character) pila.peek() != '('){
                        colaSalidaPostfija.enqueue(pila.pop());
                    }
                    if ((Character) pila.peek() == '('){
                        pila.pop();
                    }
                    else {
                        System.out.println("ERROR DE PARENT");
                    }
                }
                else{
                    if (C=='*' && infijo.charAt(i+1)=='*'){
                        i++;
                        C='^';
                    }
                    while(pila.empty() && isOperadorAlgebra((Character) pila.peek()) && (prioridadEnExpression((Character) pila.peek()) >= prioridadEnExpression(C))){
                        colaSalidaPostfija.enqueue(pila.pop());
                    }
                    pila.push(C);
                }
            }
        }
        if (!temp.toString().equals("")){colaSalidaPostfija.enqueue(temp.toString());}
        while (pila.empty()){
            colaSalidaPostfija.enqueue(pila.pop());
        }
        pila.clear();
        return colaSalidaPostfija;

    }
    public boolean isOperadorAlgebra(Character c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '%';
    }
    public boolean isOperando(Character c){
        return c != '+' && c != '-' && c != '*' && c != '/' && c != '%' && c != '^' && c != '(' && c != ')';
    }
    private int prioridadEnExpression(char operador)
    {
        if (operador == '^')
        {
            return 4;
        }
        if (operador == '*' || operador == '/' || operador == '%')
        {
            return 2;
        }
        if (operador == '+' || operador == '-')
        {
            return 1;
        }
        if (operador == '(' )
        {
            return 5;
        }
        return 0;
    }
}
