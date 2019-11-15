package visitor;

import ast.Definicion;
import ast.Program;
import ast.Regla;

public interface Visitor {
	Object visit(Program program, Object param);
	Object visit(Regla regla, Object param);
	Object visit(Definicion definicion, Object param);
}
