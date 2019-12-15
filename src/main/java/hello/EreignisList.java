package hello;

import java.util.ArrayList;

public class EreignisList {
	
	private String[] ereignisse;
	
	public EreignisList(String pMail)
	{
		Datenbankabfrage db = new Datenbankabfrage();
		ArrayList<String> list =db.gibTermineZumUser(pMail)	;
		ereignisse = new String[list.size()];
		for(int i = 0; i <list.size(); i++)
		{
			ereignisse[i] = list.get(i);
		}
	}
	
	public String[] getEreignisse()
	{
		return ereignisse;
	}
}
