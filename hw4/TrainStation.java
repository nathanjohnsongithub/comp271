public class TrainStation {

  /** The name of the train station */
  private String name;
  /** The next train station in the direction of travel */
  private TrainStation next;

  /** 
   * Basic constructor. Instantiates a TrainStation object by assignign a
   * name to it. The new station points to null as its next station. This
   * allows to change the value of attribute next, later, using a setter.
   * 
   * @param name String with name of station
   */
  public TrainStation(String name) {
    this.name = name;
    this.next = null;
  } // constructor

  /** Mutator (setter) for next. Assigns a station object after the current
  * station object.
  * 
  * @param next TrainStation object following the current object
  */
  public void setNext(TrainStation next) {
    this.next = next;
  } // method setNext

  /** Accesor (getter) for next */
  public TrainStation getNext() {
    return this.next;
  } // method getNext

  /** Accessor (getter) for name */
  public String getName() {
    return this.name;
  } // method getNext

  /** Determine if there is another station after the current object.
  *
  * @return true if current station object points to another station object;
  otherwise return false.
  */
  public boolean hasNext() {
    return this.next != null;
  } // method hasNext
  
  /** Always write a toString method for your objects */
  public String toString() {
    return this.name;
  } // method toString

  // Aux methods for testing
  
  
}