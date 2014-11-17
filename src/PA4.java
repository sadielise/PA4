// PA4
// Authors: Sadie Henry, Gabriella Fontani
// Date: 11/13/2014
// Class: CS200

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PA4 {

	public static void main(String[] args){

		try {
			Scanner scan = new Scanner(new File(args[0]));

			//scan in the size of the hash table
			int tempInt = scan.nextInt();
			//initialize WebPages with the int
			WebPages pages = new WebPages(tempInt);
			//clear scanner
			scan.nextLine();
			
			//read in the files to be added
			String tempString = scan.nextLine();
			//while there are more files to be read in
			while(!tempString.equals("*EOFs*")){
				//add the page
				pages.addPage(tempString);
				//get the name of the next page from the scanner
				tempString = scan.nextLine();
			}

			//remove the stop words
			tempString = scan.nextLine();
			//while there are more stop words to prune
			while(!tempString.equals("*STOPs*")){
				//prune the stop word
				pages.pruneStopWords(tempString);
				//get the next word to be pruned
				tempString = scan.nextLine();
			}
			
			//print the terms
			pages.printTerms();
			
			
 			//best pages!!
			String[] docs;

			while(scan.hasNext()){
				String word = scan.nextLine();
				docs = pages.whichPages(word.toLowerCase());
				if(docs==null){
					System.out.println(word + " not found");
				}
				else{
					String documents =  Arrays.toString(docs);
					System.out.println(word + " in pages: " +documents.substring(1, documents.length()-1));
				}

			}
			scan.close();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		
	}
}