// PA3
// Authors: Sadie Henry, Melinda Ryan 
// Date: 10/17/2014
// Class: CS200

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WebPages 
{
	//Instance variable for binary search tree of Terms
	private BST termIndex;
	
	//instance variable for the number of pages read in
	private int pageCount;

	//initializes a new index, a binary search tree of Term
	public WebPages()
	{
		termIndex = new BST();
	}

	//reads in the page in filename, divides it into words as before 
	//and adds those words and their counts to the termIndex. 
	public void addPage(String filename)
	{
		try
		{
			//increment the pageCount
			pageCount++;
			
			//read line-by-line through the file to get words
			Scanner readFile = new Scanner(new File(filename));
			while(readFile.hasNextLine())
			{
				//read in a line
				String line = readFile.nextLine();

				//remove HTML tags from the line
				line = stripHTML(line);

				//delimit by everything but letters, numbers, ', and <>
				Scanner readLine = new Scanner(line).useDelimiter("[^\\w'<>]+");

				while(readLine.hasNext())
				{
					//set the line to lowercase
					String word = readLine.next().toLowerCase();

					//add the word to the TermIndex
					addToTermIndex(word, filename);					

				}

				readLine.close();
			}

			readFile.close();
		}
		catch(IOException e)
		{
			System.out.println("Error: Unable to read file");
		}

	}
	
	public void printDepth(String word)
	{
		//get term depth in binary tree
		termIndex.get(word, true);

	}

	//prints on a separate line each word followed by two spaces followed by its 
	//frequency in the order in which it is stored in the ArrayList (as in PA1).
	public void printTerms()
	{
		System.out.println(toListString());	
	}

	//returns required output string for arraylist
	public String toListString()
	{
		String outputString = "WORDS\n";
		//check for an empty tree
		if(termIndex.size() == 0)
			return "Error: Empty List";
		else
		{
			BSTIterator itr = new BSTIterator(termIndex);
			while(itr.hasNext())
			{
				outputString += ((itr.next()).getName() + "\n");
			}		
		}

		return outputString; 
	}

	//method to strip HTML tags out of a string
	public String stripHTML(String a)
	{
		return a.replaceAll("<.*?>", "");
	}

	//adds words to the term tree
	public void addToTermIndex(String word, String document)
	{
		termIndex.add(document, word);
	}
	
	//TFIDF method
	public double TFIDF(String document, String word)
	{
		//get the term for that word
		Term term = termIndex.get(word, false);
	
		
		float TF = (float) term.getTermFrequency(document);
		//System.out.println("Word: " + word + " Document: " + document + " TF: " + TF);
		float D = pageCount;
		//System.out.println("Word: " + word + " Document: " + document + " D: " + D);
		float DF = term.getDocFrequency();
		//System.out.println("Word: " + word + " Document: " + document + " DF: " + DF);
		
		return TF * Math.log(D / DF);
	}

	//whichPages method
	public String[] whichPages(String word){

		//make a new term to compare to the term index
		Term newTerm = new Term(word);
		
		//search through term index
		if(termIndex.contains(newTerm)){
			
			//get the listOfFileNames for that term
			ArrayList<String> arrayList = termIndex.get(word, false).getListOfFileNames();
			
			//copy array list to string array
			String[] stringArray = new String[arrayList.size()];
			for(int i = 0; i < arrayList.size(); i++){
				stringArray[i] = arrayList.get(i);
			}
			
			//return array
			return stringArray;
			
		}
		
		else{
			return null;
		}
		
	}







}