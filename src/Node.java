// PA3
// Authors: Sadie Henry, Melinda Ryan 
// Date: 10/17/2014
// Class: CS200

/** 
*  Code borrowed from CS200 Fall 2014 recitation 7.
*  No changes made.
**/

/**
 * Implements a simple node object for use in lists for use in CSU
 * CS200 Fall 2010 Lab 9.
 * Based on code downloaded from the Carrano and Pritchard
 * text web site in 2007. Modified to make greater use of
 * generics.
 * @author David Newman
 * @date 2010-10-14
 *
 * @param <T>
 */

  public class Node<T> {
  private T item;
  private Node<T>next;

  public Node(T newItem) {
    item = newItem;
    next = null;
  } // end constructor

  public Node(T newItem, Node<T> nextNode) {
    item = newItem;
    next = nextNode;
  } // end constructor

  public void setItem(T newItem) {
    item = newItem;
  } // end setItem

  public T getItem() {
    return item;
  } // end getItem

  public void setNext(Node<T> nextNode) {
    next = nextNode;
  } // end setNext

  public Node<T> getNext() {
    return next;
  } // end getNext

} // end class Node

