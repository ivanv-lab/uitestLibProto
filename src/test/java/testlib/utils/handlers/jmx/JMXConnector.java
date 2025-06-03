package testlib.utils.handlers.jmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testlib.utils.handlers.PropertyHandler;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JMXConnector {

    private static final Logger logger= LoggerFactory.getLogger(JMXConnector.class);
    private final String jmxServiceUrl= PropertyHandler.getProperty("cache.address")+":"+PropertyHandler
            .getProperty("cache.port");
    private MBeanServerConnection mbsc;
    private javax.management.remote.JMXConnector connector;

    public void connect() throws IOException {
        try{
            JMXServiceURL url=new JMXServiceURL(jmxServiceUrl);
            Map<String, Object> environment=new HashMap<>();
            String[] credentials=new String[] {"jmxadmin", " jmxpwd-wcsd"};
            environment.put("jmx.remote.credentials",credentials);

            connector= JMXConnectorFactory.connect(url,environment);
            mbsc=connector.getMBeanServerConnection();
            logger.info("Connected to JMX service at: {}", jmxServiceUrl);
        } catch (MalformedURLException e){
            logger.error("Invalid JMX service URL: {}", jmxServiceUrl, e);
            throw new IOException("Invalid JMX service URL", e);
        } catch (IOException e){
            logger.error("Failed to connect to JMX service at: {}", jmxServiceUrl, e);
            throw new IOException("Failed to connect to JMX service", e);
        }
    }

    public MBeanServerConnection getMbsc(){
        return mbsc;
    }

    public void close(){
        if(connector!=null){
            try{
                connector.close();
                logger.info("JMX connection closed");
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
