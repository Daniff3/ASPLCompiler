package html.parser;

import java.util.ArrayList;
import java.util.List;

import html.ast.*;

public class Parser {
	
	Lexicon lex;
	boolean errorSint = false;
	
	public Parser (Lexicon lex) {
		this.lex = lex;
	}

	public Program parse() {
		Program program = null;
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.HTML )
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <html>.", token.getLine());
			
		Head head = parseHead();
		Body body = parseBody();
					
		token = lex.getToken();
		
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
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.HEAD )
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <head>.", token.getLine());
		
		Title title = parseTitle();
		Link link = parseLink();	
		token = lex.getToken();
		
		if(token.getToken() != TokensId.HEADC)
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </head>.", token.getLine());
		
		if( !errorSint )
			head = new Head(title, link);
		
		return head;
	}
	
	public Title parseTitle() {
		Title title = null;
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.TITLE )
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <title>.", token.getLine());

		token = lex.getToken();

		if( token.getToken() != TokensId.TEXT)
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba texto.", token.getLine());

		String titleStr = token.getLexeme();
		token = lex.getToken();	
		
		if(token.getToken() != TokensId.TITLEC) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </title>.", token.getLine());
		
		if( !errorSint ) 
			title = new Title(titleStr);
			
		return title;
	}
	
	public Link parseLink() {
		Link link = null;
		String href = null;
		String rel = null;
		String type = null;
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.LINK ) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <link.", token.getLine());
		
		token = lex.getToken();
		if ( token.getToken() == TokensId.HREF ) {
			token = lex.getToken();
			if(token.getToken() == TokensId.EQUAL) {
				token = lex.getToken();
				if(token.getToken() == TokensId.URL) 
					href = token.getLexeme().replace("\"", "");
				else
					errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba URL.", token.getLine());
			}
			else 
				errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba =.", token.getLine());
		}
		else 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba href.", token.getLine());
		
		token = lex.getToken();
		if ( token.getToken() == TokensId.REL ) {
			token = lex.getToken();
			if(token.getToken() == TokensId.EQUAL) {
				token = lex.getToken();
				if(token.getToken() == TokensId.URL) 
					rel = token.getLexeme().replace("\"", "");
				else
					errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba URL.", token.getLine());
			}
			else 
				errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba =.", token.getLine());
		}
		else 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba rel.", token.getLine());
		
		token = lex.getToken();
		if ( token.getToken() == TokensId.TYPE ) {
			token = lex.getToken();
			if(token.getToken() == TokensId.EQUAL) {
				token = lex.getToken();
				if(token.getToken() == TokensId.URL) 
					type = token.getLexeme().replace("\"", "");
				else
					errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba URL.", token.getLine());
			}
			else 
				errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba =.", token.getLine());
		}
		else 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba type.", token.getLine());
		
		token = lex.getToken();

		if(token.getToken() != TokensId.CLOSE) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba '>'.", token.getLine());
		
		if(href == null || rel == null || type == null)
			errorSintactico("La etiqueta <link> debe tener href, rel y type", token.getLine());

		if( !errorSint ) 
			link = new Link(href, rel, type);		
		
		return link;
	}
	
	public Body parseBody() {
		Body body = null;
		List<Parrafo> parrafos = new ArrayList<Parrafo>();
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.BODY )
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <body>.", token.getLine());

		token = lex.getToken();

		while ( token.getToken() == TokensId.H1 || 
				token.getToken() == TokensId.H2 || 
				token.getToken() == TokensId.P) 
		{
			switch(token.getToken()){
				case H1:
					lex.returnLastToken();
					parrafos.add(parseH1());
					break;
				case H2:
					lex.returnLastToken();
					parrafos.add(parseH2());
					break;
				case P:
					lex.returnLastToken();
					parrafos.add(parseP());
					break;
				default:
					errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba h1, h2 o p", token.getLine());
			}		
			token = lex.getToken();
		}
		
		if(token.getToken() != TokensId.BODYC) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </body>.", token.getLine());
		
		if( !errorSint ) 
			body = new Body(parrafos);
		
		return body;
	}
		
	public H1 parseH1(){
		H1 h1 = null;
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.H1 )
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <h1>.", token.getLine());

		token = lex.getToken();

		if( token.getToken() != TokensId.TEXT)
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba texto.", token.getLine());

		String text = token.getLexeme();
		token = lex.getToken();	
		
		if(token.getToken() != TokensId.H1C) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </h1>.", token.getLine());
		
		if( !errorSint ) 
			h1 = new H1(text);
			
		return h1;
	}

	public H2 parseH2(){
		H2 h2 = null;
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.H2 )
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <h2>.", token.getLine());

		token = lex.getToken();

		if( token.getToken() != TokensId.TEXT)
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba texto.", token.getLine());

		String text = token.getLexeme();
		token = lex.getToken();	
		
		if(token.getToken() != TokensId.H2C) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </h2>.", token.getLine());
		
		if( !errorSint ) 
			h2 = new H2(text);
			
		return h2;
	}

	public P parseP(){
		P p = null;
		List<Bloque> bloques = new ArrayList<Bloque>();
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.P )
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <p>.", token.getLine());

		token = lex.getToken();

		while ( token.getToken() == TokensId.BOLD || 
				token.getToken() == TokensId.ITALIC || 
				token.getToken() == TokensId.UNDERL ||
				token.getToken() == TokensId.TEXT) 
		{
			switch(token.getToken()){
				case BOLD:
					lex.returnLastToken();
					bloques.add(parseBoldText());
					break;
				case ITALIC:
					lex.returnLastToken();
					bloques.add(parseItalicText());
					break;
				case UNDERL:
					lex.returnLastToken();
					bloques.add(parseUnderlinedText());
					break;
				case TEXT:
					lex.returnLastToken();
					bloques.add(parseTexto());
					break;
				default:
					errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <b>, <i>, <u> o texto.", token.getLine());
			}		
			token = lex.getToken();
		}
		
		if(token.getToken() != TokensId.PC) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </p>.", token.getLine());
		
		if( !errorSint ) 
			p = new P(bloques);
			
		return p;
	}

	public Texto parseTexto(){
		Texto texto = null;
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.TEXT )
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba texto.", token.getLine());

		String text = token.getLexeme();
		
		if( !errorSint ) 
			texto = new Texto(text);
			
		return texto;
	}

	public BoldText parseBoldText(){
		BoldText bt = null;
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.BOLD )
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <b>.", token.getLine());

		token = lex.getToken();

		if( token.getToken() != TokensId.TEXT)
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba texto.", token.getLine());

		String text = token.getLexeme();
		token = lex.getToken();	
		
		if(token.getToken() != TokensId.BOLDC) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </b>.", token.getLine());
		
		if( !errorSint ) 
			bt = new BoldText(text);
			
		return bt;
	}

	public ItalicText parseItalicText(){
		ItalicText it = null;
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.ITALIC )
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <i>.", token.getLine());

		token = lex.getToken();

		if( token.getToken() != TokensId.TEXT)
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba texto.", token.getLine());

		String text = token.getLexeme();
		token = lex.getToken();	
		
		if(token.getToken() != TokensId.ITALICC) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </i>.", token.getLine());
		
		if( !errorSint ) 
			it = new ItalicText(text);
			
		return it;
	}

	public UnderlinedText parseUnderlinedText(){
		UnderlinedText ut = null;
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.UNDERL )
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba <u>.", token.getLine());

		token = lex.getToken();

		if( token.getToken() != TokensId.TEXT)
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba texto.", token.getLine());

		String text = token.getLexeme();
		token = lex.getToken();	
		
		if(token.getToken() != TokensId.UNDERLC) 
			errorSintactico("Encontrado "+token.getLexeme()+". Se esperaba </u>.", token.getLine());
		
		if( !errorSint ) 
			ut = new UnderlinedText(text);
			
		return ut;
	}

	//Gestion de Errores
	public void errorSintactico (String e, int line) {
		errorSint = true;
		System.out.println("Error Sintactico : "+e+" en la linea "+line);
	}
		
}
