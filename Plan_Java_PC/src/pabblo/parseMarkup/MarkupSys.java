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
	private int getIndex(String text2, String c, int begin_index){
		int a = text2.indexOf(c,begin_index);
		if(a>=0)
			return a;
		else
			return text2.length();
	}
	int getIndexNextBeginMarkup(int index_current){
		while(getIndex(text,"<",index_current)==getIndex(text,"</",index_current)){
			index_current=getIndex(text,"<",index_current)+1;
			if(index_current>=text.length())
				break;
		}
		return getIndex(text,"<",index_current);
	}
	int getIndexNextEndMarkup(int index_current){
		return getIndex(text,"</",index_current)+1;
	}
	
	private void parse() {
		if(text.length()>0){
			ArrayList<Markup> lastFatherList;
			int index_current=0;
			int start_markup_begin=0,start_markup_end=0;
			String name_current="";
			while(index_current<text.length()){
				start_markup_begin = getIndexNextBeginMarkup(index_current)+1;
				start_markup_end = getIndex(text,">",start_markup_begin);
				if(start_markup_begin<start_markup_end)
					name_current=text.substring(start_markup_begin,start_markup_end);
				System.out.println(name_current);
				
				index_current = start_markup_end;
				//System.out.println(text.substring(start_markup_begin, start_markup_end));
			}
			
		}
	}

}
