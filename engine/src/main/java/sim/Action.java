package sim;

public class Action {
  private final Unit unit;

  private final ActionType actionType;
  private double actionValue;
  private Action prevAction;
  private Action nextAction;

  public Action(Unit unit, double actionValue, ActionType actionType) {
    this.unit = unit;
    this.actionValue = actionValue;
    this.actionType = actionType;
  }

  public Action(Unit unit, Action prevAction, ActionType actionType) {
    this.unit = unit;
    this.prevAction = prevAction;
    prevAction.setNextAction(this);
    this.actionType = actionType;

    this.actionValue = unit.getNextActionValue();
  }

  public void execute() {
    getUnit().getSim().addToActionLog(this);
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

  public ActionType getActionType() {
    return actionType;
  }

  @Override
  public String toString() {
    return getUnit().getName() + " " + getActionType() + " at " + String.format("%.2f", getActionValue());
  }
}
