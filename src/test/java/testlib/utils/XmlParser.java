package testlib.utils;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.StringReader;

public class XmlParser {

    public static String getValueByXpath(Object xmlObject, String xpathExpression){
        if(xmlObject==null)
            return null;

        try {
            String xmlString = xmlObject.toString();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlString)));

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expression = xPath.compile(xpathExpression);

            return (String) expression.evaluate(document, XPathConstants.STRING);
        } catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
