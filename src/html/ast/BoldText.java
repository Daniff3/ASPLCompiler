package html.ast;

import html.visitor.Visitor;

public class BoldText implements AstHtml, Bloque {

	public String text;
	
	public BoldText(String text) {
		this.text = text;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}