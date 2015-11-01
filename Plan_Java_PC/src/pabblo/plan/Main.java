package pabblo.plan;

import java.util.ArrayList;

import pabblo.parseMarkup.MarkupSys;

public class Main {

	public static void main(String[] args) {
		ResSched sys = new ResSched();
		//sys.nowyPrzedmiot("Komunikacja Cz³owiek-Komputer", "KCK", 1);
		//String response = "<dfs><as><ld>sa</ld></as><as><ld>ssdaa</ld></as></dfs>";
		//MarkupSys test = new MarkupSys(response);
		ArrayList<Przedmiot> przedmioty = sys.getPrzedmioty();
		for(int i=0;i<przedmioty.size();i++)
			System.out.println(przedmioty.get(i).getName());
	}

}
