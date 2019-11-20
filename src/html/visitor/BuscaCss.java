package html.visitor;

import html.ast.Bloque;
import html.ast.Body;
import html.ast.Head;
import html.ast.Link;
import html.ast.Parrafo;
import html.ast.Program;
import html.ast.Title;

// Devuelve la url del css que lleva enlazado el html
public class BuscaCss implements Visitor {

	@Override
	public Object visit(Program program, Object param) {
		return (String) program.head.accept(this, param);
	}

	@Override
	public Object visit(Head head, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Body body, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Title title, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Link link, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Parrafo parrafo, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Bloque bloque, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}
