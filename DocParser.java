package docParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class DocParser {

	public TreeSet<String> readFile(String fileName) {		
		
		TreeSet<String> wordList=new TreeSet<String>();
		
		try {
			FileInputStream fis=new FileInputStream(fileName);
			BufferedReader br=new BufferedReader(new InputStreamReader(fis));			
			String line;
			String[] words;
			
			while((line=br.readLine())!=null)
			{
				words=line.split("[\\W]+");				//split on any none-letter character
				for (String word:words)
					wordList.add(word.toLowerCase());   //handles case sensitive
			}		
			br.close();
			
		} catch (IOException e) {
			System.out.println("File does not exist, please check file path and name.");
		}	
		return wordList;
	}
	
	public void writeFile(TreeSet<String> wordList) throws FileNotFoundException, UnsupportedEncodingException{	
		
		PrintWriter writer=new PrintWriter("output.txt","UTF8");
		Iterator<String> itr=wordList.iterator();
		
		while(itr.hasNext())
			writer.println(itr.next());						//writes to file output.txt
		
		writer.close();
		
		System.out.println("File created.");
	}
	
	public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {	
		
		Scanner sc=new Scanner(System.in);
		DocParser parser=new DocParser();
		
		System.out.println("Enter absolute path of file or \"quit\": ");
		String line=sc.next();
		
		while(!(line.equals("quit")))
		{
			parser.writeFile(parser.readFile(line));
			System.out.println("Enter absolute path of file or \"quit\": ");
			line=sc.next();
		}
		sc.close();
	}
}
