package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ToolTipManager;

import Main.InterruptType;

public class Widget {
    private AnimaçãoCompletaListener listener;
    private JPanel painelWidget;
    private JLabel label;
    private InterruptType tipo;
    private float opacidade = 1.0f; // Opacidade inicial (totalmente visível)

    public Widget(InterruptType tipo) {
        this.tipo = tipo;
        painelWidget = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Aplica a cor de fundo com a opacidade controlada
                Color corFundo = new Color(192, 192, 192, (int) (opacidade * 255)); // A cor com opacidade
                g.setColor(corFundo);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        painelWidget.setLayout(null);
        painelWidget.setBounds(0, 0, 270, 100);
        painelWidget.setOpaque(false); // Permite controle manual da opacidade

        // Criando o label com o texto baseado no tipo de interrupção
        label = new JLabel(tipo.name());
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBounds(20, 20, 230, 30);
        painelWidget.add(label);

        // Configura o Tooltip para o painel
        painelWidget.setToolTipText("Especificações: " + tipo.name()); // Exibe o nome da interrupção
        ToolTipManager.sharedInstance().setInitialDelay(0);  // Atraso inicial para exibir o tooltip
        ToolTipManager.sharedInstance().setDismissDelay(5000); // Tempo para desaparecer após 5 segundos

        // Adiciona MouseListener para mostrar o Tooltip customizado
        painelWidget.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                painelWidget.setToolTipText("<html>Tipo de Interrupção: " + tipo.name() + "<br>Prioridade: " + tipo.getPrioridade() + "</html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                painelWidget.setToolTipText(null); // Remove o tooltip
            }
        });
    }

    public JPanel getPainel() {
        return painelWidget;
    }

    public void atualizarTexto(String novoTexto) {
        label.setText(novoTexto);
    }

    public void setListener(AnimaçãoCompletaListener listener) {
        this.listener = listener;
    }

    public InterruptType getTipo() {
        return tipo;
    }

    public void iniciarAnimacao() {
        Timer timer = new Timer(30, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacidade > 0) {
                    opacidade -= 0.05f;
                    painelWidget.repaint(); // Repinta o painel com a nova opacidade
                } else {
                    ((Timer) e.getSource()).stop();
                    painelWidget.getParent().remove(painelWidget);
                    painelWidget.getParent().revalidate();
                    painelWidget.getParent().repaint();
                }
            }
        });
        timer.start();
    }

    public void resolverInterrupcao() {
        Timer timer = new Timer(30, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacidade > 0) {
                    opacidade -= 0.05f;
                    painelWidget.repaint(); // Repinta o painel com a nova opacidade
                } else {
                    ((Timer) e.getSource()).stop();

                    // Verifica se o painel tem um contêiner pai antes de remover
                    if (painelWidget.getParent() != null) {
                        painelWidget.getParent().remove(painelWidget);
                        painelWidget.getParent().revalidate();
                        painelWidget.getParent().repaint();
                    }

                    if (listener != null) {
                        listener.onAnimaçãoCompleta(Widget.this);
                    }
                }
            }
        });
        timer.start();
    }


    // Este método não é mais necessário, já que a opacidade é controlada em paintComponent
    public void renderComOpacidade() {
        painelWidget.repaint(); // Repinta o painel com a opacidade atual
    }
}
