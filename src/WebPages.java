// PA4
// Authors: Sadie Henry, Gabriella Fontani 
// Date: 11/19/2014
// Class: CS200

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class WebPages 
{
	//Instance variable for hash table of Terms
	private HashTable termIndex;

	//instance variable for the number of pages read in
	private int pageCount;

	// variables for highest sim value
	String highestSimString;
	double highestSimVal = 0.0;

	//instance variable for the names of pages read in
	private ArrayList<String> fileNames = new ArrayList<String>();

	//initializes a new index, a binary search tree of Term
	public WebPages(int hashSize)
	{
		termIndex = new HashTable(hashSize);
	}

	//reads in the page in filename, divides it into words as before 
	//and adds those words and their counts to the termIndex. 
	public void addPage(String filename)
	{
		try
		{
			//increment the pageCount
			pageCount++;

			//add the file name to arraylist
			fileNames.add(filename);

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
			HashTableIterator itr = new HashTableIterator(termIndex);
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
	private double TFIDF(String document, String word)
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

	// Wi,q method
	private double WIQ(String word){

		// get the term
		Term temp = termIndex.get(word, false);

		// get the number of docs that contain word
		int dfi = temp.getDocFrequency();

		return (.5)*(1 + Math.log(pageCount/dfi));
	}

	private String[] sortArray(String[] array){

		for(int i = 0; i < array.length; i++){

			// if the item is greater than the one after it, swap
			if(array[i].compareTo(array[i+1]) > 0){
				String temp = array[i];
				array[i] = array[i+1];
				array[i+1] = temp;
			}
		}

		return array;
	}

	private void setZero(double[] array){

		for(int i = 0; i < array.length; i++){
			array[i] = 0.0;
		}
	}


	/**
	 * remove word from the hash table
	 * @param word the word that needs to be removed from the hash table
	 */
	public void pruneStopWords(String word) {
		termIndex.delete(word);

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

	// compute cosine similarity
	public void bestPages(String query){

		// array list for individual terms in a query
		ArrayList<String> queryList = new ArrayList<String>();

		// scanner to ready query
		Scanner scan = new Scanner(query);

		// read each term of the query into the ArrayList
		while(scan.hasNext()){
			String s = scan.next();
			queryList.add(s);
		}

		// sort query list
		String[] queryListArray = new String[queryList.size()];
		queryListArray = (String[])queryList.toArray();
		queryListArray = sortArray(queryListArray);

		/* array that supports mapping b/w positions in the component
		   arrays and which documents are being referenced */
		String[] docs = new String[pageCount];
		docs = (String[])fileNames.toArray();

		// sort docs array
		docs = sortArray(docs);

		// array that keeps the numerators
		double[] common = new double[pageCount];
		// set all positions to 0.0
		setZero(common);

		// array that keeps the first summation in the denominators
		double[] docSpecific = new double[pageCount];
		// set all positions to 0.0
		setZero(docSpecific);

		// variable for second summation in the denominator (scalar)
		Double queryWeights = 0.0;

		// traverse over the term index
		for(int j = 0; j < termIndex.size(); j++){

			// get temp term
			Term temp = termIndex.get(j);

			// variable for wiq
			double wiq = 0;

			// check if term is in query
			if(queryList.contains(temp.getName())){

				// compute WIQ and square it
				wiq = WIQ(temp.getName());
				wiq = wiq * wiq;

				// add to queryWeights
				queryWeights += wiq;

			}

			// get list of files that contain term 
			ArrayList<String> docFileNames = temp.getListOfFileNames();

			// go through list of files that contain term
			for(int k = 0; k < docFileNames.size(); k++){

				// compute and square tfidf
				double tfidf = TFIDF(docFileNames.get(k), temp.getName());
				double tfidf2 = tfidf * tfidf;

				// find index of file in docs array
				int index = fileNames.indexOf(docFileNames.get(k));
				// add to value in docSpecific in position for doc d
				docSpecific[index] += tfidf2;

				// if term is in both query and doc k
				if(queryList.contains(temp.getName())){

					// multiply wi,d and wi,q
					double commonVal = tfidf * wiq;

					// add to value in common array in position for doc k
					common[index] += commonVal;
				}
			}
		}

		// variables for highest sim value
		String highestSimString;
		double highestSimVal = 0.0;

		// traverse over list of documents
		for(int m = 0; m < docs.length; m++){

			double sim = common[m]/(Math.sqrt(docSpecific[m]))*(Math.sqrt(queryWeights));

			// keep track of doc w/ highest sim value
			if(sim >= highestSimVal){
				highestSimVal = sim;
				highestSimString = docs[m];
			}
		}

	}






}







