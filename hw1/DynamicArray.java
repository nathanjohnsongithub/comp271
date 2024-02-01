/**
 * An object with dynamic storage capability for Strings.
 * Desired behavior:
 * - Detect when full and size up by a given factor;
 * - Capable of reporting how much much data store;
 * - index addressable (random access)
 * - a contains method
 * - data to be added to the end of existing data
 * - ability to delete data and re-compact the storage
 */
public class DynamicArray {

    /** Constant with array default size */
    private static final int DEFAULT_SIZE = 2;

    /** Resize factor for enlarging a full array */
    private static final int RESIZE_FACTOR = 2;

    private static final int INDEXOF_DEFAULT_VALUE = -1;

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

    /**
     * Method to add a string to the object
     * 
     * @param string String to add to the object's underying array
     */
    public void add(String string) {
        // If array is full resize first then add String to underlying array
        if (this.nextAvailable == this.data.length) {
            resize();
        }
        this.data[this.nextAvailable] = string;
        // Advance to the next available position
        nextAvailable++;
    } // method add

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
     * check if the string inputed by the user is inside of the Dynamic array object
     * we created. If it is present inside array, return true.
     * If it is not present inside the array, return false;
     *
     * @param String string. Is the word that the user wants to check is contained
     *               inside of the DynamicArray
     * @return a boolean value for true if the word is present in the array and
     *         false if the word if not present.
     */
    public boolean contains(String string) {
        // set the resBoolean variable to be false because we assume that the word is
        // not in the array
        boolean resBoolean = false;
        // initialize variable i to 0 to "prime" the loop
        int i = 0;
        // while we still havent found the word and there are more words to check
        while (!resBoolean && i < this.nextAvailable) {
            // check if the current position of the array is equal to the word the user
            // inputed
            // if it is. Sets the value of resBoolean to true breaking out of the loop
            resBoolean = this.data[i++].equals(string);
        }
        return resBoolean;
    } // method contains

    /**
     * takes a String value from the user and returns the index of that word inside
     * of the array. If the word is not found it returns a -1
     *
     * @param String string. Is the word that the user wants to obtain the index of
     *               inside the DynamicArray
     * @return a int value that will contain the index of the String the user
     *         wanted. If not found the integer value will be set to -1.
     */
    public int indexOf(String string) {
        // set return index to be the indexOf_default_value which is -1.
        // Were assuming that the word is not inside of the array
        int resIndex = INDEXOF_DEFAULT_VALUE;
        // set integer i to 0 to "prime the loop"
        int i = 0;
        // while we still havent found the word were looking for 
        // and there are still more words to check
        while (resIndex == -1 && i < this.nextAvailable) {
            // if the current element in the array equals the word given by the user
            if (this.data[i].equals(string)) {
                // set the return Index to the value of I. 
                // Which also breaks out of the while loop
                resIndex = i;
            }
            // increment i by 1
            i++;
        }
        return resIndex;
    } // method indexOf

} // class DynamicArray