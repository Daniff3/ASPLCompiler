package css.ast;

import css.visitor.Visitor;

public class Definicion implements AstCss {

	public String campo;
	public String valor;
	
	public Definicion(String campo, String valor) {
		this.campo = campo;
		this.valor = valor;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
		
}
