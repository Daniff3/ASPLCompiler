package render;

public class FormattedText {

	String color;
	double font_size;
	String font_style;
	double metrics = -1;
	String text;
	
	public FormattedText(String color, double font_size, String font_style, String text) {
		super();
		this.color = color;
		this.font_size = font_size;
		this.font_style = font_style;
		this.text = text;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getFont_size() {
		return font_size;
	}

	public void setFont_size(double font_size) {
		this.font_size = font_size;
	}

	public String getFont_style() {
		return font_style;
	}

	public void setFont_style(String font_style) {
		this.font_style = font_style;
	}

	public double calculateMetrics() {
		return 1;
	}
	
	public double getMetrics() {
		calculateMetrics();
		return metrics;
	}

	public void setMetrics(double metrics) {
		this.metrics = metrics;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
		
}
