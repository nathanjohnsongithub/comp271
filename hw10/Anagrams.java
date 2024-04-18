public class Anagrams {

  /*
   * Variable that is the size of the alphabet which will be used to declare
   * the array of all of the letters in the alphabet
   */
  private static final int ALPHABET_SIZE = 26;
  /*
   * Variable that has the ASCII value of capatilized A which will be used to
   * convert the letters from 65-90 to the range 0-25 for the array index
   * Where A is 0 and Z is 25
   */
  private static final int ASCII_OFFSET = 65;

  /**
   * Determine if two strings are anagrams of each other. Assume that
   * the strings contain only valid letters. Letter-case is not important,
   * for example, "BAT" and "Tab" are still anagrams.
   *
   * This method works by creating 2 arrays with the size of the alphabet.
   * Each array will be filled with the number of times a letter appears in
   * the each of the strings. If the number of letters inside both of the
   * arrays at every index is the same. They must be anagrams.
   *
   * @param first  String one of the two strings to compare
   * @param second String the other of the two strings to compare
   * @return true if first and second strings are anagrams of eachother,
   *         false otherwise or if either string is null
   */
  public static boolean areAnagrams(String first, String second) {
    // Guard statement
    if (first.length() != second.length() || first.isEmpty() || second.isEmpty()) {
      /*
       * If they're not that same length or one or both are empty
       * we know they cannot be anagrams so we stop the program
       * NOTE. Im unsure if you assume two empty strings are anagram.
       * If they are, can remove the first.isEmpty() and second.isEmpty()
       * and the program will work with empty strings.
       */
      return false;
    }
    // Return variable to keep track of if the two words are anagrams
    boolean theyAre = true;
    // We create two arrays to store the count of each letter of the strings.
    int[] fCount = new int[ALPHABET_SIZE];
    int[] sCount = new int[ALPHABET_SIZE];
    // Convert both of the string to lowercase because they're case insensitive
    first = first.toUpperCase();
    second = second.toUpperCase();
    // We loop through each letter of both string and count them inside the array.
    // We know theyre the same length so this works we dont have to
    // worry about running out of characters in the second string
    for (int i = 0; i < first.length(); i++) {
      fCount[first.charAt(i) - ASCII_OFFSET]++;
      sCount[second.charAt(i) - ASCII_OFFSET]++;
    }
    // Loop counter for the arrays
    int i = 0;
    // If they still have the possibility of being anagrams
    // I.E The arrays with the counts are still the same
    // and we arent out of letters to check, we keep looping
    while (theyAre && i < ALPHABET_SIZE) {
      // check if the counts are equal. If they are, we keep looping
      // If not, we stop and return that theyre not anagram
      theyAre = fCount[i] == sCount[i];
      i++;
    }
    return theyAre;
  } // method areAnagrams
}