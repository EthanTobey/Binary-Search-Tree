public class BinarySearchTree {
  
  //stores root node of the tree
  private Node root;
  
  //constructor
  public BinarySearchTree() {
    this.root = null;
  }
  
  //adds a node into the BST
  public void insert(int key) {
    Node nodeptr = root;
    
    //if tree empty
    if (getRoot() == null)
      setRoot(new Node(key));
 
    //iterate through tree
    while (nodeptr != null) {
      //3 cases:
      //key = node then do nothing
      if (key == nodeptr.getKey())
        break;
      //key < node
      if (key < nodeptr.getKey()) {
        //if open space then add and exit loop
        if (nodeptr.getLeftChild() == null) {
          nodeptr.setLeftChild(new Node(key));
          break;
        }
        //otherwise continue down tree
        else
          nodeptr = nodeptr.getLeftChild();
      }
      //key > node
      if (key > nodeptr.getKey()) {
        //if open space then add and exit loop
        if (nodeptr.getRightChild() == null) {
          nodeptr.setRightChild(new Node(key));
          break;
        }
        //otherwise continue down tree
        else
          nodeptr = nodeptr.getRightChild();
      }
    }
  }
  
  //creates new tree from array of values
  public void createTree(int[] values) {
    setRoot(null);
    for (int i = 0; i < values.length; i++) {
      insert(values[i]);
    }
  }
  
  //returns whether or not key exists in BST
  public boolean search (int key) {
    Node nodeptr = getRoot();
    
    //iterate through tree
    while (nodeptr != null) {
      //if found
      if (key == nodeptr.getKey())
        return true;
      
      //if key < nodeptr key
      else if (key < nodeptr.getKey())
        nodeptr = nodeptr.getLeftChild();
      //if key > nodeptr key
      else if (key > nodeptr.getKey())
        nodeptr = nodeptr.getRightChild();
    }
    //if goes through loop without finding
    return false;
  }
  
  //deletes a node from the BST & returns that node
  public Node delete(int key) {
    Node nodeptr = getRoot();
    Node parent = null;
    
    //if key is in BST
    if (search(key) == true) {
      //iterate through BST
      while (nodeptr.getKey() != key) {
        parent = nodeptr;
        //if key < nodeptr key
        if (key < nodeptr.getKey())
          nodeptr = nodeptr.getLeftChild();
        //if key > nodeptr key
        else if (key > nodeptr.getKey())
          nodeptr = nodeptr.getRightChild();
      }
      
      //Case 1: node has no children
      if (nodeptr.getLeftChild() == null && nodeptr.getRightChild() == null) {
        if (parent != null) {
          //if key is on right
          if (parent.getRightChild() == nodeptr)
            parent.setRightChild(null);
          //if key is on left
          else if (parent.getLeftChild() == nodeptr)
            parent.setLeftChild(null);
        }
        //node is root
        else
          setRoot(null);
      }
      //Case 2: node has one child
      else if (nodeptr.getLeftChild() == null || nodeptr.getRightChild() == null) {
        if (parent != null) {
          //if key is on right
          if (parent.getRightChild() == nodeptr) {
            //if nodeptr's child is on left
            if (nodeptr.getLeftChild() != null)
              parent.setRightChild(nodeptr.getLeftChild());
            //if nodeptr's child is on right
            else if (nodeptr.getRightChild() != null)
              parent.setRightChild(nodeptr.getRightChild());
          }
          //if key is on left
          else if (parent.getLeftChild() == nodeptr) {
            //if nodeptr's child is on left
            if (nodeptr.getLeftChild() != null)
              parent.setLeftChild(nodeptr.getLeftChild());
            //if nodeptr's child is on right
            else if (nodeptr.getRightChild() != null)
              parent.setLeftChild(nodeptr.getRightChild());
          }   
        }
        //node is root
        else {
          //if nodeptr's child is on left
          if (nodeptr.getLeftChild() != null)
            setRoot(nodeptr.getLeftChild());
          //if nodeptr's child is on right
          else if (nodeptr.getRightChild() != null)
            setRoot(nodeptr.getRightChild());
        }
      } 
      //Case 3: node has two children - iterate to bottom of right child's left branch
      else if (nodeptr.getLeftChild() != null && nodeptr.getRightChild() != null) {
        Node replaceNode = nodeptr.getRightChild();
        while(replaceNode.getLeftChild() != null) {
          replaceNode = replaceNode.getLeftChild();
        }
        //can just overrite key into deleted node in this situation
        delete(replaceNode.getKey());    //Recursive call to delete replaceNode Case 1 or 2
        nodeptr.setKey(replaceNode.getKey());
      }
      return nodeptr;
    }
    //if key is not in BST
    else
      return null;
  }
  
  //print nodes in tree inOrder
  public void inorderRec() {
    StringBuilder builder = new StringBuilder();
    //run helper method
    builder.append(inorder(getRoot()));
    //remove last two characters (", ") from builder
    builder.delete(builder.length() - 2, builder.length());
    System.out.println(builder.toString());
  }
  
  //Helper For inorderRec recursion
  private String inorder(Node root) {
    //base case 1
    if (root == null)
      return "Tree is empty  ";
    //base case 2 - node is leaf
    else if (root.getLeftChild() == null && root.getRightChild() == null)
      return (root.getKey() + ", ");
    //Case 1: has only left child
    else if (root.getLeftChild() != null && root.getRightChild() == null)
      return (inorder(root.getLeftChild()) + root.getKey() + ", ");
    //Case 2: has only right child
    else if (root.getLeftChild() == null && root.getRightChild() != null)
      return (root.getKey() + ", " + inorder(root.getRightChild()));
    //Case 3: has two children
    else 
      return (inorder(root.getLeftChild()) + root.getKey() + ", " + inorder(root.getRightChild()));
  }
  
  //print nodes in tree preOrder
  public void preorderRec() {
    StringBuilder builder = new StringBuilder();
    //run helper method
    builder.append(preorder(getRoot()));
    //remove last two characters (", ") from builder
    builder.delete(builder.length() - 2, builder.length());
    System.out.println(builder.toString());
  }
  
  //Helper for preorderRec recursion
  private String preorder(Node root) {
    //base case 1
    if (root == null)
      return "Tree is empty  ";
    //base case 2 - node is leaf
    else if (root.getLeftChild() == null && root.getRightChild() == null)
      return (root.getKey() + ", ");
    //Case 1: has only left child
    else if (root.getLeftChild() != null && root.getRightChild() == null)
      return (root.getKey() + ", " + preorder(root.getLeftChild()));
    //Case 2: has only right child
    else if (root.getLeftChild() == null && root.getRightChild() != null)
      return (root.getKey() + ", " + preorder(root.getRightChild()));
    //Case 3: has two children
    else 
      return (root.getKey() + ", " + preorder(root.getLeftChild()) + preorder(root.getRightChild()));
  }
  
  //print nodes of tree in postOrder
  public void postorderRec() {
    StringBuilder builder = new StringBuilder();
    //run helper method
    builder.append(postorder(getRoot()));
    //remove last two characters (", ") from builder
    builder.delete(builder.length() - 2, builder.length());
    System.out.println(builder.toString());
  }
  
  //Helper for postorderRec recursion
  private String postorder(Node root) {
    //base case 1
    if (root == null)
      return "Tree is empty  ";
    //base case 2 - node is leaf
    else if (root.getLeftChild() == null && root.getRightChild() == null)
      return (root.getKey() + ", ");
    //Case 1: has only left child
    else if (root.getLeftChild() != null && root.getRightChild() == null)
      return (postorder(root.getLeftChild()) + root.getKey() + ", ");
    //Case 2: has only right child
    else if (root.getLeftChild() == null && root.getRightChild() != null)
      return (postorder(root.getRightChild()) + root.getKey() + ", ");
    //Case 3: has two children
    else 
      return (postorder(root.getLeftChild()) + postorder(root.getRightChild()) + root.getKey() + ", ");
  }
  
  //find and return kth smallest element in BST
  public Node kthSmallest (int k) {
    //if BST not empty
    if (getRoot() != null) {
      //take inOrder
      String inOrderString = inorder(getRoot());
      int numberCount = 1;            //one extra b/c last number won't have a comma
      //iterate through inorder to find how many nodes
      for (int i = 0; i < inOrderString.length(); i++) {
        if (inOrderString.charAt(i) == ',')
          numberCount++;
      }
      //hold keys in an array
      int[] keyArray = new int[numberCount];
      //iterate through inorder String, appending lowest number to front of array, higher to back
      int keyIndex = 0;
      int stringIndex = 0;
      while (stringIndex < inOrderString.length()) {
        //iterate to next number
        while (stringIndex < inOrderString.length() && 
               (inOrderString.charAt(stringIndex) == ',' || inOrderString.charAt(stringIndex) == ' ')) {
          stringIndex++;
        }
        //front of the number substring
        int frontBound = stringIndex;
     //   System.out.println("start number: " + inOrderString.charAt(stringIndex));
        //iterate to end of number
        while (stringIndex < inOrderString.length() && inOrderString.charAt(stringIndex) != ',') {
          stringIndex++;
        }
        //add number to array
        if (frontBound != stringIndex) {
          keyArray[keyIndex] = Integer.parseInt(inOrderString.substring(frontBound, stringIndex));
        }
        keyIndex++;
      }  
      Node nodeptr = root;
      while (nodeptr != null) {
        //if found
        if (keyArray[k] == nodeptr.getKey())
          return nodeptr;
        
        //if key < nodeptr key
        else if (keyArray[k] < nodeptr.getKey())
          nodeptr = nodeptr.getLeftChild();
        //if key > nodeptr key
        else if (keyArray[k] > nodeptr.getKey())
          nodeptr = nodeptr.getRightChild();
      }
      //if goes through loop without finding
      return null;
    }
    else
      return null;
  }

  
  //Helper returns root
  private Node getRoot() {
    return this.root;
  }
  
  //Helper changes value of root
  private void setRoot(Node root) {
    this.root = root;
  }
  
  //private Helper nested class to define Node
  private class Node {
    //instance fields
    private int key;
    private Node leftChild;
    private Node rightChild;
    
    //constructor
    private Node(int key) {
      this.key = key;
      this.leftChild = null;
      this.rightChild = null;
    }
    
    //returns key
    private int getKey() {
      return this.key;
    }
    
    //changes value of key
    private void setKey(int key) {
      this.key = key;
    }
    
    //returns left child
    private Node getLeftChild() {
      return this.leftChild;
    }
    
    //changes value of left child
    private void setLeftChild(Node child) {
      this.leftChild = child;
    }
    
    //returns right child
    private Node getRightChild() {
      return this.rightChild;
    }
    
    //changes value of right child
    private void setRightChild(Node child) {
      this.rightChild = child;
    }
  }
}