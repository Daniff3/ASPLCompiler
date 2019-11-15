package ast;

import java.util.List;

import visitor.Visitor;

public class Program implements AstCss {

	public List<Regla> reglas;
	
	public Program(List<Regla> reglas) {
		this.reglas = reglas;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}
