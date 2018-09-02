package ro.msg.edu.jbugs.business.service.user;

import javax.ejb.Singleton;
import java.util.HashMap;

@Singleton
public class SessionSingleton {
    HashMap<String, Integer> userOverflow = new HashMap<>();
}
