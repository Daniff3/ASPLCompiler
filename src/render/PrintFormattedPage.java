package render;

public class PrintFormattedPage {

	private FormattedPage fp;
	
	public PrintFormattedPage(FormattedPage fp) {
		this.fp = fp;
	}
	
	public void printPage() {
		System.out.println("-------------\nTítulo de la página: " + fp.page_title + "\n-------------\n");
		for (FormattedLine fl: fp.getLines())
			printLine(fl);
	}
	
	private void printLine(FormattedLine fl) {
	    System.out.println("(Line align: " + fl.text_align + " | Metrics: " + fl.getMetrics() + " >>");    
	    for (FormattedText ft: fl.getTexts())
	      printText(ft);
	    System.out.println(")\n");
	}
	
	private void printText(FormattedText ft) {
		System.out.println("\t(Format: " + ft.color + ", " + ft.font_size + ", " + ft.font_style + " | Metrics: " + ft.getMetrics() + " >> " + ft.text + ")");
	}
		
}
