import java.util.Iterator;

public class HashTableIterator implements Iterator<Term>{
	
	private TermIndex hTable;
	private int position = 0;
	
	public HashTableIterator(HashTable hTable){
		
		this.hTable = hTable;
	}

	public boolean hasNext() {
		
		return position < hTable.size();
	}

	public Term next() {

		if(hasNext()){
			// return term at position++
		}
		else{
			// throw exception
		}
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	

}
