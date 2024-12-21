package gui;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Component;
import javax.swing.JPanel;

public class Painel {
	protected JPanel painel;
	public Painel(int width, int heigth, int x, int y, Color Background, LayoutManager layout) {
		painel = new JPanel();
		painel.setBounds(x, y, width, heigth);
		painel.setBackground(Background);
		painel.setLayout(layout);
	}
	
	public void addComponente(Component componente) {
		painel.add(componente);
	}
	public JPanel getPainel() {
		return painel;
	}
	public void setVisivel(boolean viz) {
		painel.setVisible(viz);
	}

}
