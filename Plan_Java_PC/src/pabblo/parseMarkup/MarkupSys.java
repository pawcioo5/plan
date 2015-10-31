package pabblo.parseMarkup;

import java.util.ArrayList;

public class MarkupSys {
	String text;
	int zaglebienie;
	ArrayList<Markup> lista_znacznikow;
	
	public MarkupSys(String response) {
		zaglebienie = 0;
		this.text = response;
		lista_znacznikow = new ArrayList<Markup>();
		parse();
	}
	private void parse() {
		if(text.length()>0){
			Markup lastFather;
			int index_current=0;
			int start_markup_begin=0,start_markup_end=0;
			
			while(index_current<text.length()){
				start_markup_begin = text.indexOf('<',index_current);
			}
			
		}
	}

}
