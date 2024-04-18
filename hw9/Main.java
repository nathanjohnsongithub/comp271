class Main {
  public static void main(String[] args) {
    BinaryTree demo = new BinaryTree();
    demo.insertIterative("now");
    demo.insertIterative("is");
    demo.insertIterative("the");
    demo.insertIterative("winter");
    demo.insertIterative("of");
    demo.insertIterative("our");
    demo.insertIterative("discontent");
    demo.insertIterative("made");
    demo.insertIterative("glorious");
    demo.insertIterative("by");
    demo.insertIterative("this");
    demo.insertIterative("son");
    demo.insertIterative("of");
    demo.insertIterative("york");
    // System.out.println(demo.root.right.left.word);
    // demo.inOrderTraversal(demo.root);
    demo.inOrder(demo.root);
    System.out.println(demo.size);
    
  }
}