package css.parser;

import java.util.ArrayList;
import java.util.List;

import css.ast.Definicion;
import css.ast.Program;
import css.ast.Regla;

public class Parser {
	
	Lexicon lex;
	boolean errorSint = false;
	
	public Parser (Lexicon lex) {
		this.lex = lex;
	}

	public Program parse() {
		Program program = null;
		List<Regla> reglas = new ArrayList<Regla>();
		Token token = lex.getToken();
		
		while (token.getToken() == TokensId.IDENTIFICADOR) {
			lex.returnLastToken();
			Regla regla = parseRegla();
			if( (regla!= null) && (!errorSint) ) {
				reglas.add(regla);
			}
			token = lex.getToken();
		}
		if(token.getToken() != TokensId.EOF) {
			errorSintactico("Encontrado " + token.getLexeme() 
				+ ". Se esperaba el fin de fichero.", token.getLine());
		}
		if( !errorSint ) {
			program = new Program(reglas);
		}
		return program;
	}
	
	private Regla parseRegla() {
	    Regla regla = null; 
	    List<Definicion> definiciones = new ArrayList<Definicion>();
	    Token token = lex.getToken();
	    String identificador = token.getLexeme(); // No hace falta comprobarlo porque viene de Program
	    token = lex.getToken();
	    
	    if (token.getToken() != TokensId.OPEN_BRACKETS)
	      errorSintactico("Encontrado " + token.getLexeme() + ". Se esperaba '{'", token.getLine());
	    
	    token = lex.getToken();
	    
	    while ((token.getToken() == TokensId.COLOR) ||
	    	   (token.getToken() == TokensId.FONT_SIZE) ||
	    	   (token.getToken() == TokensId.FONT_STYLE) ||
	    	   (token.getToken() == TokensId.TEXT_ALIGN) ){
	      definiciones.add(parseDefinicion());
	      token = lex.getToken();
	    }
	    
	    if( token.getToken() != TokensId.CLOSE_BRACKETS ) {
	    	errorSintactico("Encontrado " + token.getLexeme() + ". Se esperaba '}'", token.getLine());
			return null;
	    }
	    
	    regla = new Regla(identificador, definiciones);
	    return regla;
	  }
		
	private Definicion parseDefinicion() {
		lex.returnLastToken();
		Definicion definicion = parseVarconf();
		Token token = lex.getToken();
		
		if( token.getToken() != TokensId.SEMICOLON ) {
			errorSintactico("Encontrado " + token.getLexeme() + ". Se esperaba ';'", token.getLine());
			return null;
		}
		
		return definicion;
	}
	
	private Definicion parseVarconf() {
		Definicion varconf = null; 
	    Token token = lex.getToken();
	    String label = token.getLexeme();
	    String value = null;
	    Token tAnt = token;
	    token = lex.getToken();
	    
	    if (token.getToken() != TokensId.COLON) 
	      errorSintactico("Encontrado " + token.getLexeme() + ". Se esperaba ':'", token.getLine());
	    
	    switch(tAnt.getToken()) {
		    case COLOR:
		    	value = parseColors();
		    	break;
			case FONT_STYLE:
				value = parseStyles();
				break;
			case TEXT_ALIGN:
				value = parseAlignments();
				break;
			case FONT_SIZE:
			    	token = lex.getToken();
				value = token.getLexeme();
				break;
			default:
				errorSintactico("Encontrado " + token.getLexeme() + ". Se esperaba una definicion de color, font-style, font-size o text-align.", token.getLine());
				return null;
	    }
	    
	    varconf = new Definicion(label, value);
	    return varconf;
	}
	
	private String parseColors() {
		Token token = lex.getToken();
		switch(token.getToken()) {
			case BLACK:
				return ("black");
			case RED:
				return ("red");
			case BLUE:
				return ("blue");
			case GREEN:
		        return ("green");
			default:
				errorSintactico("Encontrado " + token.getLexeme() + ". Se esperaba un color: black, red, blue o green.", token.getLine());
				return null;
		}
	}
	
	private String parseStyles() {
		Token token = lex.getToken();
		switch(token.getToken()) {
			case NORMAL:
				return ("normal");
			case ITALIC:
				return ("italic");
			case BOLD:
				return ("blood");
			case UNDERLINED:
		        return ("underlined");
			default:
				errorSintactico("Encontrado " + token.getLexeme() + ". Se esperaba un estilo de texto: normal, italic, underlined o bold.", token.getLine());
				return null;
		}
	}
	
	private String parseAlignments() {
		Token token = lex.getToken();
		switch(token.getToken()) {
			case LEFT:
				return ("left");
			case RIGHT:
				return ("right");
			case CENTER:
				return ("center");
			default:
				errorSintactico("Encontrado " + token.getLexeme() + ". Se esperaba una orientación de texto: left, right o center.", token.getLine());
				return null;
		}
	}
	
	//Gestión de Errores Sintáctico
	void errorSintactico (String e, int line) {
		errorSint = true;
		System.out.println("Error Sintáctico : "+e+" en la línea "+line);
	}
}
