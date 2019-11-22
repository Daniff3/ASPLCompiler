package css.parser;

import java.io.FileReader;
import java.util.*;
import java.io.*;

public class LexiconCss {

	// Gestión de tokens
	List<Token> tokens = new ArrayList<Token>();
	int i = 0; //Último token entregado en getToken()
	
	//Gestión de lectura del fichero
	FileReader filereader;
	boolean charBuffUsed = false;
	char charBuff;
	int line = 1; // indica la línea del fichero fuente
	
	HashSet<Character> charText = new HashSet<Character>();
	
	public LexiconCss (FileReader f) {
		filereader = f;

		try{
			char valor=(char) 0;
			while(valor!=(char) -1){
				valor=nextChar();
			
				switch( valor ) {
					case '{':
						tokens.add(new Token(TokensId.OPEN_BRACKETS, "{", line));
						break;
					case '}':
						tokens.add(new Token(TokensId.CLOSE_BRACKETS, "}", line));
						break;
					case ':':
						tokens.add(new Token(TokensId.COLON, ":", line));
						break;
					case ';':
						tokens.add(new Token(TokensId.SEMICOLON, ";", line));
						break;
					case '\n':
						line++;
						break;
					case '\r':
						break;
					case '\t':
						break;
					case (char) -1:
						break;
					case ' ':
						break;
					default:
						if( Character.isDigit(valor) ) {
							String valor_entero = getSize(""+valor);
							tokens.add(new Token(TokensId.SIZE, valor_entero, line));
						}
						else if( Character.isAlphabetic(valor) ) { 
							String valor_entero = getText(""+valor);
							
							switch( valor_entero ) {
								case "color":
									tokens.add(new Token(TokensId.COLOR, valor_entero, line));
									break;
								case "black":
									tokens.add(new Token(TokensId.BLACK, valor_entero, line));
									break;
								case "blue":
									tokens.add(new Token(TokensId.BLUE, valor_entero, line));
									break;
								case "green":
									tokens.add(new Token(TokensId.GREEN, valor_entero, line));
									break;
								case "red":
									tokens.add(new Token(TokensId.RED, valor_entero, line));
									break;
								case "font-size":
									tokens.add(new Token(TokensId.FONT_SIZE, valor_entero, line));
									break;	
								case "font-style":
									tokens.add(new Token(TokensId.FONT_STYLE, valor_entero, line));
									break;
								case "italic":
									tokens.add(new Token(TokensId.ITALIC, valor_entero, line));
									break;
								case "bold":
									tokens.add(new Token(TokensId.BOLD, valor_entero, line));
									break;
								case "normal":
									tokens.add(new Token(TokensId.NORMAL, valor_entero, line));
									break;
								case "underlined":
									tokens.add(new Token(TokensId.UNDERLINED, valor_entero, line));
									break;
								case "text-align":
									tokens.add(new Token(TokensId.TEXT_ALIGN, valor_entero, line));
									break;
								case "center":
									tokens.add(new Token(TokensId.CENTER, valor_entero, line));
									break;
								case "right":
									tokens.add(new Token(TokensId.RIGHT, valor_entero, line));
									break;
								case "left":
									tokens.add(new Token(TokensId.LEFT, valor_entero, line));
									break;
								default:
									tokens.add(new Token(TokensId.IDENTIFICADOR, valor_entero, line));
							}	
						}
				}
			}
			filereader.close();
        }catch(IOException e){
            System.out.println("Error E/S: "+e);
        }
		
	}
	
	// ++
	// ++ Operaciones para el Sintactico
	// ++
	
	// Retroceder al anterior token
	public void returnLastToken () {
		i--;
	}
	
	// Get Token actual y avanza al siguiente token
	public Token getToken () {
		if (i < tokens.size()) {
			return tokens.get(i++);
		}
		return new Token (TokensId.EOF,"EOF", line);
	}	

	//Privadas
	private String getSize (String lexStart) throws IOException {
		String lexReturned = lexStart;
		char valor;
		do {
			valor=nextChar();
			lexReturned = lexReturned+(valor);
		} 
		while ((valor != 'p') && (valor != -1));
		//returnChar(valor);
		if (valor == 'p') {
			//lexReturned = lexReturned+(valor);
			valor=nextChar();
			if (valor == 'x') {
				lexReturned = lexReturned+(valor);
			} 
			else {
				errorLexico ("Encontrado "+lexReturned+". Se esperada un token SIZE.");
				return null;
			}
		}
		return lexReturned;
	}

	private String getText (String lexStart) throws IOException {
		String lexReturned = lexStart;
		char valor = nextChar();
		while (Character.isDigit(valor) || Character.isAlphabetic(valor) || (valor == '-')) {
			lexReturned = lexReturned+(valor);
			valor=nextChar();
		}
		returnChar(valor);
		return lexReturned;
	}
	
	private char nextChar() throws IOException{
		if (charBuffUsed) {
			charBuffUsed = false;
			return charBuff;
		} 
		else {
			int valor=filereader.read();
			return ((char) valor);
		}
	}
	
	private void returnChar (char r) {
		charBuffUsed = true;
		charBuff = r;
	}

	private void errorLexico (String e) {
		System.out.println("Error léxico en : "+e);
	}
}
