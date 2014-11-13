
public class HashTable implements TermIndex{
	//size keeps track of how many terms are in the hash table
	private int size;
<<<<<<< HEAD
	Term[] termTable;
	
	
	public void HashTable(int sizeOfArray){
		termTable = new Term[sizeOfArray];
		size = 0;
	}
=======
	//termTable is the hash table where terms are stored
	Term[] termTable;
	//hash size is the size of the array 
	private int hashSize;
	
	//constructor creates termTable to be the correct size
	public void HashTable(int hashSize){
		termTable = new Term[hashSize];
		size = 0;
		this.hashSize = hashSize;
	}
	//adds a term to the hash table
	//expands size of table and rehashes when 80% full
>>>>>>> origin/work-on-hash-table
	public void add(String filename, String newWord){
		int hashCode = hashCode(newWord);

		
		
		
		
	}
	public int size(){
		return this.size;
	}
	public void delete(String word){
		
	}
	public Term get(String word, Boolean printP){
		Term temp = new Term(word);
		return temp;
	}
	
	public int hashCode(String word){
		int intCode = word.toLowerCase().hashCode();
		intCode = intCode%hashSize;
		return intCode;
	}

}
