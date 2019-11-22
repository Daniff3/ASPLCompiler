package render;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import css.ast.AstCss;
import css.visitor.BuscaParamEnCssVisitor;
import html.ast.AstHtml;
import html.visitor.BuscaCssEnHtmlVisitor;
import html.visitor.RenderVisitor;

public class Main {

    public static void main(String[] args) throws IOException {
        AstHtml htmlAst = null;
        AstCss cssAst = null;
        AstCss defaultAst = null;

        try {
            htmlAst = creaArbolHtml("EX4.html");
            System.out.println("Creado el árbol AST del HTML");

            BuscaCssEnHtmlVisitor bCss = new BuscaCssEnHtmlVisitor();
            String css = (String) htmlAst.accept(bCss, null);
            System.out.println("Extraído el fichero CSS");

            cssAst = creaArbolCss(css);
            System.out.println("Creado el árbol del CSS");

            defaultAst = creaArbolCss("Default.css");
            System.out.println("Creado el árbol del CSS por defecto");

            RenderVisitor render = new RenderVisitor(htmlAst, new BuscaParamEnCssVisitor(), defaultAst, cssAst);
            FormattedPage fp = render.getFormattedPage();
        } 
        catch (IOException e) {
            System.out.println("Ha habido algún problema intentando cargar los ficheros");
        }
    }

    private static AstHtml creaArbolHtml(String file) throws FileNotFoundException {
        FileReader fileHtml = new FileReader(file);
        html.parser.Lexicon lexHtml = new html.parser.Lexicon(fileHtml);
        html.parser.Parser parserHtml = new html.parser.Parser(lexHtml);
        return parserHtml.parse();
    }

    private static AstCss creaArbolCss(String file) throws FileNotFoundException {
        FileReader fileCss = new FileReader(file);
        css.parser.Lexicon lexCss = new css.parser.Lexicon(fileCss);
        css.parser.Parser parserCss = new css.parser.Parser(lexCss);
        return parserCss.parse();
    }

}
