
package cgi2013;
/**
 * CGI class to manage server side CGI
 *
 *
 * This is as originally written as cgi_lib by:
 *      Pat L. Durante (pldurante@tasc.com)
 *
 * Updated to HashMap container for Java 1.5.0 Barry Wilks 2006
 *    (barry.wilks@scu.edu.au)
 *
 * A bug with this is when multiple selections are made on a menu.  Since
 * data is returned in a hashtable, there is only one key so duplicate
 * (name,value) pairs are lost.
 * 
 */

import java.util.*;
import java.io.*;

class CGI {

  public static HashMap<String,String> ReadParse(InputStream inStream) {
      HashMap<String,String> form_data = new HashMap<String,String>();

      String inBuffer = "";

      if (MethGet())
      {
          inBuffer = System.getProperty("cgi.query_string");
      }
      else
      {
          DataInput d = new DataInputStream(inStream);
          String line;
          try
          {
              while((line = d.readLine()) != null)
              {
                  inBuffer = inBuffer + line;
              }
          }
          catch (IOException ignored) { }
      }
      //
      //  Split the name value pairs at the ampersand (&)
      //
      StringTokenizer pair_tokenizer = new StringTokenizer(inBuffer,"&");

      while (pair_tokenizer.hasMoreTokens())
      {
          String pair = urlDecode(pair_tokenizer.nextToken());
          //
          // Split into key and value
          //
          StringTokenizer keyval_tokenizer = new StringTokenizer(pair,"=");
          String key = new String();
          String value = new String();
          if (keyval_tokenizer.hasMoreTokens())
            key = keyval_tokenizer.nextToken();
          else ; // ERROR - shouldn't ever occur
          if (keyval_tokenizer.hasMoreTokens())
            value = keyval_tokenizer.nextToken();
          else ; // ERROR - shouldn't ever occur
          //
          // Add key and associated value into the form_data Hashtable
          //
          form_data.put(key,value);
      }

      return form_data;

  }

  /**
   *
   * URL decode a string.<p>
   *
   * Data passed through the CGI API is URL encoded by the browser.
   * All spaces are turned into plus characters (+) and all "special"
   * characters are hex escaped into a %dd format (where dd is the hex
   * ASCII value that represents the original character).  You probably
   * won't ever need to call this routine directly; it is used by the
   * ReadParse method to decode the form data.
   *
   * @param in The string you wish to decode.
   *
   * @return The decoded string.
   *
   */

  public static String urlDecode(String in)
  {
      StringBuffer out = new StringBuffer(in.length());
      int i = 0;
      int j = 0;

      while (i < in.length())
      {
         char ch = in.charAt(i);
         i++;
         if (ch == '+') ch = ' ';
         else if (ch == '%')
         {
            ch = (char)Integer.parseInt(in.substring(i,i+2), 16);
            i+=2;
         }
         out.append(ch);
         j++;
      }
      return new String(out);
  }

  /**
   *
   * Generate a standard HTTP HTML header.
   *
   * @return A String containing the standard HTTP HTML header.
   *
   */
  public static String Header()
  {
      return "Content-type: text/html\n\n";
  }

  /**
   *
   * Generate some vanilla HTML that you usually
   * want to include at the top of any HTML page you generate.
   *
   * @param Title The title you want to put on the page.
   *
   * @return A String containing the top portion of an HTML file.
   *
   */
  public static String HtmlTop(String Title)
  {
      String Top = new String();
      Top = "<html>\n";
      Top+= "<head>\n";
      Top+= "<title>\n";
      Top+= Title;
      Top+= "\n";
      Top+= "</title>\n";
      Top+= "</head>\n";
      Top+= "<body>\n";
      //
      //extra bits added by Bill Smart
      //
      Top+= "<p align=center><a href=http://www.scu.edu.au>";
      Top+= "Southern Cross University</a><br/> ";
      Top+= "Client/Server Systems - ISY00246";
     
      return Top;

  }

  /**
   *
   * Generate some vanilla HTML that you usually
   * want to include at the bottom of any HTML page you generate.
   *
   * @return A String containing the bottom portion of an HTML file.
   *
   */
  public static String HtmlBot()
  {
      return "</body>\n</html>\n";
  }

  /**
   *
   * Determine if the REQUEST_METHOD used to
   * send the data from the browser was the GET method.
   * @return true, if the REQUEST_METHOD was GET.  false, otherwise.
   *
   */
  public static boolean MethGet()
  {
     String RequestMethod = System.getProperty("cgi.request_method");
     boolean returnVal = false;

     if (RequestMethod != null)
     {
         if (RequestMethod.equals("GET") ||
             RequestMethod.equals("get"))
         {
             returnVal=true;
         }
     }
     return returnVal;
  }


  /**
   *
   * Determine if the REQUEST_METHOD used to
   * send the data from the browser was the POST method.
   * @return true, if the REQUEST_METHOD was POST.  false, otherwise.
   *
   */
  public static boolean MethPost()
  {
     String RequestMethod = System.getProperty("cgi.request_method");
     boolean returnVal = false;

     if (RequestMethod != null)
     {
         if (RequestMethod.equals("POST") ||
             RequestMethod.equals("post"))
         {
             returnVal=true;
         }
     }
     return returnVal;
  }

  /**
   *
   * Determine the Base URL of this script.
   * (Does not include the QUERY_STRING (if any) or PATH_INFO (if any).
   *
   * @return The Base URL of this script as a String.
   *
   */
  public static String MyBaseURL()
  {
      String returnString = new String();
      returnString = "http://" +
                     System.getProperty("cgi.server_name");
      if (!(System.getProperty("cgi.server_port").equals("80")))
          returnString += ":" + System.getProperty("cgi.server_port");
      returnString += System.getProperty("cgi.script_name");

      return returnString;
  }

  /**
   *
   * Determine the Full URL of this script.
   * (Includes the QUERY_STRING (if any) or PATH_INFO (if any).
   *
   * @return The Full URL of this script as a String.
   *
   */
  public static String MyFullURL()
  {
      String returnString;
      returnString = MyBaseURL();
      returnString += System.getProperty("cgi.path_info");
      String queryString = System.getProperty("cgi.query_string");
      if (queryString.length() > 0)
         returnString += "?" + queryString;
      return returnString;
  }

}
