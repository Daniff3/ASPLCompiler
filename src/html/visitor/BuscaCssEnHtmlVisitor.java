package html.visitor;

import html.ast.*;

// Devuelve la url del css que lleva enlazado el html
public class BuscaCssEnHtmlVisitor implements Visitor {

	@Override
	public Object visit(Program program, Object param) {
		return (String) program.head.accept(this, null);
	}

	@Override
	public Object visit(Head head, Object param) {
		return (String) head.link.accept(this, param);
	}

	@Override
	public Object visit(Body body, Object param) {
		return null;
	}

	@Override
	public Object visit(Title title, Object param) {
		return null;
	}

	@Override
	public Object visit(Link link, Object param) {
		return (String) link.href;
	}

	@Override
	public Object visit(H1 h1, Object param) {
		return null;
	}

	@Override
	public Object visit(H2 h2, Object param) {
		return null;
	}

	@Override
	public Object visit(P p, Object param) {
		return null;
	}

	@Override
	public Object visit(Texto texto, Object param) {
		return null;
	}

	@Override
	public Object visit(BoldText bt, Object param) {
		return null;
	}

	@Override
	public Object visit(ItalicText it, Object param) {
		return null;
	}

	@Override
	public Object visit(UnderlinedText ut, Object param) {
		return null;
	}

}
