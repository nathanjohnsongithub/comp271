
/**
 * Implementation class for UniqueWords with DynamicArray
 */
class Main {
    public static void main(String[] args) {
    // Book used here: US Declaration of Independence
    String bookLink = "https://www.gutenberg.org/cache/epub/1/pg1.txt";
    int numberOfWords = UniqueWords.showRunner(bookLink).length;
    System.out.printf("\nFound %d unique words.\n", numberOfWords);
  }
}