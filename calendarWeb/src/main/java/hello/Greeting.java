package hello;

public class Greeting {

    private final long id;
    private final String [] inhalt = new String[3];

    public Greeting(long id) {
        this.id = id;
        inhalt[0]= "1";
        inhalt[1]= "3";
        inhalt[2]= "2";
    }

    public long getId() {
        return id;
    }

    public String [] getContent() {
        return inhalt;
    }
}