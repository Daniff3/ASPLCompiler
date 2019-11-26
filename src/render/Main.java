package render;

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

public class Main {

	private static final String HTML_FILE = "EX4.html";
	private static final String CSS_DEFAULT_FILE = "Default.css";
	
    public static void main(String[] args) {
         try {
        	AstHtml htmlAst = creaArbolHtml(HTML_FILE);
            System.out.println("Creamos el AST del HTML");

            BuscaCssEnHtmlVisitor bCss = new BuscaCssEnHtmlVisitor();
            String css = (String) htmlAst.accept(bCss, null);
            System.out.println("Cogemos el fichero CSS asociado al HTML");

            AstCss cssAst = creaArbolCss(css);
            System.out.println("Creamos el AST del CSS asociado al HTML");

            AstCss defaultAst = creaArbolCss(CSS_DEFAULT_FILE);
            System.out.println("Creamos el AST del CSS por defecto");

            RenderVisitor render = new RenderVisitor(htmlAst, new BuscaParamEnCssVisitor(), defaultAst, cssAst);
            FormattedPage fp = render.getFormattedPage();
            PrintFormattedPage pp = new PrintFormattedPage();
            pp.printPage(fp);
        } 
        catch (IOException e) {
            System.out.println("Ha habido algun problema intentando cargar los ficheros");
        }
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
