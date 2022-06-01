public class DynamicQueue{

  private DynamicNode front, rear;

  public DynamicQueue(){
    front = null;
    rear = null;
  }

  public boolean empty(){
    return (front == null);
  }

  public void insert(Object x){

    DynamicNode p = new DynamicNode(x, null);

    if(empty())
      front = p;
    else
      rear.setNext(p);

    rear = p;

  } // end insert

  public Object remove(){

    if(empty()){
      System.out.println("queue underflow");
      System.exit(1);
    }

    DynamicNode p = front;
    Object temp = p.getInfo();
    front = p.getNext();
    if(front == null)
      rear = null;

    return temp;

  } // end remove


} // end class DynamicQueue
