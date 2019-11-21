package html.visitor;

import css.ast.AstCss;
import css.visitor.BuscaParamEnCssVisitor;
import html.ast.Body;
import html.ast.BoldText;
import html.ast.H1;
import html.ast.H2;
import html.ast.Head;
import html.ast.ItalicText;
import html.ast.Link;
import html.ast.P;
import html.ast.Program;
import html.ast.Texto;
import html.ast.Title;
import html.ast.UnderlinedText;
import render.FormattedPage;

public class RenderVisitor implements Visitor {

	Program p = null;
	BuscaParamEnCssVisitor bpCss = null;
	AstCss defaultCss = null;
	AstCss currentCss = null;
	FormattedPage fp = null;
	
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
	public Object visit(H1 h1, Object param) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object visit(H2 h2, Object param) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object visit(P p, Object param) {
		return null;
	}
	@Override
	public Object visit(Texto texto, Object param) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object visit(BoldText bt, Object param) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object visit(ItalicText it, Object param) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object visit(UnderlinedText ut, Object param) {
		// TODO Auto-generated method stub
		return null;
	}	

	public FormattedPage getFormattedPage() {
		fp = (FormattedPage) p.accept(this, null);
		return fp;
	}
	
}
