package html.visitor;

import html.ast.*;

public class PrintAstHtmlVisitor implements Visitor {

	@Override
	public Object visit(Program program, Object param) {
		String resultado = "<html>\n";
		resultado += (String) program.head.accept(this, param);
		resultado += (String) program.body.accept(this, param);
		resultado += "</html>";
		return "HTML \n-------------------\n" + resultado;
	}

	@Override
	public Object visit(Head head, Object param) {
		String resultado = "\t<head>\n";
		resultado += (String) head.title.accept(this, param);
		resultado += (String) head.link.accept(this, param);
		resultado += "\t</head>\n";
		return resultado;
	}

	@Override
	public Object visit(Body body, Object param) {
		String resultado = "\t<body>\n";
		for (Parrafo parrafo: body.parrafos)
			resultado += (String) parrafo.accept(this, param);
		resultado += "\t</body>\n";
		return resultado;
	}

	@Override
	public Object visit(Title title, Object param) {
		return (String) "\t\t<title>" + title.title + "</title>\n";
	}

	@Override
	public Object visit(Link link, Object param) {
		String resultado = "\t\t<link href=\"" + link.href + "\" ";
		resultado += " rel=\"" + link.rel + "\" ";
		resultado += " type=\"" + link.type + "\">\n";
		return resultado;
	}
	
	@Override
	public Object visit(H1 h1, Object param) {
		return (String) "\t\t<h1>" + h1.text + "</h1>\n";
	}

	@Override
	public Object visit(H2 h2, Object param) {
		return (String) "\t\t<h2>" + h2.text + "</h2>\n";
	}

	@Override
	public Object visit(P p, Object param) {
		String resultado = "\t\t<p>";
		for (Bloque bloque: p.bloques)
			resultado += (String) bloque.accept(this, param);
		resultado += "</p>\n";
		return resultado;
	}

	@Override
	public Object visit(Texto texto, Object param) {
		return (String) texto.text;
	}

	@Override
	public Object visit(BoldText bt, Object param) {
		return (String) "<b>" + bt.text + "</b>";
	}

	@Override
	public Object visit(ItalicText it, Object param) {
		return (String) "<i>" + it.text + "</i>";
	}

	@Override
	public Object visit(UnderlinedText ut, Object param) {
		return (String) "<u>" + ut.text + "</u>";
	}

}
