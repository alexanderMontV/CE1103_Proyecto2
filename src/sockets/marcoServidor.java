package sockets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import conversionExpresion.conversorInfijoAPostfijo;
import estructurasExpresion.Arbol;
import estructurasExpresion.ArbolExpresion;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
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
            Tesseract tesseract = new Tesseract();

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
                        tesseract.setDatapath("./tessdata/");
                        // the path of your tess data folder
                        // inside the extracted file
                        String text= tesseract.doOCR(new File("./assets/scan"+portS+".jpg"));
                        System.out.println(text);
                        resultado = aex.evaluarExpresion(arbol.construct(cnv.convertirPQ(text, tipo)));
                    }
                    respuesta = resultado;
                } else {
                    respuesta = "Ingrese una operacion";
                }
                String archivoCSV = "./dataBase/"+portS+".csv";
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV,true))) {
                    String datos = data+","+respuesta+","+LocalDateTime.now();
                    // Escribir datos en el archivo CSV, separados por comas
                    bw.write(datos);
                    bw.newLine(); // Nueva línea para la siguiente fila
                } catch (IOException e) {
                    e.printStackTrace();
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
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }


    }
    
    /** 
     * @param e
     * @param port
     */
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