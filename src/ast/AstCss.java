package ast;

import visitor.*;

public interface AstCss {
	Object accept(Visitor visitor, Object param);
}
