public class BinarySearchTreeDemo {
  
  public static void main(String[] args) {
  //EMPTY TREE
    System.out.println("TESTING EMPTY TREE");
    //initialize a BST
    BinarySearchTree tree = new BinarySearchTree();
    //use inorder, preorder and postorder to print empty tree
    System.out.print("Inorder empty tree: ");
    tree.inorderRec();
    System.out.print("Preorder empty tree: ");
    tree.preorderRec();
    System.out.print("Postorder empty tree: ");
    tree.postorderRec();
    //show that delete returns null
    System.out.println("Delete empty tree returns: " + tree.delete(1));
    //show that search returns false
    System.out.println("Search empty tree returns: " + tree.search(1));
    //show that kthSmallest returns null
    System.out.println("kthSmallest empty tree returns: " + tree.kthSmallest(0));
    
  //EXAMINE TREE USING INSERT METHOD
    System.out.println("\nTESTING TREE WITH INSERT METHOD");
    //insert some elements with insert method
    tree.insert(5);
    tree.insert(3);
    tree.insert(2);
    tree.insert(4);
    tree.insert(7);
    tree.insert(6);
    tree.insert(8);
    //print them out inorder, preorder, postorder
    System.out.print("Inorder traversal of tree: ");
    tree.inorderRec();
    System.out.print("Preorder traversal of tree: ");
    tree.preorderRec();
    System.out.print("Postorder traversal of tree: ");
    tree.postorderRec();
    //show deletion for leaf, single child, double child, and root (print inorder after each deletion)
    tree.delete(2);
    System.out.print("Deleted leaf 2; tree inorder now: ");
    tree.inorderRec();
    tree.delete(4);
    System.out.print("Deleted single child root 4; tree inorder now: ");
    tree.inorderRec();
    tree.delete(7);
    System.out.print("Deleted two child root 7; tree inorder now: ");
    tree.inorderRec();
    tree.delete(5);
    System.out.print("Deleted root; tree inorder now: ");
    tree.inorderRec();
    
  //CREATETREE METHOD
    System.out.println("\nTESTING TREE WITH CREATE TREE METHOD");
    //use createTree to make a new tree
    int[] numbers = {7,3,11,1,5,9,13,0,2,4,6,8,10,12,14};
    tree.createTree(numbers);
    //print it out inorder,preorder,postorder to show new tree
    System.out.print("Inorder traversal of tree: ");
    tree.inorderRec();
    System.out.print("Preorder traversal of tree: ");
    tree.preorderRec();
    System.out.print("Postorder traversal of tree: ");
    tree.postorderRec();
    //demo kthSmallest
    System.out.println("\nTESTING KTH SMALLEST");
    //prive address same by using return of deleting smallest node
    System.out.println("0th Smallest is: " + tree.kthSmallest(0) + 
                       "\nAddress matches: " + tree.delete(0) + " which is node of key 0");
    tree.insert(0); //reinsert deleted node
    System.out.println("1st Smallest is: " + tree.kthSmallest(1) + 
                       "\nAddress matches: " + tree.delete(1) + " which is node of key 1");
    tree.insert(1); //reinsert deleted node
    System.out.println("5th Smallest is: " + tree.kthSmallest(5) + 
                       "\nAddress matches: " + tree.delete(5) + " which is node of key 5");
    tree.insert(5); //reinsert deleted node
    System.out.println("10th Smallest is: " + tree.kthSmallest(10) + 
                       "\nAddress matches:  " + tree.delete(10) + " which is node of key 10");
    tree.insert(10); //reinsert deleted node
    
  //Further test search
    System.out.println("\nTESTING SEARCH METHOD");
    int[] numbers2 = {14,6,22,2,10,18,26,0,4,8,12,16,20,24,28};
    tree.createTree(numbers2);                              //create new tree with gaps for testing
    System.out.print("New tree inorder for testing: ");
    tree.inorderRec();
    //search outside left branch left side
    System.out.println("Searching for -1: " + tree.search(-1));
    //search in left branch left side
    System.out.println("Searching for 2: " + tree.search(2));
    //search outside left branch right side
    System.out.println("Searching for 5: " + tree.search(5));
    //search inside left branch right side
    System.out.println("Searching for 10: " + tree.search(10));
    //search outside right branch left side
    System.out.println("Searching for 15: " + tree.search(15));
    //search in right branch left side
    System.out.println("Searching for 18: " + tree.search(18));
    //search outside right branch right side
    System.out.println("Searching for 300: " + tree.search(300));
    //search in right branch right side
    System.out.println("Searching for 28: " + tree.search(28));
    //search at root
    System.out.println("Searching at for root 14: " + tree.search(14));
  }
}