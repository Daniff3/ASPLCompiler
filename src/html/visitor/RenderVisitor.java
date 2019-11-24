package html.visitor;

import css.ast.AstCss;
import css.visitor.BuscaParamEnCssVisitor;
import html.ast.*;
import render.FormattedLine;
import render.FormattedPage;
import render.FormattedText;

public class RenderVisitor implements Visitor {

	Program p = null;
	BuscaParamEnCssVisitor bpCss = null;
	AstCss defaultCss = null;
	AstCss currentCss = null;
	FormattedPage fp = null;
	
	public RenderVisitor(AstHtml p, BuscaParamEnCssVisitor bpCss, AstCss defaultCss, AstCss currentCss){
		super();
		this.p = (Program) p;
		this.bpCss = bpCss;
		this.currentCss = currentCss;
		this.defaultCss = defaultCss;		
	}

	@Override
	public Object visit(Program program, Object param) {
		String page_title = (String) program.head.accept(this, param);
		FormattedPage fp = (FormattedPage) program.body.accept(this, param);
		fp.setPage_title(page_title);
		return fp;
	}
	@Override
	public Object visit(Head head, Object param) {
		return (String) head.title.accept(this, param);
	}
	@Override
	public Object visit(Body body, Object param) {
		FormattedPage fp = new FormattedPage();

		for(Parrafo parrafo: body.parrafos)
			fp.addLine((FormattedLine)parrafo.accept(this, param));

		return fp;
	}
	@Override
	public Object visit(Title title, Object param) {
		return (String) title.title;
	}
	@Override
	public Object visit(Link link, Object param) {
		return null;
	}
	@Override
	public Object visit(H1 h1, Object param) {
		String identificador = "h1";
		String color = bpCss.search(identificador, "color", currentCss);
		String font_size_str = bpCss.search(identificador, "font-size", currentCss);
		String font_style = bpCss.search(identificador, "font-style", currentCss);
		String text_align = bpCss.search(identificador, "text-align", currentCss);

		if(text_align == null)
			text_align = bpCss.search(identificador, "text-align", defaultCss);
		if (color == null) 
			color = bpCss.search(identificador, "color", defaultCss);
		if (font_size_str == null)
			font_size_str = bpCss.search(identificador, "font-size", defaultCss);
		if(font_style == null)
			font_style = bpCss.search(identificador, "font-style", defaultCss);
			
		font_size_str = font_size_str.replace("px", "");
		double font_size = Double.parseDouble(font_size_str);
		FormattedText ft = new FormattedText(color, font_size, font_style, h1.text.trim());
		FormattedLine fl = new FormattedLine();
		fl.addText(ft);
		fl.setText_align(text_align);
		return fl;
	}
	@Override
	public Object visit(H2 h2, Object param) {
		String identificador = "h2";
		String color = bpCss.search(identificador, "color", currentCss);
		String font_size_str = bpCss.search(identificador, "font-size", currentCss);
		String font_style = bpCss.search(identificador, "font-style", currentCss);
		String text_align = bpCss.search(identificador, "text-align", currentCss);

		if(text_align == null)
			text_align = bpCss.search(identificador, "text-align", defaultCss);
		if (color == null) 
			color = bpCss.search(identificador, "color", defaultCss);
		if (font_size_str == null)
			font_size_str = bpCss.search(identificador, "font-size", defaultCss);
		if(font_style == null)
			font_style = bpCss.search(identificador, "font-style", defaultCss);
			
		font_size_str = font_size_str.replace("px", "");
		double font_size = Double.parseDouble(font_size_str);
		FormattedText ft = new FormattedText(color, font_size, font_style, h2.text.trim());
		FormattedLine fl = new FormattedLine();
		fl.addText(ft);
		fl.setText_align(text_align);
		return fl;
	}
	@Override
	public Object visit(P p, Object param) {
		FormattedLine fl = new FormattedLine();
		String identificador = "p";
		String text_align = bpCss.search(identificador, "text-align", currentCss);

		if(text_align == null)
			text_align = bpCss.search(identificador, "text-align", defaultCss);

		for(Bloque bloque: p.bloques)
			fl.addText((FormattedText)bloque.accept(this, identificador));

		fl.setText_align(text_align);
		return fl;
	}
	@Override
	public Object visit(Texto texto, Object param) {
		String identificador = (String) param;
		String color = bpCss.search(identificador, "color", currentCss);
		String font_size_str = bpCss.search(identificador, "font-size", currentCss);
		String font_style = bpCss.search(identificador, "font-style", currentCss);;
		
		if (color == null) 
			color = bpCss.search(identificador, "color", defaultCss);
		if (font_size_str == null)
			font_size_str = bpCss.search(identificador, "font-size", defaultCss);
		if(font_style == null)
			font_style = bpCss.search(identificador, "font-style", defaultCss);
			
		font_size_str = font_size_str.replace("px", "");
		double font_size = Double.parseDouble(font_size_str);
		FormattedText ft = new FormattedText(color, font_size, font_style, texto.text.trim());
		return ft;
	}
	@Override
	public Object visit(BoldText bt, Object param) {
		String identificador = (String) param;
		String color = bpCss.search(identificador, "color", currentCss);
		String font_size_str = bpCss.search(identificador, "font-size", currentCss);
		String font_style = "bold";
		
		if (color == null) 
			color = bpCss.search(identificador, "color", defaultCss);
		if (font_size_str == null)
			font_size_str = bpCss.search(identificador, "font-size", defaultCss);
			
		font_size_str = font_size_str.replace("px", "");
		double font_size = Double.parseDouble(font_size_str);
		FormattedText ft = new FormattedText(color, font_size, font_style, bt.text.trim());
		return ft;
	}
	@Override
	public Object visit(ItalicText it, Object param) {
		String identificador = (String) param;
		String color = bpCss.search(identificador, "color", currentCss);
		String font_size_str = bpCss.search(identificador, "font-size", currentCss);
		String font_style = "italic";
		
		if (color == null) 
			color = bpCss.search(identificador, "color", defaultCss);
		if (font_size_str == null)
			font_size_str = bpCss.search(identificador, "font-size", defaultCss);
			
		font_size_str = font_size_str.replace("px", "");
		double font_size = Double.parseDouble(font_size_str);
		FormattedText ft = new FormattedText(color, font_size, font_style, it.text.trim());
		return ft;
	}
	@Override
	public Object visit(UnderlinedText ut, Object param) {
		String identificador = (String) param;
		String color = bpCss.search(identificador, "color", currentCss);
		String font_size_str = bpCss.search(identificador, "font-size", currentCss);
		String font_style = "underlined";
		
		if (color == null) 
			color = bpCss.search(identificador, "color", defaultCss);
		if (font_size_str == null)
			font_size_str = bpCss.search(identificador, "font-size", defaultCss);
			
		font_size_str = font_size_str.replace("px", "");
		double font_size = Double.parseDouble(font_size_str);
		FormattedText ft = new FormattedText(color, font_size, font_style, ut.text.trim());
		return ft;
	}	

	public FormattedPage getFormattedPage() {
		fp = (FormattedPage) p.accept(this, null);
		return fp;
	}
	
}
