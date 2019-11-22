package render;

import java.util.List;

public class FormattedPage {

	String page_title;
	int text_line;
	List<FormattedLine> lines;

	public void addLine(FormattedLine line){
		this.lines.add(line);
	}

	public List<FormattedLine> getLines(){
		return lines;
	}

	public void setPage_title(String page_title){
		this.page_title = page_title;
	}

	public String getPage_title(){
		return page_title;
	}

	public void setText_line(int text_line){
		this.text_line = text_line;
	}

	public int getText_line(){
		return text_line;
	}
	
}
