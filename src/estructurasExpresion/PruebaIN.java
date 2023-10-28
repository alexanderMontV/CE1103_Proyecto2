package estructurasExpresion;

import archivosDespreciables.conversorRestore;
import conversionExpresion.conversorInfijoAPostfijo;

public class PruebaIN {
    public static void main(String[] args){

        try {
            Cola myQ = new conversorRestore().convertirPQ("~8&3", "Logica");
            System.out.println("Tamaño del queue: "+myQ.getList().size());
            if (myQ.size() == 0){
                System.out.println("No hay elementos en la cola");
            }
            else{
                while(!myQ.empty()){
                    System.out.println(myQ.dequeue());
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }




    }
}
