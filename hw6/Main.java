class Main {
  public static void main(String[] args) {
    final String PUSH =   "  Push test";
    final String POP =    "   Pop test";
    final String APPEND = "Append test";
    String[] testData = {
        "Thrumdell",
        "Glimmerforge",
        "Mistveil",
        "Oakenthorn"
    }; // Middle earth sounding localities
    InNOut testObject = new InNOut(4);
    boolean test1 = true;
    for (String item: testData) {
      test1 = test1 && testObject.push(item);
      System.out.println(testObject);
    }
    test1 = test1 && !testObject.push("Ravencliff");
    String outcome = (test1) ? "Successful" : "Not successful";
    System.out.printf("\n\n%s: %s", PUSH, outcome);
    int i = testData.length-1;
    boolean test2 = true;
    while (i >=0 && testObject.getSize()>0) {
      test2 = test2 && testObject.pop().equals(testData[i--]);
    }
    test2 = test2 && testObject.pop() == null;
    outcome = (test2) ? "Successful" : "Not successful";
    System.out.printf("\n%s: %s", POP, outcome);
    boolean test3 = true;
    for (String item: testData) {
      test3 = test3 && testObject.append(item);
    }
    test3 = test3 && !testObject.append("Ravencliff");
    outcome = (test3) ? "Successful" : "Not successful";
    System.out.printf("\n%s: %s", APPEND, outcome);
    boolean test4 = true;
    i = 0;
    while (i < testData.length && testObject.getSize() > 0) {
      test4 = test4 && testObject.pop().equals(testData[i++]);
    }
    test4 = test4 && testObject.pop() == null;
    outcome = (test4) ? "Successful" : "Not successful";
    System.out.printf("\n%s: %s\n\n", POP, outcome);
  } // main
} // Main