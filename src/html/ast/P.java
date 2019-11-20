package html.ast;

import java.util.List;

import html.visitor.Visitor;

public class P implements AstHtml, Parrafo {

	public List<Bloque> bloques;
	
	public P(List<Bloque> bloques) {
		this.bloques = bloques;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}
