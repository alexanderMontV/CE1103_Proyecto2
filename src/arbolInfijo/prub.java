package arbolInfijo;

import estructurasExpresion.Queue;

public class prub {
    public static void main(String[] args) {
        convertInfxPostfx cnv = new convertInfxPostfx();
        Queue myQ =cnv.convertirPQ("((16+57)*(34*(8+29)))");

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
