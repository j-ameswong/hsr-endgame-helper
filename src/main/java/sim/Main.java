package sim;

public class Main {
  public static void main(String[] args) {
    // Unit unit = new Unit("Sparkle", 160, false, false, 5);
    Unit testUnit = new Unit(160);
    // String summary = unit.getSummary();

    String summary = testUnit.getSummary();
    System.out.println(summary);
  }
}
