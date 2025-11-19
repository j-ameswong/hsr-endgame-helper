package sim;

import java.util.List;

public class ActionHandler {
  private Sim sim;

  public ActionHandler(Sim sim) {
    this.sim = sim;
  }

  public static void execute(Action a) {
    // DDD
    if (a.getActionType().equals(ActionType.ULTIMATE) &&
        a.getUnit().hasDDD()) {
      List<Action> actions = a.getUnit().getSim().getPendingActions();
      for (Action act : actions.stream()
          .filter(thisAction -> thisAction.getActionValue() > a.getActionValue())
          .toList()) {
        System.out.println("Would execute DDD on " + act);
      }
    }
    a.execute();
  }
}
