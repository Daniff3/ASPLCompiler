package html.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LexiconHtml {

	// Gestión de tokens
	List<Token> tokens = new ArrayList<Token>();
	int i = 0; //Último token entregado en getToken()
	
	//Gestión de lectura del fichero
	FileReader filereader;
	boolean charBuffUsed = false;
	char charBuff;
	int line = 1; // indica la línea del fichero fuente
	
	HashSet<Character> charText = new HashSet<Character>();
	
	public LexiconHtml (FileReader f) {
		filereader = f;
		String lex;
		loadSet();
		
		try{
			char valor=(char) 0;
			while(valor!=(char) -1){
				valor=nextChar();
			
				switch( (char) valor ) {
// * Etiquetas
				case '<':
					valor = nextChar();
// ** Etiquetas de cierre
					if ( (char) valor == '/' ) {
						valor = nextChar();
						switch( (char) valor ) {
						case 'h':
							lex = getLexeme("</h", '>');
							switch(lex) {
							case "</html>":
								tokens.add(new Token(TokensId.HTMLC, lex, line));
								break;	
							case "</head>":
								tokens.add(new Token(TokensId.HEADC, lex, line));
								break;
							case "</h1>":
								tokens.add(new Token(TokensId.H1C, lex, line));
								break;
							case "</h2>":
								tokens.add(new Token(TokensId.H2C, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
						
						case 't':
							lex = getLexeme("</t", '>');
							switch(lex) {
							case "</title>":
								tokens.add(new Token(TokensId.TITLEC, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
						
						case 'b':
							lex = getLexeme("</b", '>');
							switch(lex) {
							case "</b>":
								tokens.add(new Token(TokensId.BOLDC, lex, line));
								break;
							case "</body>":
								tokens.add(new Token(TokensId.BODYC, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
							
						case 'p':
							lex = getLexeme("</p", '>');
							switch(lex) {
							case "</p>":
								tokens.add(new Token(TokensId.PC, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
							
						case 'i':
							lex = getLexeme("</i", '>');
							switch(lex) {
							case "</i>":
								tokens.add(new Token(TokensId.ITALICC, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
							
						case 'u':
							lex = getLexeme("</u", '>');
							switch(lex) {
							case "</u>":
								tokens.add(new Token(TokensId.UNDERLC, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
						}
					}
// ** Etiquetas de apertura
					else {
						switch( (char) valor ) {
						case 'h':
							lex = getLexeme("<h", '>');
							switch(lex) {
							case "<html>":
								tokens.add(new Token(TokensId.HTML, lex, line));
								break;	
							case "<head>":
								tokens.add(new Token(TokensId.HEAD, lex, line));
								break;
							case "<h1>":
								tokens.add(new Token(TokensId.H1, lex, line));
								break;
							case "<h2>":
								tokens.add(new Token(TokensId.H2, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
						
						case 't':
							lex = getLexeme("<t", '>');
							switch(lex) {
							case "<title>":
								tokens.add(new Token(TokensId.TITLE, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
							
						case 'l':
							lex = getLexeme("<l", 'k');
							switch(lex) {
							case "<link":
								tokens.add(new Token(TokensId.LINK, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
						
						case 'b':
							lex = getLexeme("<b", '>');
							switch(lex) {
							case "<b>":
								tokens.add(new Token(TokensId.BOLD, lex, line));
								break;
							case "<body>":
								tokens.add(new Token(TokensId.BODY, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
							
						case 'p':
							lex = getLexeme("<p", '>');
							switch(lex) {
							case "<p>":
								tokens.add(new Token(TokensId.P, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
							
						case 'i':
							lex = getLexeme("<i", '>');
							switch(lex) {
							case "<i>":
								tokens.add(new Token(TokensId.ITALIC, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
							
						case 'u':
							lex = getLexeme("<u", '>');
							switch(lex) {
							case "<u>":
								tokens.add(new Token(TokensId.UNDERL, lex, line));
								break;
							default:
								errorLexico("Error en línea " + line);
								break;
							}
							break;
						}
					}
					break;
// * otros				
				case '"':
					lex = getLexeme("\"", '"');
					tokens.add(new Token(TokensId.URL, lex, line));
					break;
				case '=':
					tokens.add(new Token(TokensId.EQUAL, ""+valor, line));
					break;
				case '>':
					tokens.add(new Token(TokensId.CLOSE, ""+valor, line));
					break;
// * blanks					
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
					lex = getLexemeTEXT("" + (char) valor);
					switch(lex) {
// ** href
					case "href":
						tokens.add(new Token(TokensId.HREF, lex, line));
						break;
// ** rel
					case "rel":
						tokens.add(new Token(TokensId.REL, lex, line));
						break;
// ** type
					case "type":
						tokens.add(new Token(TokensId.TYPE, lex, line));
						break;
// ** text	
					default:
						tokens.add(new Token(TokensId.TEXT, lex, line));
		          }
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
	
	// Saca del fichero un lexema de texto que termina con cualquier caracter que no esté contenido en charText.
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
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.,;:+-*/()[]!? ";
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
