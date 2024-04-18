/**
 * An object with dynamic storage capability for Strings.
 * Desired behavior:
 * - Detect when full and size up by a given factor;
 * - Capable of reporting how much much data stored;
 * - index addressable (random access)
 * - a contains method
 * - data to be added to the end of existing data
 * - ability to delete data and re-compact the storage
 * - ability to insert data in specific positions (other than end)
 * - ability to retrieve data from front
 * - ability to compare itself to other objects of same class
 * - ability to represent itself as a string
 *
 * The object is based on a simple array of strings that is divised into two
 * parts: the used part, and the unused part. Strings are added to the array
 * in a contiguous manner, starting from position 0.
 *
 * The object also uses an integer variable (nextAvailable) to keep track of
 * which position is the array is ready to accept the next string added to the
 * array. Every time we add a string to the array, nextAvailable moves one
 * position to the right. When nextAvailble == length of the array, the array
 * is full.
 *
 * When the array is full, we resize it.
 *
 * |<-------- used ------->|.......................................
 * ........................|<------------- unused --------------->|
 * 
 * +--+--------------------+--+--------------------------------+--+
 * |..|....................|..|................................|..|
 * +--+--------------------+--+--------------------------------+--+
 *
 * [0] ................... [nextAvailable] ................... [length-1]
 */
public class DynamicArray implements Comparable<DynamicArray> {

  /** Constant with array default size */
  private static final int DEFAULT_SIZE = 2;

  /** Resize factor for enlarging a full array */
  private static final int RESIZE_FACTOR = 2;

  /** Underlying array with strings to store */
  private String[] data;

  /** Next available position to accept data */
  private int nextAvailable;

  /** Basic constructor */
  public DynamicArray(int size) {
    // Make sure size is a legit value
    if (size < 1)
      size = DEFAULT_SIZE;
    this.data = new String[size];
    this.nextAvailable = 0;
  } // basic constructor

  /** Default constructor */
  public DynamicArray() {
    // Call basic constructor with size = DEFAULT_SIZE
    this(DEFAULT_SIZE);
  } // default constructor

  /** Accessor for nextAvailable */
  public int getNextAvailable() {
    return this.nextAvailable;
  }

  /**
   * Method to add a string to the object
   * 
   * @param string String to add to the object's underying array
   */
  public void add(String string) {
    /**
     * the difference of the two implementations is that insert needs to shift
     * everything back if you want to insert in a certain place where add always
     * inserts at the end
     * insert() needs a position add() does not.
     * add() didnt check if the position was legit because there was no position to
     * check
     * Insert checks if the position is legit because it is passed a position
     */
    // call the insert() method to insert the string at the next available position
    this.insert(nextAvailable, string);
  } // method add

  /**
   * Insert a string at a given position (if legal) and shift everything
   * else accordigly.
   *
   * @param where int with position where to insert string
   * @param what  String to insert to the array
   */
  public void insert(int where, String what) {
    // First check if position is legit
    // Now also check if its equal to which means were adding to the end
    if (where >= 0 && where <= this.nextAvailable) {
      // position legit; is array full?
      if (this.nextAvailable == this.data.length) {
        resize();
      }
      // Room assured.
      for (int i = this.nextAvailable; i != where; i--) {
        this.data[i] = this.data[i - 1];
      }
      // Shifting complete. Overwrite position [where]
      this.data[where] = what;
      this.nextAvailable++;
    }
  } // method insert

  /**
   * Method to resize the array when full
   */
  public void resize() {
    // Create a larger array
    String[] temp = new String[RESIZE_FACTOR * this.data.length];
    // copy strings from current array to larger array
    for (int i = 0; i < this.data.length; i++) {
      temp[i] = this.data[i];
    }
    // Replace underlying array with the new larger array
    this.data = temp;
  } // method resize

  /**
   * Retrieve an element by location index.
   *
   * @param index int with the position of element we want to retrieve
   * @return the string an positiion [index] or null if position is invalind or
   *         empty
   */
  public String retrieve(int index) {
    String retrieved = null;
    if (index >= 0 && index < this.nextAvailable) {
      retrieved = this.data[index];
    }
    return retrieved;
  } // method retrieve

