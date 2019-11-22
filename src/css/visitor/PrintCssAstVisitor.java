package css.visitor;

import css.ast.Definicion;
import css.ast.Program;
import css.ast.Regla;

public class PrintCssAstVisitor implements Visitor {

	String sp = "";

	@Override
	public Object visit(Program program, Object param) {
		String resultado = "";
		
		for (Regla regla: program.reglas) {
			resultado = resultado + (String) regla.accept(this, sp);
		}
		
		return "CSS declarations \n-------------------\n" + resultado;
	}

	@Override
	public Object visit(Regla regla, Object param) {
		String resultado = "";
		
		for (Definicion definicion: regla.definiciones)
			resultado = resultado + sp + (String) definicion.accept(this, (String) param);
		
		return regla.identificador + " {\n" + resultado + "}\n";
	}

	@Override
	public Object visit(Definicion definicion, Object param) {
		return "    " + definicion.campo + ": " + definicion.valor + "\n";
	}
	
}
