package sockets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import conversionExpresion.conversorInfijoAPostfijo;
import estructurasExpresion.Arbol;
import estructurasExpresion.ArbolExpresion;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase MarcoApp que muestra el Frame de la pantalla de chat, además
 * se añaden los elementos visuales, el cuadro de texto que muestra los mensajes recibidos por el server
 * @see "https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html
 * */
public class marcoServidor implements Runnable {

    private ArbolExpresion aex;
    private Arbol arbol;
    private conversorInfijoAPostfijo cnv;
    /**
     * Constructor de la clase MarcoApp
     * */
    public marcoServidor(){

        aex = new ArbolExpresion();
        arbol = new Arbol();
        cnv = new conversorInfijoAPostfijo();

        Thread escucho = new Thread(this);
        escucho.start();

    }

    @Override
    public void run() {

        int portS = 0;
        try {
            ServerSocket server = new ServerSocket(9999); //Socket de entrada que recibe los mensajes

            System.out.println("Servidor abierto");
            String data, tipo, metodo, respuesta, resultado;

            ArrayList<String> listaIp = new ArrayList<String>();
            ArrayList<Integer> listaIpnPort = new ArrayList<Integer>();

            //paqueteDato paqueteR;

            while (true) {//queda a la espera de un nuevo mensaje

                Socket mysocket = server.accept();

                ObjectInputStream paqueteEntrada = new ObjectInputStream(mysocket.getInputStream()); //Flujo de datos usando mysocket

                String msg = (String) paqueteEntrada.readObject();
                JsonObject jobj = new JsonParser().parse(msg).getAsJsonObject();
                metodo = jobj.get("tipoIngreso").getAsString();
                portS = jobj.get("puerto").getAsInt();
                tipo = jobj.get("tipoOP").getAsString();
                data = jobj.get("data").getAsString();

                if (!data.equals("")) {
                    if (metodo.equals("manual")) {
                        resultado = aex.evaluarExpresion(arbol.construct(cnv.convertirPQ(data, tipo)));
                    } else {
                        resultado = "0.1";
                    }
                    respuesta = resultado;
                } else {
                    respuesta = "Ingrese una operacion";
                }
                Socket socketDestino = new Socket("localhost", portS); //Socket salida

                ObjectOutputStream paqueteE = new ObjectOutputStream(socketDestino.getOutputStream());


                paqueteE.writeObject(respuesta);

                paqueteE.close();

                socketDestino.close();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            enviarError(e, portS);
            throw new RuntimeException(e);
        }


    }
    private void enviarError(Exception e, int port){
        try {
            Socket socketDestino = new Socket("localhost", port); //Socket salida

            ObjectOutputStream paqueteE = new ObjectOutputStream(socketDestino.getOutputStream());


            paqueteE.writeObject(e.getMessage());

            paqueteE.close();

            socketDestino.close();

        }
        catch (IOException ec){
            throw new RuntimeException();
        }
    }
}