  /**
   * Remove an element by location index and rearrange the remaining elements
   * to fill the gap.
   *
   * @param index int with the position of element we want to retrieve
   * @return the string an positiion [index] or null if position is invalind or
   *         empty
   */
  public String remove(int index) {
    String removed = this.retrieve(index);
    if (removed != null) {
      for (int i = index; i < this.nextAvailable - 1; i++) {
        this.data[i] = this.data[i + 1];
      }
      this.data[nextAvailable - 1] = null;
    }
    return removed;
  } // method retrieve

  /**
   * How many times a string appears in the object.
   *
   * @param string String to count appearances of
   * @return the number of times the string appears in the array
   */
  public int count(String string) {
    // Initialize a counter
    int counter = 0;
    // Consider every **used** position in the array
    for (int i = 0; i < this.nextAvailable; i++) {
      if (this.data[i].equals(string)) {
        // Update counter every time we find an element with the string value
        counter++;
      }
    }
    return counter;
  } // method count

  /**
   * Remove and return the front element of the array, then rearrange elements
   * to fill the gap.
   *
   * @return the string at the front of the array after removing it.
   */
  public String removeFront() {
    return this.remove(0);
  }

  /**
   * Add something to the front of the object. First make room for it by moving
   * the remaining elements one position to the right.
   *
   * @param string String to insert in the front of the array.
   */
  public void addFront(String string) {
    this.insert(0, string);
  } // method addFront

  /**
   * Remove the last element of the object and make its position available
   *
   * @return the last string in the object.
   */
  public String removeLast() {
    return this.remove(this.nextAvailable - 1);
  } // method removeLast

  /**
   * Add a string to the object only if the string is not present alredy
   * 
   * @param string String to add
   */
  public void addUnique(String string) {
    // Check is string already in array using the contains method
    if (!this.contains(string)) {
      // String not found in array, let's add it.
      this.add(string);
    }
  } // method addUnique

  /**
   * Checks if element is present in the object's array.
   *
   * @param String value to search for in used part of array.
   * @return true if object is found in array, false otherwise
   */
  public boolean contains(String target) {
    return this.indexOf(target) != -1;
  } // method contains

  /**
   * Find the location of the first instance of a string in the object.
   *
   * @param string String to search for
   * @return int with position of string in underlying array or -1 if
   *         the string is not present.
   */
  public int indexOf(String target) {
    // Assume string not present and prepare to return -1
    int index = -1;
    // Guard against a null input to the method
    if (target != null) {
      // Prepare to traverse the underlying array
      int i = 0;
      // Traversal ends when we find an occurence or when we
      // reach the last used position in the array.
      while (index < 0 && i < this.nextAvailable) {
        if (target.equals(this.data[i])) {
          // An occurence has been found; setting the return variable index
          // to a non-negative value, will stop the while loop.
          index = i;
        }
        // Prepare to advance to the next element in the underlying array
        i++;
      }
    }
    // Done!
    return index;
  } // method indexOf

  /**
   * Size of the used portion of the array.
   */
  public int size() {
    return this.getNextAvailable();
  } // method size

  /**
   * A basic compareTo between this object and another object. Comparison is based
   * on the size of the used portion of the underlying array in both objects.
   * 
   * @param other DynamicArray object to compare to
   * @return a negative int if this object is smaller than the other object,
   *         0 if objects are same,
   *         a positive int if this object is larger than the other object.
   */
  public int compareTo(DynamicArray other) {
    return this.size() - other.size();
  } // method compareTo

  /**
   * a method to convert the DynamicArray into a Normal String array
   * Does not include any of the null values. Copies all elements over
   * using a simple for-loop
   * 
   * @return the new String[] array we created that contains everything in the
   *         Dynamic array
   */
  public String[] toArray() {
    // Create a string array of size this.size() -1 or the position right
    // before nextAvailable because that is all that is being used by the array
    String[] wordsArray = new String[this.size()];
    // Loop over all the elements and copy from underlying array into new array
    for (int i = 0; i < wordsArray.length; i++) {
      wordsArray[i] = this.data[i];
    }
    // return the new String[] array
    return wordsArray;
  }

  /**
   * String representation of a DynamicArray object.
   */
  public String toString() {
    // Demonstrate the use of StringBuilder
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Number of items currently stored: %d", this.nextAvailable));
    sb.append(String.format("\nCurrent capacity: %d", this.data.length));
    sb.append(String.format("\nContents:"));
    for (int i = 0; i < this.nextAvailable; i++) {
      sb.append(String.format("\n\t%s", this.data[i]));
    }

    return sb.toString();
  } // method toString

} // class DynamicArray