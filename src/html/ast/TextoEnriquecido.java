package html.ast;

import html.visitor.Visitor;

public class TextoEnriquecido implements AstHtml, Bloque {

	public String text;
	public String decoration;
	
	public TextoEnriquecido(String text, String decoration) {
		this.decoration = decoration;
		this.text = text;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}
