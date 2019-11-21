package html.ast;

import html.visitor.Visitor;

public class ItalicText implements AstHtml, Bloque {

	public String text;
	
	public ItalicText(String text) {
		this.text = text;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}