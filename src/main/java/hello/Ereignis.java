package hello;

public class Ereignis {
	private String startdatum;

	private String enddatum;
	private String name;
	private String beschreibung;
	private String ereignisID;
	
	public Ereignis(String ereignisID, String startdatum, String enddatum, String bezeichnung, String beschreibung){

		this.startdatum = startdatum;
		this.enddatum = enddatum;
		this.name = bezeichnung;
		this.beschreibung = beschreibung;
		this.ereignisID = ereignisID;
	}

	public Ereignis(String startdatum) {
		Datenbankabfrage db = new Datenbankabfrage();
		Ereignis e = db.gibTermine(startdatum);
		this.ereignisID = e.getEreignisID();
		this.startdatum = e.getStartdatum();
		this.enddatum = e.getEnddatum();
	}
	
	@Override
	public String toString() {
		return "Ereignis [startdatum=" + startdatum + ", enddatum=" + enddatum + ", name=" + name + ", beschreibung="
				+ beschreibung + ", ereignisID=" + ereignisID + "]";
	}
	public String getStartdatum() {
		return startdatum;
	}

	public String getEnddatum() {
		return enddatum;
	}

	public String getName() {
		return name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public String getEreignisID() {
		return ereignisID;
	}
	
	

}
