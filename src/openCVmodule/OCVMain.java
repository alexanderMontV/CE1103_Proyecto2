package openCVmodule;

import com.google.gson.JsonObject;
import com.sun.source.doctree.StartElementTree;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OCVMain extends JFrame{

    private int port;
    private JLabel cameraScreen;
    private JButton captureButton;

    private VideoCapture capture;
    private Mat image;
    private boolean clicked = false;

    private Thread hilo;

    private boolean run = true;

    private String tipoOP;

    public OCVMain(){

    }
    public OCVMain(int port, String tipoOP){
        this.port=port;
        this.tipoOP=tipoOP;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Load success");
        setLayout(null);
        cameraScreen=new JLabel();
        cameraScreen.setBounds(0,0,900,500);
        add(cameraScreen);

        captureButton = new JButton("Capture");
        captureButton.setBounds(700,510, 80,40);
        add(captureButton);

        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked=true;
            }
        });

        setSize(new Dimension(900,600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public void startCamera(){
        capture= new VideoCapture(0);
        image = new Mat();
        byte[] imageData;
        ImageIcon icon;
        while (run){
            capture.read(image);

            final MatOfByte buf = new MatOfByte();
            Imgcodecs.imencode(".jpg",image,buf);

            imageData=buf.toArray();

            icon = new ImageIcon(imageData);

            cameraScreen.setIcon(icon);
            
            if (clicked){
                String ruta="./assets/"+"scan"+port+".jpg";
                Imgcodecs.imwrite(ruta,image);
                clicked=false;
                closeCamera();
                sendPicture(port,ruta);
                this.dispose();
            }


        }
    }

    private void sendPicture(int port, String ruta){
        try {
            Socket mysocket = new Socket("localhost",9999); //Abre el socket para enviar los datos al servidor

            JsonObject jobj = new JsonObject();

            jobj.addProperty("puerto",port);
            jobj.addProperty("tipoOP", tipoOP);
            jobj.addProperty("tipoIngreso","OCR");
            jobj.addProperty("data",ruta);
            String jsonO = String.valueOf(jobj);


            ObjectOutputStream paqueteDatos = new ObjectOutputStream(mysocket.getOutputStream()); //Flujo de salidad para poder enviar el objeto por la red

            paqueteDatos.writeObject(jsonO);

            mysocket.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void closeCamera() {
        if (capture != null && capture.isOpened()) {
            run=false;
            capture.release();
        }
        dispose();
    }
    public void runCamera(int portx, String tipo) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                OCVMain camera = new OCVMain(portx, tipo);

                hilo= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        camera.startCamera();
                    }
                });
                hilo.start();
            }
        });
    }
}
