
// Do not import any packages except java.util.Scanner and java.net.URL
import java.util.Scanner;
import java.net.URL;

public class UniqueWords {

  // ############## IM DELETING ALL UNUSED THINGS ############

  /** Constant to determine size of array to hold words from book */
  static final int EXPECTED_WORDS = 10;

  /**
   * Change the class variable to be of type DynamicArray because it will
   * be used the most inside of this class. We also need to expand the size
   * which DynamicArray is good at
   */
  static DynamicArray words = new DynamicArray(EXPECTED_WORDS);

  /**
   * Show runner method: attempts to connect to a book, scan its words,
   * and save each unique word in an array that it returns.
   *
   * @param String link to the book we wish to scan
   * @return array with the words in the book containing no duplicates
   */
  public static String[] showRunner(String link) {
    // Make sure that the link provided is neither null nor empty
    if (link != null && link.length() > 0) {
      // Make sure that you can connect a Scanner to that link.
      Scanner book = scanURL(link);
      // We wrote method scanURL to return a null if connection is not possible.
      if (book != null) {
        // Scanner connection successful. Start at the beginning of the array
        // Pull one word out at a time and add it to the array
        while (book.hasNext()) {
          String word = book.next();
          // add the word to the DynamicArray if it is the first instance of it.
          // this Also increase the size of the array if it is full
          words.addUnique(word);
        } // end while for book scanner
      }
    }

    // call toArray() which I created inside of DynamicArray to convert the
    // DynamicArray into a String[] array so that we can return it
    return words.toArray();
  } // method uniqueWordsFromBook

  /**
   * Convert a string with a website link to a URL object that can be processed
   * by a Scanner. If the string does not point to a valid website, the method
   * returns a null pointer.
   *
   * @param link String to the website that we want to connect to.
   * @return a URL object to that website, or null if connection not possible.
   */
  public static URL connectToLink(String link) {
    URL connection;
    try {
      // If the website is good, convert it to a URL object
      connection = new URL(link);
    } catch (Exception e) {
      // If we can't connect to the website, prepare to return null.
      connection = null;
    }
    return connection;
  } // method connectToLink

  /**
   * Establish a Scanner connection to a website. If connection cannot be
   * established, method returns a null instead of throwing an exception.
   * 
   * Usage:
   * Scanner sc = scanURL("http://...");
   *
   * Scanner sc connects to the website provided. If connection is not
   * possible then sc == null.
   *
   * @param link String with link to the website to connect.
   * @return a Scanner for the contents of the website or a null pointer if
   *         a connection to the website cannot be established.
   */
  public static Scanner scanURL(String link) {
    // Assume attempt will fail, and we'll return null
    Scanner scanner = null;
    // Convert string with link to a URL object
    URL connection = connectToLink(link);
    // If conversion was not successful and we got a null, skip to return
    if (connection != null) {
      // Conversion was successful; try to pass it to Scanner
      try {
        scanner = new Scanner(connection.openStream());
      } catch (Exception e) {
        // If Scanner cannot open the URL, scanner remains null
        scanner = null;
      }
    }
    return scanner;
  } // method scanURL

}