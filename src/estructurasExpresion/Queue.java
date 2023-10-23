package estructurasExpresion;

import java.util.LinkedList;

public class Queue {

    private LinkedList<Object> list;

    public Queue() {
        this.list = new LinkedList<>();
    }

    public void enqueue(Object element) {
        this.list.addLast(element);}
    public Object dequeue(Object element) { return this.list.removeFirst(); }

    public Object dequeue(){
        return this.list.removeFirst();
    }
    public Object getFirst(){
        return this.list.getFirst();
    }


    public LinkedList<Object> getList() {
        return list;
    }

}