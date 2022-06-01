public class PriorityQueue{

  private DynamicNode front, rear;
  private final int QUEUESIZE = 4;

  public PriorityQueue(){
    front = null;
    rear = null;
  }

  public DynamicNode getFront() {
    return front;
  }

  public DynamicNode getRear() {
    return rear;
  }

  /**
   * Checks if the queue is empty.
   * @return True if empty, false if not
   */
  public boolean empty(){
    return (front == null);
  }

  /**
   * Inserts a new object at the end of the queue.
   * Dequeues the object in front if the queue is full.
   * @param x Object to be inserted at the end of the queue
   */
  public void insert(Object x){

    DynamicNode p = new DynamicNode(x, null);

    if(empty()) {  // If the queue is empty
      front = p;  // Set the front node to the one being inserted
      System.out.printf("Inserting " + x.toString() + " in rear. ");
    }
    else if(search(x)) {  // If the object being inserted is found in the queue
      searchMove(x);      // Move that object to the end
    }
    else if (!empty() && size()==QUEUESIZE) {  // If the queue is full and the object being inserted is not found in the queue
      remove();  // Dequeue the object in front
      rear.setNext(p);  // Insert the new object at the end of the queue
      System.out.printf("Q is full, removing front. Inserting " + x.toString() + " in rear. ");
    }
    else {  // If the queue is not empty, but not full, and the object being inserted is not found in the queue
      rear.setNext(p);  // Simply insert the new object at the end of the queue
      System.out.printf("Inserting " + x.toString() + " in rear. ");
    }

    rear = p;  // Set the rear pointer to the new object

  } // end insert

  /**
   * Dequeues the object in front.
   * @return The object that was removed.
   */
  public Object remove(){

    if(empty()){  // If the queue is empty, exit the program due to underflow
      System.out.println("queue underflow");
      System.exit(1);
    }

    DynamicNode p = front;
    Object q = p.getInfo();  // Store the information from the object being removed
    front = p.getNext();  // Delete the first node
    if(front == null)  // If this removal makes the list empty
      rear = null;  // Ensure that both the front and rear pointers are null

    return q;

  } // end remove

  /**
   * Determines the length of the queue.
   * @return The length of the queue
   */
  public int size(){

    DynamicNode p = front;
    int counter = 0;
    while(p != null){
      p = p.getNext();
      counter++;
    }
    return counter;

  } // end size

  /**
   * Searches the queue for an object.
   * @param x The object to be found
   * @return True if the object is in the queue, false if not
   */
  public boolean search(Object x){
    DynamicNode p = front;
    while(p != null){
      if(p.getInfo()==x){
        return true;
      }
      p = p.getNext();
    }
    return false;
  } // end search

  /**
   * Searches the queue for an object, then moves it to the rear of the queue.
   * @param x The object to be found and moved to the end of the queue
   * @return True if the object was found, false if not.
   */
  public boolean searchMove(Object x){
    DynamicNode o = null;  // previous node
    DynamicNode p = front;  // current node
    DynamicNode q = p.getNext();  // next node
    DynamicNode r = front;  // second to last node
    while(p != null){
      if(o==null && p.getInfo()==x) { // If the object is found at the front of the queue
        front = p.getNext();  // Set the front to the second item in the queue
        rear.setNext(p);  // Move the found object to the end of the queue
        p.setNext(null);  // Ensure that the queue does not infinitely call itself by setting the new rear to point to null
        System.out.printf("Moving " + x.toString() + " to rear. ");
        return true;
      }
      else if(o!=null && q!=null && p.getInfo()==x){ // If the object is found in the middle of the queue
        o.setNext(q);
        rear.setNext(p);  // Move the found object to the end of the queue
        p.setNext(null);  // Ensure that the queue does not infinitely call itself by setting the new rear to point to null
        while(r.getNext().getNext()!=null) {  // Set r to the second to last node in the queue
          r=r.getNext();
        }
        if(QUEUESIZE<=4) {
          r.setNext(rear);  // Ensure that the second to last node points to the new rear of the queue
        }
        else {
          r.setNext(p);  // This feels like a hacky solution for larger queue sizes to prevent infinite loops
        }
        System.out.printf("Moving " + x.toString() + " to rear. ");
        return true;
      }
      else if(q==null && p.getInfo()==x) { // If the object is found at the rear of the queue, do nothing
        System.out.printf(x.toString() + " is already in rear. ");
        return true;
      }
      o = p;
      p = p.getNext();
      q = p.getNext();
    }
    return false;
  } // end searchMove

  /**
   * Outputs the contents of the queue to the console.
   */
  public void printQueue() {
    if (front == null) {
      System.out.println("null");
    }
    DynamicNode p = front;
    while (p != null) {
      System.out.print(p.getInfo() + ((p.getNext() != null) ? "->" : ""));
      p = p.getNext();
    }
    System.out.println();
  }


} // end class PriorityQueue
