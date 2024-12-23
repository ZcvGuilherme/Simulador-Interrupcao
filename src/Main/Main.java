package Main;

import java.util.PriorityQueue;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Fila de prioridade para interrupções
    	//Exemplo de mudança
        PriorityQueue<Interruption> filaDeInterrupcoes = new PriorityQueue<>();
        Thread geradorDeInterrupcoes = new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    int intervalo = 1000 + random.nextInt(4000);
                    Thread.sleep(intervalo);
                    int numeroDeInterrupcoes = 1 + random.nextInt(3);
                    for (int i = 0; i < numeroDeInterrupcoes; i++) {
                        int tipoAleatorio = random.nextInt(3); // 0, 1 ou 2
                        switch (tipoAleatorio) {
                            case 0 -> InterruptionManager.gerarInterrupcaoTimer(filaDeInterrupcoes);
                            case 1 -> InterruptionManager.gerarInterrupcaoIO(filaDeInterrupcoes);
                            case 2 -> InterruptionManager.gerarInterrupcaoErroSistema(filaDeInterrupcoes);
                        }
                    }
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    System.out.println("Gerador de interrupções interrompido.");
                    break;
                }
            }
        });

        // Thread processadora de interrupções
        Thread processadorDeInterrupcoes = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // Aguarda um tempo antes de processar as interrupções

                    // Limpa a tela utilizando múltiplas quebras de linha
                    

                    System.out.println("Estado atual da fila de interrupções:");
                    InterruptionManager.verificarStatusQueue(filaDeInterrupcoes);
                    System.out.println("\n");
                    InterruptionManager.processarInterrupcoes(filaDeInterrupcoes);
                    System.out.println("\n");
                } catch (InterruptedException e) {
                    System.out.println("Processador de interrupções interrompido.");
                    break;
                }
            }
        });

        // Inicia as threads
        geradorDeInterrupcoes.start();
        processadorDeInterrupcoes.start();
    }

}
