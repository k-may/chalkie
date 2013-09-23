/**
 *  A class to simplify keyboard input.
 *  Abstracts the Scanner class provided in the Java SDK.
 */
public class Keyboard
{
   private static Keyboard kb = new Keyboard();

   /** The SDK provided Scanner object, used to obtain keyboard input */
   private java.util.Scanner scan;

   private Keyboard()
   {
      scan = new java.util.Scanner(System.in);
   }

   public static Keyboard getKeyboard()
   {
      return kb;
   }
   
   /**
    *  Reads an integer from the keyboard and returns it. <br>
    *  Uses the provided prompt to request an integer from the user.
    */
   public int readInt(String prompt)
   {
      System.out.print(prompt);
      int num = 0;

      try
      {
         num = scan.nextInt();
         readString("");  //clear the buffer
      }
      catch (java.util.InputMismatchException ime)  //wrong type inputted
      {
         readString("");  //clear the buffer
         num = 0;
      }
      catch (java.util.NoSuchElementException nsee)  //break out of program generates an exception
      {
         readString("");  //clear the buffer
         num = 0;
      }
      return num;
   }

   /**
    *  Reads a double from the keyboard and returns it. <br>
    *  Uses the provided prompt to request a double from the user.
    */
   public double readDouble(String prompt)
   {
      System.out.print(prompt);
      double num = 0.0;

      try
      {
         num = scan.nextDouble();
         readString("");  //clear the buffer
      }
      catch (java.util.InputMismatchException ime)
      {
         readString("");  //clear the buffer
         num = 0;
      }
      catch (java.util.NoSuchElementException nsee)
      {
         readString("");  //clear the buffer
         num = 0;
      }

      return num;
   }

   /**
    *  Reads a line of text from the keyboard and returns it as a String. <br>
    *  Uses the provided prompt to request a line of text from the user.
    */
   public String readString(String prompt)
   {
      System.out.print(prompt);
      String str = "";

      try
      {
         str = scan.nextLine();
      }
      catch (java.util.NoSuchElementException nsee)
      {
         readString("");  //clear the buffer
         str = "";
      }

      return str;
   }
}
