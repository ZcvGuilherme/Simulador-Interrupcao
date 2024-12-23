package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class Botao {
    protected JButton botao;
    public Botao(String texto, int largura, int altura, Color corDeFundo) {
        botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(largura, altura)); 
        botao.setBackground(corDeFundo); 
        botao.setFocusPainted(false); 
        botao.setBorder(new EmptyBorder(10, 20, 10, 20)); 
    }

    // Método para obter o botão
    public JButton getBotao() {
        return botao;
    }

    public void setVisivel(boolean visivel) {
        botao.setVisible(visivel);
    }
}
