package pabblo.parseMarkup;

import java.util.ArrayList;

public class MarkupSys {
	String text;

	ArrayList<Markup> lista_znacznikow;
	int current;
	
	public MarkupSys(String response) {
		current=-1;
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
			ArrayList<Markup> lastFatherList = new ArrayList<Markup>();
			int index_current=0;
			int start_markup_begin=0,start_markup_end=0;
			String name_current="";
			String text_current="";
			Markup temp_m;
			while(index_current<text.length()){
				start_markup_begin = getIndexNextBeginMarkup(index_current);
				start_markup_end = getIndexNextEndMarkup(index_current);
				if(start_markup_begin<start_markup_end){
					name_current = parseNameMarkup(start_markup_begin);
					if(getIndexNextBeginMarkup(start_markup_begin+1)>start_markup_end)
						text_current=text.substring(getIndex(text, ">", start_markup_begin)+1,start_markup_end-1);
					else
						text_current=null;
					if(lastFatherList.size()>0)
						temp_m = new Markup(name_current, text_current, lastFatherList.get(lastFatherList.size()-1));
					else
						temp_m = new Markup(name_current, text_current);
					lista_znacznikow.add(temp_m);
					lastFatherList.add(temp_m);
					index_current=start_markup_begin+1;
				}
				else{
					name_current = parseNameMarkup(start_markup_end);
					if(lastFatherList.size()>0)
						temp_m = new Markup(name_current+"_end", null, lastFatherList.get(lastFatherList.size()-1));
					else
						temp_m = new Markup(name_current+"_end", null);
					lista_znacznikow.add(temp_m);
					lastFatherList.remove(lastFatherList.size()-1);
					index_current=start_markup_end;
				}
				//System.out.println(name_current);
			}
		}
		lista_znacznikow.remove(lista_znacznikow.size()-1);
	}
	private String parseNameMarkup(int start_markup_begin) {
		if(start_markup_begin<getIndex(text, ">", start_markup_begin))
			return text.substring(start_markup_begin+1,getIndex(text, ">", start_markup_begin));
		return " ";
	}
	public Markup getNextMarkup(){
		current++;
		return lista_znacznikow.get(current);
	}
	public boolean isNext() {
		if(current<lista_znacznikow.size()-1)
			return true;
		return false;
	}

}
