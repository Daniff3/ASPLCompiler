package main;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	
	public static void main(String[] args) throws IOException {
        fp = loadPage();
        createAndShowGUI();
    }
	
	private static void createAndShowGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(fp);
					frame.setTitle(fp.getPage_title());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static FormattedPage loadPage() {
		try {
            AstHtml htmlAst = creaArbolHtml("EX4.html");
            BuscaCssEnHtmlVisitor bCss = new BuscaCssEnHtmlVisitor();
            String css = (String) htmlAst.accept(bCss, null);
            AstCss cssAst = creaArbolCss(css);
            AstCss defaultAst = creaArbolCss("Default.css");
            RenderVisitor render = new RenderVisitor(htmlAst, new BuscaParamEnCssVisitor(), defaultAst, cssAst);
            return render.getFormattedPage();
        } 
        catch (IOException e) {
            System.out.println("Ha habido algun problema intentando cargar los ficheros");
        }
		return null;
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
