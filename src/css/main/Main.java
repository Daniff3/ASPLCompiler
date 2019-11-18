package css.main;

import java.io.FileReader;
import java.io.IOException;

import css.ast.AstCss;
import css.parser.Lexicon;
import css.parser.Parser;
import css.parser.Token;
import css.parser.TokensId;
import css.visitor.BuscaParamEnCssVisitor;
import css.visitor.PrintCssAstVisitor;

public class Main {

	public static void main(String[] args) throws IOException {
		FileReader filereader = new FileReader ("EX1.CSS");
		Lexicon lex = new Lexicon(filereader);
		//listaTokens(lex);
		Parser parser = new Parser (lex);
		AstCss ast = parser.parse();
		
		if( ast != null) {
			PrintCssAstVisitor pCss = new PrintCssAstVisitor();
			String cssAst = (String) ast.accept(pCss, null);
			System.out.println(cssAst);
		}
		
		BuscaParamEnCssVisitor bCss = new BuscaParamEnCssVisitor();
	    String busqueda = (String) bCss.search("h1", "color", ast);
	    System.out.println(busqueda);
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
