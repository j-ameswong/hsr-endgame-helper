package sim;

public class Main {
  public static void main(String[] args) {
    // Unit unit = new Unit("Sparkle", 160, false, false, 5);
    Unit testUnit = new Unit(160);

    String summary = testUnit.getSummary();
    System.out.println(summary);

    // Generate initial actions
    for (int i = 0; i < 3; i++) {
      testUnit.takeAction();
      for (Action action : testUnit.getActionHistory()) {
        System.out.println(action);
      }
    }

  }
}
