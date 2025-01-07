public class GestorReservas extends Thread {
    private final Monitor monitor;

    public GestorReservas(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            boolean disparo = monitor.fireTransition(-1);
            if (disparo) {
                System.out.println("Gestor de Reservas disparó una transición");
            } else {
                System.out.println("Gestor de Reservas no pudo disparar ninguna transición");
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
