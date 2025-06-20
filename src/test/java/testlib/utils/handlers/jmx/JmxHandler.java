package testlib.utils.handlers.jmx;

import testlib.utils.handlers.PropertyHandler;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.xml.parsers.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testlib.utils.XmlParser.getValueByXpath;

public class JmxHandler {

    private final String host= PropertyHandler.getProperty("cache.address");
    private final int port=Integer.parseInt(PropertyHandler.getProperty("cache.port"));
    private JMXConnector jmxConnector;
    private MBeanServerConnection mBeanServerConnection;
    SAXParserFactory factory=SAXParserFactory.newInstance();
    SAXParser parser;
    private Object cacheValue;

    public void connect(){
        try {
            String domain = "jmxrmi";
            JMXServiceURL url =
                    new JMXServiceURL(String.format("service:jmx:rmi:///jndi/rmi://%s:%s/%s", host, port, domain));
            Map<String, Object> environment = new HashMap<>();
            environment.put(JMXConnector.CREDENTIALS, new String[]{"jmxadmin", "jmxpwd-wcsd"});
            jmxConnector = JMXConnectorFactory.connect(url, environment);
            mBeanServerConnection = jmxConnector.getMBeanServerConnection();
            parser=factory.newSAXParser();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Ошибка подключения к JMX",e);
        }
    }

    public void disconnect(){
        try {
            if (jmxConnector != null) {
                jmxConnector.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public JmxHandler invoke(String mbeanName, String methodName, Object... params){

        try {
            ObjectName objectName = new ObjectName(mbeanName);
            String[] signature = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                signature[i] = params[i].getClass().getName();
            }

            cacheValue=mBeanServerConnection
                    .invoke(objectName, methodName, params, signature);
            return this;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Ошибка получения значения из JMX",e);
        }
    }

    /**
     * Проверяет соответствие значения КЭШа по указанному пути с указанным значением
     * @param path Путь для значения в КЭШе, например "/root/name", будет найдено значение name
     * @param value Значение для проверки
     * @return
     */
    public JmxHandler cacheValueContains(String path, String value) {
        String expectedValue=getValueByXpath(cacheValue,path);
        assertTrue(expectedValue.equals(value));
        return this;
    }

    /**
     * Проверяет несоответствие значения КЭШа по указанному пути с указанным значением
     * @param path Путь для значения в КЭШе, например "/root/name", будет найдено значение name
     * @param value Значение для проверки
     * @return
     */
    public JmxHandler cacheValueNotContains(String path, String value){
        String expectedValue=getValueByXpath(cacheValue,path);
        assertFalse(expectedValue.equals(value));
        return this;
    }

    /**
     * Проверяет, что полученное значение КЭШа пустое
     * @return
     */
    public JmxHandler cacheValueIsNull(){
        assertTrue(cacheValue.equals(null));
        return this;
    }
}
