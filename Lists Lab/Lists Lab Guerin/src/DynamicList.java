public class DynamicList {
    private DynamicNode head;

    public DynamicList() {
        head = null;
    }

    public DynamicList(DynamicNode head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertFirst(Object x) {
        DynamicNode q = new DynamicNode(x, null);
        if (!isEmpty())
            q.setNext(head);
        head = q;
    }

    public void insertAfter(DynamicNode p, Object x) {
        if (p == null) {
            System.out.println("void insertion");
            System.exit(1); //or just return;
        }
        DynamicNode q = new DynamicNode(x, p.getNext());
        p.setNext(q);
    }

    public Object deleteFirst() {
        if (isEmpty()) {
            System.out.println("void deletion");
            System.exit(1); //or just return null;
        }
        Object temp = head.getInfo();
        if (head.getNext() == null)
            head = null;
        else
            head = head.getNext();
        return temp;
    }

    public Object deleteAfter(DynamicNode p) {
        if (p == null || p.getNext() == null) {
            System.out.println("void deletion");
            System.exit(1);
        }
        DynamicNode q = p.getNext();
        Object temp = q.getInfo();
        p.setNext(q.getNext());
        return temp;
    }

    public DynamicNode getList() {
        return head;
    }


    public void print() {
        if (head == null) {
            System.out.println("null");
        }
        DynamicNode p = head;
        while (p != null) {
            System.out.print(p.getInfo() + ((p.getNext() != null) ? "->" : ""));
            p = p.getNext();
        }
        System.out.println();
    }


    // OR a simpler take
    void printList() {
        DynamicNode p;
        for (p = head; p != null; p = p.getNext())
            System.out.println(p.getInfo() + " ");
    }


    public DynamicNode searchInsert(Object x) {
        // If the list is not empty
        if(!this.isEmpty())	{
            // Set the pointer to the first item in the list
            DynamicNode nd = head;
            // While there is another node ahead (This is bad! The last item will never be checked!)
            while (nd.getNext() != null) {
                // If the object in the current node is the one for which we are searching
                if (nd.getInfo().equals(x)) {
                    // Return the node in which it is
                    return nd;
                }
                // Set the pointer to the next node in the list
                nd = nd.getNext();
            }

            // Create a new node containing the object for which we are searching and set it as the next item in the list
            nd.setNext(new DynamicNode(x, null));
            // Set the pointer to the next node in the list
            nd = nd.getNext();
            // Return the node in which it is
            return nd;
        }

        // If the list is empty, create a new node containing the object for which we are searching
        DynamicNode nd2 = new DynamicNode(x, null);
        // Set the first item in the list as this new node
        head = nd2;
        // Return the node
        return nd2;
    }


    // mystery method belongs to the DynamicList class, returns Object
    // appears to remove the middle item in a list if the number of nodes is odd
    // does nothing to the list if the number of nodes is even
    Object mystery(){
        if(head==null){                                 // If the list is empty
            return null;                                // Return nothing
        }else{                                          // If the list is not empty
            DynamicNode current = null;                 // Set the current node to refer to nothing
            DynamicNode temp1 = head, temp2 = head;     // Set the temp1 and temp2 nodes to refer to the beginning of the list

            while(temp2.getNext()!=null){               // While the temp2 pointer can refer to further items in the list
                current = temp1;                        // Set the current pointer to refer to the temp1 pointer
                temp1 = temp1.getNext();                // Set the temp1 pointer to refer to the next item in the list
                temp2 = temp2.getNext();                // Set the temp2 pointer to refer to the next item in the list

                if(temp2.getNext() == null){            // If the temp2 pointer can no longer refer to further items in the list (The number of items is even)
                    return null;                        // Return nothing
                }else{                                  // If the temp2 pointer can refer to further items in the list
                    temp2 = temp2.getNext();            // Set the temp2 pointer to refer to the next item in the list
                }
            }

            if(current == null){                        // If the current pointer still does not refer to anything
                head = null;                            // Reset the head pointer
            }else{                                      // If current refers to something
                current.setNext(temp1.getNext());       // Delete the value to which temp1 refers by making it refer to the following value in the list
            }
            return temp1.getInfo();                     // Return the value in the middle of the list, which was deleted
        }
    }

    /**
     * Appends all elements in the parameter list otherList
     * to the end of this list.
     * Returns true if the list was changed, false otherwise.
     * Please note that NO new list is created.
     * Also, it is wrong to (repeatedly) insert new nodes to the list.
     *
     */
    public boolean appendList(DynamicList otherList) {
        // If the list to be appended is not empty
        if(!otherList.isEmpty()) {
            // Create a node for the original list
            DynamicNode np = null;
            // If this list is empty
            if(this.isEmpty()) {
                // Set the head of this list to the other list
                this.head = otherList.head;
            }
            else {
                // Find the end of the original list
                for (np = head; np.getNext() != null; np = np.getNext()) ;
                // Create a node for the list to be added
                DynamicNode ot = null;
                // Make the end of the list point to the list being added
                np.setNext(otherList.head);
            }
            return true;
        }
        // If the list to be appended is empty, return false
        return false;
    }

}