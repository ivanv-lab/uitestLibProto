package testlib.utils.handlers.jmx;

import testlib.utils.handlers.PropertyHandler;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.HashMap;
import java.util.Map;

public class JmxHandler {

    private final String host= PropertyHandler.getProperty("cache.address");
    private final int port=Integer.parseInt(PropertyHandler.getProperty("cache.port"));
    private JMXConnector jmxConnector;
    private MBeanServerConnection mBeanServerConnection;

    public void connect() throws Exception{

        String domain="jmxrmi";
        JMXServiceURL url=
                new JMXServiceURL(String.format("service:jmx:rmi:///jndi/rmi://%s:%s/%s", host, port, domain));
        Map<String,Object> environment=new HashMap<>();
        environment.put(JMXConnector.CREDENTIALS,new String[]{"jmxadmin","jmxpwd-wcsd"});
        jmxConnector= JMXConnectorFactory.connect(url,environment);
        mBeanServerConnection=jmxConnector.getMBeanServerConnection();
    }

    public void disconnect() throws Exception{
        if(jmxConnector!=null){
            jmxConnector.close();
        }
    }

    public Object invoke(String mbeanName, String methodName, Object... params) throws Exception{

        ObjectName objectName=new ObjectName(mbeanName);
        String[] signature=new String[params.length];
        for(int i=0;i<params.length;i++){
            signature[i]=params[i].getClass().getName();
        }

        return mBeanServerConnection
                .invoke(objectName,methodName,params,signature);
    }
}
