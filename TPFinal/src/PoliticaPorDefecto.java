public class PoliticaPorDefecto implements Politica {
    @Override
    public int seleccionarTransicion(boolean[] sensibilizadas, RdP red, int[] conteoReservas) {
        for (int i = 0; i < sensibilizadas.length; i++) {
            if (sensibilizadas[i]) {
                return i; // Selecciona la primera sensibilizada
            }
        }
        return -1; // Ninguna transición está sensibilizada
    }
}
