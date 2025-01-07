import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Arrays;

/**
 * Clase RdP.
 * Representa la Red de Petri con la capacidad de cargar su configuración desde
 * archivos
 * y gestionar las transiciones sensibilizadas y disparos de eventos.
 * 
 * @author Made Orona
 * @version 1.0
 */
public class RdP {
    // Variable que controla la depuración
    private boolean debugMode = false; // Por defecto desactivado

    int P = 15;
    int T = 12;
    String path_matrixI = "/home/made/Documentos/FACULTAD/PConc/TPFinal_Concu_2024/TPFinal/lib/matrizI.txt";
    String path_matrixI_neg = "/home/made/Documentos/FACULTAD/PConc/TPFinal_Concu_2024/TPFinal/lib/matriz_I.txt";
    String path_vectorM = "/home/made/Documentos/FACULTAD/PConc/TPFinal_Concu_2024/TPFinal/lib/vectorM.txt";

    private int[][] matrixI;
    private int[][] matrixI_neg;
    private int[] vectorM;
    private boolean[] sensibilizadas;

    /**
     * CONSTRUCTOR
     */
    public RdP() {
        matrixI = new int[P][T];
        matrixI_neg = new int[P][T];
        vectorM = new int[P];

        // Carga de matriz de incidencias y vector de marcado:
        try {
            matrixI = readMatrix(path_matrixI);
            matrixI_neg = readMatrix(path_matrixI_neg);
            vectorM = readVector(path_vectorM);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sensibilizadas = new boolean[matrixI[0].length];
        updateSensibilizadas();
    }

    /**
     * Método que actualiza el vector de transiciones sensibilizadas.
     * Una transición está sensibilizada si cumple las precondiciones definidas en
     * la matriz
     * de incidencia negativa y las restricciones definidas (si es que tiene).
     */
    public void updateSensibilizadas() {

        // System.out.println("Marcado:");
        // System.out.println(Arrays.toString(vectorM));

        // Iteramos sobre todas las transiciones (columnas de la matriz)
        for (int t = 0; t < sensibilizadas.length; t++) {
            boolean sensibilizada = true;

            // Revisamos cada plaza
            for (int p = 0; p < vectorM.length; p++) {
                // Verificamos que haya suficientes tokens (M[p] >= I^-[p][t])
                if (vectorM[p] < matrixI_neg[p][t]) {
                    sensibilizada = false;
                    break;
                }
            }
            // acá verificamos la condición de tiempo

            // almacenamos si la transición está sensibilizada
            sensibilizadas[t] = sensibilizada;
        }
    }

    /**
     * Ejecuta una transición disparándola si está sensibilizada.
     * Actualiza el vector de marcado y las transiciones sensibilizadas después del
     * disparo.
     * 
     * @param transicion índice de la transición a disparar (debe estar
     *                   sensibilizada).
     * @throws ArrayIndexOutOfBoundsException si el índice de la transición no es
     *                                        válido.
     */
    public void disparar(int transicion) {
        if (sensibilizadas[transicion]) {
            // Modificar vectorM directamente
            for (int p = 0; p < vectorM.length; p++) {
                vectorM[p] += matrixI[p][transicion];
            }

            // Log de disparo, solo si es necesario
            if (debugMode) { // Usa una variable booleana para controlar la impresión
                System.out.println("Disparó T" + transicion);
                System.out.println("Marcado después de T" + transicion + ": " + java.util.Arrays.toString(vectorM));
            }

            // Actualizar sensibilizaciones después del disparo
            updateSensibilizadas();
        }
    }

    /**
     * Verifica si una transición está sensibilizada.
     * 
     * @param transicion índice de la transición.
     * @return true si la transición está sensibilizada; false en caso contrario.
     */
    public boolean isSensibilizada(int transicion) {
        return sensibilizadas[transicion];
    }

    /**
     * Obtiene el vector de transiciones sensibilizadas.
     * 
     * @return un arreglo booleano donde cada posición indica si la transición está
     *         sensibilizada.
     */
    public boolean[] getSensibilizadas() {
        return sensibilizadas;
    }

    public int[] getVectorM() {
        return vectorM; // Devuelve el vector de marcado
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Lee una matriz desde un archivo de texto.
     * 
     * @param filePath ruta al archivo que contiene la matriz.
     * @return una matriz bidimensional de enteros leída desde el archivo.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    private int[][] readMatrix(String filePath) throws IOException {
        int[][] matrix = new int[P][T];
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split("\t");
                for (int col = 0; col < values.length; col++) {
                    matrix[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
        }
        return matrix;
    }

    private int[] readVector(String filePath) throws IOException {
        int[] vector = new int[P];
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                String[] values = line.split("\t");
                for (int i = 0; i < values.length; i++) {
                    vector[i] = Integer.parseInt(values[i]);
                }
            }
        }
        return vector;
    }

    public void printInfo() {
        if (debugMode) {
            System.out.println("Matriz I:");
            printMatrix(matrixI);
            System.out.println("Matriz I_neg:");
            printMatrix(matrixI_neg);
            System.out.println("Vector M:");
            printVector(vectorM);
        }
    }

    private void printMatrix(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + "\t");
            }
            System.out.println();
        }
    }

    private void printVector(int[] vector) {
        for (int valor : vector) {
            System.out.print(valor + "\t");
        }
        System.out.println();
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

}
