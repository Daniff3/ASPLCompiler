package html.ast;

import html.visitor.Visitor;

public class H2 implements AstHtml, Parrafo {

public String text;
	
	public H2(String text) {
		this.text = text;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}
