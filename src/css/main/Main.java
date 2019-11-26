package css.main;

import java.io.FileReader;
import java.io.IOException;

import css.ast.AstCss;
import css.parser.LexiconCss;
import css.parser.ParserCss;
import css.parser.Token;
import css.parser.TokensId;
import css.visitor.BuscaParamEnCssVisitor;
import css.visitor.PrintCssAstVisitor;

public class Main {
	
	private static final String CSS_FILE = "EX1.css";

	public static void main(String[] args) {
		try {
			FileReader filereader = new FileReader (CSS_FILE);
			LexiconCss lex = new LexiconCss(filereader);
			listaTokens(lex);
			
			filereader = new FileReader (CSS_FILE);
			lex = new LexiconCss(filereader);
			ParserCss parser = new ParserCss (lex);
			AstCss ast = parser.parse();
			
			if( ast != null) {
				PrintCssAstVisitor pCss = new PrintCssAstVisitor();
				String cssAst = (String) ast.accept(pCss, null);
				System.out.println(cssAst);
				
				BuscaParamEnCssVisitor bCss = new BuscaParamEnCssVisitor();
			    String busqueda = (String) bCss.search("h1", "color", ast);
			    System.out.println("La propiedad color del elemento h1 es: " + busqueda);
			    
			    busqueda = (String) bCss.search("h2", "font-size", ast);
			    System.out.println("La propiedad font-size del elemento h2 es: " + busqueda);
			    
			    busqueda = (String) bCss.search("p", "text-align", ast);
			    System.out.println("La propiedad text-align del elemento p es: " + busqueda);
			    
			    busqueda = (String) bCss.search("h1", "font-style", ast);
			    System.out.println("La propiedad font-style del elemento h1 es: " + busqueda);
			}
		} 
        catch (IOException e) {
            System.out.println("Ha habido algun problema intentando cargar los ficheros");
        }
	}

	//Auxiliares
	//Lista de Tokens
	static void listaTokens (LexiconCss lex) {
		Token t = lex.getToken();
		while (t.getToken() != TokensId.EOF) {
			System.out.println(t.toString());
			t = lex.getToken();
		}
		System.out.println ("\nFin de fichero. \n"+t.toString()+"\n");	
	}
}
