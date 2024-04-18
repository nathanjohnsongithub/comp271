/**
 * A rather spooky hotel, where guests are sent to one of its 26 rooms, and
 * stay on a hammock; if there are multiple guests assigned to a room, the
 * most recent guest get the top hammock and the remaining guests' hammocks
 * are appended below the first guest's hammoc.
 */
public class HotelAlphabetical {

  /** Constant with number of letters in alphabet */
  private static final int NUMBER_OF_ROOMS = 26;

  /** The final variables for what we want the toString() method to print out 
      Using multiple variables because I didnt want one gigantic String    */
  private static final String TO_STRING1 = "There are %d guest at the hotel, occuping %d rooms.\n";
  private static final String TO_STRING2 = "%d rooms are currently empty\n\n";
  private static final String TO_STRING3 = "The longest last name we have is %d characters long.";

  /** Array of chainable hammocks. One hammock chain per room */
  Hammock[] rooms = new Hammock[NUMBER_OF_ROOMS];

  /** Class variable to keep track of the number of guests in the hotel */
  private int numberOfGuests = 0;
  
  /** Class variable to keep track of the number of Used rooms in the hotel */
  private int numberOfUsedRooms = 0;

  /** Class variable to keep track of the longest Lastname in the hotel */
  private int longestLastName = 0;

  /**
   * Determine if a char is an actual letter.
   * (Yes, we can do this with a regex but where's the fun in that?)
   * 
   * @param character char to determine if it's an actual letter
   * @return true if character is Aa-Zz, false otherwise.
   */
  static public boolean isLetter(char character) {
    return ((character >= 'A' && character <= 'Z') ||
        (character >= 'a' && character <= 'z'));
  } // isLetter

  /**
   * Determine if a string starts with an true alphabetical character.
   * 
   * @param string String we test to see if first char is letter.
   * @return true if first character is letter, false otherwise
   *         or when string is null or empty.
   */
  static public boolean startsWithLetter(String string) {
    return (string != null && // guard against null entry
        string.length() > 0 && // guard against empty string
        isLetter(string.charAt(0)) // check string's first char
    );
  } // method startsWithLetter

  private static int assignRoom(String lastName) {
      return  ((int) lastName.toUpperCase().charAt(0) - (int) 'A')%NUMBER_OF_ROOMS;
  }

  /**
   * Add a guest to the hotel by finding the corresponding room label
   * and sending them to that room with their hammock.
   *
   * @param firstName string with guest's first name
   * @param lastName  string with guest's last name
   */
  public void addGuest(String firstName, String lastName) {
    // Guard againsts invalid last name
    if (startsWithLetter(lastName)) {
      // Get first letter of last name in upper case
      char firstLetter = lastName.toUpperCase().charAt(0);
      // Convert first letter into an int beween 0 and 26 to find the
      // array position for this guest's hammock.
      int number = assignRoom(lastName);
      // Hammock for the new guest
      Hammock newGuest = new Hammock(firstName, lastName);
      // we now have another guest. So increment it
      this.numberOfGuests++;
      // Check if the new guests last name is longer than the current longest
      if (this.longestLastName < lastName.length()) {
        // if it is, set it as the longest last name
        this.longestLastName = lastName.length();
      }
      // Let's check the room where guest should go. Anyone already there?
      if (this.rooms[number] == null) {
        // Room empty, first hammock in room is for new guest.
        rooms[number] = newGuest;
        // One more room is now occupied. So increment numberOfUsedRooms
        this.numberOfUsedRooms++;
      } else {
        // Room not empty. Guest removes existing hammocks, adds theirs,
        // and attaches other hammocks to their hammock.
        newGuest.setNext(this.rooms[number]);
        this.rooms[number] = newGuest;
      }
    }
  } // method addGuest

  /**
   * String representation of HotelAlphabetical
   *
   * @return String representation of object
   */
  public String toString() {
    // Return the String format of the TO_STRING final variables we made.
    // Then insert the values of the private variables using format placeholders
    return String.format(TO_STRING1 + TO_STRING2 + TO_STRING3, this.numberOfGuests, 
                        this.numberOfUsedRooms, NUMBER_OF_ROOMS - this.numberOfUsedRooms, 
                        this.longestLastName);
  }
}