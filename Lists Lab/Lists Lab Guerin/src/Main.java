public class Main {
    public static void main(String[]args){
        DynamicList firstList=new DynamicList();

        firstList.insertFirst(5);
        firstList.insertFirst(6);
        firstList.insertFirst(1);

        System.out.println("The first list is:");
        firstList.printList();

        DynamicList secondList=new DynamicList();

        secondList.insertFirst(2);
        secondList.insertFirst(8);
        secondList.insertFirst(3);

        System.out.println("The second list is:");
        secondList.printList();

        firstList.appendList(secondList);


        System.out.println("After appending the second list to the first list, the first list is now:");
        firstList.printList();


        System.out.println("SearchInsert Tests:");

        System.out.println("If the list has one node whose info matches the target x");
        System.out.println("Here, I ran searchInsert(4) on a list only containing one node, which contained the value 4.");
        System.out.println("Printing the whole list returns this:");
        DynamicList ListA=new DynamicList();
        ListA.insertFirst(4);
        ListA.searchInsert(4);
        ListA.printList();

        System.out.println("If the list has one node whose info does not match the target x");
        System.out.println("Here, I ran searchInsert(4) on a list only containing one node, which contained the value 2.");
        System.out.println("Printing the whole list returns this:");
        DynamicList ListB=new DynamicList();
        ListB.insertFirst(2);
        ListB.searchInsert(4);
        ListB.printList();

        System.out.println("If the list has more than 2 nodes, and the matching target is not found");
        System.out.println("Here, I ran searchInsert(4) on a list containing [1, 2, 3].");
        System.out.println("Printing the whole list returns this:");
        DynamicList ListC=new DynamicList();
        ListC.insertFirst(3);
        ListC.insertFirst(2);
        ListC.insertFirst(1);
        ListC.searchInsert(4);
        ListC.printList();

        System.out.println("If the list has more than 2 nodes, and the matching target is found at the first node");
        System.out.println("Here, I ran searchInsert(1) on a list containing [1, 2, 3].");
        System.out.println("Printing the whole list returns this:");
        DynamicList ListD=new DynamicList();
        ListD.insertFirst(3);
        ListD.insertFirst(2);
        ListD.insertFirst(1);
        ListD.searchInsert(1);
        ListD.printList();

        System.out.println("If the list has more than 2 nodes, and the matching target is found at the last node");
        System.out.println("Here, I ran searchInsert(3) on a list containing [1, 2, 3].");
        System.out.println("Printing the whole list returns this:");
        DynamicList ListE=new DynamicList();
        ListE.insertFirst(3);
        ListE.insertFirst(2);
        ListE.insertFirst(1);
        ListE.searchInsert(3);
        ListE.printList();


        System.out.println("Mystery tests");

        System.out.println("Four Nodes");
        DynamicList FourNodeMystery = new DynamicList();
        FourNodeMystery.insertFirst(4);
        FourNodeMystery.insertFirst(3);
        FourNodeMystery.insertFirst(2);
        FourNodeMystery.insertFirst(1);
        // System.out.println(FourNodeMystery.mystery());
        FourNodeMystery.mystery();
        FourNodeMystery.printList();


        System.out.println("Five Nodes");
        DynamicList FiveNodeMystery = new DynamicList();
        FiveNodeMystery.insertFirst(2);
        FiveNodeMystery.insertFirst(9);
        FiveNodeMystery.insertFirst(2);
        FiveNodeMystery.insertFirst(4);
        FiveNodeMystery.insertFirst(3);
        // System.out.println(FiveNodeMystery.mystery());
        FiveNodeMystery.mystery();
        FiveNodeMystery.printList();
    }
}
