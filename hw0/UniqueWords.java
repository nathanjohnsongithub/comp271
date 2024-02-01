
// Do not import any packages except java.util.Scanner and java.net.URL
import java.util.Scanner;
import java.net.URL;

public class UniqueWords {

    /** Constant to determine size of array to hold words from book */
    static final int EXPECTED_WORDS = 10;

    /** Array with words */
    static String[] words = new String[EXPECTED_WORDS];

    /** More recently added element in words */
    static int mostRecentElement = -1;

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
                    // Check if the array is full
                    if (mostRecentElement == words.length - 1) {
                        // Most recent element is at the last position of the array
                        // Array is full!
                        // METHOD resize() DONES NOT EXIST. GO TO LINE 93 TO BUILD IT
                        words = resize(words);
                    }
                    // At this point we can be certain that the array words is not full.
                    // Either because we just resized it or because it didn't need
                    // resizing, so it has available space.
                    // Check if word is already in array; if not, add it.
                    if (!isPresent(word)) {
                        // word is not in the array.
                        addWord(word);
                        // System.out.println(Arrays.toString(words));
                    }
                } // end while for book scanner
            }
        }
        // Return the array
        return words;
    } // method uniqueWordsFromBook

    /**
     * Determine if a word is in the array. The method searches array
     * wordsFromBook up to and including the mostRecentElement. We
     * assume that mostRecentElement < wordsFromBook.length.
     *
     * @param String word to search from in array wordsFromBook
     * @return true if word is found among the use portion of the array (up to
     *         and including [mostRecentElement]), otherwise false.
     */
    public static boolean isPresent(String word) {
        // Assume the word you're looking for is not in the array
        boolean found = false;
        // Scan the array from the top
        int i = 0;
        // Up to the most recently occupied element
        while (!found && i < mostRecentElement) {
            found = words[i].equals(word);
            i++;
        }
        return found;
    } // method isWordPresent

    /**
     * Add a word in the next available position of array words
     */
    public static void addWord(String word) {
        mostRecentElement++;
        words[mostRecentElement] = word;
    } // method addWord

    /**
     * My goal for this method was to create a new array that is 10x the current
     * size of 'words'
     * Then copy all of the elements of words into the new array and return the new
     * array
     * Decided to choose a large size to increase so its less copying of elements 
     * and therefore more efficent
     *
     * @param words is the String array containing all of the words we have pulled
     *              from the book so far
     * @return return type is a String array that will contain the same elements as
     *         words with double the size. the other part of the array will just be
     *         null
     */
    public static String[] resize(String[] words) {
        // Define a final variable for the multiple we use to double the size of the
        // array
        final int ARRAY_MULTIPLIER = 10;
        // Define the array we will be returning as double the size of the current array
        // 'words'
        String[] biggerWords = new String[words.length * ARRAY_MULTIPLIER];
        // loop through the size of 'words' and copy the values from 'words' to the new
        // array 'biggerWords'
        for (int i = 0; i < words.length; i++) {
            biggerWords[i] = words[i];
        }
        // return
        return biggerWords;
    } // method resize

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