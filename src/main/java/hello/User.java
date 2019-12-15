package hello;

import java.util.ArrayList;

public class User {
	private String email;
	

	private String vorname;
	private String nachname;
	private String passwort;
	
	public User(String email) {
		Datenbankabfrage db = new Datenbankabfrage();
		User u = db.gibUser(email);
		this.email = u.getEmail();
		this.nachname = u.getNachname();
		this.vorname = u.getVorname();
		this.passwort = u.getPasswort();
		
		
	}
	
	public User(String email, String nachname, String vorname, String passwort) {
		this.email = email;
		this.nachname = nachname;
		this.vorname = vorname;
		this.passwort = passwort;
	}
	
	public String getEmail() {
		return email;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public String getPasswort() {
		return passwort;
	}

}
