package pabblo.plan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import pabblo.parseMarkup.Markup;
import pabblo.parseMarkup.MarkupSys;

public class ResSched {
	private static String passwd_admin = "87a9sd4c2H8y1f2c89fD57sD78h23d8F2HJ";
	private ArrayList<Plan> plany;
	private ArrayList<Przedmiot> przedmioty;
	
	public ResSched() {
		przedmioty = new ArrayList<Przedmiot>();
		plany = new ArrayList<Plan>();
		long start = System.currentTimeMillis();
		downloadSubjectsList();
		System.out.println("\n"+(System.currentTimeMillis()-start)+"ms");
	}
	private void downloadSubjectsList(){
		
		try{
			URL url = new URL("http://pabblo.wk2.pl/plan_zajec/mobile/view_przedmioty.php");
	        Map<String,Object> params = new LinkedHashMap<>();
	        params.put("passwd", passwd_admin);
	
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String,Object> param : params.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);
	        String response="";
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        String temp;
	        while ((temp = in.readLine()) != null ) {
	            response+=temp;
	          }
	        in.close();
	        if(response.matches("401"))
	        	throw new Exception("Nie pobrano listy przedmiotów.\nKod b³êdu: "+response);
	        //System.out.println(response);
	        MarkupSys as = new MarkupSys(response);
	        addPrzedmiotyOnline(as);
		}
		catch (IOException e){
			System.out.println("B³¹d po³¹czenia...");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private void addPrzedmiotyOnline(MarkupSys as) {
		przedmioty.clear();
		int id=0;
		String name="";
		String skrot="";
		int typ=0;
		String kolor="";
		while(as.isNext()){
			Markup temp = as.getNextMarkup();
			
			
			if(temp.getName().matches("id"))
				id = Integer.valueOf(temp.getText());
			if(temp.getName().matches("nazwa"))
				name = temp.getText();
			if(temp.getName().matches("skrot"))
				skrot = temp.getText();
			if(temp.getName().matches("typ"))
				typ = Integer.valueOf(temp.getText());
			if(temp.getName().matches("kolor"))
				kolor = temp.getText();
			if(temp.getName().matches("przedmiot_end"))
				przedmioty.add(new Przedmiot(id,name,skrot,typ,kolor));
		}
		
		
	}
	void nowyPrzedmiot(String nazwa, String skrot, int typ){
		nowyPrzedmiot(nazwa, skrot, typ, 588464);
	}
	
	void nowyPrzedmiot(String nazwa, String skrot, int typ, int kolor){
		try{
			URL url = new URL("http://pabblo.wk2.pl/plan_zajec/mobile/insert_przedmiot.php");
	        Map<String,Object> params = new LinkedHashMap<>();
	        params.put("passwd", passwd_admin);
	        params.put("nazwa", nazwa);
	        params.put("skrot", skrot);
	        params.put("typ", typ);
	        params.put("kolor", kolor);

	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String,Object> param : params.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);
	        String response="";
	        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        for ( int c = in.read(); c != -1; c = in.read() )
	        	response+=(char)c;
	        if(response.matches("Success"))
	        	System.out.println("Pomyœlnie dodano przedmiot.");
	        else
	        	throw new Exception("Nie dodano przedmiotu.\nKod b³êdu: "+response);
			}
		catch (IOException e){
			System.out.println("B³¹d po³¹czenia...");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public ArrayList<Przedmiot> getPrzedmioty() {
		return przedmioty;
	}
}
