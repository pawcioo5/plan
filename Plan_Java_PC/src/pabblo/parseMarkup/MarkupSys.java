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
	private int getIndex(String text2, char c, int begin_index){
		int a = text2.indexOf(c,begin_index);
		if(a>=0)
			return a;
		else
			return text2.length();
	}
	
	private void parse() {
		if(text.length()>0){
			Markup lastFather;
			int index_current=0;
			int start_markup_begin=0,start_markup_end=0;
			
			while(index_current<text.length()){
				start_markup_begin = getIndex(text,'<',index_current);
				index_current = start_markup_begin;
				start_markup_end = getIndex(text,'>',index_current);
				index_current = start_markup_end;
				System.out.println(text.substring(start_markup_begin, start_markup_end));
			}
			
		}
	}

}
