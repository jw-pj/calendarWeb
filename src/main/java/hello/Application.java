	package hello;

import java.net.InetAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
       try {
			System.out.println( "Host Name/Adresse: " + InetAddress.getLocalHost() );
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    SpringApplication.run(Application.class, args);
    }
}
