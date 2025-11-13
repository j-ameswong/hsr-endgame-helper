package sim;

public class Action {
  private final Unit unit;
  private double actionValue;
  private Action prevAction;
  private Action nextAction;

  public Action(Unit unit, double actionValue) {
    this.unit = unit;
    this.actionValue = actionValue;
  }

  public Action(Unit unit, Action prevAction) {
    this.unit = unit;
    this.prevAction = prevAction;
    prevAction.setNextAction(this);

    this.actionValue = prevAction.actionValue + (10000 / unit.getSpeed());
  }

  public Unit getUnit() {
    return unit;
  }

  public double getActionValue() {
    return actionValue;
  }

  public void setActionValue(double actionValue) {
    this.actionValue = actionValue;
  }

  public Action getPrevAction() {
    return prevAction;
  }

  public void setPrevAction(Action prevAction) {
    this.prevAction = prevAction;
  }

  public Action getNextAction() {
    return nextAction;
  }

  public void setNextAction(Action nextAction) {
    this.nextAction = nextAction;
  }

  @Override
  public String toString() {
    return getUnit().getName() + " <ACTION> at " + String.valueOf(getActionValue());
  }
}
