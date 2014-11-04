// PA3
// Authors: Sadie Henry, Melinda Ryan 
// Date: 10/17/2014
// Class: CS200

/** 
*  Code borrowed from CS200 Fall 2014 recitation 7.
*  No changes made.
**/

/**
 * Implements a simple reference based for use in CSU
 * CS200 Fall 2010 Lab 9.
 * Based on code downloaded from the Carrano and Pritchard
 * text web site in 2007. Modified to make greater use of
 * generics.
 * @author David Newman
 * @date 2010-10-14
 *
 * @param <T>
 */
public class QueueReferenceBased <T>
implements QueueInterface<T> {
  private Node<T> lastNode;
  
  public QueueReferenceBased() {
    lastNode = null;   
  }  // end default constructor
  
  // queue operations:
  public boolean isEmpty() {
    return lastNode == null;
  }  // end isEmpty

  public void dequeueAll() {
    lastNode = null;
  }  // end dequeueAll

  public void enqueue(T newItem) {
    Node<T> newNode = new Node<T>(newItem);

    // insert the new node
    if (isEmpty()) {
      // insertion into empty queue
      newNode.setNext(newNode);
    }
    else {
      // insertion into nonempty queue
      newNode.setNext(lastNode.getNext());
      lastNode.setNext(newNode);
    }  // end if

    lastNode = newNode;  // new node is at back
  }  // end enqueue

  public T dequeue() throws QueueException {
    if (!isEmpty()) {
      // queue is not empty; remove front
      Node<T> firstNode = lastNode.getNext();
      if (firstNode == lastNode) { // special case?
        lastNode = null;           // yes, one node in queue
      }
      else {
        lastNode.setNext(firstNode.getNext());
      }  // end if
      return firstNode.getItem();
    }
    else {
      throw new QueueException("QueueException on dequeue:"
                             + "queue empty");
    }  // end if
  }  // end dequeue

  public T peek() throws QueueException {
    if (!isEmpty()) {  
      // queue is not empty; retrieve front
      Node<T> firstNode = lastNode.getNext();
      return firstNode.getItem();
    }
    else {
      throw new QueueException("QueueException on peek:"
                             + "queue empty");
    }  // end if
  }  // end peek
   
} // end QueueReferenceBased