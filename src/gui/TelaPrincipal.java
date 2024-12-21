package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
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
        // Posição inicial do widget fora da tela, na parte inferior do painel direito
        int widgetYPosition = painelDireito.getPainel().getHeight();

        // Criação do widget
        Widget novoWidget = new Widget(tipo);
        novoWidget.getPainel().setBounds(0, widgetYPosition, painelDireito.getPainel().getWidth(), alturaWidget);

        // Adicionando o widget à lista de widgets ativos
        widgetsAtivos.add(novoWidget);
        
        // Adicionando o widget ao painel direito
        painelDireito.getPainel().add(novoWidget.getPainel());

        // Posição final (acima dos widgets existentes)
        int posicaoFinalY = margemSuperior + (alturaWidget * (painelDireito.getPainel().getComponentCount() - 1));

        // Timer para animar o movimento do widget
        Timer timerAnimacao = new Timer(10, null);

        timerAnimacao.addActionListener(e -> {
            // Movimento do widget para cima
            if (novoWidget.getPainel().getY() > posicaoFinalY) {
                novoWidget.getPainel().setLocation(novoWidget.getPainel().getX(), novoWidget.getPainel().getY() - 5);
            } else {
                timerAnimacao.stop(); // Para a animação ao atingir a posição final
                painelDireito.getPainel().revalidate(); // Atualiza o layout
                painelDireito.getPainel().repaint();   // Repinta o painel
            }
        });

        timerAnimacao.setRepeats(true);
        timerAnimacao.start();
    }

    private void processarErros() {
        // Ordenar widgets com base na prioridade (definido pelo tipo de erro)
        widgetsAtivos.sort(Comparator.comparingInt(widget -> widget.getTipo().getPrioridade()));

        // Para cada widget, anima o desaparecimento e movimenta os widgets acima
        for (Widget widget : widgetsAtivos) {
            Timer timerAnimacao = new Timer(10, null);

            // Animação de desaparecimento do widget
            timerAnimacao.addActionListener(e -> {
                if (widget.getOpacidade() > 0) {
                    widget.setOpacidade(widget.getOpacidade() - 0.05f); // Faz o widget desaparecer
                } else {
                    // Remove o widget da tela
                    painelDireito.getPainel().remove(widget.getPainel());
                    painelDireito.getPainel().revalidate();
                    painelDireito.getPainel().repaint();

                    // Ajusta a posição dos widgets acima
                    ajustarWidgets();
                    timerAnimacao.stop();
                }
            });

            timerAnimacao.setRepeats(true);  // O Timer repete a ação a cada 10 milissegundos
            timerAnimacao.start();           // Inicia o Timer
        }
    }


    private void ajustarWidgets() {
        // Ajustar a posição dos widgets restantes após a remoção
        for (int i = 0; i < painelDireito.getPainel().getComponentCount(); i++) {
            JPanel widget = (JPanel) painelDireito.getPainel().getComponent(i);
            int novaPosicaoY = margemSuperior + (alturaWidget * i);
            
            // Anima a movimentação dos widgets para cima
            Timer moverWidgetTimer = new Timer(10, e -> {
                if (widget.getY() > novaPosicaoY) {
                    widget.setLocation(widget.getX(), widget.getY() - 5);
                } else {
                    ((Timer)e.getSource()).stop();
                }
            });
            moverWidgetTimer.start();
        }
    }
}
