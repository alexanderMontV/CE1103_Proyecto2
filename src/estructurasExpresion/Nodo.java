package estructurasExpresion;
/**
 * Classe de estructura Nodo
 */
public class Nodo
{
    public String valor;
    public Nodo left;
    public Nodo right;

    public Nodo(String data)
    {
        this.valor = data;
        this.left = this.right = null;
    }

    public Nodo (String data, Nodo left, Nodo right)
    {
        this.valor = data;
        this.left = left;
        this.right = right;
    }
}