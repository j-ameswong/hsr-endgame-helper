package sim;

import java.util.Comparator;

public class ActionComparator implements Comparator<Action> {
  @Override
  public int compare(Action a, Action b) {
    return Double.compare(a.getActionValue(), b.getActionValue());
  }
}
