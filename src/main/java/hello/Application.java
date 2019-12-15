	package hello;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	Logger logger = LoggerFactory.getLogger(Application.class);
    	try {
    	
			logger.info( "Host Name/Adresse: " + InetAddress.getLocalHost() );
			logger.info(Inet4Address.getLocalHost().toString());
			logger.info(Inet6Address.getLocalHost().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	InetAddress.getByName(host)

    	SpringApplication.run(Application.class, args);
    }
}
