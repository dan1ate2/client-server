/*
    Dan Hogan
    dhogan15
    22108261
    ISY00246
    Assignment 2
    Part 2
    Purpose: Print out details/fields of a team xml file
 */

package Part2;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Part2 {

    public static void main(String[] args) {
//        if (args.length != 2) {
//            System.out.println("Requires team xml file as arg");
//            System.exit(-1); // exit program
//         }
        try {
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(System.in)); // console input
            
            // welcome message
            System.out.println("This program Print out details/fields of a team xml file.");
            // get files
            File xmlFile = new File("src/Part2/part2.xml");
            // dom parser
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =
                factory.newDocumentBuilder();
            // parse xml
            Document doc = builder.parse(xmlFile);

            Element root = doc.getDocumentElement();
            NodeList ns = root.getChildNodes();
            for (int i=0; i<ns.getLength(); i++) {
                if (ns.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) ns.item(i);
                    if (el.getTagName().equals("teamDetail")) {
                        Text str = (Text) el.getFirstChild();
                        System.out.println("element: "+str.getWholeText());
                    }
//                    else if (el.getTagName().equals("address")) {
//                        NodeList adrs = el.getChildNodes();
//                        for (int j=0; j<adrs.getLength(); j++) {
//                            if (adrs.item(j).getNodeType() == Node.ELEMENT_NODE) {
//                                Element elt2 = (Element) adrs.item(j);
//                                if (elt2.getTagName().equals("street")) {
//                                    Text str = (Text) elt2.getFirstChild();
//                                    System.out.println("street: "+str.getWholeText());
//                                }
//                                else if (elt2.getTagName().equals("city")) {
//                                    Text str = (Text) elt2.getFirstChild();
//                                    System.out.println("City: "+str.getWholeText());
//                                    System.out.println("Postcode: "+elt2.getAttribute("postcode"));
//                                }
//                            }// end if
//                        } // end for
//                    } // end elseif
                } // end if
            } // end for
            
            // print confirmation
            System.out.println("Press any key to exit.");
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
