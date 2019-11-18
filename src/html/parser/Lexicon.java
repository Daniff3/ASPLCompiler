package html.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import css.parser.Token;
import css.parser.TokensId;

public class Lexicon {

	// Gestión de tokens
	List<Token> tokens = new ArrayList<Token>();
	int i = 0; //Último token entregado en getToken()
	
	//Gestión de lectura del fichero
	FileReader filereader;
	boolean charBuffUsed = false;
	char charBuff;
	int line = 1; // indica la línea del fichero fuente
	
	HashSet<Character> charText = new HashSet<Character>();
	
	public Lexicon (FileReader f) {
		filereader = f;
		String lex;
		loadSet();
		
		try{
			char valor=(char) 0;
			while(valor!=(char) -1){
				valor=nextChar();
			
				switch( (char) valor ) {
				case '<':
					valor = nextChar();
					if ( (char) valor == '/' ) {
						valor = nextChar();
						switch( (char) valor ) {
						case 'h':
							
						}
					}
					else {
						
					}
					break;
					
				case 'h':
					
					break;
					
				case 'r':
					
					break;
					
				case 't':
					
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
					//TODO get text bla bla
				}
			}
			filereader.close();
        }
		catch(IOException e){
            System.out.println("Error E/S: "+e);
        }
	}
	
	// ++
	// ++ Operaciones para el Léxio
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
	// Saca del fichero fuente un lexema que comienza por la cadena lexStart y termina en el caracter finChar
	private String getLexeme (String lexStart, char finChar) throws IOException {
		String lexReturned = lexStart;
		char valor;
		do {
			valor=nextChar();
			lexReturned = lexReturned+((char) valor);
		} while (((char) valor != finChar) && ((char) valor != -1));
		//returnChar(valor); Consume hasta el último caracter, no hay cnada que devolver
		return lexReturned;
	}
	
	// Saca del fichero un lexema de texto que termina con cualquier caracter que esté contenido en charText.
	// Necesita devolver el último caracter porque la condición del es con un caracter que no
	// pertenece al lexema
	private String getLexemeTEXT (String lexStart) throws IOException {
		String lexReturned = lexStart;
		char valor = nextChar();
		while (charText.contains(((char) valor)) && ((char) valor != -1)) {
			lexReturned = lexReturned+((char) valor);
			valor=nextChar();
		}
		returnChar(valor);
		return lexReturned;
	}
	
	// Crea un conjunto con los caracteres permitidos para los lexemas de texto (TEXT)
	private void loadSet () {
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.,;:+-*/()[]!?";
		int i=0;
		Character a = new Character('a');
		while (i < s.length()) {
			a = s.charAt(i);
			charText.add(a);
			i++;
		}
		//System.out.println(charText);
	}
	
	// Devuelve el siguiente caracter, si se ha devuelto previamente uno lo devuelve del buffer, si no lo devuelve del fichero
	private char nextChar() throws IOException{
		if (charBuffUsed) {
			charBuffUsed = false;
			return charBuff;
		} else {
		int valor=filereader.read();
		return ((char) valor);
		}
	}
	
	// Devuelde un caracter al buffer
	private void returnChar (char r) {
		charBuffUsed = true;
		charBuff = r;
	}

	private void errorLexico (String e) {
		System.out.println("Error léxico en : "+e);
	}
	
}
