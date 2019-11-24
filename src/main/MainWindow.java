package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import render.FormattedLine;
import render.FormattedPage;
import render.FormattedText;

public class MainWindow extends JFrame {

	private FormattedPage fp;
	
	private static final long serialVersionUID = -1765963283385546021L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextPane textPane;

	public MainWindow(FormattedPage fp) {
		this.fp = fp;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		
		try {
			cargaPagina();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void cargaPagina() throws BadLocationException {
		StyledDocument document = textPane.getStyledDocument();
		SimpleAttributeSet lineAttributeSet;
		SimpleAttributeSet textAttributeSet;
		
		for(FormattedLine fl: fp.getLines()) {
			for(FormattedText ft: fl.getTexts()) {
				textAttributeSet = new SimpleAttributeSet();
				
				switch(ft.getFont_style()) {
				case "bold":
					StyleConstants.setBold(textAttributeSet, true);
					break;
				case "italic":
					StyleConstants.setItalic(textAttributeSet, true);
					break;
				case "underlined":
					StyleConstants.setUnderline(textAttributeSet, true);
					break;
				}
				
				int font_size = (int) ft.getFont_size();
				Color color = getColor(ft.getColor());
				
				if(font_size != -1)
					StyleConstants.setFontSize(textAttributeSet, font_size);
				if(color != null)
					StyleConstants.setForeground(textAttributeSet, color);
				
				document.insertString(document.getLength(), ft.getText(), textAttributeSet);
				document.insertString(document.getLength(), " ", null);			
			}	
			
			lineAttributeSet = new SimpleAttributeSet();
			int alignment = getAlignment(fl.getText_align());
			if (alignment != -1)
				StyleConstants.setAlignment(lineAttributeSet, alignment);
			
			document.setParagraphAttributes(document.getLength(), document.getLength(), lineAttributeSet, false);
			document.insertString(document.getLength(), "\n", null);
		}
		
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	private Color getColor(String color) {
		Color resultado = null;
		
		switch(color) {
		case "blue":
			resultado = Color.blue;
			break;
		case "red":
			resultado = Color.red;
			break;
		case "green":
			resultado = Color.green;
			break;
		case "black":
			resultado = Color.black;
			break;
		}
		
		return resultado;
	}
	
	private int getAlignment(String text_align) {
		int result = -1;
		
		switch(text_align) {
		case "center":
			result = StyleConstants.ALIGN_CENTER;
			break;
		case "left":
			result = StyleConstants.ALIGN_LEFT;
			break;
		case "right":
			result = StyleConstants.ALIGN_RIGHT;
		}
		
		return result;
	}
	
	private JTextPane getTextPane() {
	    if (textPane == null) {
	      textPane = new JTextPane();
	      textPane.setEditable(false);
	    }
	    return textPane;
	}
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getTextPane());
		}
		return scrollPane;
	}
	
}
