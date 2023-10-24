package arbolInfijo;

import estructurasExpresion.Queue;

import java.util.Stack;

public class convertInfxPostfx {
    public convertInfxPostfx(){}

    public Queue convertirPQ (String infijo){
        Queue postf = new Queue();
        String temp = "";
        Stack pila = new Stack();
        for (int i=0;i<infijo.length();i++){
            Character C = infijo.charAt(i);
            if (isOperando(C)){
                temp+=C;
            }
            else{
                if (temp!=""){
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
                    while(!pila.empty() && isOperator((Character) pila.peek()) && (prioridadEnExpresion((Character) pila.peek()) >= prioridadEnExpresion(C))){
                        postf.enqueue(pila.pop());
                    }
                    pila.push(C);
                }
            }
        }
        while (!pila.empty()){
            postf.enqueue(pila.pop());
        }
        pila.clear();
        return postf;

    }

    public boolean isOperator(Character c){
        if (c=='+' || c=='-' || c=='*' || c=='/' || c=='^'){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isOperando(Character c){
        if (c=='+' || c=='-' || c=='*' || c=='/' || c=='^' || c=='(' || c==')'){
            return false;
        }
        else{
            return true;
        }
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
