class Main {
  /**
   * NAIVE TESTING CODE -- DO NOT MODIFY 
   */
  public static void main(String[] args) {

    // Realistic line
    TrainLine redLineSB = new TrainLine();
    redLineSB.addStation("Howard");
    redLineSB.addStation("Jarvis");
    redLineSB.addStation("Morse");
    redLineSB.addStation("Loyola");
    redLineSB.addStation("Granville");
    redLineSB.addStation("Thorndale");
    redLineSB.addStation("Bryn Mawr");

    // Imaginary line
    TrainLine hogwarts = new TrainLine();
    // Create a few stations first
    TrainStation kin = new TrainStation("King's Cross");
    TrainStation min = new TrainStation("Ministry of Magic");
    TrainStation wiz = new TrainStation("Wizengamot Way");
    TrainStation the = new TrainStation("Thestral Terminal");
    TrainStation hip = new TrainStation("Hippogriff Halt");
    TrainStation pat = new TrainStation("Patronus Pass");
    TrainStation fbd = new TrainStation("Forbidden Forest");
    TrainStation pro = new TrainStation("Professor's Platform");
    TrainStation hgj = new TrainStation("Hogsmeade Junction");
    TrainStation hog = new TrainStation("Hogwarts");
    hogwarts.addStation(kin);
    hogwarts.addStation(min);
    hogwarts.addStation(wiz);
    hogwarts.addStation(the);
    hogwarts.addStation(hip);
    hogwarts.addStation(pat);
    hogwarts.addStation(fbd);
    hogwarts.addStation(pro);
    hogwarts.addStation(hgj);
    hogwarts.addStation(hog);
    hog.setNext(hip);

    // Empty line
    TrainLine empty = new TrainLine();

    boolean testRedLineSB = redLineSB.hasLoop();
    boolean testHogwarts = hogwarts.hasLoop();
    boolean testEmpty = empty.hasLoop();

    boolean testIndexOfMorse = redLineSB.indexOf("Morse") == 2;
    boolean testIndexOfBM = redLineSB.indexOf("Bryn Mawr") == 6;
    boolean testIndexOfKimball = redLineSB.indexOf("Kimball") == -1;
    boolean testIndexOfEmpty = empty.indexOf("Morse") == -1;

    boolean testResult = (!testRedLineSB && testHogwarts && !testEmpty &&
        testIndexOfMorse && testIndexOfKimball &&
        testIndexOfEmpty && testIndexOfBM);

    String test = (testResult) ? "successfully" : "unsuccessfully";

    System.out.printf("\n\n\nYour code tests %S.\n\n\n", test);
  }
}