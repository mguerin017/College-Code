

public class DynamicQueueTest{

  public static void main(String args[]){

    DynamicQueue q = new DynamicQueue();
    String s[] = {"1", "2", "3", "4", "5"};

    for(int i = 0; i < s.length; i++)
      q.insert(s[i]);

    for(int i = 0; i < s.length; i++)
      System.out.println(q.remove());

    // check for underflow
    System.out.println("Program should exit here due to underflow.");
    q.remove();

  } // end main
} // end class DynamicQueueTest

