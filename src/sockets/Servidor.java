package sockets;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase Servidor, clase principal del archivo, ejecuta el main que da arranque al server
 */
public class Servidor  {


    /**
     * Llama a la clase del servidor donde se abre el socket
     * @param args defualt args
     */
    public static void main(String[] args) {

        marcoServidor marco = new marcoServidor();
    }
}