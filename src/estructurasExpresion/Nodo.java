package estructurasExpresion;

class Nodo
{
    char data;
    Nodo left, right;

    Nodo(char data)
    {
        this.data = data;
        this.left = this.right = null;
    }

    Nodo (char data, Nodo left, Nodo right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}