package testlib.utils.handlers.jmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.*;
import java.io.IOException;

public class JMXClient {

    private static final Logger logger= LoggerFactory.getLogger(JMXClient.class);
    private final MBeanServerConnection mbsc;

    public JMXClient(MBeanServerConnection mbsc){
        this.mbsc=mbsc;
    }

    public Object getAttribute(String objectName, String attributeName){
        try{
            ObjectName name=new ObjectName(objectName);
            return mbsc.getAttribute(name,attributeName);
        } catch (InstanceNotFoundException | AttributeNotFoundException | ReflectionException | IOException |
                 MalformedObjectNameException | MBeanException e) {
            logger.error("Error getting attribute {} from {}: {}", attributeName, objectName, e.getMessage(), e);
            return null;
        }
    }

    public void setAttribute(String objectName, String attributeName, Object value){
        try{
            ObjectName name=new ObjectName(objectName);
            mbsc.setAttribute(name,new Attribute(attributeName,value));
        } catch (InstanceNotFoundException | AttributeNotFoundException | ReflectionException | IOException |
                 MalformedObjectNameException | InvalidAttributeValueException | MBeanException e) {
            logger.error("Error setting attribute {} on {}: {}", attributeName, objectName, e.getMessage(), e);
        }
    }

    public Object invokeOperation(String objectName, String operationName, Object[] params, String[] signature) {
        try {
            ObjectName name = new ObjectName(objectName);
            return mbsc.invoke(name, operationName, params, signature);
        } catch (InstanceNotFoundException | MBeanException | ReflectionException | IOException | MalformedObjectNameException e) {
            logger.error("Error invoking operation {} on {}: {}", operationName, objectName, e.getMessage(), e);
            return null;
        }
    }
}
