public interface Politica {
    /**
     * Selecciona una transición para disparar según la política definida.
     * 
     * @param sensibilizadas Vector de transiciones sensibilizadas.
     * @param red Instancia de la Red de Petri.
     * @param conteoReservas Array para balancear reservas.
     *                       [0] -> Clientes atendidos en P6
     *                       [1] -> Clientes atendidos en P7
     *                       [2] -> Reservas confirmadas en P11
     *                       [3] -> Reservas canceladas en P12
     * @return Índice de la transición seleccionada, o -1 si no se puede disparar ninguna.
     */
    int seleccionarTransicion(boolean[] sensibilizadas, RdP red, int[] conteoReservas);
}
