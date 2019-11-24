package css.visitor;

import css.ast.AstCss;
import css.ast.Definicion;
import css.ast.Program;
import css.ast.Regla;

public class BuscaParamEnCssVisitor implements Visitor {

	String label = null;
	String identificador = null;

	@Override
	public Object visit(Program program, Object param) {
		for (Regla regla: program.reglas)
			if (regla.identificador.equals(identificador))
				return (String) regla.accept(this, null);
		
		return null;
	}

	@Override
	public Object visit(Regla regla, Object param) {
		for (Definicion definicion: regla.definiciones)
			if (definicion.campo.equals(label))
				return (String) definicion.accept(this, null);
		
		return null;
	}

	@Override
	public Object visit(Definicion definicion, Object param) {
		return definicion.valor;
	}

	public String search(String identificador, String label, AstCss program) {
		this.identificador = identificador;
		this.label = label;
		
		if (identificador == null || label == null)
			return null;
		
		return (String) program.accept(this, null);
	}
	
}
