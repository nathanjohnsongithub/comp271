public class Node {

  /** Node's payload is just a string */
  private String payload;
  /** Pointer to next node */
  private Node next;

  /**
   * Basic constructor
   * 
   * @param payload String to store in node
   */
  public Node(String payload) {
    this.payload = payload;
    this.next = null;
  } // basic constructor

  /**
   * Set a node's next node
   *
   * @param next Node that this node will point to
   */
  public void setNext(Node node) {
    this.next = node;
  } // method setNext

  /**
   * Boolean about next node
   * 
   * @return true if this node has a next one, false otherwise
   */
  public boolean hasNext() {
    return this.next != null;
  } // method hasNext

  /**
   * Checks if a node has a specific number of nodes after it
   *
   * My idea for this is that getNext is very similar to hasNext. But they have
   * Slightly different specifications which is why the ternary operator is needed
   * More explination below
   *
   * @param howMany int number of nodes this node is expected to have after it
   */
  public boolean hasNext(int howMany) {
    /*
     * First ill explaing why we subtract 1 from howMany. The specification for
     * This state that ".hasNext(1)" and ".hasNext()" are equivalent. This if
     * unlike .getNext() which has ".getNext(0)" and ".getNext()" are equivalent.
     * This causes the getNext method to have one initiale ".getNext()" that is not
     * accounted for. Because of this we subtract one from howMany. The reason we
     * Need to return true for zero is because the testing code says 0 should be
     * true. This normally wouldnt we a problem for ".getNext()" but because
     * the subtracted one, zero turns into -1 and .getNext() returns null.
     * which here would return false.
     */
    // If howMany is 0, return true. Otherwise, call getNext, and if the value
    // returned is not null, we return true; if it is null, return false.
    return (howMany == 0) ? true : this.getNext(howMany - 1) != null;
  } // method hasNext// method hasNext(int)

  /**
   * Get a node's next node.
   * 
   * @return the next node object, or null if none.
   */
  public Node getNext() {
    return this.next;
  } // method getNext

  /**
   * Obtain the next.next...next node from this node.
   *
   * My idea for this method is to traverse through the linkedlist
   * and count how many Nodes we've gone. If we reach the amount specified
   * with no errors, great, return that node! If not we return null.
   * 
   * @param howMany int with the number of nodes to skip
   * @return Node after so many hops or null if end of list
   */
  public Node getNext(int howMany) {
    // Initialize that variable we will be returning. Lets call it cursor
    Node cursor = null;
    // We check if the input is non-negative.
    // Check if there is a next node from the node that called
    if (howMany >= 0 && this.hasNext()) {
      // We know its safe to get the next node so get it
      cursor = this.getNext();
      /*
       * Initialize count to 0 even though we've already went one ".getNext()"
       * This is because the testing code assumes ".getNext(0)" and ".getNext()"
       * Are equivalent.
       */
      int count = 0;
      // If theres a next node and we still have to keep going to the next node
      while (cursor.hasNext() && count < howMany) {
        // go to next
        cursor = cursor.getNext();
        // store that you went to the next
        count++;
      }
      /*
       * This if statement is to account for overflow. If we broke out of
       * the while loop, Its either because we ran out of nodes to check,
       * cursor == null, or count is now equal to howMany. Lets say the value
       * of howMany is greater than the length of the linked list. We would run
       * out of nodes, but cursor wouldnt be null (while loop breaks before update)
       * So we check if count WAS NOT the one to break out of the while loop.
       * Which would mean that we ran out of nodes, and we should return null.
       */
      if (count != howMany) {
        // set cursor (thing we return) to null
        cursor = null;
      }
    }
    return cursor;
  } // method getNext(int)

  /**
   * Obtain a node's data content (its payload)
   * 
   * @return String with node's payload
   */
  public String getPayload() {
    return this.payload;
  } // method getPayload

  /**
   * String representation of the node object
   * 
   * @return String with information about the node
   */
  public String toString() {
    String nextPayload = (this.next == null) ? "null" : this.getNext().getPayload();
    return String.format("((%s)) --> (%s)",
        this.payload,
        nextPayload);
  } // method toString

}