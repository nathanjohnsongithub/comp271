
class Main {

  private static final String TEST_REPORT = "\n\nMethod .insert() %S some or all the tests.\n\n";

  public static void main(String[] args) {
    // Realistic line
    TrainLine redLineSB = new TrainLine();
    // Station names to add to line
    String[] stationNames = { "Howard", "Morse",
        "Loyola", "Thorndale", "Bryn Mawr" };
    // Empty line
    TrainLine mauveLine = new TrainLine();
    // Populate line with the stations
    for (String name : stationNames)
      redLineSB.addStation(name);
    // Set up the testing

    redLineSB.insert("Loyola", "Morse");
    System.out.print(redLineSB);

    String afterStation, newStation;
    int secret = redLineSB.getNumStations();
    boolean secret_test = true;
    // First test
    afterStation = "Howard";
    newStation = "Jarvis";
    boolean test1_insert = redLineSB.insert(afterStation, newStation);
    boolean test1_verify = redLineSB.toString().contains(newStation);
    secret_test = redLineSB.getNumStations() == ++secret;
    // Second test
    afterStation = "Bryn Mawr";
    newStation = "Berwyn";
    boolean test2_insert = redLineSB.insert(afterStation, newStation);
    boolean test2_verify = redLineSB.toString().contains(newStation);
    secret_test = redLineSB.getNumStations() == ++secret;
    // Third test
    afterStation = null;
    newStation = "Morse";
    boolean test3_before = redLineSB.insert(afterStation, newStation);
    boolean test3_after = redLineSB.insert(newStation, afterStation);
    boolean test3_both = redLineSB.insert(null, null);
    // Fourth test
    afterStation = "Jarvis";
    newStation = "Jarvis";
    boolean test4 = redLineSB.insert(afterStation, newStation);
    // Fifth test
    afterStation = "Hogsmeade";
    newStation = "Hogwarts";
    boolean test5 = mauveLine.insert(afterStation, newStation);
    // Test summary
    boolean test = ((test1_insert && test1_verify) &&
        (test2_insert && test2_verify) &&
        !test3_before && !test3_after && !test3_both &&
        !test4 &&
        !test5);
    // Report test results
    String outcome = (test) ? "passed" : "failed";
    System.out.printf(TEST_REPORT, outcome);
    // Case-by-case reporting
    outcome = (test1_insert && test1_verify) ? "passed" : "failed";
    System.out.printf("%s\tTest 1 (insertion after first)\n", outcome);
    outcome = (test2_insert && test2_verify) ? "passed" : "failed";
    System.out.printf("%s\tTest 2 (insertion after last)\n", outcome);
    outcome = (!test3_before && !test3_after && !test3_both) ? "passed" : "failed";
    System.out.printf("%s\tTest 3 (null argument)\n", outcome);
    outcome = (!test4) ? "passed" : "failed";
    System.out.printf("%s\tTest 4 (duplicate station)\n", outcome);
    outcome = (!test5) ? "passed" : "failed";
    System.out.printf("%s\tTest 5 (empty TrainLine)\n\n", outcome);
    outcome = (secret_test) ? "passed" : "failed";
    System.out.printf("%s\t(Secret test, numberOfStations++)\n\n", outcome);
  }
}