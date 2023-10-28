package arbolInfijo;

import conversionExpresion.conversorInfijoAPostfijo;
import estructurasExpresion.Cola;

public class prub {
    public static void main(String[] args) throws Exception {
        conversorInfijoAPostfijo cnv = new conversorInfijoAPostfijo();
        Cola myQ =cnv.convertirPQ("8**3", "Algebraica");

        System.out.println("Tamaño del queue: "+myQ.getList().size());
        if (myQ.getList().size() == 0){
            System.out.println("QUEUE VACIO");
        }
        else{
            while(!myQ.getList().isEmpty()){
                System.out.println(myQ.dequeue());
            }
        }
    }
}
