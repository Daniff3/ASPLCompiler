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

    public static void main(String[] args) {
        AstHtml htmlAst = null;
        AstCss cssAst = null;
        AstCss defaultAst = null;

        try {
            htmlAst = creaArbolHtml("EX4.html");
            System.out.println("Creado el arbol AST del HTML");

            BuscaCssEnHtmlVisitor bCss = new BuscaCssEnHtmlVisitor();
            String css = (String) htmlAst.accept(bCss, null);
            System.out.println("Extraido el fichero CSS");

            cssAst = creaArbolCss(css);
            System.out.println("Creado el arbol AST del CSS");

            defaultAst = creaArbolCss("Default.css");
            System.out.println("Creado el arbol AST del CSS por defecto");

            RenderVisitor render = new RenderVisitor(htmlAst, new BuscaParamEnCssVisitor(), defaultAst, cssAst);
            FormattedPage fp = render.getFormattedPage();
            PrintFormattedPage pp = new PrintFormattedPage(fp);
            pp.printPage();
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
