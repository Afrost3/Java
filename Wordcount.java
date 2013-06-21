/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;
import java.io.FileWriter;
import java.io.File;
/**
 *
 * @author Andrew
 */
public class Wordcount {
    static int linecount;
    static int alphacount;
    static int sentencecount;
    static int vowelcount;
    static int punctcount;
    static int wordcount;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner br = new Scanner(System.in);
        String ans;
        System.out.println("Which file would you like to open?");
        ans = br.next();
        read(ans);
        if(linecount == 0)
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
    
    public static void read(String file){
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
 
			String sCurrentLine;
 
			while ((sCurrentLine = br.readLine()) != null) {
                                linecount++;
				System.out.println(sCurrentLine);
                                getLetterCount(sCurrentLine);
                                getSentenceCount(sCurrentLine);
                                getVowelCount(sCurrentLine);
                                getPunctCount(sCurrentLine);
                                getWordCount(sCurrentLine);
			}
 
		} catch (IOException e) {
		} 
    
    }
    
  public static void getWordCount(String currentline){
  String trimmed = currentline.trim();
  wordcount += trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
          
}
    
  public static void getLetterCount(String currentline){   
  Pattern p = Pattern.compile(".*\\w+.*");  
  Matcher m = null;  
  for (int i=0; i<currentline.length(); i++)
  {
    m = p.matcher(Character.toString(currentline.charAt(i)));  
          if(m.find()){  
              alphacount++; 
          }
  }
}

   public static void getSentenceCount(String currentline){
  Pattern p = Pattern.compile("[\\.\\?!]");  
  Matcher m = null;  
  for (int i=0; i<currentline.length(); i++)
  {
    m = p.matcher(Character.toString(currentline.charAt(i)));  
          if(m.find()){  
              sentencecount++; 
          }
  }
}
   
      public static void getVowelCount(String currentline){
  Pattern p = Pattern.compile("[aeiouAEIOU]");  
  Matcher m = null;  
  for (int i=0; i<currentline.length(); i++)
  {
    m = p.matcher(Character.toString(currentline.charAt(i)));  
          if(m.find()){  
              vowelcount++; 
          }
  }
}
      public static void getPunctCount(String currentline){
  Pattern p = Pattern.compile("[^\\w\\d\\s]");  
  Matcher m = null;  
  for (int i=0; i<currentline.length(); i++)
  {
    m = p.matcher(Character.toString(currentline.charAt(i)));  
          if(m.find()){  
              punctcount++; 
          }
  }
}
      
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
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
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
                        bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    
}
