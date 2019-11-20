package html.visitor;

import css.ast.AstCss;
import css.visitor.BuscaParamEnCssVisitor;
import html.ast.Bloque;
import html.ast.Body;
import html.ast.Head;
import html.ast.Link;
import html.ast.Parrafo;
import html.ast.Program;
import html.ast.Title;

public class RenderVisitor implements Visitor {

	Program p = null;
	BuscaParamEnCssVisitor bpCss = null;
	AstCss defaultCss = null;
	AstCss currentCss = null;
	
	@Override
	public Object visit(Program program, Object param) {
		// TODO Auto-generated method stub
		return null;
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
