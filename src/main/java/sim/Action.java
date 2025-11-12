package sim;

public class Action {
  private final Unit unit;
  private double actionValue;

  public Action(Unit unit, double actionValue) {
    this.unit = unit;
    this.actionValue = actionValue;
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

  @Override
  public String toString() {
    return getUnit().getName() + " acting at " + String.valueOf(getActionValue());
  }
}
