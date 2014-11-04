// PA3
// Authors: Sadie Henry, Melinda Ryan 
// Date: 10/17/2014
// Class: CS200

/**
 * Implements a simple binary search tree class 
 * Based on code from the Carrano and Pritchard
 * text (pp. 616-619). Modified generics for Term class,
 * added instance variable to track the 
 * depth of an item during retrieval 
 * 
 */

public class BinaryTree 
{
  protected TreeNode<Term> root;
  private int nullDepth;
	
  public BinaryTree() 
  {
	  root = null;
  }  

  public BinaryTree(Term rootItem) 
  {
	  root = new TreeNode<Term>(rootItem, null, null);
  }  // end constructor


  public void setRootItem(Term newItem) throws UnsupportedOperationException
  {
    
	  throw new UnsupportedOperationException();
    
  }  // end setRootItem

  public void insert(Term newItem)
  {
	  root = insertItem(root, newItem);
  }//end insert
  
  public Term retrieve(String word)
  {
	  return retrieveItem(root, word, 1);
  }//end retrieve

  public void delete(String word) throws TreeException
  {
	  root = deleteItem(root, word);
  }//end delete
  
  public void delete(Term item) throws TreeException
  {
	  root = deleteItem(root, item.getName());
  }//end delete
  
  protected TreeNode<Term> insertItem(TreeNode<Term> tNode, Term newItem)
  {
	  TreeNode<Term> newSubtree;
	  if(tNode == null)
	  {
		  //position of insertion found
		  tNode = new TreeNode<Term>(newItem, null, null);
		  return tNode;
	  }//end if
	  
	  Term nodeItem = tNode.item;
	  
	  //search for insertion position
	  if(newItem.getName().compareTo(nodeItem.getName()) < 0)
	  {
		  //search the left subtree
		  newSubtree = insertItem(tNode.leftChild, newItem);
		  tNode.leftChild = newSubtree;
		  return tNode;
	  }
	  else  
	  {
		//search right subtree
		  newSubtree = insertItem(tNode.rightChild, newItem);
		  tNode.rightChild = newSubtree;
		  return tNode; 
	  }
  }//end insertItem
  
  protected Term retrieveItem(TreeNode<Term> tNode, String word, int depth)
  {
	  Term treeItem;
	  if(tNode == null)
	 {
		  treeItem = null;
		  nullDepth = depth;
	 }
		 
	  else
	  {
		  Term nodeItem = tNode.item;
		  if(word.compareTo(nodeItem.getName()) == 0)
		  {
			  //item is in the root of some subtree
			  treeItem = tNode.item;
			  tNode.getItem().setDepth(depth);
		  }
		  else if(word.compareTo(nodeItem.getName()) < 0)
		  {
			  //search the left subtree
			  treeItem = retrieveItem(tNode.leftChild, word, depth + 1);
		  }
		  else 
		  {
			  //search the right subtree
			  treeItem = retrieveItem(tNode.rightChild, word, depth + 1);
		  }
  
	  }
	  
	  return treeItem;
  }//end retrieveItem
  
  protected TreeNode<Term> deleteItem(TreeNode<Term> tNode, String word)
  {
	  // calls deleteNode
	  TreeNode<Term> newSubtree;
	  if(tNode == null)
		  throw new TreeException("Tree Exceptiopn, item not found");
	  else
	  {
		  Term nodeItem = tNode.item;
		  if(word.compareTo(nodeItem.getName()) == 0)
		  {
			  //item in the root of some subtree
			  tNode = deleteNode(tNode);
		  }
		  else if(word.compareTo(nodeItem.getName()) < 0)
		  {
			  //search left subtree
			  newSubtree = deleteItem(tNode.leftChild, word);
			  tNode.leftChild = newSubtree;
		  }
	  }
	  
	  return tNode;
  }//end deleteItem
  
  protected TreeNode<Term> deleteNode(TreeNode<Term> tNode)
  {
	  Term replacementItem;
	  //test for a leaf
	  if((tNode.leftChild == null) && (tNode.rightChild == null))
		  return null;
	  else if(tNode.leftChild == null)
		  return tNode.rightChild;
	  else if(tNode.rightChild == null)
		  return tNode.leftChild;
	  
	  //there are two children
	  else
	  {
		  replacementItem = findLeftmost(tNode.rightChild);
		  tNode.item = replacementItem;
		  tNode.rightChild = deleteLeftmost(tNode.rightChild);
		  return tNode;
	  }
  }//end deleteNode
  
  protected Term findLeftmost(TreeNode<Term> tNode)
  {
	  if(tNode.leftChild == null)
		  return tNode.item;
	  else
		  return findLeftmost(tNode.leftChild);
  }//end findLeftmost
  
  protected TreeNode<Term> deleteLeftmost(TreeNode<Term> tNode)
  {
	  if(tNode.leftChild == null)
		  return tNode.rightChild;
	  else
	  {
		  tNode.leftChild = deleteLeftmost(tNode.leftChild);
		  return tNode;
	  }
  }//end deleteLeftmost
  
  public int getNullDepth()
  {
	  return nullDepth;
  }
  public void setNullDepth(int depth)
  {
	  nullDepth = depth;

  }

} // end BinaryTree
