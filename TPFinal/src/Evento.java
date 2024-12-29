
import java.util.List;

public class Evento {
    private List<Estado> precondiciones;
    private List<Estado> postcondiciones;

    public Evento(List<Estado> precondiciones, List<Estado> postcondiciones) {
        this.precondiciones = precondiciones;
        this.postcondiciones = postcondiciones;
    }

    public boolean estaSensibilizado() {
        for (Estado estado : precondiciones) {
            if (estado.getTokens() == 0) {
                return false;
            }
        }
        return true;
    }

    public void ejecutar() {
        if (estaSensibilizado()) {
            for (Estado estado : precondiciones) {
                estado.removerToken();
            }
            for (Estado estado : postcondiciones) {
                estado.agregarToken();
            }
        } else {
            throw new IllegalStateException("La transición no está sensibilizada.");
        }
    }
}
