package openCVmodule;

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
import java.text.SimpleDateFormat;
import java.util.Date;

public class OCVMain extends JFrame {

    private JLabel cameraScreen;
    private JButton captureButton;

    private VideoCapture capture;
    private Mat image;
    private boolean clicked = false;

    public OCVMain(){
        setLayout(null);
        cameraScreen=new JLabel();
        cameraScreen.setBounds(0,0,500,400);
        add(cameraScreen);

        captureButton = new JButton("Capture");
        captureButton.setBounds(200,405, 80,40);
        add(captureButton);

        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked=true;
            }
        });


        setSize(new Dimension(500,500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void startCamera(){
        capture= new VideoCapture(0);
        image = new Mat();
        byte[] imageData;
        ImageIcon icon;
        while (true){
            capture.read(image);

            final MatOfByte buf = new MatOfByte();
            Imgcodecs.imencode(".jpg",image,buf);

            imageData=buf.toArray();

            icon = new ImageIcon(imageData);

            cameraScreen.setIcon(icon);
            
            if (clicked){
                String name = JOptionPane.showInputDialog("Enter Image Name");
                if (name==null){
                    name = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
                }
                Imgcodecs.imwrite("./images/"+name + ".jpg",image);
                clicked=false;
            }


        }
    }
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Load success");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                OCVMain camera = new OCVMain();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        camera.startCamera();
                    }
                }).start();

            }
        });
    }
}
