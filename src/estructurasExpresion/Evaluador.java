package estructurasExpresion;


/**
 * Classe para evaluar y convertir la entrada infija en una entrada postfija
 * Tomada de GitHub.com de Olave,V.(2019), usuario de github @victorolave
 * @see "https://github.com/victorolave/Infijo_A_Posfijo_Java/"
 */
public class Evaluador
{

    public Evaluador() {
    }

    //Este metodo establece el tipo de caracter
    public  String Operadore(String exp)
    {
        String datoSalida = "";

        for (int i = 0 ; i < exp.length() ; i++)
        {
            if(esOperador(exp.charAt(i)))
            {
                datoSalida += exp.charAt(i);
            }
        }
        return datoSalida;
    }

    //Metodo que resive la expresion y la pasa a el metodo de conversion
    public Queue Evaluar(String infija)
    {
        Queue posfija = convertir(infija);
        //posfija = posfija.replaceAll("[()]","");

        System.out.println("(Alex: estructuras.Evaluador)La expresion postfija es: ");
        System.out.println(" "+posfija+" ");
        return posfija;
    }

    //Metodo de conversion de infija a posfija
    public static Queue convertir(String infija)
    {
        Queue posfija = new Queue();
        String temp="";
        Pila pila = new Pila(100);

        //Evalua el recorrido del string caracte por caracter
        for (int i = 0; i < infija.length() ; i++)
        {
            char letra = infija.charAt(i);
            // Si es un operador entonces lo apila
            if(esOperador (infija.charAt(i)))
            {
                posfija.enqueue(temp);
                temp="";
                if(pila.PilaVacia())
                {
                    pila.Apilar(letra);
                }
                else
                {
                    //Evalua su prioridad o jerarquia con la prioridad en la pila
                    int pe = prioridadEnExpresion(letra);
                    int pp = prioridadEnPila((char)pila.elementoTope());

                    if (pe > pp)
                    {
                        pila.Apilar(letra);
                    }
                    else
                    {
                        if (pila.Desapilar() != "(" || pila.Desapilar()!=")"){
                            posfija.enqueue(pila.Desapilar());}
                        else{
                            pila.Desapilar();
                        }
                        pila.Apilar(letra);
                    }
                }
            }
            else
            {
                temp += letra;
            }
        }
        //Vacia la pila para que no queden operadores
        while (!pila.PilaVacia())
        {
            posfija.enqueue(pila.Desapilar());

        }
        return posfija;
    }

    //Metodo para evaluar la jerarquia de operaciones de la expresion
    private static int prioridadEnExpresion (char operador)
    {
        if (operador == '^')
        {
            return 4;
        }
        if (operador == '*' || operador == '/')
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

    //Metodo para evaluar la jerarquia de operaciones en la pila
    private static int prioridadEnPila (char operador)
    {
        if (operador == '^')
        {
            return 3;
        }
        if (operador == '*' || operador == '/')
        {
            return 1;
        }
        if (operador == '+' || operador == '-')
        {
            return 2;
        }
        if (operador == '(' )
        {
            return 0;
        }
        return 0;
    }

    //Determina si el caracter es un operador o un entero
    //En caso tal de ser operador lo apila, y en caso de ser caracter lo envia a postfijo
    private static boolean esOperador (char letra)
    {
        if (letra =='^' || letra =='*' || letra =='/' || letra =='+'|| letra =='-' || letra =='(' || letra ==')')
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
