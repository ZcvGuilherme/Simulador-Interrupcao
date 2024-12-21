package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Main.InterruptType;

public class TelaPrincipal extends Tela {
    private Color marromSuave = new Color(100, 50, 20);
    private Color marromEscuro = new Color(75, 40, 15);
    private Color laranjaSuave = new Color(255, 223, 186);
    private Color destaqueProcessar = new Color(255, 102, 0); // Cor vibrante para o botão de Processar

    private Botao Timer;
    private Botao IO;
    private Botao SysError;
    private Botao processar;

    private Painel painelEsquerdo;
    private Painel painelCentral;
    private Painel painelDireito;

    private List<Widget> widgetsAtivos = new ArrayList<>(); // Lista de widgets para processar

    private int alturaWidget = 60; // Altura dos widgets empilhados
    private int margemSuperior = 20; // Margem superior para o primeiro widget

    public TelaPrincipal() {
        super("Simulador Interrupção", 900, 700, 50, 40, false);

        // Criando os painéis com as proporções desejadas e cores agradáveis
        painelEsquerdo = new Painel(270, 700, 0, 0, new Color(240, 240, 240), null); // Cinza claro
        painelCentral = new Painel(360, 700, 270, 0, laranjaSuave, null); // Laranja suave
        painelDireito = new Painel(270, 700, 630, 0, new Color(240, 240, 240), null); // Cinza claro

        // Adicionando bordas arredondadas e estética aos painéis
        painelEsquerdo.getPainel().setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 2, true), // Borda arredondada
            new EmptyBorder(10, 10, 10, 10) // Espaçamento interno
        ));

        painelCentral.getPainel().setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 150, 100), 2, true), // Borda arredondada
            new EmptyBorder(10, 10, 10, 10) // Espaçamento interno
        ));

        painelDireito.getPainel().setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 2, true), // Borda arredondada
            new EmptyBorder(10, 10, 10, 10) // Espaçamento interno
        ));

        // Criando botões com espaçamento adequado
        Timer = new Botao("Criar Erro de Timer", 200, 50, marromEscuro);
        IO = new Botao("Criar Erro de IO", 200, 50, marromEscuro);
        SysError = new Botao("Criar Erro de Sys", 200, 50, marromEscuro);
        processar = new Botao("Processar Erros", 200, 50, destaqueProcessar);

        // Adicionando os painéis à tela
        tela.setLayout(null); // Usando layout absoluto
        tela.add(painelEsquerdo.getPainel());
        tela.add(painelCentral.getPainel());
        tela.add(painelDireito.getPainel());

        // Configurações finais
        widgetsConfig();
        mostrar();
    }

    private void widgetsConfig() {
        // Ajustando as posições dos botões com um espaçamento adequado
        Timer.getBotao().setBounds(80, 100, 200, 50);
        IO.getBotao().setBounds(80, 160, 200, 50);
        SysError.getBotao().setBounds(80, 220, 200, 50);
        
        // Posicionando o botão "Processar Erros" mais para baixo
        processar.getBotao().setBounds(80, 500, 200, 50); // Mais para baixo no painel central

        painelCentral.addComponente(Timer.getBotao());
        painelCentral.addComponente(IO.getBotao());
        painelCentral.addComponente(SysError.getBotao());
        painelCentral.addComponente(processar.getBotao());

        // Personalizando os botões
        customizarBotoes(Timer.getBotao());
        customizarBotoes(IO.getBotao());
        customizarBotoes(SysError.getBotao());
        customizarBotoes(processar.getBotao());
        
        // Adicionando action listeners para os botões
        Timer.getBotao().addActionListener(e -> exibirWidget(InterruptType.TIMER));
        IO.getBotao().addActionListener(e -> exibirWidget(InterruptType.IO));
        SysError.getBotao().addActionListener(e -> exibirWidget(InterruptType.SYSTEM_ERROR));
        processar.getBotao().addActionListener(e -> processarErros());
    }

    private void customizarBotoes(JButton botao) {
        botao.setBackground(marromSuave); // Cor de fundo
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte personalizada
        botao.setBorder(BorderFactory.createLineBorder(marromEscuro, 2, true)); // Borda arredondada
        botao.setFocusPainted(false); // Remove a borda de foco
        botao.setContentAreaFilled(true); // Garante que o fundo seja preenchido
        botao.setOpaque(true); // Garante que o fundo não seja transparente
    }

    private void exibirWidget(InterruptType tipo) {
        int widgetYPosition = painelDireito.getPainel().getHeight();
        Widget novoWidget = new Widget(tipo);
        novoWidget.getPainel().setBounds(0, widgetYPosition, painelDireito.getPainel().getWidth(), alturaWidget);

        widgetsAtivos.add(novoWidget);
        painelDireito.getPainel().add(novoWidget.getPainel());

        int posicaoFinalY = margemSuperior + (alturaWidget * (painelDireito.getPainel().getComponentCount() - 1));

        Timer timerAnimacao = new Timer(10, null);
        timerAnimacao.addActionListener(e -> {
            if (novoWidget.getPainel().getY() > posicaoFinalY) {
                novoWidget.getPainel().setLocation(novoWidget.getPainel().getX(), novoWidget.getPainel().getY() - 5);
            } else {
                timerAnimacao.stop();
                painelDireito.getPainel().revalidate();
                painelDireito.getPainel().repaint();
            }
        });

        timerAnimacao.setRepeats(true);
        timerAnimacao.start();

        // Definir o listener para saber quando a animação é concluída
        novoWidget.setListener(new AnimaçãoCompletaListener() {
            @Override
            public void onAnimaçãoCompleta(Widget widget) {
                // Aqui você pode fazer qualquer ação quando a animação for concluída
                System.out.println("Animação do widget " + widget.getTipo().name() + " completada!");
            }
        });
    }


    private void processarErros() {
        // Ordenar os widgets por prioridade (de 1 para cima)
        widgetsAtivos.sort(Comparator.comparingInt(widget -> widget.getTipo().getPrioridade()));

        // Timer para processar um erro por vez a cada 0.3 segundos
        Timer timer = new Timer(300, e -> {
            if (!widgetsAtivos.isEmpty()) {
                // Obter o primeiro widget (com maior prioridade)
                Widget widget = widgetsAtivos.get(0);

                // Resolver a interrupção (pode ser um texto ou mudança de cor)
                widget.resolverInterrupcao();

                // Remover o widget do painel e da lista de widgets ativos
                painelDireito.getPainel().remove(widget.getPainel());
                widgetsAtivos.remove(widget);

                // Atualizar a interface para refletir a remoção
                painelDireito.getPainel().revalidate();
                painelDireito.getPainel().repaint();

                // Verifica se ainda há widgets para processar
                if (widgetsAtivos.isEmpty()) {
                    // Parar o timer quando todos os widgets forem processados
                    ((Timer) e.getSource()).stop();  // Usar o timer atual para parar
                    JOptionPane.showMessageDialog(tela, "Todos os erros foram processados!");
                }
            }
        });

        // Iniciar o processamento dos erros
        timer.start();
    }

}
