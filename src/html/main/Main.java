package html.main;

import java.io.FileReader;
import java.io.IOException;

import html.parser.Lexicon;
import html.parser.Token;
import html.parser.TokensId;

public class Main {

	public static void main(String[] args) throws IOException {
		FileReader filereader = new FileReader ("EX4.html");
		Lexicon lex = new Lexicon(filereader);
		listaTokens(lex);
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
