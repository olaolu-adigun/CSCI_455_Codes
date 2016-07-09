// Name: ADIGUN OLAOLUWA 
// USC loginid: adigun
// CS 455 PA4
// Spring 2016

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;


/**
 * Prefix class
 * Prefix class represents a set of words that forms a Prefix object.
 * 
 * This class assumes that
 * -- The prefix is extracted from the text in map text
 * -- All the prefix in text have same length of words
 */
public class Prefix {

   /**
   * REPRESENTATION INAVRIANT
   * -- prefix cannot be "" or " ". 
   */
   private String prefix;

   /**
   * Constructs Prefix object and initializes its instance variables
   *  
   * @param thePrefix is a string containing some words. 
   * PRE: thePrefix is non-empty i.e. neither " " nor ""
   */
   public Prefix (String thePrefix){
      this.prefix = thePrefix.trim();
   }
	
   /**
   * This method returns the prefix of Prefix object
   * @return prefix
   */
   public String getPrefix(){

       return prefix;
   }
	
   /**
   * Checks the validity of the Prefix object.
   * @param text is the map containing all valid prefixes
   * @return whether the Prefix object is valid or not.
   * PRE: Map text is non-empty
   */
    public boolean validPrefix(Map<String,LinkedList<String>> text ){ 
       return text.containsKey(prefix);
   }
	
   /**
   * This method gets the word that comes after the prefix.
   * @params text is the map containing all prefixes and list of possible words after each.
   * @return list of possible suffixes for the prefix.
   * PRE: Map text is non-empty.
   *      prefix is a key on  map text.
   */
    public LinkedList<String> getNextWord(Map<String,LinkedList<String>> text){

      LinkedList<String> list = text.get(prefix);
      return list;
   }
   /**
   * This method concatenates the prefix and the next word and removes
   * first word of the prefix. 
   * @param newWord is the word to be add to the prefix
   * @return the updated word
   * PRE: newWord must be non-empty.
   * 	  newWord is not space " ".
   */
   public String updatePrefix(String newWord){
      Scanner in =  new Scanner(getPrefix());
      in.next();
      String update = "";
      if(in.hasNext())
      {
         update = in.next();
      }
      while (in.hasNext())
      {
         update = update + " " + in.next();
      }
      in.close();
      update = update + " " + newWord;
      return update;
   }
		
}
