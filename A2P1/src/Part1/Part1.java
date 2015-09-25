package Part1;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.validation.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Part1 {

    public static void main(String[] args) {
        Source part1Schema = null;
        
        try {
            File xmlFile = new File("part1.xml");
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            SchemaFactory sfactory = 
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sfactory.newSchema(part1Schema);
            factory.setSchema(schema);
            DocumentBuilder builder =
                factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            }
        catch (SAXException se) {
            System.err.println(se.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
