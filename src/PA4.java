// PA4
// Authors: Sadie Henry, Gabriella Fontani
// Date: 11/13/2014
// Class: CS200

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class PA4 {

	public static void main(String[] args) 
	{
		//arraylist for the file names
		ArrayList<String> fileNames = new ArrayList<String>();
		//arraylist for words on which you should run whichPages
		ArrayList<String> whichPagesWords = new ArrayList<String>();

		try{
			//Read in the file
			Scanner scanFile = new Scanner(new File(args[0]));

			//tell the scanner to scan file names until the flag
			while(true){

				//scan in the line
				String s = scanFile.nextLine();

				if(s.equals("*EOFs*")){
					break;
				}

				else{
					fileNames.add(s);
				}
			}

			//eat the extra newline
			//scanFile.nextLine();

			//read in the words for whichPages
			while(scanFile.hasNext()){
				String s = scanFile.nextLine();                        
				whichPagesWords.add(s);
			}	
			
			scanFile.close();
		}

		catch(Exception e){
			System.out.println("Error: " + e);
			System.exit(0);
		}

		//create the WebPages object
		WebPages webPages = new WebPages();

		//add the new pages
		for(int i = 0; i < fileNames.size(); i++){
			webPages.addPage(fileNames.get(i));
		}

		//print terms
		webPages.printTerms();

		//run whichPages method
		for(int i = 0; i < whichPagesWords.size(); i++){
			
			//String array of pages in which the word occurs
			String[] array = webPages.whichPages(whichPagesWords.get(i).toLowerCase());
		
			//String to be printed for each whichPages word
			String s = whichPagesWords.get(i);
			
			//print the depth
			webPages.printDepth(s);

			//if the word isn't found, return word not found
			if(array == null){
				s += " not found";
			}

			//otherwise, return the word and the pages it occurs on + the TFIDF for that page
			else{
				
				//String to print
				s += " in pages: ";
				double d;
				DecimalFormat df = new DecimalFormat("0.00");
				
				for(int j = 0; j < array.length-1; j++){
					d = webPages.TFIDF(array[j], whichPagesWords.get(i).toLowerCase());
					s += array[j] + ": " + df.format(d) + ", ";
				}

				d = webPages.TFIDF(array[array.length-1], whichPagesWords.get(i).toLowerCase());
				s += array[array.length-1] + ": " + df.format(d);
			}
			
			System.out.println(s);
		}
	}
}