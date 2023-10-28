package interfaz;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Vector;

public class PantallaHistorial extends  JFrame{

    public PantallaHistorial(int port){
        JFrame frame = new JFrame("Java Swing Table");
        frame.setSize(900, 900);
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 900, 900);
        JTable jTable1 = new JTable();
        JScrollPane sp = new JScrollPane(jTable1);
        panel.add(sp);
        frame.add(panel);

        String archivoCSV = "./dataBase/"+port+".csv";
        String linea;
        DefaultTableModel csv_data = new DefaultTableModel();

        csv_data.addColumn("Operacion");
        csv_data.addColumn("Resultado");
        csv_data.addColumn("Fecha");

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            while ((linea = br.readLine()) != null) {
                // Dividir la línea por comas para obtener los campos individuales
                String[] datos = linea.split(",");

                Vector row = new Vector();
                for (String dato : datos) {
                    row.add(dato);
                }
                csv_data.addRow(row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        jTable1.setModel(csv_data);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
