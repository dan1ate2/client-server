package Part1;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
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
            System.out.println("root element: "+root.getTagName());

            String someAttribute = root.getAttribute("teamDetail");
//            if (someAttribute != null) {
//                System.out.println("attribute ID: "+someAttribute);
//            }
            NodeList ns = root.getChildNodes();
            for (int i=0; i<ns.getLength(); i++) {
                System.out.println("Node type= "+ns.item(i).getNodeType());
                if (ns.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element elt = (Element) ns.item(i);
                    System.out.println("Element: "+elt.getTagName());
                }
            }

            for (int i=0; i<ns.getLength(); i++) {
                if (ns.item(i).getNodeType() == Node.TEXT_NODE) {
                    Text txt = (Text) ns.item(i);
                    System.out.println("Text: "+txt.getWholeText());
                }
                if (ns.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element elt = (Element) ns.item(i);
                    System.out.println("Element: "+elt.getTagName());
                }
            }
            
        } // end try
        catch (SAXException se) {
            System.err.println(se.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
