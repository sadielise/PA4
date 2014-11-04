// PA3
// Authors: Sadie Henry, Melinda Ryan 
// Date: 10/17/2014
// Class: CS200

import java.util.Iterator;

/**
 * Implements an iterator for trees.
 * Code reused from CSU
 * CS200 Fall 2014 Lab 7.
 *  
**/

public class BSTIterator implements Iterator<Term> {

	// a queue tracks the order for visiting the tree nodes
	QueueReferenceBased<TreeNode<Term>> inqueue = new QueueReferenceBased<TreeNode<Term>>();
	private BinaryTree binTree;


	public BSTIterator(BinaryTree binTree) {

		this.binTree = binTree; 
		setInorder();
	}


	/* (non-Javadoc)
	 * Return true iff the iterator has more objects yet to return.
	 * @see java.util.Iterator#hasNext()
	 */
	
	public boolean hasNext() {
		return !inqueue.isEmpty();
	}

	/* (non-Javadoc)
	 * Return the first object that has not yet been returned.
	 * @see java.util.Iterator#next()
	 */
	
	public Term next() {
		return inqueue.dequeue().getItem();
	}

	/* (non-Javadoc)
	 * This is an illegal operation for this iterator.
	 * @see java.util.Iterator#remove()
	 */
	
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/*
	 * Put the correct order of nodes onto the queue
	 */
	public void setInorder() {
		inqueue.dequeueAll();
		inorder(binTree.root);
	}
	
	private void inorder(TreeNode treeNode) {
		if(treeNode != null){
			inorder(treeNode.getLeft());
			inqueue.enqueue(treeNode);
			inorder(treeNode.getRight());
		}
	}


}
