class Main {
  public static void main(String[] args) {
    HotelAlphabetical starBase = new HotelAlphabetical();
    starBase.addGuest("Jean-Luc", "Picard");
    starBase.addGuest("Kathryn", "Janeway");
    starBase.addGuest("Spock", "Sarek");
    starBase.addGuest("B'Elanna", "Torres");
    starBase.addGuest("James T.", "Kirk");
    starBase.addGuest("Deanna", "Troi");
    starBase.addGuest("Worf", "Mogh");
    starBase.addGuest("Benjamin", "Sisko");
    starBase.addGuest("Data", "Soong");
    starBase.addGuest("Leonard", "McCoy");
    starBase.addGuest("Jadzia", "Dax");
    starBase.addGuest("Miles", "O'Brien");
    starBase.addGuest("Nyota", "Uhura");
    starBase.addGuest("Quark", "");
    starBase.addGuest("Gul", "Dukat");
    starBase.addGuest("Hikaru", "Sulu");
    starBase.addGuest("Khan", "Noonien");
    starBase.addGuest("Chakotay", "");
    starBase.addGuest("Guinan", "");
    System.out.println(starBase.toString());
    HotelAlphabetical jngr = new HotelAlphabetical();
    jngr.addGuest("Nathan", "Johnson");
    jngr.addGuest("jacob", "mason");
    jngr.addGuest("james", "mason");
    jngr.addGuest("ethan", "mallory");
    jngr.addGuest("alex", "glas");
    // System.out.println(jngr);
    }
}