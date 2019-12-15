	package hello;

import java.net.InetAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
       	System.out.println( "Host Name/Adresse: " + InetAddress.getLocalHost() );

	    SpringApplication.run(Application.class, args);
    }
}
