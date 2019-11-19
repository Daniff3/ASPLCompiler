package html.ast;

import css.visitor.Visitor;

public class Head implements AstHtml {

	public String title;
	public Link link;
	
	public Head(String title, Link link) {
		this.title = title;
		this.link = link;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}