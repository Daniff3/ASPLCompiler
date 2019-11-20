package html.ast;

import html.visitor.Visitor;

public class Program implements AstHtml {

	public Head head;
	public Body body;
	
	public Program(Head head, Body body) {
		this.head = head;
		this.body = body;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}
