package main;

import java.util.PriorityQueue;

/**
 * Classe responsável pelo gerenciamento de interrupções, incluindo a geração e o processamento de interrupções.
 * Fornece métodos para gerar e processar interrupções de diferentes tipos,
 * além de verificar o status atual da fila.
 */
public class InterruptionManager {

    /**
     * Adiciona uma interrupção do tipo Timer à fila de interrupções.
     *
     * @param fila a fila de interrupções do tipo {@code PriorityQueue<Interruption>} onde a interrupção será adicionada.
     */
    public static void gerarInterrupcaoTimer(PriorityQueue<Interruption> fila) {
        Interruption timer = new Interruption(InterruptType.TIMER);
        fila.add(timer);
        System.out.println("Interrupção de Timer gerada! \n");
    }

    /**
     * Adiciona uma interrupção do tipo IO à fila de interrupções.
     *
     * @param fila a fila de interrupções do tipo {@code PriorityQueue<Interruption>} onde a interrupção será adicionada.
     */
    public static void gerarInterrupcaoIO(PriorityQueue<Interruption> fila) {
        Interruption io = new Interruption(InterruptType.IO);
        fila.add(io);
        System.out.println("Interrupção de Entrada/Saída gerada!\n");
    }

    /**
     * Adiciona uma interrupção do tipo Erro de Sistema ({@code SYSTEM_ERROR}) à fila de interrupções.
     *
     * @param fila a fila de interrupções do tipo {@code PriorityQueue<Interruption>} onde a interrupção será adicionada.
     */
    public static void gerarInterrupcaoErroSistema(PriorityQueue<Interruption> fila) {
        Interruption erro = new Interruption(InterruptType.SYSTEM_ERROR);
        fila.add(erro);
        System.out.println("Interrupção de Erro de Sistema gerada!\n");
    }

    /**
     * Processa todas as interrupções na fila de acordo com a prioridade, removendo-as em ordem de prioridade.
     * Imprime no console o status de cada interrupção processada.
     *
     * @param fila a fila de interrupções do tipo {@code PriorityQueue<Interruption>} contendo as interrupções.
     */
    public static void processarInterrupcoes(PriorityQueue<Interruption> fila) {
        if (fila.isEmpty()) {
            System.out.println("A fila de interrupções está vazia.");
            return;
        }

        System.out.println("\n\nIniciando processamento das interrupções:");
        while (!fila.isEmpty()) {
            Interruption interrupcao = fila.poll();
            System.out.println("Processando: " + formatarInterrupcao(interrupcao));
        }
    }

    /**
     * Verifica o status atual da fila de interrupções, exibindo todas as interrupções ordenadas por ordem de prioridade.
     *
     * @param fila a fila de interrupções do tipo {@code PriorityQueue<Interruption>} contendo as interrupções.
     */
    public static void verificarStatusQueue(PriorityQueue<Interruption> fila) {
        fila.stream().sorted().forEach(interrupcao -> System.out.println(formatarInterrupcao(interrupcao)));
    }

    /**
     * Formata uma interrupção como uma string, incluindo o tipo e a prioridade.
     *
     * @param interrupcao a interrupção a ser formatada.
     * @return uma string formatada contendo informações do tipo e prioridade da interrupção.
     */
    private static String formatarInterrupcao(Interruption interrupcao) {
        return String.format("Tipo: %-15s | Prioridade: %d",
                interrupcao.getTipo(), interrupcao.getPrioridade());
    }

}
