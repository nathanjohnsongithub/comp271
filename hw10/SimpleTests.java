import java.util.Random;

/** Simple testing */
class SimpleTests {

  static int tests = 100;
  static Random random = new Random();
  static LinkedList demo;
  static String[] letters = {
      "a", "b", "c", "d", "e", "f", "g",
      "h", "i", "j", "k", "l", "m", "n",
      "o", "p", "q", "r", "s", "t" };

  public static void loadData() {
    demo = new LinkedList();
    for (String load : letters) {
      demo.add(load);
    }
  } // method main

  public static void testHasNext() {
    loadData();
    boolean success;
    int test = 0;
    int index;
    do {
      index = random.nextInt(tests);
      if (index < letters.length) {
        success = demo.getHead().hasNext(index);
      } else {
        success = !demo.getHead().hasNext(index);
      }
      test += 1;
    } while (test < tests && success);
    if (!success) {
      System.out.println(index);
    }
    String all = (success) ? "ALL" : "Only a few";
    System.out.printf("\n\n%s tests hasNext(int) were successful (%d/%d)",
        all, test, tests);
  } // method testHasNext

  public static void testGetNext() {
    loadData();
    boolean success;
    int test = 0;
    int index;
    do {
      index = random.nextInt(tests);
      if (index < letters.length - 1) {
        success = (demo.getHead()
            .getNext(index)
            .getPayload()
            .equals(letters[index + 1]));
      } else {
        success = demo.getHead().getNext(index) == null;
      }
      test += 1;
    } while (test < tests && success);
    if (!success) {
      System.out.println(index);
    }
    String all = (success) ? "ALL" : "Only a few";
    System.out.printf("\n\n%s tests of getNext(int) were successful (%d/%d)",
        all, test, tests);
  } // method testGetNext

  public static void testAreAnagrams() {
    boolean anagram1 = Anagrams.areAnagrams("tab", "bat");
    boolean anagram2 = Anagrams.areAnagrams("listen", "silent");
    boolean anagram3 = Anagrams.areAnagrams("tab", "cat");
    boolean anagram4 = Anagrams.areAnagrams("tab", "bats");
    boolean anagram5 = Anagrams.areAnagrams("", "bat");
    boolean anagram6 = Anagrams.areAnagrams("TAB", "bat");
    boolean result = anagram1 && anagram2 &&
        !anagram3 && !anagram4 &&
        !anagram5 && anagram6;
    if (result) {
      System.out.println("\n\nMethod areAnagrams(String, String) works fine.\n");
    } else {
      System.out.println("\n\nMethod areAnagrams(String, String) is not quite ready yet.\n");
    }
  }
}