package conversionExpresion;

import estructurasExpresion.Cola;
import estructurasExpresion.Pila;

public class conversorInfijoAPostfijo {
    public conversorInfijoAPostfijo(){}

    public Cola convertirPQ (String infijo, String tipo) throws RuntimeException {
        Cola postf = new Cola();
        StringBuilder temp = new StringBuilder();
        Pila pila = new Pila();
        for (int i=0;i<infijo.length();i++){
            Character C = infijo.charAt(i);
            if (!isOperadorAlgebra(C) && !isOperadorLogic(C) && !isPatentesis(C)){
                temp.append(C);
            }
            else{
                if (!temp.toString().equals("")){
                    postf.enqueue(temp.toString());
                    temp = new StringBuilder();}
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
                    if (tipo.equals("Algebra")){
                        if (isOperadorLogic(C)){
                            return null;
                        }
                        if (C=='*' && infijo.charAt(i+1)=='*'){
                            i++;
                            C='#';
                        }
                    }
                    else {
                        if (isOperadorAlgebra(C)){
                            return null;
                        }
                        if (C=='"' && infijo.charAt(i+1)=='*'){
                            i++;
                            C='^';
                        }
                    }
                    while((!pila.empty()) && isOperador((Character) pila.peek()) && (prioridadEnExpression((Character) pila.peek()) >= prioridadEnExpression(C))){
                        postf.enqueue(pila.pop());
                    }
                    pila.push(C);
                }
            }
        }
        if (!temp.toString().equals("")){postf.enqueue(temp.toString());}
        while (!pila.empty()){
            postf.enqueue(pila.pop());
        }
        pila.clear();
        return postf;

    }

    public boolean isOperador(Character c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '&' || c == '!' || c == '~' || c == '^' || c== '#';
    }
    public boolean isOperadorAlgebra(Character c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c=='#';
    }
    public boolean isOperadorLogic(Character c){
        return c == '&' || c == '|' || c == '~' || c == '^' || c=='"';
    }
    public boolean isPatentesis(Character c){
        return c == '(' || c == ')';
    }
    private int prioridadEnExpression(char operador)
    {
        if (operador == '#' || operador =='~')
        {
            return 4;
        }
        if (operador == '*' || operador == '/' || operador == '%' || operador == '&')
        {
            return 2;
        }
        if (operador == '+' || operador == '-' || operador == '|' || operador == '^')
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