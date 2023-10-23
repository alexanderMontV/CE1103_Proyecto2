package estructurasExpresion;

class Nodo
{
    String data;
    Nodo left, right;

    Nodo(String data)
    {
        this.data = data;
        this.left = this.right = null;
    }

    Nodo (String data, Nodo left, Nodo right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}