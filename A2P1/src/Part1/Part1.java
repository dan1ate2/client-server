/*
    Dan Hogan
    dhogan15
    22108261
    ISY00246
    Assignment 2
    Part 1
    Purpose: Check if xml file validates by it's schema
 */
package Part1;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.validation.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Part1 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Requires args files .xml .xsd");
            System.exit(-1); // exit program
         }
        try {
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(System.in)); // console input
            
            // welcome message
            System.out.println("This program checks an xml file is valid by it's schema.");
            // get files
            File xmlFile = new File(args[0]);
            File schemaFile = new File(args[1]);
            // dom parser
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            // set up schema
            SchemaFactory sfactory = 
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sfactory.newSchema(schemaFile);
            factory.setSchema(schema);
            DocumentBuilder builder =
                factory.newDocumentBuilder();
            // parse xml
            Document doc = builder.parse(xmlFile);
            // get/print root element of schema
            Element root = doc.getDocumentElement();
            System.out.println("Root element is: "+root.getTagName());
            // print confirmation
            System.out.println("Validated the xml file ok."
                    + "\nPress any key to exit.");
            in.readLine();
            System.exit(-1); // exit program
        } // end try
        catch (SAXException se) {
            System.err.println(se.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
