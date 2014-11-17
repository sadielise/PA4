
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
		//temp variable for determining the hash code
		int hashCode = 0;
		
		//check if the hash table is 80% full
		if(size/hashSize >=0.8){
			//if the hash size is getting full make a new table
			hashSize = (2*hashSize) +1;
			Term[] newTable = new Term[hashSize];
			//re-add all of the terms
			for(int i = 0; i < termTable.length; i++){
				//if the position in the original array is full then re add the value to the new array
				if(!termTable[i].equals(null) && !termTable[i].getName().equals("RESERVED")){
					//compute the hash code
					hashCode = hashCode(termTable[i].getName());
					//add the word
					boolean added = false;
					int counter = 0;
					//while the word has not been added
					while(!added){
						//if the space is open
						if(newTable[hashCode].equals(null) || newTable[hashCode].getName().equals("RESERVED")){
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
			
		}//end if
		
		//add the new term to the termTable
		//compute the hash code
		hashCode = hashCode(newWord);	
		
		//instance variables to assist in adding
		boolean added = false;
		int counter = 0;
		
		//while the word has not been added
		while(!added){
			//if the word is already in the array increase the frequency
			if(termTable[hashCode].equals(newWord)){
				termTable[hashCode].incFrequency(filename);
				added = true;
			}
			
			//if the space is open
			else if(termTable[hashCode].equals(null) || termTable[hashCode].getName().equals("RESERVED")){
				Term tempTerm = new Term(newWord);
				tempTerm.incFrequency(filename);
				termTable[hashCode] = tempTerm;
				added = true;
			}
			
			//if the space is full and the word has not been added already, do quadratic probing
			else{
				counter++;
				hashCode = (hashCode + (int)Math.pow(counter, 2))%hashSize;
			}//end else
		}//end while
		
		//increment size
		this.size++;
	}
	
	//getter method for the size
	public int size(){
		return this.size;
	}
	
	
	public void delete(String word){
		
	}
	public Term get(String word, Boolean printP){
		Term temp = new Term(word);
		return temp;
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
		
	}

}
