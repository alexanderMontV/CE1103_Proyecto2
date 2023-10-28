package sockets;

import interfaz.interfazCliente;

import javax.swing.*;
import java.awt.*;

public class clienteCalculadora extends JFrame {
    public static void main(String[] args) {
        interfazCliente ventana = new interfazCliente();
        ventana.setTitle("Calculadora Proyecto 2");
        ventana.setVisible(true);
        ventana.setSize((new Dimension(785, 550)));
        ventana.pack();
        ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
