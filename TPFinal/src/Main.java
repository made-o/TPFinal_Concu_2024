//import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Crear una instancia de la RdP
        RdP rdp = new RdP();

        // Activar modo depuración para ver detalles
        rdp.setDebugMode(false);  // Activar la depuración
        rdp.printInfo();

        // Verificar sensibilización inicial:
        System.out.println("Transiciones sensibilizadas inicialmente:");
        boolean[] sensibilizadas = rdp.getSensibilizadas();
        for (int i = 0; i < sensibilizadas.length; i++) {
            System.out.println("T" + i + ": " + sensibilizadas[i]);
        }

         // Intentar disparar una transición
        int transicion = 0; // Puedes probar con diferentes transiciones
        if (rdp.isSensibilizada(transicion)) {
            System.out.println("Disparando transición T" + transicion);
            rdp.disparar(transicion);
        } else {
            System.out.println("La transición T" + transicion + " no está sensibilizada.");
        }

        // Verificar sensibilización después del disparo
        System.out.println("Transiciones sensibilizadas después del disparo:");
        sensibilizadas = rdp.getSensibilizadas();
        for (int i = 0; i < sensibilizadas.length; i++) {
            System.out.println("T" + i + ": " + sensibilizadas[i]);
        }

        // Imprimir el estado actualizado de la red
        rdp.printInfo(); 
        
    }
}
