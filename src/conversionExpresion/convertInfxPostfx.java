package conversionExpresion;

import estructurasExpresion.Cola;

import java.util.Stack;

public class convertInfxPostfx {
    public convertInfxPostfx(){}

    public Cola convertirPQ (String infijo){
        Cola postf = new Cola();
        String temp = "";
        String potencia="**";
        Stack pila = new Stack();
        for (int i=0;i<infijo.length();i++){
            Character C = infijo.charAt(i);
            if (isOperando(C)){
                temp+=C;
            }
            else{
                if (!temp.equals("")){
                postf.enqueue(temp);
                temp="";}
                if (C=='('){
                    pila.push(C);
                }else if (C==')'){
                    while(!pila.empty() && (Character) pila.peek() != '('){
                        postf.enqueue(pila.pop());
                    }
                    if ((Character) pila.peek() == '('){
                        pila.pop();
                    }
                    else {
                        System.out.println("ERORR DE PARENTESIS");
                    }
                    C=null;
                }
                else{
                    if (C=='*' && infijo.charAt(i+1)=='*'){
                        i++;
                        C='^';
                    }
                    while(!pila.empty() && isOperadorAlgebra((Character) pila.peek()) && (prioridadEnExpresion((Character) pila.peek()) >= prioridadEnExpresion(C))){
                        postf.enqueue(pila.pop());
                    }
                    pila.push(C);
                }
            }
        }
        if (!temp.equals("")){postf.enqueue(temp);}
        while (!pila.empty()){
            postf.enqueue(pila.pop());
        }
        pila.clear();
        return postf;

    }
    public boolean isOperadorAlgebra(Character c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '%';
    }
    public boolean isOperando(Character c){
        return c != '+' && c != '-' && c != '*' && c != '/' && c != '%' && c != '^' && c != '(' && c != ')';
    }
    private int prioridadEnExpresion (char operador)
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
