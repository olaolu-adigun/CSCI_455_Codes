// Name: ADIGUN OLAOLUWA 
// USC loginid: adigun
// CS 455 PA4
// Spring 2016

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Arrays;
import java.io.IOException;

/** 
 * 
 * GenText Class 
 * The program reads in an input file and then generate a text from the
 * input. The user specifies the number of prefix, number of words, input file,
 * and output file.
 * 
 * How to call the program from command line.
 * 
 * DEBUG MODE: java GenText [-d] prefixLength numWords sourceFile outFile 
 * NON-DEBUG MODE: java GenText prefixLength numWords sourceFile outFile 
 *
 * The program stores the randomly generated text into outFile for both 
 * DEBUG and NON-DEBUG mode.
 */

public class GenText {

   static final int DEBUG = 1;
   static final int NON_DEBUG = 0;

   /**
    * Takes the input argument from command line and executes the program accordingly
    * @params args contains all the input arguments from command line.
    */
   public static void main(String[] args)  {
      try {
             if ((args[0] == "-d" && args.length < 5) ||(args[0] != "-d" && args.length < 4))
	     {
	        throw new BadInputException("Bad Input: Missing command-line arguments. \nRight Input: java GenText [-d] prefixLength numWords sourceFile outFile ");
	     }
	     else
	     {
	        if (args[0].equals("-d"))
		{
		   args =  Arrays.copyOfRange(args,1, 5);
		   writeToOutputDebug(textFinder(args, DEBUG), args);
		}
		else
		{
		    writeToOutput(textFinder(args, NON_DEBUG),args);
		}
	     }
      }
      catch (BadInputException exec) {
         System.out.println("Bad Input: " + exec.getMessage());
      }
      catch (FileNotFoundException exec){
         System.out.println("Bad Input: Source file does not exist");
      }
      catch (NumberFormatException exec){
         System.out.println("Bad Input: Prefix length or number of words is not an integer. \nRight Input: java GenText [-d] prefixLength numWords sourceFile outFile");
      }
      catch (IOException exception) {
         exception.printStackTrace();
      }
   }
	
   /**
    * This is the helper function that generates the random text from the input file.
    * @param args is an array containing the input arguments for this program  
    * @param status specifies when the program should be in DEBUG mode or not.
    * @return LinkedList of LinkedList<String> containing information about each word
    * 	      word picked from the input text. 
    * @throws IOException
    */
    private static LinkedList<LinkedList<String>> textFinder (String[] args, int status) throws IOException{
		
      File input = new File (args[2]);
      Scanner in = new Scanner(input);
			
      int prefixLength = Integer.parseInt(args[0]);
      int numWords = Integer.parseInt(args[1]);	
      if (prefixLength < 1)
      {
         throw new BadInputException("Bad Input: Prefix length is less than 1. \nRight Solution: java GenText [-d] prefixLength numWords sourceFile outFile ");
      }
      if (numWords < 0)
      {
         throw new BadInputException("Number of words is less than 0. \nRight Solution: java GenText [-d] prefixLength numWords sourceFile outFile ");
      }
      RandomTextGenerator myTextGenerator = new RandomTextGenerator(in, prefixLength, numWords);
      LinkedList<LinkedList<String>> list = myTextGenerator.getText(status);
      return list;
   }
	
   /**
    * This is an helper function that writes the output of NON-DEBUG mode into the output file.
    * @param args contains information about the output file
    * @param list contains the information about the text generated  
    * @throws FileNotFoundException for errors due to writing into the output file
    */
   private static void writeToOutput ( LinkedList<LinkedList<String>> list, String[] args) {
      ListIterator<LinkedList<String>> iter = list.listIterator();
      int length = 0;
      String nextWord;
      try{
         PrintWriter out = new PrintWriter(args[3]);
	 LinkedList<String> list1 = new LinkedList<String>();	
	 while (iter.hasNext())
	 {
	    list1 = iter.next();
	    nextWord = list1.getLast();
	    if (nextWord != "")
	    {
	       length = length + nextWord.length() + 1;
	       if (length < 80)
	       {
	          out.printf("%s ", nextWord);
	       }
	       else
	       {
	          out.printf("\n%s ", nextWord);
		  length = nextWord.length()+ 1;
	       }
	    }
	 }
         out.close();
      }
      catch(IOException exec){
         System.out.println("Cannot write to the output file");
      }
   }
	
   /**
    * This static method writes the randomly generated file into the output file for DEBUG mode.
    * It uses the helper function helperWriteDebug to write the output file in DEBUG mode.
    * @param list contains information about the randomly generated text 
    * @param args contains information about the output file
    */
   private static void writeToOutputDebug ( LinkedList<LinkedList<String>> list, String[] args){
		
      try {
             helperWriteDebug(list,args);
      }
      catch (IOException exec){
	 System.out.println("Cannot write to the output file");
      }
   }
	
   /**
    * This is the helper function that writes the randomly selected text into the output file 
    * @param list contains information about the randomly generated text 
    * @param args contains information about the output file
    * @throws IOException handles error due to writing into the output file
    */
   private static void helperWriteDebug(LinkedList<LinkedList<String>> list, String[] args) throws IOException{
      ListIterator<LinkedList<String>> iter = list.listIterator();
      LinkedList<String> list1 = new LinkedList<String>();
      PrintWriter out = new PrintWriter(args[3]);
      int sentinel = 0;
      while(iter.hasNext())
      {
         list1 = iter.next();
	 String word = list1.getFirst();
	 if(word == "")
	 {
	    out.printf("DEBUG: successors: <END OF FILE> \n");
	 }
	 else
	 {
	    ListIterator<String> iter1 = list1.listIterator();
	    if (sentinel == 0)
	    {
	       out.printf("DEBUG: choose a new initial prefix: %s\n", word);
	    }	
	    out.printf("DEBUG: prefix: %s\n", iter1.next());
	    out.printf("DEBUG: successors: %s\n" , iter1.next());
	    out.printf("DEBUG: word generated: %s\n" ,iter1.next());
	 }
	 sentinel++;
      }
      out.close();
   }
}
