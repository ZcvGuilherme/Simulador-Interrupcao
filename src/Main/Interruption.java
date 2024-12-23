package main;

/**
 * Classe que representa uma interrupção e implementa a interface {@code Comparable} para ordenação.
 * A ordenação é realizada com base na prioridade da interrupção.
 */
public class Interruption implements Comparable<Interruption> {

    /**
     * O tipo de interrupção, contendo informações sobre sua prioridade.
     */
    private InterruptType tipo;

    /**
     * Construtor que inicializa a interrupção com um tipo específico.
     *
     * @param tipo o tipo da interrupção (por exemplo, Timer, IO, System Error, etc.).
     */
    public Interruption(InterruptType tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtém o tipo da interrupção.
     *
     * @return o tipo da interrupção.
     */
    public InterruptType getTipo() {
        return tipo;
    }

    /**
     * Obtém a prioridade da interrupção com base no seu tipo.
     *
     * @return a prioridade da interrupção.
     */
    public int getPrioridade() {
        return tipo.getPrioridade();
    }

    /**
     * Um método privado de exemplo que retorna sempre o valor 0.
     *
     * @param a um parâmetro inteiro.
     * @return sempre retorna o valor 0.
     */
    @SuppressWarnings("unused")
    private int metodo(int a) {
        return 0;
    }

    /**
     * Compara esta interrupção com outra para fins de ordenação.
     *
     * @param o a outra interrupção a ser comparada.
     * @return um valor negativo se esta interrupção tiver menor prioridade,
     *         zero se tiver a mesma prioridade, ou um valor positivo se tiver maior prioridade.
     */
    @Override
    public int compareTo(Interruption o) {
        return Integer.compare(tipo.getPrioridade(), o.getPrioridade());
    }

    /**
     * Retorna uma representação em forma de string da interrupção.
     *
     * @return uma string que descreve a interrupção, incluindo seu tipo e prioridade.
     */
    @Override
    public String toString() {
        return "Interruption{" +
                "tipo=" + tipo +
                ", prioridade=" + getPrioridade() +
                '}';
    }
}
