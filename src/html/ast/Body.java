package html.ast;

import java.util.List;

import html.visitor.Visitor;

public class Body implements AstHtml {

	public List<Parrafo> parrafos;
	
	public Body(List<Parrafo> parrafos) {
		this.parrafos = parrafos;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}
