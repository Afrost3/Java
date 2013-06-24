/*
* Andrew Frost
* 6/23/13
* J. L. Bhola
* Assignment 2
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.util.regex.*;

/*
 * The purpose of this program is to get input from the user that has the name
 * of a file and then read through the file counting the amount of words
 * lines, alphanumeric characters, sentences, vowels, and punctuation.
 * If the file is empty it will tell you so and end. The program then outputs
 * the data to a file named output.txt in the same directory that the input
 * file is from.
 */
public class Wordcount {
    /*
     * This is where the counters are declared.
     */
    static int wordcount;
    static int linecount;
    static int alphacount;
    static int sentencecount;
    static int vowelcount;
    static int punctcount;
    

    public static void main(String[] args) {
        /*
         * These next few lines make a scanner with which to get the users input
         * and then set it equal to the string ans and pass it to the read 
         * method
         */
        Scanner bar = new Scanner(System.in);
        String ans;
        System.out.println("Which file would you like to open?");
        ans = bar.next();
        read(ans);
        /*
         * These if statements are used to deal with the different situations
         * that the user input file could present
         * The first if statement is for if the input the user gives is not a
         * file. The second is for an empty file and the third is for when there
         * is a file with lines and so it continues on and then does the 
         * writetofile method.
         */
        if(linecount == -1)
        System.out.println("Please enter a correct filename/path next time");
        else if(linecount == 0)
        System.out.println("The input file is empty");
        else{
        System.out.println("The number of words is: " + wordcount);
        System.out.println("The number of line is: " + linecount);
        System.out.println("The number of characters is: " + alphacount);
        System.out.println("The number of sentences is: " + sentencecount);
	System.out.println("The number of vowels is: " + vowelcount);
        System.out.println("The number of punctuation is: " + punctcount);
        WritetoFile();
        }
    }
    /*
     * This is the read method and uses the bufferedreader to read the input
     * file one line at a time
     */
    public static void read(String file){
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			String sCurrentLine;
 /*
  * This is where the file is looped through line by line and the methods are 
  * passed the currentline to check and update counts
  */ 
			while ((sCurrentLine = br.readLine()) != null) {
                                linecount++;
                                getLetterCount(sCurrentLine);
                                getSentenceCount(sCurrentLine);
                                getVowelCount(sCurrentLine);
                                getPunctCount(sCurrentLine);
                                getWordCount(sCurrentLine);
			}
 
		} catch (IOException e) {
                    //This is can catch if the file was not found
                    linecount = -1;
                    System.out.println(e);
		} 
    
    }
    
  /*
   * This is the method for getting the word count and works even if there
   * are multiple spaces and leading trailing spaces or blank lines
   * its makes use of the trim method for strings.
   */
  public static void getWordCount(String currentline){
  String trimmed = currentline.trim();
  wordcount += trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
          
}
  /*
   * This is the method that gets the letter count using regex and going through
   * each character in the string one at a time and if the character is a letter
   * or number it increases the alpha count
   */ 
  public static void getLetterCount(String currentline){   
  Pattern p = Pattern.compile("\\w");  
  Matcher m;  
  for (int i=0; i<currentline.length(); i++)
  {
    m = p.matcher(Character.toString(currentline.charAt(i)));  
          if(m.find()){  
              alphacount++; 
          }
  }
}

  /*
 * This is the method that gets the sentence count using regex and going through
 * each character in the string one at a time and if the character is a 
 * period exclamation or question mark then it increments the sentence count
  */ 
   public static void getSentenceCount(String currentline){
  Pattern p = Pattern.compile("[\\.\\?!]");  
  Matcher m;  
  for (int i=0; i<currentline.length(); i++)
  {
    m = p.matcher(Character.toString(currentline.charAt(i)));  
          if(m.find()){  
              sentencecount++; 
          }
  }
}
  /*
   * This is the method that gets the vowel count using regex and going through
   * each character in the string one at a time and if the character is one of 
   * the vowels it increments the count by 1
   */ 
      public static void getVowelCount(String currentline){
  Pattern p = Pattern.compile("[aeiouAEIOU]");  
  Matcher m;  
  for (int i=0; i<currentline.length(); i++)
  {
    m = p.matcher(Character.toString(currentline.charAt(i)));  
          if(m.find()){  
              vowelcount++; 
          }
  }
}
      
  /*
   * This is the method that gets the punctuation count using regex and going 
   * through each character in the string one at a time and if the character is
   * not a letter digit or space then it increments the punctuation count
   */ 
      public static void getPunctCount(String currentline){
  Pattern p = Pattern.compile("[^\\w\\d\\s]");  
  Matcher m;  
  for (int i=0; i<currentline.length(); i++)
  {
    m = p.matcher(Character.toString(currentline.charAt(i)));  
          if(m.find()){  
              punctcount++; 
          }
  }
}
      /*
       * This is the method for writing to the file the first couple lines
       * set strings equal to the text that I want to be displayed 
       * in the out put file. It then checks if the output file exists and if
       * not it then creates a new one.
       */
      public static void WritetoFile(){
	try {
 
	String words = "The number of words in the file: " + wordcount;
        String lines = "The number of lines is: " + linecount;
        String chars = "The number of characters is: " + alphacount;
        String sentences = "The number of sentences is: " + sentencecount;
        String vowels = "The number of vowels is: " + vowelcount;
        String punctuations = "The number of punctuation is: " + punctcount;
                        
	File file = new File("/output.txt");
 
	// if file doesnt exists, then create it
	if (!file.exists()) {
		file.createNewFile();
	}
        /*
         * This uses the filewriter and the bufferedwriter to write the strings
         * into the output file and uses try catch for any problems that may 
         * happen.
         */
	FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    try (BufferedWriter bw = new BufferedWriter(fw)) {
                        bw.write(words);
                        bw.newLine();
                        bw.write(lines);
                        bw.newLine();
                        bw.write(chars);
                        bw.newLine();
                        bw.write(sentences);
                        bw.newLine();
                        bw.write(vowels);
                        bw.newLine();
                        bw.write(punctuations);
                    } catch (IOException e) {
                      System.out.println(e);
		    }
 
			System.out.println("Done");
 
		} catch (IOException e) {
                    System.out.println(e);
		}
	}
    
    
}
