package html.main;

import java.io.FileReader;
import java.io.IOException;

import html.ast.AstHtml;
import html.parser.Lexicon;
import html.parser.Parser;
import html.parser.Token;
import html.parser.TokensId;
import html.visitor.BuscaCssEnHtmlVisitor;
import html.visitor.PrintAstHtmlVisitor;

public class Main {

	public static void main(String[] args) throws IOException {
		FileReader filereader = new FileReader ("EX4.html");
		Lexicon lex = new Lexicon(filereader);
		//listaTokens(lex);
		
		Parser parser = new Parser (lex);
		AstHtml ast = parser.parse();
		
		if( ast != null) {
			PrintAstHtmlVisitor printVisitor = new PrintAstHtmlVisitor();
			String htmlAst = (String) ast.accept(printVisitor, null);
			System.out.println(htmlAst);
			
			BuscaCssEnHtmlVisitor buscaHtml = new BuscaCssEnHtmlVisitor();
		    String busqueda = (String) ast.accept(buscaHtml, null);
		    System.out.println(busqueda);
		}
				
		//RenderVisitor render = new Render(htmlAst, new BuscaParamEnCssVisitor(), defaultCss, cssAst);
		//FormatedPage fp = render.renderPage();
	}

	//Auxiliares
	//Lista de Tokens
	static void listaTokens (Lexicon lex) {
		Token t = lex.getToken();
		while (t.getToken() != TokensId.EOF) {
			System.out.println(t.toString());
			t = lex.getToken();
		}
		System.out.println ("\nFin de fichero. \n"+t.toString());	
	}
	
}
