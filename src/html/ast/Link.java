package html.ast;

import css.visitor.Visitor;

public class Link implements AstHtml {

	public String href;
	public String rel;
	public String type;
	
	public Link(String href, String rel, String type) {
		this.href = href;
		this.rel = rel;
		this.type = type;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
		
}
