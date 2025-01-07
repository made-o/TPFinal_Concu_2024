public class ConfirmacionYPago extends Thread {
    private final Monitor monitor;

    public ConfirmacionYPago(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            // Etapa P9 -> P11: Confirmación de reserva
            if (monitor.fireTransition(11)) {
                System.out.println("Reserva confirmada (P11)");
            }
            
            // Etapa P11 -> P13: Pago realizado
            if (monitor.fireTransition(13)) {
                System.out.println("Pago realizado (P13)");
            }
            
            // Etapa P13 -> P14: Cliente listo para salir
            if (monitor.fireTransition(14)) {
                System.out.println("Cliente listo para salir de la agencia (P14)");
            }
            
            try {
                Thread.sleep(600); // Simulación de tiempo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
