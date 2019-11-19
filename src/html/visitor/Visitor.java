package html.visitor;

import css.ast.Definicion;
import css.ast.Program;
import css.ast.Regla;

public interface Visitor {
	Object visit(Program program, Object param);
	Object visit(Regla regla, Object param);
	Object visit(Definicion definicion, Object param);
}
