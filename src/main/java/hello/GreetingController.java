package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/Moritz")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Moritz") String name) {
        return new Greeting(counter.incrementAndGet());
    }
    
    @RequestMapping("/Jannis")
    public Greeting greet(@RequestParam(value="name", defaultValue="Moritz") String name) {
        return new Greeting(counter.incrementAndGet());
    }
    
//    @RequestMapping("/Termine")
//    public UserAbfrage user(@RequestParam(value="name", defaultValue="user") String name) {
//        return new UserAbfrage(name);
//    }
//    
    @RequestMapping("/User")
    public User user(@RequestParam(value="Mail", defaultValue="user") String pMail) {
        return new User(pMail);
    }
    
    @RequestMapping("/Ereignis")
    public Ereignis ereignis(@RequestParam(value="Date") String ereignis) {
    	return new Ereignis(ereignis);
    }

}