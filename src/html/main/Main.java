package html.main;

import java.io.FileReader;
import java.io.IOException;

import html.ast.AstHtml;
import html.parser.LexiconHtml;
import html.parser.ParserHtml;
import html.parser.Token;
import html.parser.TokensId;
import html.visitor.BuscaCssEnHtmlVisitor;
import html.visitor.PrintAstHtmlVisitor;

public class Main {

	private static final String HTML_FILE = "EX4.html";
	
	public static void main(String[] args) {
		try {	
			FileReader filereader = new FileReader (HTML_FILE);
			LexiconHtml lex = new LexiconHtml(filereader);
			listaTokens(lex);
			
			filereader = new FileReader (HTML_FILE);
			lex = new LexiconHtml(filereader);
			ParserHtml parser = new ParserHtml (lex);
			AstHtml ast = parser.parse();
			
			if( ast != null) {
				PrintAstHtmlVisitor printVisitor = new PrintAstHtmlVisitor();
				String htmlAst = (String) ast.accept(printVisitor, null);
				System.out.println(htmlAst);
				
				BuscaCssEnHtmlVisitor buscaHtml = new BuscaCssEnHtmlVisitor();
			    String busqueda = (String) ast.accept(buscaHtml, null);
			    System.out.println("\nEl fichero css del html es: "+busqueda);
			}
		} 
        catch (IOException e) {
            System.out.println("Ha habido algun problema intentando cargar los ficheros");
        }
	}

	//Auxiliares
	//Lista de Tokens
	static void listaTokens (LexiconHtml lex) {
		Token t = lex.getToken();
		while (t.getToken() != TokensId.EOF) {
			System.out.println(t.toString());
			t = lex.getToken();
		}
		System.out.println ("\nFin de fichero. \n"+t.toString()+"\n");	
	}
	
}
