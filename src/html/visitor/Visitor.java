package html.visitor;

import html.ast.*;

public interface Visitor {
	Object visit(Program program, Object param);
	Object visit(Head head, Object param);
	Object visit(Body body, Object param);
	Object visit(Title title, Object param);
	Object visit(Link link, Object param);
	Object visit(H1 h1, Object param);
	Object visit(H2 h2, Object param);
	Object visit(P p, Object param);
	Object visit(Texto texto, Object param);
	Object visit(BoldText bt, Object param);
	Object visit(ItalicText it, Object param);
	Object visit(UnderlinedText ut, Object param);
}
