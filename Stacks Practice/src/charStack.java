// a charStack class code similar to the lecture slides to get you started
// the code is given as is, your task is to make it work with a few test cases
// in a main method
// also note that to keep the code simple and same as the original book
// it uses System.exit(1) calls for underflow / overflow
// would be better to replace with exceptions if you wish to make the effort

public class charStack {

    private final int 	STACKSIZE=100; 	// max size
    private int     	top;	 		// indicates the top element
    private char [] 	items; 			// the array that has the items

    public charStack() {
        items = new char[STACKSIZE];
        top = -1; 	// indicates the stack is empty
    }

    public boolean empty() {
        if(top==-1)
            return true;  	// Stack is empty
        else
            return false;  	// Stack is not empty
    }

    public char pop() {
        if(empty()) {
            System.out.println("Stack Underflow POP");
            System.exit(1); // could be replaced with an exception
        }
        return items[top--];
    }

    public void push(char x) {
        if(top==STACKSIZE-1) {
            System.out.println("Stack Overflow PUSH");
            System.exit(1); // could be replaced with an exception
        }
        items[++top] = x;
    }

    public char peek() {
        if(empty()) {
            System.out.println("Stack Underflow PEEK");
            System.exit(1); // could be replaced with an exception
        }
        return items[top];
    }
}

