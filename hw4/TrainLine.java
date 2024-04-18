/**
 * A class that simulates a train line by linking train station objects
 * together. The class uses two train station objects to define a train
 * line: its first station and its last station. New stations are added
 * always at the end of the line, and that's why it's important to know
 * where the last station is.
 */
public class TrainLine {

  /** Constant with message for string representation of an empty line */
  private static final String EMPTY_LINE = "This train line has no stations.";

  /**
   * The first station in the train line. This field is significant because
   * it provides us with a starting point for traversing the train line in
   * search of a particular station or for other purposes when traversal is
   * necessary.
   */
  private TrainStation first;

  /**
   * The last station in the train line. This field is significant because it
   * allows us to add new stations at the end of the line, superfase.
   */
  private TrainStation last;

  /**
   * Default constructor. Sets both first and last stations to null.
   */
  public TrainLine() {
    this.first = null;
    this.last = null;
  } // default constructor

  /**
   * Add a train station at the back of the train line. The new station
   * will become the last station in the line.
   *
   * @param name String with name of new station to add.
   */
  public void addStation(String name) {
    // Create the new train station to add
    TrainStation newStation = new TrainStation(name);
    // Determine where to add the new train station
    if (this.first == null) {
      // Train line is empty: this is the first station we add
      this.first = newStation;
    } else {
      // Train line not empty: new station goes after last station
      this.last.setNext(newStation);
    }
    // Update the last station to the newly added station
    this.last = newStation;
  } // method addStation

  /**
   * Overloaded method addStation to take a TrainStation object as param.
   */
  public void addStation(TrainStation trainStation) {
    if (this.first == null) {
      this.first = trainStation;
    } else {
      this.last.setNext(trainStation);
    }
    this.last = trainStation;
  } // method addStation

  /**
   * Determine if a train station with a given name exists in the train line.
   *
   * @param name String with name of train station to search the train line for
   * @return true if train station with given name exists in line,
   *         false, otherwise.
   */
  public boolean contains(String name) {
    /**
    * call the indexOf method with the name as a parameter.
    * If it returns -1. Is not containted so false. If it returns anything else.
    * If it contained so returns true. IndexOf returns -1 if it doesnt find the 
    * Word inside. So if the value returns is not equal to -1 (found something)
    * then we return true. If it is equal to -1 (didnt find anything) -1 != -1 is false
    * because it is equal. so returns false
    */
    return this.indexOf(name) != -1;
  } // method contains

  /**
   * Find the middle station in the train line
   *
   * @return TrainStation that is at the middle of the line. If the line has
   *         an even number of train stations, the first of the two mid-
   *         stations is returned. If the line is empty, return null.
   */
  public TrainStation findMiddle() {
    // Prepare a null object to return (in case line is empty)
    TrainStation middle = null;
    // Make sure train line is not empty
    if (this.first != null) {
      // Use two pointers to traverse train line from beginning.
      // Slow traverses one station at a time.
      // Fast traverses skips a station and goes to the one after that
      TrainStation slow = this.first;
      TrainStation fast = this.first;
      // Traverse as long as fast pointer has somewhere to go
      while (fast.hasNext() && fast.getNext().hasNext()) {
        // Fast goes to its next stations' next station
        fast = fast.getNext().getNext();
        // Slow goes to its next station
        slow = slow.getNext();
      }
      // When the loop ends, slow is the middle station
      middle = slow;

    }
    return middle;
  } // method findMiddle

  /** 
   * Determine if there is a loop in the trainline.
   * For explination of this, refer to the comments inside of the code
   *
   * @return true if a loop exists, false otherwise 
   */
  public boolean hasLoop() {
    // set boolean loop to false because we assume that there is not a loop
    boolean loop = false;
    // If a train line exists
    if (this.first != null) {
      // Get a slow station that will move one at a time
      TrainStation slow = this.first;
      // And a fast station that will move two at a time
      TrainStation fast = this.first;
      // While we still havent found a loop and there is another station.
      // And the next station has another station. I.E No loop and more stations
      while (!loop && fast.hasNext() && fast.getNext().hasNext()) {
        // Slow goes to its next station
        slow = slow.getNext();
        // Fast goes to its next stations' next station
        fast = fast.getNext().getNext();
        /**
        * We now check if the names of the slow and fast station are equal.
        * They should never be equal if there is no loop. For that case,
        * The loop will break because we ran out of stations
        * For the other case, where there is a loop. It will keep looping until
        * They fall on the same station, which always happens because of the 
        * different increments. This would cause loop to become true which
        * would break out of the while loop 
        */
        loop = slow.getName().equals(fast.getName());
      } // end while loop
    }
    // return the value
    return loop;
  } // method hasLoop

  /**
   * Find the position of a station in the line.
   *
   * @param String name of the train station to search for
   * @return int with the position of the train station along the line or
   *         -1 if station not present
   */
  public int indexOf(String name) {
    // set index to be -1 which is the return value if the Station 
    // is not inside of the Trainline. So were assuming it doesnt exist
    int index = -1;
    // Initialize the counter so we know where we are 
    // while traversing the train line 
    int counter = 0;
    // Search only if line is not empty
    if (this.first != null) {
      // Initialze a cursor which will move throught the Trainline
      // Between the stations
      TrainStation cursor = this.first;
      // If we havent reached the end of this line and still havent found the word
      while (cursor != null && index == -1) {
        // Check if current station (where the cursor is) matches the name
        if (cursor.getName().equals(name)){
          // If it does, set the value of index to the current positon 
          // of the cursor, or the index. This will also break the while loop
          index = counter;
        }
        // Proceed to the next station
        cursor = cursor.getNext();
        // Increment counter by one because we moved up a station
        counter++;
      }
    }
    // return what we found
    return index;
  } // method indexOf

  /**
   * String representation of the train line object, demonstrating the use
   * of StringBuilder.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (this.first == null) {
      // Line is empty
      sb.append(EMPTY_LINE);
    } else {
      // Line not empty, traverse and print its stations
      TrainStation current = this.first;
      while (current != null) {
        sb.append(String.format("\n%s", current.getName()));
        current = current.getNext();
      }
    }
    return sb.toString();
  } // method toString

} // class TrainLine