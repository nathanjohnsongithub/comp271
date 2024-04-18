class Node {

  /** Constant for toString method */
  private static final String EMPTY = "(empty)";

  public String word;
  public Node left;
  public Node right;

  /** Basic constructor */
  public Node(String word) {
    this.word = word;
    this.left = null;
    this.right = null;
  }

  /**
   * String representation
   */
  public String toString() {
    String leftWord = (this.left != null) ? this.left.word : EMPTY;
    String rightWord = (this.right != null) ? this.right.word : EMPTY;
    return String.format(" word: %s\n left: %s\nright: %s",
        this.word, leftWord, rightWord);
  }

}