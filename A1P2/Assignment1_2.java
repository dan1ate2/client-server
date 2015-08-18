package CGI2015;

/*
*  Assignment1-2 2015 - CGI(Java) program
*
*  Normally a comment goes here to tell you what
*  the program does.  In this case you have to figure it out for yourself.
*/

import java.util.*;
import java.io.*;


// object to hold info for stock

class MailData {

   private String name;
   private String email;
   private boolean privacy;
   private int age;


   MailData(String nm, String em, boolean pr, int ag){
      name = nm;
      email = em;
      privacy = pr;
      age = ag;  // in cents
      }

   public String getName() {return name;}
   public String getEmail() {return email;}
   public boolean getPrivacy() {return privacy;}
   public int getAge() {return age;}
}


class Assignment1_2 {

   private static final String dataFile = "maillist.data"; // static database
   private static HashMap<String,MailData> database;  	// dynamic database


   public static boolean loadDatabase() {

      File db = new File(dataFile);
      database=new HashMap<String, MailData>();
      String name, email;
      boolean privacy;
      int age;

      if(!db.exists())
         return false;

      try{
         BufferedReader inf = new BufferedReader(new FileReader(db));

         while ((name=inf.readLine()) != null){
            email=inf.readLine();
            privacy=Boolean.parseBoolean(inf.readLine());
            age=Integer.parseInt(inf.readLine());
            MailData md = new MailData(name, email, privacy, age);
            database.put(email, md);
            }

         inf.close();
         }
      catch (Exception e){
         printError(e.toString());
         return false;	// error occurred
         }
      return true;		// successful load
      }//end loadDatabase



   public static void storeDatabase(){

      File db = new File(dataFile);
      File tmp = new File(dataFile+".tmp");
      String email;
      MailData sd;

      try{
         PrintWriter outf =
            new PrintWriter(
               new BufferedWriter(
                  new FileWriter(tmp)));
         Iterator<String> itr = database.keySet().iterator();


         while (itr.hasNext()) {
            email = itr.next();
            sd = (MailData) database.get(email);
            outf.println(sd.getName());
            outf.println(email);
            outf.println(sd.getPrivacy());
            outf.println(sd.getAge());
            }
         outf.close();
         db.delete();
         tmp.renameTo(db);
         }
      catch (Exception e) {
         printError("Error during database update<br>"+e.toString());
         }
   }//end storeDatabase


   private static void printError(String s){
      System.out.println("<br>");
      System.out.println("<h3>"+s+"</h3>");
      System.out.println("<br>");
      }//end printError


   public static void queryMail(HashMap<String,String> f_data){

      String name, email;
      boolean privacy;
      int age;
      MailData md;

      System.out.println("<hr><br>");
      System.out.print("<p align=center><font color=#FF0000><b>");
      System.out.println("Result: Search by E-mail address</b></font></p>");
      System.out.println("<hr><br>");

      email = ((String) f_data.get("email")).toLowerCase();

      if (email==null || email.equals(""))
         printError("Form error: No email given for search");
      else{
         md = (MailData) database.get(email);

         if (md == null)
            printError("That email not in database");
         else{
            name = md.getName();
            privacy = md.getPrivacy();
            age = md.getAge();
            System.out.println("<br><h3>Search result:</h3>");
            System.out.print("<br><b>Name: </b>" + name);
            System.out.print("<br><b>E-mail: </b>" + email);
            System.out.print("<br><b>Privacy: </b>" + privacy);
            System.out.println("<br><b>Age: </b>" + age + "<hr><br>");
            }
         }
     }//end queryShare



   public static void doUpdate(HashMap<String,String> f_data){
      String name, email;
      boolean privacy;
      int age;

      System.out.println("<hr><br>");
      System.out.print("<p align=center><font color=#FF0000><b>");
      System.out.println("Result: Update Database Request</b></font></p>");
      System.out.println("<hr><br>");

      // get info from form
      email = ((String) f_data.get("email")).toLowerCase();
      name = (String) f_data.get("name");
      privacy = ((String) f_data.get("privacy")).equals("true");
      try {
         age = Integer.parseInt((String) f_data.get("age"));
          }
      catch (NumberFormatException nfe) {
          printError("Holding field is not a number");
          return;
      }

      if(email.equals(""))
         printError("Must specify code.");
      else if(database.get(email) != null)
         printError("Error: email already exists.");
      else if(name.equals(""))
         printError("Must enter a name");
      else if(age == 0)
         printError("Must have a number age.");
      else{
         MailData md = new MailData(name, email, privacy, age);
         database.put(email,md);
         storeDatabase();
         System.out.println("e-mail: "+ email + " added to database.");
         }
      } //end doUpdate


   public static void doDelete(HashMap<String,String> f_data) {

      System.out.println("<hr><br>");
      System.out.print("<p align=center><font color=#FF0000><b>");
      System.out.println("Result: Delete Request</b></font></p>");
      System.out.println("<hr><br>");

      String email = ((String) f_data.get("email")).toLowerCase();

      if(email.equals(""))
         printError("No email entered.");
      else if (database.get(email) == null)
         printError("Error: This e-mail ("+email+") is not in the database");
      else{
         database.remove(email);
         storeDatabase();
         System.out.println("email: " + email + " removed from database.");
         }
      }//end doDelete



   public static void doPrintAll(){

      Iterator<String> itr = database.keySet().iterator();
      int counter = 0;
      String name, email;
      boolean privacy;
      int age;
      MailData md;

      System.out.println("<hr>");
      System.out.print("<p align=center><font color=#FF0000><b>");
      System.out.println("Result: Print all Records</b></font></p>");
      System.out.println("<hr><br>");

      while (itr.hasNext()){ //work way through database
         email = itr.next();
         md =(MailData) database.get(email);
         name = md.getName();
         privacy = md.getPrivacy();
         age = md.getAge();
         System.out.print("<b>Name: </b>" + name);
         System.out.print("<br><b>E-mail: </b>"+email);
         System.out.print("<br><b>Privacy: </b>" + privacy);
         System.out.println("<br><b>Age: </b>" + age + "<hr><br>");
         counter++;
         }
      System.out.println("Total e-mail addresses in database = " + counter);
      } // end doPrintAll



   public static void main(String args[]){

      // Print the required CGI header.
      System.out.println(CGI.Header());
      System.out.println(CGI.HtmlTop("Assigment 1, Part 2a, Understanding CGI/form/Java."));

      // Must have a POST method!
      if(CGI.MethGet())
         printError("Error: must use POST request");
      else{
         //  Parse the form data into a Hashtable.

         HashMap<String, String> form_data = CGI.ReadParse(System.in);
         if (!loadDatabase())	// load file data
             printError("Error loading database<br>");
         else{
            // check form data is valid
            String action = (String) form_data.get("action");
            // process all of the different operations
            if(action.equals("Search"))
               queryMail(form_data);
            else if (action.equals("Add"))
               doUpdate(form_data);
            else if (action.equals("Remove"))
               doDelete(form_data);
            else if (action.equals("PrintAll"))
               doPrintAll();
            else
               printError("Error: invalid action field");
            }
         }
      // Create the Bottom of the returned HTML page
      System.out.println(CGI.HtmlBot());
      } //end main
} //end class Assignment1_2
