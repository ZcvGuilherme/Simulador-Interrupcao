package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class Botao {
    protected JButton botao;

    // Construtor para criar um botão com tamanho, texto e cor personalizados
    public Botao(String texto, int largura, int altura, Color corDeFundo) {
        botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(largura, altura)); // Define o tamanho do botão
        botao.setBackground(corDeFundo); // Define a cor de fundo do botão
        botao.setFocusPainted(false); // Remove o efeito de foco ao clicar
        botao.setBorder(new EmptyBorder(10, 20, 10, 20)); // Define o espaçamento interno
    }

    // Método para obter o botão
    public JButton getBotao() {
        return botao;
    }

    // Método para definir a visibilidade do botão
    public void setVisivel(boolean visivel) {
        botao.setVisible(visivel);
    }
}
