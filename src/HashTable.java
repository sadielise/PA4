
public class HashTable implements TermIndex{
	private int size;
	Term[] termTable;
	
	
	public void HashTable(int sizeOfArray){
		termTable = new Term[sizeOfArray];
		size = 0;
	}
	public void add(String filename, String newWord){
		
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

}
