package render;

import java.util.ArrayList;
import java.util.List;

public class FormattedLine {

	String text_align;
	List<FormattedText> texts; 
	
	public FormattedLine() {
		this.texts = new ArrayList<FormattedText>();
	}
	
	public void addText(FormattedText ft){
		this.texts.add(ft);
	}

	public List<FormattedText> getTexts(){
		return texts;
	}

	public String getText_align(){
		return text_align;
	}

	public void setText_align(String text_align){
		this.text_align = text_align;
	}

	public double getMetrics() {
		return 1;
	}
		
}
