public class Monitor implements MonitorInterface {
    private final RdP red;
    private final Politica politica;
    private final int[] conteoReservas; // Métricas para P6, P7, P11, P12

    public Monitor(RdP red, Politica politica) {
        this.red = red;
        this.politica = politica != null ? politica : new PoliticaPorDefecto();
        this.conteoReservas = new int[4];
    }

    @Override
    public synchronized boolean fireTransition(int transition) {
        // Verificar si se pasa una transición específica
        if (transition >= 0 && transition < red.getSensibilizadas().length) {
            if (red.isSensibilizada(transition)) {
                red.disparar(transition);
                actualizarConteos(transition);
                return true;
            }
            return false; // La transición específica no está sensibilizada
        }

        // Si no se pasa una transición específica, usar la política
        int seleccion = politica.seleccionarTransicion(red.getSensibilizadas(), red, conteoReservas);
        if (seleccion != -1) { // Validar que la política devolvió una transición válida
            red.disparar(seleccion);
            actualizarConteos(seleccion);
            return true;
        }

        // Ninguna transición puede dispararse
        return false;
    }

    private void actualizarConteos(int transicion) {
        switch (transicion) {
            case 6: // T2 → Usa P6
                conteoReservas[0]++;
                break;
            case 7: // T3 → Usa P7
                conteoReservas[1]++;
                break;
            default:
                break;
        }
    }

    public int[] getConteoReservas() {
        return conteoReservas;
    }
}
