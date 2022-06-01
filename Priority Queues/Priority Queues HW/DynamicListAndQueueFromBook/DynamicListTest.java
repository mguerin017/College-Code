
public class DynamicListTest{

  public static void main(String args[]){

    DynamicList l = new DynamicList();
    DynamicNode dn = null;

    String s[] = {"1", "2", "5", "0", "2"};

    for(int i = 0; i < s.length; i++)
      l.insertFirst(s[i]);

    for(int i = 0; i < s.length; i++){
      dn = l.search(s[i]);
      System.out.print(dn.getInfo() + " ");
    }

    dn = l.search("3");
    if(dn != null)
      System.out.println("Test failed");
  

  }

}
