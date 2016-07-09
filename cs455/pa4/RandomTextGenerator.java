// Name: ADIGUN OLAOLUWA 
// USC loginid: adigun
// CS 455 PA4
// Spring 2016

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 * RandomTextGenerator
 * 
 * This class controls the task of randomly generating text from a given
 * input file. 
 * 
 *  This class assumes that
 *  -- The input file is not empty
 *  -- The input file contains enough words for the desired size of output
 *  -- The new words are picked according to Markov text generation 
 */

public class RandomTextGenerator {

   /**
    * REPRESENTATION INVARIANTS
    * 
    * -- ArrayList allWords is non-empty i.e. input file is non-empty.
    * -- Map text is non-empty. 
    * -- For numOfWords,  (0 < numOfWords).
    * -- For prefixSize;  (0< prefixLength < allWords.size()).
    * -- The parameters numOfWords and prefixSize are integers.   
    */
   private Map<String, LinkedList<String>> text;
   private ArrayList<String> allWords;
   private Random gen;
   private int numOfWords;
   private int prefixSize;	
   
    private static final int DEBUG = 1;	
   /**
    * Constructs the RandomText Generator
    * @param in is the scanner object containing the content of input file.
    * @param length is the length of prefix to be considered for a new word. 
    * @param num is the expected number of words in the generated text.
    * PRE: 0 < numOfWords
    * 	   0 < prefixSize < allWords.size()
    * 	   allWords.size() != 0
    * 	   text.size() != 0.
   */
   public RandomTextGenerator(Scanner in, int length, int num){
      this.allWords = new ArrayList<String>();
      while(in.hasNext())
      {
         this.allWords.add(in.next());
      }
      this.gen = new Random();
      this.numOfWords = num;
      this.prefixSize = length;
      this.text = genMap(prefixSize, allWords);
   }
	
   /**
    * This is the public interface that randomly picks the text from the input file.
    * @param status specifies when the program should be in DEBUG mode or not.
    * @return list containing the details of words randomly selected form the text according
    * 		   to Markov word generation model. It contains information about each prefix, 
    * 		   its successors, and the word generated from the pool of successors. 
    * @throws BadInputException reports the error of bad input argument.
    */
   public LinkedList<LinkedList<String>> getText(int status) throws BadInputException{
		
      if (prefixSize >= allWords.size())	
      {
         throw new BadInputException("Words in the input file are less than prefix length.");
      }
      return helperGetText(allWords,text, gen,numOfWords,prefixSize, status);		
   }

   /**
    * This the helper function for getting the randomly generated text from the input file.
    * @param allWords contains the words in the input file
    * @param text contains the set of possible prefixes and corresponding next word
    * @param gen is the object of random class.
    * @param numOfWords is the number of expected words. 
    * @param prefixSize is the size of prefix for each selected word.
    * @param status specifies when the program should be in DEBUG mode or not.
    * @return the list containing detailed information about the words selected.
    */
    private static LinkedList<LinkedList<String>> helperGetText(ArrayList<String> allWords, Map<String, LinkedList<String>> text, Random gen, int numOfWords, int prefixSize, int status){
		
      LinkedList<LinkedList<String>> wordsPicked = new LinkedList<LinkedList<String>>();
      LinkedList<String> list = new LinkedList<String>();
		
      String preWord = generatePreWords(prefixSize, allWords, gen, status);
      Prefix currPrefix =  new Prefix(preWord);
      LinkedList<String> suffixes = currPrefix.getNextWord(text);
      String newWord = markovPick(suffixes,gen,status);
      helpUpdateList(currPrefix,list,suffixes,newWord);
      wordsPicked.addLast(list);	
      int size = 1;
      while (size < numOfWords)
      {
         list = new LinkedList<String>();
	 currPrefix = new Prefix(currPrefix.updatePrefix(newWord));
	 if (currPrefix.validPrefix(text)== false)
         {
	    list.addLast("");
	    wordsPicked.addLast(list);
	    list = new LinkedList<String>();
	    currPrefix = new Prefix(generatePreWords(prefixSize, allWords, gen, status ));
	 }
	 suffixes = currPrefix.getNextWord(text);
	 newWord = markovPick(suffixes,gen, status);
	 helpUpdateList(currPrefix,list,suffixes,newWord);
	 wordsPicked.addLast(list);
	 size++;
      }
      return  new LinkedList<LinkedList<String>>(wordsPicked);
   }
	
   /**
    * This is an helper function for writing a prefix, possible next words, and the chosen next word
    * into a LinkedList
    * @param currPrefix is the current prefix
    * @param list is the list that collects the details
    * @param suffixes is the list of possible words after the prefix 
    * @param newWord is the word chosen from the possible words 
    */
   private static void helpUpdateList(Prefix currPrefix, LinkedList<String> list, LinkedList<String> suffixes, String newWord){
      list.addLast(currPrefix.getPrefix());
      list.addLast(suffixes.toString());
      list.addLast(newWord);
   }

   /**
    * This static method random picks a prefix of size prefixSize from the input file.
    * 
    * @param prefixSize is the size of expected prefix.
    * @param allWords contains all the words in the input file.
    * @param gen is the random object from generating random numbers.
    * @param status specifies when the program should be in DEBUG mode or not.
    * @return the string of prefix generated.
    */
    private static String generatePreWords(int prefixSize, ArrayList<String> allWords, Random gen, int status){
		
      int num = gen.nextInt(allWords.size() - prefixSize);
      if(status == DEBUG)
      {
	  num = 0;
      }
      String newPrefix = allWords.get(num);
      for (int i = 1 ; i < prefixSize; i++)
      {
         newPrefix = newPrefix + " " +allWords.get(num+i) ;
      }
      return newPrefix;
   }
	
   /** 
    * This is a static method that reads the input file into a map. The keys are the possible
    * prefixes and the values are LinkedList of possible words after the prefix.
    * 
    * @param prefixSize is the size of the prefix.
    * @param allWords contains all the words in the input file.
    * @return the map of prefixes as the key and list of corresponding next words as the values.
    */
   private static Map<String, LinkedList<String>> genMap (int prefixSize,ArrayList<String> allWords){
		
      Map<String, LinkedList<String>> words = new HashMap<String, LinkedList<String>>();
      String thePrefix;
      for (int i = 0; i < allWords.size()- prefixSize; i++)
      {
         LinkedList<String> value = new LinkedList<String>();
         thePrefix = allWords.get(i);
	 for (int j = i+1; j < i + prefixSize; j++ )
	 {
	    thePrefix = thePrefix + " " + allWords.get(j);
	 }
	 if(words.containsKey(thePrefix)== true)
	 {
	    value = words.get(thePrefix);
	 }
	 value.addLast(allWords.get(i+prefixSize));
	 words.put(thePrefix, new LinkedList<String>(value));
      }
      return words;
   }

   /**
    * This method random picks a word from a list according to Markov word generation model.
    * 
    * @param prefixes contains all the possible words.
    * @param gen is the random object for random number generation.
    * @param status specifies when the program should be in DEBUG mode or not.
    * @return the chosen word according to the Markov model.
    */
    private static String markovPick(LinkedList<String> prefixes, Random gen, int status){
      ArrayList<String> arr = new ArrayList<String>(prefixes);
      if (status == DEBUG)
      {
	  return arr.get(0);
      }
      return arr.get(gen.nextInt(arr.size()));
   }
	
}
