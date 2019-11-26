package render;

public class PrintFormattedPage {

	public void printPage(FormattedPage fp) {
		System.out.println("-------------\nTítulo de la página: " + fp.page_title + "\n-------------\n");
		for (FormattedLine fl: fp.getLines())
			printLine(fl);
	}
	
	public void printLine(FormattedLine fl) {
	    System.out.println("(Line align: " + fl.text_align + " | Metrics: " + fl.getMetrics() + " >>");    
	    for (FormattedText ft: fl.getTexts())
	      printText(ft);
	    System.out.println(")\n");
	}
	
	public void printText(FormattedText ft) {
		System.out.println("\t(Format: " + ft.color + ", " + ft.font_size + ", " + ft.font_style + " | Metrics: " + ft.getMetrics() + " >> " + ft.text + ")");
	}
		
}
