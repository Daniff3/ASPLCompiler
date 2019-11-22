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

	public static void main(String[] args) throws IOException {
		FileReader filereader = new FileReader ("EX1.CSS");
		LexiconCss lex = new LexiconCss(filereader);
		//listaTokens(lex);
		ParserCss parser = new ParserCss (lex);
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
	static void listaTokens (LexiconCss lex) {
		Token t = lex.getToken();
		while (t.getToken() != TokensId.EOF) {
			System.out.println(t.toString());
			t = lex.getToken();
		}
		System.out.println ("\nFin de fichero. \n"+t.toString());	
	}
}
