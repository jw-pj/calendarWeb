package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Datenbankabfrage {

	private static String db_TableConn = "jdbc:sqlite:SuperKalender.db";

	private static String db_Mail = "EMail";
	private static String db_Vorname = "Vorname";
	private static String db_Nachname = "Nachname";
	private static String db_Passwort = "Passwort";

	private static String db_Name_User = "User";

	public static boolean ErstelleUser(String pMail, String pVorname, String pNachname, String pPasswort) {
		// User in DB speichern
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(db_TableConn);
			Statement stat = conn.createStatement();
			String sql = "INSERT INTO " + db_Name_User + " (" + db_Mail + ", " + db_Vorname + ", " + db_Nachname + ", "
					+ db_Passwort + ") VALUES ('" + pMail + "','" + pVorname + "','" + pNachname + "','" + pPasswort
					+ "')";
			int rs = stat.executeUpdate(sql);
			conn.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public ArrayList<String> gibTermineZumUser(String pMail) {
		// User in DB speichern
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(db_TableConn);
			Statement stat = conn.createStatement();
			String sql = "select e.* from Ereignis e, UserEreignis ue where e.EreignisID = ue.Ererignis and e.User = '"
					+ pMail + "';";
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<String> list = new ArrayList<String>();
			while (rs.next()) {
				Ereignis e = new Ereignis(rs.getString("Startdatum"), rs.getString("Enddatum"), rs.getString("Name"),
						rs.getString("Beschreibung"), rs.getString("EreignisID"));
				list.add(e.toString());
			}
			conn.close();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public User gibUser(String pMail) {
		// User in DB speichern
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(db_TableConn);
			Statement stat = conn.createStatement();
			String sql = "select * from User where EMail = '" + pMail + "';";
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<User> list = new ArrayList<User>();
			User user = null;
			while (rs.next()) {
				user = new User(rs.getString("EMail"), rs.getString("Vorname"), rs.getString("Nachname"),
						rs.getString("Passwort"));

			}
			conn.close();
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean MailVorhanden(String pMail) {
		boolean vorhanden = true;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(db_TableConn);
			Statement stat = conn.createStatement();
			String sql = "Select Count(*) from " + db_Name_User + " WHERE " + db_Mail + " = '" + pMail + "*";
			ResultSet rs = stat.executeQuery(sql);

			if (rs.getFetchSize() == 0) {
				vorhanden = false;
			}

			rs.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vorhanden;
	}

	public static boolean Login(String pMail, String pPasswort) {
		boolean result = false;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(db_TableConn);
			Statement stat = conn.createStatement();
			String sql = "Select " + db_Passwort + " from " + db_Name_User + " WHERE " + db_Mail + " = '" + pMail + "'";
			ResultSet rs = stat.executeQuery(sql);

			if (rs.getString(db_Passwort).equals(pPasswort)) {
				result = true;
			}

			rs.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static void ErstelleEintrag() {

	}

	public Ereignis gibTermine(String ereignisID) {

		// User in DB speichern
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(db_TableConn);
			Statement stat = conn.createStatement();
			String sql = "select * from Ereignis where ereignisID = '" + ereignisID + "';";
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<User> list = new ArrayList<User>();
			Ereignis ereignis = null;
			while (rs.next()) {
				String ereignisiD = rs.getString("ereignisID");
				String startdatum = rs.getString("Startdatum");
				String enddatum = rs.getString("Enddatum");
				String bezeichnung = rs.getString("Bezeichnung");
				String beschreibung = rs.getString("beschreibung");
				
				ereignis = new Ereignis(ereignisiD, startdatum, enddatum, bezeichnung, beschreibung);

			}
			conn.close();
			return ereignis;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
