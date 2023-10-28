package interfaz;//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import com.google.gson.JsonObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class interfazCliente extends JFrame implements Runnable{
    private JComboBox selectorTipo;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JTextField ingresoDato;
    private JTextArea salidaOperacion;
    private JButton btncalcular;
    private JButton btnCamara;
    private JButton historial;

    public Integer getMyPort() {
        return myPort;
    }

    private Integer myPort;

    public interfazCliente() {
        //construct preComponents
        String[] selectorTipoItems = {"Algebra", "Logica"};

        //construct components
        selectorTipo = new JComboBox (selectorTipoItems);
        jcomp2 = new JLabel ("Calculadora Algebra Logica");
        jcomp3 = new JLabel ("2 Proyecto Datos I");
        jcomp4 = new JLabel ("Alexander Montero, Bryan Sibaja");
        ingresoDato = new JTextField (5);
        salidaOperacion = new JTextArea (1, 1);
        btncalcular = new JButton ("calcular");
        btnCamara = new JButton ("Usar Camara");
        historial = new JButton ("ver Historial");

        //set components properties
        salidaOperacion.setEnabled (false);

        //adjust size and set layout
        setPreferredSize (new Dimension (785, 530));
        setLayout (null);
        setVisible(true);

        salidaOperacion.setDisabledTextColor(new Color(0X000000));
        salidaOperacion.setFont(new Font("Arial", Font.BOLD,16));

        envioCalculo entradaOperacion = new envioCalculo();
        btncalcular.addActionListener(entradaOperacion);

        btnCamara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setResult();
            }
        });

        historial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               PantallaHistorial phistorial = new PantallaHistorial();
            }
        });

        //add components
        add (selectorTipo);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (ingresoDato);
        add (salidaOperacion);
        add (btncalcular);
        add (btnCamara);
        add (historial);

        //set component bounds (only needed by Absolute Positioning)
        selectorTipo.setBounds (20, 15, 100, 25);
        jcomp2.setBounds (300, 20, 160, 20);
        jcomp3.setBounds (620, 430, 110, 25);
        jcomp4.setBounds (580, 465, 195, 20);
        ingresoDato.setBounds (190, 135, 370, 65);
        salidaOperacion.setBounds (215, 340, 320, 35);
        btncalcular.setBounds (330, 240, 110, 50);
        btnCamara.setBounds (430, 95, 120, 25);
        historial.setBounds (20, 450, 110, 25);

        Thread hilo = new Thread(this);

        hilo.start();
    }

    public void run() {
        try {
            /**
             * @see https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html#ServerSocket-int-
             * */
            ServerSocket server = new ServerSocket(0);//puerto del cliente a la espera de recibir mensajes, el puerto abre en el que encuentre disponible
            myPort = server.getLocalPort();//obtiene el valor del puerto que abre automáticamente para poder emplearlo en el servidor


            Socket cliente;

            System.out.println(myPort);

            while (true) { //siempre ejecutandose para esperar un nuevo mensaje

                cliente = server.accept();

                ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());

                String resultadoRespuesta = (String) flujoEntrada.readObject();

                salidaOperacion.setText(resultadoRespuesta);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void setResult(){
        salidaOperacion.setText("Texto prueba");
    }

    class envioCalculo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket mysocket = new Socket("localhost",9999); //Abre el socket para enviar los datos al servidor

                JsonObject jobj = new JsonObject();

                jobj.addProperty("puerto",myPort);
                jobj.addProperty("tipoOP", (String) selectorTipo.getSelectedItem());
                jobj.addProperty("tipoIngreso","manual");
                jobj.addProperty("data",ingresoDato.getText());
                String jsonO = String.valueOf(jobj);


                ObjectOutputStream paqueteDatos = new ObjectOutputStream(mysocket.getOutputStream()); //Flujo de salidad para poder enviar el objeto por la red

                paqueteDatos.writeObject(jsonO);

                mysocket.close();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
