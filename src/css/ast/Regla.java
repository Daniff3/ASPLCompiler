package css.ast;

import java.util.List;

import css.visitor.Visitor;

public class Regla implements AstCss {

	public String identificador;
	public List<Definicion> definiciones;
	
	public Regla(String identificador, List<Definicion> definiciones) {
		this.definiciones = definiciones;
		this.identificador = identificador;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}	
	
}
