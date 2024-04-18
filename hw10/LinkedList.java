public class LinkedList {

  /** The beginning node of the linked list */
  private Node head;
  /** The ending node of the linked list */
  private Node tail;

  /**
   * Getter for head
   * 
   * @return Node at the beginning of the linkedlist
   */
  public Node getHead() {
    return this.head;
  } // method getHead

  /**
   * Add a new node to the list with the provided string as its payload
   * 
   * @param payload String to include in node
   */
  public void add(String payload) {
    Node newNode = new Node(payload);
    if (this.head == null) {
      this.head = newNode;
    } else {
      this.tail.setNext(newNode);
    }
    this.tail = newNode;
  } // method add

  /**
   * String representation
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (this.head == null) {
      sb.append("List is empty.");
    } else {
      Node current = this.head;
      while (current != null) {
        sb.append(String.format("%s -> ", current.getPayload()));
        current = current.getNext();
      }
    }
    return sb.toString();
  } // method toString
}
