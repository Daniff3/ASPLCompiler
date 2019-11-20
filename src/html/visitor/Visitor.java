package html.visitor;

import html.ast.*;

public interface Visitor {
	Object visit(Program program, Object param);
	Object visit(Head head, Object param);
	Object visit(Body body, Object param);
	Object visit(Title title, Object param);
	Object visit(Link link, Object param);
	Object visit(Parrafo parrafo, Object param);
	Object visit(Bloque bloque, Object param);
}
