
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private Lock lock = new ReentrantLock();

    public void dispararTransicion(Evento evento) {
        lock.lock();
        try {
            if (evento.estaSensibilizado()) {
                evento.ejecutar();
            } else {
                System.out.println("La transición no está sensibilizada.");
            }
        } finally {
            lock.unlock();
        }
    }
}
