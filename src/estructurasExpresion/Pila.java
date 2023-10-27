package estructurasExpresion;

import java.util.LinkedList;

public class Pila {

    private final LinkedList<Object> list;

    public Pila() {
        this.list = new LinkedList<>();
    }

    public void push(Object element) {
        this.list.addFirst(element);}

    public Object pop(){
        return this.list.removeLast();
    }
    public Object peek(){
        return this.list.getLast();
    }
    public boolean isempty(){
        return this.list.isEmpty();
    }
    public int size(){
        return this.list.size();
    }

    public LinkedList<Object> getList() {
        return list;
    }

}