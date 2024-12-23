package main;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Classe principal que simula a geração e o processamento de interrupções em um sistema.
 */
public class Main {

    /**
     * Método principal que inicia a simulação.
     */
    public static void main(String[] args) {
       
        PriorityQueue<Interruption> filaDeInterrupcoes = new PriorityQueue<>();

        // Thread responsável por gerar interrupções aleatórias
        Thread geradorDeInterrupcoes = new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    // Define um intervalo aleatório entre 1 e 4 segundos para criar interrupções
                    int intervalo = 1000 + random.nextInt(4000);
                    Thread.sleep(intervalo);

                    // Gera de 1 a 3 interrupções aleatórias
                    int numeroDeInterrupcoes = 1 + random.nextInt(3);
                    for (int i = 0; i < numeroDeInterrupcoes; i++) {
                        int tipoAleatorio = random.nextInt(3); // 0, 1 ou 2 correspondem aos tipos de interrupções
                        switch (tipoAleatorio) {
                            case 0 -> InterruptionManager.gerarInterrupcaoTimer(filaDeInterrupcoes);
                            case 1 -> InterruptionManager.gerarInterrupcaoIO(filaDeInterrupcoes);
                            case 2 -> InterruptionManager.gerarInterrupcaoErroSistema(filaDeInterrupcoes);
                        }
                    }

                    // Espera adicional de 4 segundos antes da próxima geração
                    Thread.sleep(4000);

                } catch (InterruptedException e) {
                    System.out.println("Gerador de interrupções interrompido.");
                    break;
                }
            }
        });

        // Thread responsável por processar as interrupções presentes na fila de prioridade
        Thread processadorDeInterrupcoes = new Thread(() -> {
            while (true) {
                try {
                    // Aguarda 5 segundos antes de processar as interrupções
                    Thread.sleep(5000);

                    // Exibe o estado atual da fila de interrupções
                    System.out.println("Estado atual da fila de interrupções:");
                    InterruptionManager.verificarStatusQueue(filaDeInterrupcoes);
                    System.out.println("\n");

                    // Processa todas as interrupções presentes na fila
                    InterruptionManager.processarInterrupcoes(filaDeInterrupcoes);
                    System.out.println("\n");

                } catch (InterruptedException e) {
                    System.out.println("Processador de interrupções interrompido.");
                    break;
                }
            }
        });

        // Inicia as threads geradora e processadora de interrupções
        geradorDeInterrupcoes.start();
        processadorDeInterrupcoes.start();
    }
}