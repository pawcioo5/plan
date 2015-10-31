package pabblo.parseMarkup;

public class Markup {
	String name;
	String inside;
	Markup father;
	
	public Markup(String name, String inside, Markup father) {
		this.name = name;
		this.inside = inside;
		this.father = father;
	}
	public Markup(String name, String inside) {
		this.name = name;
		this.inside = inside;
		this.father = null;
	}
}
