//import java.util.Arrays;

//import java.util.Arrays;

public class AgenciaDeViajes {

    public static void main(String[] args) {

        /*
         * // PRUEBAS DE QUE FUNCIONA LA RDP:
         * // Mostrar el estado inicial
         * System.out.println("Marcado inicial:");
         * System.out.println(Arrays.toString(red.getVectorM()));
         * System.out.println("Transiciones sensibilizadas iniciales:");
         * System.out.println(Arrays.toString(red.getSensibilizadas()));
         * 
         * // Disparar T0
         * red.disparar(0);
         * System.out.println("Después de disparar T0:");
         * System.out.println("Marcado:");
         * System.out.println(Arrays.toString(red.getVectorM()));
         * System.out.println("Transiciones sensibilizadas:");
         * System.out.println(Arrays.toString(red.getSensibilizadas()));
         * 
         * // Disparar T1
         * red.disparar(1);
         * System.out.println("Después de disparar T1:");
         * System.out.println("Marcado:");
         * System.out.println(Arrays.toString(red.getVectorM()));
         * System.out.println("Transiciones sensibilizadas:");
         * System.out.println(Arrays.toString(red.getSensibilizadas()));
         */

        /*
         * // PRUEBA DE MONITOR Y DISPAROS DE T0 Y T1:
         * RdP red = new RdP(); // Instancia de la red de Petri
         * Monitor monitor = new Monitor(red, new PoliticaPorDefecto()); // Monitor con
         * política por defecto
         * 
         * // Marcado inicial
         * System.out.println("Marcado inicial: " +
         * java.util.Arrays.toString(red.getVectorM()));
         * System.out.println(
         * "Transiciones sensibilizadas iniciales: " +
         * java.util.Arrays.toString(red.getSensibilizadas()));
         * 
         * // Disparar T0
         * if (monitor.fireTransition(0)) {
         * System.out.println("Disparó T0 → Cliente ingresó a la agencia");
         * }
         * 
         * // Verificar estado después de T0
         * System.out.println("Marcado después de T0: " +
         * java.util.Arrays.toString(red.getVectorM()));
         * System.out.println(
         * "Transiciones sensibilizadas después de T0: " +
         * java.util.Arrays.toString(red.getSensibilizadas()));
         * 
         * // Disparar T1
         * if (monitor.fireTransition(1)) {
         * System.out.println("Disparó T1 → Cliente en sala de espera");
         * }
         * 
         * // Verificar estado después de T1
         * System.out.println("Marcado después de T1: " +
         * java.util.Arrays.toString(red.getVectorM()));
         * System.out.println(
         * "Transiciones sensibilizadas después de T1: " +
         * java.util.Arrays.toString(red.getSensibilizadas()));
         * 
         * 
         */

        // PRUEBA DE POLÍTICA PARA DISPARO DE T2 O T3
        // 1. Inicializar la red de Petri y el monitor con la política balanceada
        RdP red = new RdP();
        Politica politica = new PoliticaBalanceada(); // Puedes cambiar a PoliticaPorDefecto si prefieres
        Monitor monitor = new Monitor(red, politica);

        // 2. Crear los hilos
        Ingreso ingreso = new Ingreso(monitor); // Maneja T0 y T1
        GestorReservas gestorReservas = new GestorReservas(monitor); // Maneja T2, T3, T4 y T5

        // 3. Iniciar los hilos
        ingreso.start();
        gestorReservas.start();

        // 4. Simular ejecución por 10 segundos
        try {
            Thread.sleep(10000); // Deja correr el sistema por 10 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 5. Interrumpir y unir los hilos
        ingreso.interrupt();
        gestorReservas.interrupt();
        try {
            ingreso.join();
            gestorReservas.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 6. Mostrar métricas finales
        System.out.println("Métricas finales:");
        System.out.println("Reservas en P6: " + monitor.getConteoReservas()[0]);
        System.out.println("Reservas en P7: " + monitor.getConteoReservas()[1]);





    }
}
