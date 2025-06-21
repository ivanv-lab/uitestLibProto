package testlib.utils.handlers.jmx;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JmxConnectionPool {
    private static final Map<String, JMXConnector> pool=new ConcurrentHashMap<>();

    public static JMXConnector getConnection(String url) throws IOException{
        return pool.computeIfAbsent(url,k ->{
            try{
                JMXServiceURL jmxServiceURL=new JMXServiceURL(url);
                return JMXConnectorFactory.connect(jmxServiceURL);
            } catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException("JMX connection failed", e);
            }
        });
    }
}
