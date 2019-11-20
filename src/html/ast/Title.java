package html.ast;

import html.visitor.Visitor;

public class Title implements AstHtml {
	
	public String title;
	
	public Title(String title) {
		this.title = title;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}	
	
}
