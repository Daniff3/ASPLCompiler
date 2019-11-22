package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import render.FormattedPage;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -995263543566623829L;
	private FormattedPage fp;
	
	private JPanel contentPane;

	public MainWindow(FormattedPage fp) {
		this.fp = fp;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		cargaPagina();
	}
	
	private void cargaPagina() {
		
	}

}
