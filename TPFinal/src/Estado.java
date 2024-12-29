
public class Estado {
    private int tokens;

    public Estado(int tokensIniciales) {
        this.tokens = tokensIniciales;
    }

    public synchronized int getTokens() {
        return tokens;
    }

    public synchronized void agregarToken() {
        tokens++;
    }

    public synchronized void removerToken() {
        if (tokens > 0) {
            tokens--;
        } else {
            throw new IllegalStateException("No hay tokens para remover.");
        }
    }
}
