package html.ast;

import html.visitor.Visitor;

public class Head implements AstHtml {

	public Title title;
	public Link link;
	
	public Head(Title title, Link link) {
		this.title = title;
		this.link = link;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}