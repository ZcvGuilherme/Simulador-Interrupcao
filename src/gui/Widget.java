package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.InterruptType;

public class Widget {
    private JPanel painelWidget;
    private JLabel label;
    private InterruptType tipo;
    private float opacidade = 1.0f;  // Opacidade inicial é 100%

    public Widget(InterruptType tipo) {
        this.tipo = tipo;
        painelWidget = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Define a opacidade do painel
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidade));
                super.paintComponent(g);
            }
        };
        painelWidget.setLayout(null); 
        painelWidget.setBackground(Color.LIGHT_GRAY); 
        painelWidget.setBounds(0, 0, 270, 100); 
        
        // Criando o label com o texto baseado no tipo de interrupção
        label = new JLabel(tipo.name());
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBounds(20, 20, 230, 30); 
        painelWidget.add(label);
    }

    public JPanel getPainel() {
        return painelWidget;
    }

    public void atualizarTexto(String novoTexto) {
        label.setText(novoTexto);
    }

    public InterruptType getTipo() {
        return tipo;
    }
    public float getOpacidade() {
    	return opacidade;
    }
    public void setOpacidade(float opacidade) {
        this.opacidade = opacidade;
        painelWidget.repaint();  // Reforça o repintar do painel após alterar a opacidade
    }
}
