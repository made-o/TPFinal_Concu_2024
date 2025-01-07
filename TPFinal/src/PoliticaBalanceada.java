public class PoliticaBalanceada implements Politica {
    @Override
    public int seleccionarTransicion(boolean[] sensibilizadas, RdP red, int[] conteoReservas) {
        if (sensibilizadas[2] && sensibilizadas[3]) {
            // Balancear entre T2 y T3
            if (conteoReservas[0] <= conteoReservas[1]) {
                return 2; // Seleccionar T2
            } else {
                return 3; // Seleccionar T3
            }
        }

        // Seleccionar solo T2 o T3 si una de ellas está sensibilizada
        if (sensibilizadas[2])
            return 2;
        if (sensibilizadas[3])
            return 3;

        // Elegir otras transiciones sensibilizadas
        for (int i = 0; i < sensibilizadas.length; i++) {
            if (sensibilizadas[i])
                return i;
        }

        return -1; // No hay transiciones sensibilizadas
    }

}
