package main;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;

import css.ast.AstCss;
import css.parser.LexiconCss;
import css.parser.ParserCss;
import css.visitor.BuscaParamEnCssVisitor;
import html.ast.AstHtml;
import html.parser.LexiconHtml;
import html.parser.ParserHtml;
import html.visitor.BuscaCssEnHtmlVisitor;
import html.visitor.RenderVisitor;
import render.FormattedPage;

public class Main {

	private static FormattedPage fp;
	private static final String HTML_FILE = "EX4.html";
	private static final String CSS_DEFAULT_FILE = "Default.css";
	
	public static void main(String[] args) {
        try {
			fp = loadPage();
			createAndShowGUI();
		} 
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
	
	private static void createAndShowGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainWindow frame = new MainWindow(fp);
				frame.setTitle(fp.getPage_title());
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
	private static FormattedPage loadPage() throws FileNotFoundException {
        AstHtml htmlAst = creaArbolHtml(HTML_FILE);
        BuscaCssEnHtmlVisitor bCss = new BuscaCssEnHtmlVisitor();
        String css = (String) htmlAst.accept(bCss, null);
        AstCss cssAst = creaArbolCss(css);
        AstCss defaultAst = creaArbolCss(CSS_DEFAULT_FILE);
        RenderVisitor render = new RenderVisitor(htmlAst, new BuscaParamEnCssVisitor(), defaultAst, cssAst);
        return render.getFormattedPage();
	}
	
	private static AstHtml creaArbolHtml(String file) throws FileNotFoundException {
        FileReader fileHtml = new FileReader(file);
        LexiconHtml lexHtml = new LexiconHtml(fileHtml);
        ParserHtml parserHtml = new ParserHtml(lexHtml);
        return parserHtml.parse();
    }

    private static AstCss creaArbolCss(String file) throws FileNotFoundException {
        FileReader fileCss = new FileReader(file);
        LexiconCss lexCss = new LexiconCss(fileCss);
        ParserCss parserCss = new ParserCss(lexCss);
        return parserCss.parse();
    }
        
}
