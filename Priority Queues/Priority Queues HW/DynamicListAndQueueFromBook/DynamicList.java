public class DynamicList{

  private DynamicNode list;

  public DynamicList(){
    list = null;
  }

  
  // inserts x at the beginning of the list
  public void insertFirst(Object x){

    DynamicNode q = new DynamicNode(x, null);
    if(!isEmpty())
      q.setNext(list);
    list = q;

  } // end insertFirst

  
  // inserts x after p
  public void insertAfter(DynamicNode p, Object x){

    if(p == null){
      System.out.println("void insertion");
      System.exit(1);
    }

    DynamicNode q = new DynamicNode(x, p.getNext());
    p.setNext(q);

  } // end insertAfter

  
  // deletes the first node on the list
  public Object deleteFirst(){

    if(isEmpty()){
      System.out.println("void deletion");
      System.exit(1);
    }

    Object temp = list.getInfo();
    if(list.getNext() != null)
      list = list.getNext();
    else
      list = null;

    return temp;

  } // end deleteFirst

  
  // deletes the node after p
  public Object deleteAfter(DynamicNode p){

    if((p == null) || p.getNext() == null){
      System.out.println("void deletion");
      System.exit(1);
    }

    DynamicNode q = p.getNext();
    Object temp = q.getInfo();
    p.setNext(q.getNext());

    return temp;

  } // end deleteAfter

 
  // appends x to the end of the list
  public void insertLast(Object x){

    DynamicNode p = new DynamicNode(x, null), q = null;
    if(isEmpty())
      list = p;
    else{
      // search for the last node
      for(q = list; q.getNext() != null; q = q.getNext());
      q.setNext(p);

    } // end else

  } // end insertLast

  
  // returns a DynamicNode refering to the first occurrence of x
  // returns null if x is not on the list
  public DynamicNode search(Object x){

    DynamicNode p;
    for(p = list; p != null; p = p.getNext())
      if(p.getInfo().equals(x))
        return p;

    return null; // x is not on the list

  } // end search

  
  // removes all nodes whose info fields contain x
  public void removeX(Object x){

    DynamicNode p = list, q = null;

    while(p != null){
      if(p.getInfo().equals(x)){
        p = p.getNext();
        if(q == null)
          deleteFirst(); // remove first node of the list
        else
          deleteAfter(q);

      } // end if
      else{
        // advance to next node of list
        q = p;
        p = p.getNext();
      } // end else

    } // end while

  } // end removeX

  
  // return true if the list has no nodes
  // returns false otherwise
  public boolean isEmpty(){
    return list == null;
  }

} // end class DynamicList
