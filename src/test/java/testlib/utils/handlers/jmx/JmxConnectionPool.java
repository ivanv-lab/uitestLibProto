package testlib.utils.handlers.jmx;

import javax.management.remote.JMXConnector;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JmxConnectionPool {
    private static final Map<String, JMXConnector> pool=new ConcurrentHashMap<>();

    public static synchronized JMXConnector getConnection(String url) throws IOException{
        if(!pool.containsKey(url) || !isConnectionActive(pool.get(url))){
            pool.put(url,createNewConnection(url));
        }
        return pool.get(url);
    }

    private static boolean isConnectionActive(JMXConnector connector){
        try {
            connector.getMBeanServerConnection().getMBeanCount();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
