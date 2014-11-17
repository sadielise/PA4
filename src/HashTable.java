
public class HashTable implements TermIndex{
	//size keeps track of how many terms are in the hash table
	private int size;
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
	
	public Term get(int position){
		return termTable[position];
	}
	
	public int hashCode(String word){
		int intCode = word.toLowerCase().hashCode();
		intCode = intCode%hashSize;
		return intCode;
	}

}
