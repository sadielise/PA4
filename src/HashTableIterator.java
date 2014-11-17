import java.util.Iterator;

public class HashTableIterator implements Iterator<Term>{
	
	private HashTable hTable;
	private int position = 0;
	
	public HashTableIterator(HashTable hTable){
		
		this.hTable = hTable;
	}

	public boolean hasNext() {
		
		return position < hTable.size();
	}

	public Term next() {

		if(hasNext()){
			position++;
			return hTable.get(position);
		}
		else{
			throw new HashTableException("No terms available");
		}
	}

	public void remove() {
		
		
	}
	
	

}
