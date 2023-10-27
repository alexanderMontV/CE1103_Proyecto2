package estructurasExpresion;

import java.util.LinkedList;

public class Cola {

    private final LinkedList<Object> list;

    public Cola() {
        this.list = new LinkedList<>();
    }

    public void enqueue(Object element) {
        this.list.addLast(element);}

    public Object dequeue(){
        return this.list.removeFirst();
    }
    public Object getFirst(){
        return this.list.getFirst();
    }
    public boolean empty(){
        return this.list.isEmpty();
    }
    public int size(){
        return this.list.size();
    }
    public LinkedList<Object> getList() {
        return list;
    }

}