public class intStack {

    private final int 	STACKSIZE=100; 	// max size
    private int     	top;	 		// indicates the top element
    private int [] 	    items; 			// the array that has the items

    public intStack() {
        items = new int[STACKSIZE];
        top = -1; 	// indicates the stack is empty
    }

    public boolean empty() {
        if(top==-1)
            return true;  	// Stack is empty
        else
            return false;  	// Stack is not empty
    }

    public int pop() {
        if(empty()) {
            System.out.println("Stack Underflow POP");
            System.exit(1); // could be replaced with an exception
        }
        return items[top--];
    }

    public void push(int x) {
        if(top==STACKSIZE-1) {
            System.out.println("Stack Overflow PUSH");
            System.exit(1); // could be replaced with an exception
        }
        items[++top] = x;
    }

    public int peek() {
        if(empty()) {
            System.out.println("Stack Underflow PEEK");
            System.exit(1); // could be replaced with an exception
        }
        return items[top];
    }
}

