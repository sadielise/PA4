// PA3
// Authors: Sadie Henry, Melinda Ryan 
// Date: 10/17/2014
// Class: CS200

/** 
*  Code borrowed from CS200 Fall 2014 recitation 7.
*  Changed generic type to Term.
**/

/**
 * Implements a simple node for trees for use in CSU
 * CS200 Fall 2010 Lab 9.
 * Based on code downloaded from the Carrano and Pritchard
 * text web site in 2007. Modified to make greater use of
 * generics.
 * @author David Newman
 * @date 2010-10-14
 *
 * @param <Term>
 */
  public class TreeNode<Term> {
  Term item;
  TreeNode<Term> leftChild;
  TreeNode<Term> rightChild;

  public TreeNode(Term newItem) {
  // Initializes tree node with item and no children.
    item = newItem;
    leftChild  = null;
    rightChild = null;
  }  // end constructor
    
  public TreeNode(Term newItem, 
                  TreeNode<Term> left, TreeNode<Term> right) {
  // Initializes tree node with item and
  // the left and right children references.
    item = newItem;
    leftChild  = left;
    rightChild = right;
  }  // end constructor

  public Term getItem() {
  // Returns the item field.
    return item;
  }  // end getItem

  public void setItem(Term newItem) {
  // Sets the item field to the new value newItem.
  item  = newItem;
  }  // end setItem

  public TreeNode<Term> getLeft() {
  // Returns the reference to the left child.
    return leftChild;
  }  // end getLeft

  public void setLeft(TreeNode<Term> left) {
  // Sets the left child reference to left.
    leftChild  = left;
  }  // end setLeft

  public TreeNode<Term> getRight() {
  // Returns the reference to the right child.
    return rightChild;
  }  // end getRight

  public void setRight(TreeNode<Term> right) {
  // Sets the right child reference to right.
    rightChild  = right;
  }  // end setRight
}  // end TreeNode