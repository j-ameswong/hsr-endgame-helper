package sim;

import sim.Unit;

public class Main {
  public static void main(String[] args) {
    Unit unit = new Unit("Sparkle", 160, false, false, 5);
    String summary = unit.getSummary();

    System.out.println(summary);
  }
}
