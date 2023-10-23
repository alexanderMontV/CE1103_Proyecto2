import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerCSV {

    public static void main(String[] args) {
        String archivoCSV = "datos.csv";
        String linea;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            while ((linea = br.readLine()) != null) {
                // Dividir la línea por comas para obtener los campos individuales
                String[] datos = linea.split(",");

                for (String dato : datos) {
                    System.out.print(dato + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
