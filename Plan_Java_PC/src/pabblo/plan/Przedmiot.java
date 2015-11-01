package pabblo.plan;

public class Przedmiot {
	private int id;
	private String name;
	private String skrot;
	private int typ;
	private String kolor;
	
	public Przedmiot(int id, String name, String skrot, int typ, String kolor) {
		this.id = id;
		this.name = name;
		this.skrot = skrot;
		this.typ = typ;
		this.kolor = kolor;
	}
	public String getName(){
		return this.name;
	}
	public int getId() {
		return id;
	}
	public String getKolor() {
		return kolor;
	}
	public String getSkrot() {
		return skrot;
	}
	public int getTyp() {
		return typ;
	}

}
