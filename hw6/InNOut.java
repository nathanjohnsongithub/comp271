import java.util.Arrays; // for Arrays.toString() only

/**
 * A simple class to stage input-output processing.
 *
 * The class comprises a humble String array to store data. Data can be placed
 * in the array either before the first item or after the last item. No other
 * random access is allowed. Furthermore, data can be removed only from the
 * front of the array. No random access is allowed for removal. Finally, this
 * object allows us to see what item is at the front of the array without
 * removing it.
 */
public class InNOut {

  /** Default capacity for the InNOut object */
  private static final int DEFAULT_CAPACITY = 4;

  /** Constants or toString method */
  private static final String SINGULAR_ITEM = "item";
  private static final String PLURAL_ITEMS = "items";

  /** Array to store items */
  private String[] data;

  /** Number of items stored in array. 0 <= size <= data.lenth */
  private int size;

  /** Default constructor */
  public InNOut() {
    this(DEFAULT_CAPACITY);
  } // default constructor;

  /** Basic constructor */
  public InNOut(int capacity) {
    this.data = new String[capacity];
    this.size = 0;
  } // constructor InNOut

  /** Accessor for size */
  public int getSize() {
    return this.size;
  } // method getSize

  /** Accessor for data */
  public String[] getData() {
    return this.data;
  } // method getData

  /** Is the array empty? */
  public boolean isEmpty() {
    return this.size == 0;
  } // method isEmpty

  /** Is the array full? */
  public boolean isFull() {
    return this.size == this.data.length;
  } // method isFull

  /**
   * Place an item at the beginning of the underlying array after making room
   * for it. If the addition is successful, return true; If there is no room
   * to add a new item, return false.
   *
   * @param string String to add to the beginning of the underlying array
   * @return true if addition is succesful, false otherwise
   */
  public boolean push(String item) {
    // Is there room in the array to add a new item?
    boolean success = (this.size < this.data.length);
    if (success) {
      // Room assured; let's move existing items one place to the right
      for (int i = this.size; i > 0; i--) {
        this.data[i] = this.data[i - 1];
      }
      // Safe to overwrite first item (it has been copied to position [1])
      this.data[0] = item;
      // Adjust size of object
      this.size++;
    }
    return success;
  } // method push

  /**
   * Remove and return an item from the beginning of the underlying array.
   * If there are no items to remove, return null. Remaining items are shifted
   * to the front of the array, and emptying places at the back of the array are
   * filled with nulls.
   * 
   * @return String at beginning of array or null if the array is empty
   */
  public String pop() {
    // Define the return variable which we set to null,
    // were assuming that the array is empty, in which the poppedString
    // would be null
    String poppedString = null;
    // if the array isnt empty, we know theres something to be popped
    if (!this.isEmpty()) {
      // set the return value to the first element of the object
      poppedString = this.data[0];
      // Shift all elements to the left by one. To overwrite the first element
      // EX [a, b, c, d, e] -> [b, c, d, e, e]
      for (int i = 0; i < this.size - 1; i++) {
        // We set the current element to equal the next element
        this.data[i] = this.data[i + 1];
      }
      // Set the last element to null.
      // EX [b, c, d, e, e] -> [b, c, d, e, null]
      this.data[size - 1] = null;
      // adjust size of object. we now have one less element
      this.size--;
    }
    // return the string we popped off
    return poppedString;
  } // method pop

  /**
   * Take a look at the value of the item in the front of the array, without
   * removing it. If they array is empty, return null.
   *
   * @return String at beginning of array or null if the array is empty
   */
  public String peek() {
    // using the ternary operator
    // return null if the array is empty, else, return the first element.
    return this.isEmpty() ? null : this.data[0];
  } // method peek

  /**
   * Add an item after the last (most recently added) item in the array.
   * 
   * @param String item to add
   * @return true if addition was successful, false otherwise
   */
  public boolean append(String item) {
    // Using the ternary operator, we check if the array is full, if it is
    // we return false, else, we set the value of this.data[this.size] to the
    // item inputted into the method. We then increment this.size. We then
    // check if that value is equal to itself, which will always return true.
    return this.isFull() ? false : (this.data[this.size++] = item) == item;
  } // method append

  /** String representation of the object */
  public String toString() {
    String itemOrItems = (this.size == 1) ? SINGULAR_ITEM : PLURAL_ITEMS;
    return String.format("Array %s has %d %s stored",
        Arrays.toString(this.data),
        this.size,
        itemOrItems);
  } // method toString

} // class InNOut