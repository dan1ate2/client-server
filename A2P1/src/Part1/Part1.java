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
        
        try {
            File xmlFile = new File("src/Part1/part1.xml");
            File schemaFile = new File("src/Part1/part1.xsd");

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            
            SchemaFactory sfactory = 
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sfactory.newSchema(schemaFile);
            
            factory.setSchema(schema);
            DocumentBuilder builder =
                factory.newDocumentBuilder();
            
            Document doc = builder.parse(xmlFile);
            Element root = doc.getDocumentElement();

            String format = root.getAttribute("id");
             if (format != null)
                 System.out.println("attribute ID: "+format);
                }
        catch (SAXException se) {
            System.err.println(se.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
