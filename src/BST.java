// PA3
// Authors: Sadie Henry, Melinda Ryan 
// Date: 10/17/2014
// Class: CS200

public class BST extends BinaryTree
{
	
	//Instance variables
	private Term root;
	private int count;
	
	/* constructor which initializes an instance variable called "root" 
	as null and an instance variable called "count" as 0.*/
	public BST()
	{
		root = null;
		count = 0;
	}

	//returns the number of unique words in the document (i.e., count).
	public int size()
	{
		return count;
	}

	/*adds a new Term or increments frequencies if the term 
	already exists in the BST.*/
	public void add(String documentName, String word)
	{
		//create a term from the word
		Term term = new Term(word);
		
		//if the word is already in the tree, increment the count
		if(contains(term))
		{
			get(word, false).incFrequency(documentName);
		}
		
		//if the word is not in the tree, add it
		else
		{
			count++; //size of tree for unique words
			insert(term);
			term.incFrequency(documentName);
		}
	}

	/*returns the Term object for the word. 
	If printDepth is true, then get should keep track of how 
	deep in the tree it finds word and print out the value 
	at the end in the form " At depth 1" (At is preceded by 2 spaces). 
	If the word is not found, it should print the deepest level that it checked.*/
	public Term get(String word, Boolean printDepth){
		
		Term item = retrieve(word);
		
		if(printDepth)
		{
			int depth;
			if(item == null)
				depth = getNullDepth();
			else
				depth = item.getDepth();
			
			System.out.println("  At depth " + depth);
		}
		return item;
	}

	/*destructively modifies the BST so that the Term indicated by 
	word is no longer in the BST, but the BST is otherwise intact. 
	Follow the convention/algorithm described in class and the text for how to delete.*/
	public void delete(String word)
	{
		delete(word);
	}

	
	public boolean contains(Object other) 
	{
		if(other instanceof Term)
		{
			Term otherTerm = (Term) other;
			if(get(otherTerm.getName(), false)!=null)
				return true;
		}
		return false;
	}

}
