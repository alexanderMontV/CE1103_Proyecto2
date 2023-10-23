import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirCSV {

    public static void main(String[] args) {
        String archivoCSV = "datos.csv";
        String[] datos = {"Dato1", "Dato2", "Dato3", "Dato4"}; // Datos a escribir en el archivo CSV

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV))) {
            // Escribir datos en el archivo CSV, separados por comas
            for (String dato : datos) {
                bw.write(dato + ",");
            }
            bw.newLine(); // Nueva línea para la siguiente fila
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


