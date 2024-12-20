package Main;

import java.util.PriorityQueue;

public class InterruptionManager {

    // Gera uma interrupção de Timer
    public static void gerarInterrupcaoTimer(PriorityQueue<Interruption> fila) {
        Interruption timer = new Interruption(InterruptType.TIMER);
        fila.add(timer);
        System.out.println("Interrupção de Timer gerada! \n");
    }

    // Gera uma interrupção de I/O
    public static void gerarInterrupcaoIO(PriorityQueue<Interruption> fila) {
        Interruption io = new Interruption(InterruptType.IO);
        fila.add(io);
        System.out.println("Interrupção de Entrada/Saída gerada!\n");
    }

    public static void gerarInterrupcaoErroSistema(PriorityQueue<Interruption> fila) {
        Interruption erro = new Interruption(InterruptType.SYSTEM_ERROR);
        fila.add(erro);
        System.out.println("Interrupção de Erro de Sistema gerada!\n");
    }

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
    public static void verificarStatusQueue(PriorityQueue<Interruption> fila) {
    	fila.stream().sorted().forEach(interrupcao -> System.out.println(formatarInterrupcao(interrupcao)));
    }
    
    private static String formatarInterrupcao(Interruption interrupcao) {
        return String.format("Tipo: %-15s | Prioridade: %d",
                interrupcao.getTipo(), interrupcao.getPrioridade());
    }
    
}
