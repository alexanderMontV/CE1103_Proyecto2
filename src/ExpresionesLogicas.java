public class ExpresionesLogicas {

    public static boolean and(boolean a, boolean b) {
        return a && b;
    }

    public static boolean or(boolean a, boolean b) {
        return a || b;
    }

    public static boolean xor(boolean a, boolean b) {
        return a ^ b;
    }

    public static boolean not(boolean a) {
        return !a;
    }

    public static void main(String[] args) {
        boolean valor1 = true;
        boolean valor2 = true;

        // Ejemplo de expresión AND
        boolean resultadoAnd = and(valor1, valor2);
        System.out.println(valor1 + " and " + valor2 + " = " + resultadoAnd);

        // Ejemplo de expresión OR
        boolean resultadoOr = or(valor1, valor2);
        System.out.println(valor1 + " or " + valor2 + " = " + resultadoOr);

        // Ejemplo de expresión XOR
        boolean resultadoXor = xor(valor1, valor2);
        System.out.println(valor1 + " xor " + valor2 + " = " + resultadoXor);

        // Ejemplo de expresión NOT
        boolean resultadoNot = not(valor1);
        System.out.println("!"+ valor1 + " = " + resultadoNot);
    }
}
