package html.parser;

import html.ast.*;

public class Parser {
	
	Lexicon lex;
	boolean errorSint = false;
	
	public Parser (Lexicon lex) {
		this.lex = lex;
	}

	public Program parse() {
		Program program = null;
		Head head = null;
		Body body = null;
		Token token = lex.getToken();
		
		if( token.getToken() == TokensId.HTML ) {
			head = parseHead();
			body = parseBody();
		}
		else 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <html>.", token.getLine());
			
		if(token.getToken() != TokensId.HTMLC) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </html>.", token.getLine());
		
		token = lex.getToken();
		
		if(token.getToken() != TokensId.EOF) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba el fin de fichero.", token.getLine());
		
		if( !errorSint ) 
			program = new Program(head, body);
		
		return program;
	}
	
	public Head parseHead() {
		Head head = null;
		Title title = null;
		Link link = null;
		Token token = lex.getToken();
		
		if( token.getToken() == TokensId.HEAD ) {
			title = parseTitle();
			link = parseLink();
		}
		else 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <head>.", token.getLine());
		
		if(token.getToken() != TokensId.HEADC)
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </head>.", token.getLine());
		
		if( !errorSint )
			head = new Head(title, link);
		
		return head;
	}
	
	public Title parseTitle() {
		Title title = null;
		String titleStr = null;
		Token token = lex.getToken();
		
		if( token.getToken() == TokensId.TITLE ) {
			token = lex.getToken();
			titleStr = token.getLexeme();
			token = lex.getToken();
		}
		else 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <title>.", token.getLine());
		
		if(token.getToken() != TokensId.TITLEC) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </title>.", token.getLine());
		
		if( !errorSint ) 
			title = new Title(titleStr);
			
		return title;
	}
	
	public Link parseLink() {
		Link link = null;
		
		
		
		
		
		return link;
	}
	
	public Body parseBody() {
		Body body = null;
		
		
		
		
		
		return body;
	}
		
	//Gestion de Errores
	public void errorSintactico (String e, int line) {
		errorSint = true;
		System.out.println("Error Sintactico : "+e+" en la linea "+line);
	}
		
}
