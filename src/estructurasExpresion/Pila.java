package estructurasExpresion;

import java.util.LinkedList;
/**
 * Classe de estructura Pila
 */

public class Pila {

    private final LinkedList<Object> list;

    public Pila() {
        this.list = new LinkedList<>();
    }


    public void push(Object element) {
        this.list.addLast(element);}

    public Object pop(){
        return this.list.removeLast();
    }
    public Object peek(){
        return this.list.getLast();
    }
    public boolean empty(){
        return this.list.isEmpty();
    }
    public void clear(){
        this.list.clear();
    }
    public int size(){
        return this.list.size();
    }
    public LinkedList<Object> getList() {
        return list;
    }

}