import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GestorEstudiantes {

    // Calcula la nota media de un estudiante
    public static double calcularNotaMedia(Estudiante estudiante) {
        double suma = 0;
        for (int i = 0; i < estudiante.getNotas().length; i++) { // Error: índice fuera de rango
            suma += estudiante.getNotas()[i];
        }
        if (estudiante.getNotas().length == 0) {
            return Double.NaN;
        }
        return suma / estudiante.getNotas().length; // Error si el array está vacío
    }

    // Encuentra al estudiante con la mejor nota media
    public static Estudiante encontrarMejorEstudiante(Estudiante[] estudiantes) {
        Estudiante mejor = null;
        double mejorNota = -1;

        for (Estudiante estudiante : estudiantes) {
            double media = calcularNotaMedia(estudiante); // Posible fallo aquí
            if (!Double.isNaN(media) && media > mejorNota) {
                mejorNota = media;
                mejor = estudiante;
            }
        }
        return mejor; // Error si la lista está vacía
    }

    // Guarda los resultados en un fichero
    public static void guardarResultados(Estudiante[] estudiantes, String rutaFichero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaFichero))) {
            for (Estudiante estudiante : estudiantes) {
                double media = calcularNotaMedia(estudiante);
                if (Double.isNaN(media)) {
                    writer.write("Nombre: " + estudiante.getNombre() + ", Nota Media: Sin notas");
                } else {
                    writer.write("Nombre: " + estudiante.getNombre() + ", Nota Media: " + String.format("%.2f", media));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el fichero: " + e.getMessage());
        }
    }

}
