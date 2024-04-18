
class Hammock {
  private String firstName;
  private String lastName;
  private Hammock next;
  public Hammock(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.next = null;
  }

  public String getFirstName() { return this.firstName; }
  public String getLastName() { return this.lastName; }
  public String toString() { return this.firstName + " " + this.lastName; } 
  public void setNext(Hammock next) { this.next = next; }
  public boolean hasNext() { return this.next != null; }
  public Hammock getNext() { return this.next; }
}
