
public class HashTable implements TermIndex{
	//size keeps track of how many terms are in the hash table
	private int size;

	//termTable is the hash table where terms are stored
	Term[] termTable;

	//hash size is the size of the array 
	private int hashSize;

	//constructor creates termTable to be the correct size
	public HashTable(int hashSize){
		termTable = new Term[hashSize];
		size = 0;
		this.hashSize = hashSize;
	}


	//adds a term to the hash table
	//expands size of table and rehashes when 80% full
	public void add(String filename, String newWord){
		//check if the hash table is 80% full
		if(((double)size/(double)hashSize) >=.8){
			reHash();
		}//end if

		//add the new term to the termTable
		//compute the hash code
		int hashCode = hashCode(newWord);	

		//instance variables to assist in adding
		boolean added = false;
		int counter = 0;

		//see if the word is already in the hashTable
		int position = this.find(newWord);

		//if the word has already been added
		if(position>=0){
			termTable[position].incFrequency(filename);
		}

		//if the word has not already been added then add the word
		else{
			//while the word has not been added
			while(!added){			
				//if the space is open
				if(termTable[hashCode]==null || termTable[hashCode].getName().equals("RESERVED")){
					Term tempTerm = new Term(newWord);
					tempTerm.incFrequency(filename);
					termTable[hashCode] = tempTerm;
					added = true;
				}

				//if the location is full and the word has not been added already, do quadratic probing
				else{
					counter++;
					hashCode = (hashCode + (int)Math.pow(counter, 2))%hashSize;
				}//end else
			}//end while

			//increment size
			this.size++;
		}
	}

	private void reHash(){
		//if the hash size is getting full make a new table
		hashSize = (2*hashSize) +1;
		Term[] newTable = new Term[hashSize];
		int hashCode = 0;

		//re-add all of the terms
		for(int i = 0; i < termTable.length; i++){
			//if the position in the original array is full then re add the value to the new array
			if(termTable[i]!=null && !termTable[i].getName().equals("RESERVED")){
				//compute the hash code
				hashCode = hashCode(termTable[i].getName());
				//add the word
				boolean added = false;
				int counter = 0;
				//while the word has not been added
				while(!added){
					//if the space is open
					if(newTable[hashCode] == null || newTable[hashCode].getName().equals("RESERVED")){
						newTable[hashCode] = termTable[i];
						added = true;
					}
					else{
						counter++;
						hashCode = (hashCode + (int)Math.pow(counter, 2))%hashSize;
					}//end else
				}//end while
			}//end if
		}//end for
		termTable = newTable;
	}

	//getter method for the size
	public int size(){
		return this.size;
	}


	public void delete(String word){

	}

	//returns the term if found and null if not found
	public Term get(String word, Boolean printP){
		int position = this.find(word);
		if(position>=0){
			return termTable[position];
		}
		else{
			return null;
		}

	}

	//finds the word and returns the position where the word is located
	//returns -1 if the word was not found
	private int find(String word){
		int positionsChecked = 0;
		//compute the hash code
		int hashCode = hashCode(word);

		while(positionsChecked<hashSize){
			//if the term is found return the location in the array 
			if(termTable[hashCode] ==null){
				//do nothing, the word has not been found
			}
			else if(termTable[hashCode].getName().equals(word)){
				return hashCode;

			}
			//otherwise do quadratic probing
			positionsChecked++;
			hashCode = (hashCode + (int)Math.pow(positionsChecked, 2))%hashSize;
		}

		//if the code gets to here then the word  was not found
		return -1;
	}

	public Term get(int position){
		return termTable[position];
	}

	public int hashCode(String word){
		int intCode = Math.abs(word.toLowerCase().hashCode());
		intCode = intCode%hashSize;
		return intCode;
	}


	//testing!!
	public static void main(String[] args){
		HashTable testing = new HashTable(5);
		testing.add("docs", "newWord");
		testing.add("doc", "newWord");
		testing.add("document", "word");
		testing.add("doc", "words");
		testing.add("doc", "Gabriella");
		testing.add("d", "g");
		
		Term test = testing.get("Gabriella", false);
		if(test!=null) System.out.println("Testing get: " + test.getName());
		
		String outputString = "";
		if(testing.size() == 0)
			System.out.println("Error: Empty List");
		else
		{
			HashTableIterator itr = new HashTableIterator(testing);
			while(itr.hasNext())
			{
				outputString += ((itr.next()).getName() + "\n");
			}	
			System.out.println(outputString);
		}
	}

}
