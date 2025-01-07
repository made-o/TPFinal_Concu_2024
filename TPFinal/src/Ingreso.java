public class Ingreso extends Thread {
    private final Monitor monitor;

    public Ingreso(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // Disparar T0: Cliente entra a la agencia
            if (monitor.fireTransition(0)) {
                System.out.println("Dispara T0 → Cliente ingresó a la agencia");
            }
            
            // Disparar T1: Cliente pasa a la sala de espera
            if (monitor.fireTransition(1)) {
                System.out.println("Disparar T1 → Cliente en sala de espera (P3)");
            }
            
            try {
                Thread.sleep(500); // Simulación de tiempo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
