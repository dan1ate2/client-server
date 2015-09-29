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
//        if (args.length != 1) {
//            System.out.println("Requires team xml file as arg");
//            System.exit(-1); // exit program
//         }
        try {
            // get file
            File xmlFile = new File("src/Part2/part2.xml");
            // dom parser
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =
                factory.newDocumentBuilder();
            // parse xml
            Document doc = builder.parse(xmlFile);
            // get root element
            Element root = doc.getDocumentElement();
            // get child elements of root
            NodeList nl = root.getChildNodes();
            Integer numTeamMembers = 0; // member counter
            for (int i = 0; i < nl.getLength(); i++) {
                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element elm = (Element) nl.item(i);
                    // if teamDetails
                    if (elm.getTagName().equals("teamDetail")) {
                        Element fullname = 
                            (Element) elm.getElementsByTagName("fullname").item(0);
                        System.out.println("\nTeam details for \""
                            +fullname.getTextContent()+"\" (code "
                            +elm.getAttribute("id")+"):\n");
                    }
                    else if (elm.getTagName().equals("member")) {
                        String memberName = elm.getAttribute("name");
                        String position = elm.getAttribute("position");
                        String mailAddress = "";
                        String altPosition = "";
                        String feesPaid = "";
                        // get member child nodes
                        NodeList memberChildNodes = elm.getChildNodes();
                        // set member variables
                        for (int n = 0; n < memberChildNodes.getLength(); n++) {
                            if (memberChildNodes.item(n).getNodeType() 
                                == Node.ELEMENT_NODE) {
                                Element memberElm = (Element) memberChildNodes.item(n);
                                if (memberElm.getTagName().equals("mailAddress")) {
                                    mailAddress = memberElm.getTextContent();
                                }
                                else if (memberElm.getTagName().equals("altPosition")) {
                                    altPosition = memberElm.getTextContent();
                                }
                                else if (memberElm.getTagName().equals("feesPaid")) {
                                    feesPaid = memberElm.getTextContent();
                                }
                            }
                        } // end for
                        // print 'players' title before first member
                        if (numTeamMembers == 0) {
                           System.out.println("Players:");
                        }
                        // print member details
                        if ("".equals(altPosition)) {
                        System.out.println(memberName+", "+position+" (Fees paid: $"+feesPaid+")\n\t"
                            +mailAddress);
                        }
                        else {
                            System.out.println(memberName+", "+position+" (Fees paid: $"+feesPaid+")\n\t"
                            +mailAddress+"\n\tAlt position: "+altPosition);
                        }
                        numTeamMembers++;
                    } // end else if member
                } // end if element
                // print team contact if last node (last member printed)
                if ((i + 1) == nl.getLength()) {
                    Element teamDetail = 
                        (Element) root.getElementsByTagName("teamDetail").item(0);
                    String leaderName = "";
                    String leaderPhone = "";
                    String leaderEmail = "";
                    NodeList teamDetailNodes = teamDetail.getChildNodes();
                    for (int j = 0; j < teamDetailNodes.getLength(); j++) {
                        if (teamDetailNodes.item(j).getNodeType() 
                            == Node.ELEMENT_NODE) {
                            Element teamElm = (Element) teamDetailNodes.item(j);
                            if (teamElm.getTagName().equals("leader")) {
                                // set leader detail variables
                                leaderName = 
                                    teamElm.getElementsByTagName("name").item(0).getTextContent();
                                leaderPhone = 
                                    teamElm.getElementsByTagName("phone").item(0).getTextContent();
                                leaderEmail = 
                                    teamElm.getElementsByTagName("email").item(0).getTextContent();
                            }
                        }
                    }
                    System.out.println("\nTotal of "+numTeamMembers+" team member(s).\n"
                        +"Team contact "+leaderName+" "+leaderPhone+", "
                        +leaderEmail+"\n");
                } // end if last node
            } // end for
        } // end try
        catch (SAXException se) {
            System.err.println(se.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
