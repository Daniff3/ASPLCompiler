package html.ast;

import html.visitor.Visitor;

public class UnderlinedText implements AstHtml, Bloque {

	public String text;
	
	public UnderlinedText(String text) {
		this.text = text;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}