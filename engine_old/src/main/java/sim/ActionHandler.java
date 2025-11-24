package sim;

import java.util.List;

public class ActionHandler {
  private Sim sim;

  public ActionHandler(Sim sim) {
    this.sim = sim;
  }

  public void execute(Action a) {
    // If action advance is involved
    Unit unit = a.getUnit();
    if (a.getActionType().equals(ActionType.ULTIMATE)) {
      double actionAdvance = 0.0;

      if (unit.hasDDD()) {
        actionAdvance += 0.16 + (0.02 * unit.getSupLevelDDD());
      }

      // TODO: Deal with eagle set... maybe creating an action advance class
    }
    if (a.getActionType().equals(ActionType.ULTIMATE) &&
        a.getUnit().hasDDD()) {
      List<Action> actions = sim.getLastActions();
      for (Action act : actions.stream()
          .filter(thisAction -> thisAction.getActionValue() > a.getActionValue())
          .toList()) {
        System.out.println("Would execute DDD on " + act);
      }
    }
    a.execute();
  }
}
