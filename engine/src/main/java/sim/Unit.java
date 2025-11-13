package sim;

import java.util.ArrayList;

public class Unit {
  private String name;
  private double speed;
  private Boolean hasEagle;
  private Boolean hasDDD;
  private int supLevelDDD;
  private double actionValueToNext;
  private ArrayList<Action> actionLog;

  public Unit(String name, double speed, Boolean hasEagle, Boolean hasDDD, int supLevelDDD) {
    this.name = name;
    this.speed = speed;
    this.hasEagle = hasEagle;
    this.hasDDD = hasDDD;
    this.supLevelDDD = supLevelDDD;

    this.actionLog = new ArrayList<>();
  }

  public Unit(double speed) {
    this("Unit_" + String.valueOf(Math.random()).substring(2, 10),
        speed,
        false,
        false,
        0);
  }

  public Unit(double speed, Boolean hasDDD, Boolean hasEagle) {
    this(speed);
    this.hasEagle = hasEagle;
    this.hasDDD = hasDDD;
  }

  public void takeAction(ActionType actionType) {
    Action action;
    if (getActionLog().isEmpty()) {
      action = new Action(this, getActionValue(), actionType);
    } else {
      action = new Action(this, getLastAction(), actionType);
    }

    addActionToActionLog(action);
  }

  public String getState() {
    String info = "Character: " + getName() + "\n" +
        "Speed: " + getSpeed() + "\n" +
        "Last Action: " + getLastAction() + "\n";

    return info;
  }

  public String getSummary() {
    String summary = "Character " + getName() +
        " has " + getSpeed() + " SPD";

    return summary;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public Boolean hasEagle() {
    return hasEagle;
  }

  public Boolean hasDDD() {
    return this.hasDDD;
  }

  public int getSupLevelDDD() {
    return supLevelDDD;
  }

  public void setSupLevelDDD(int supLevelDDD) {
    this.supLevelDDD = supLevelDDD;
  }

  public ArrayList<Action> getActionLog() {
    return actionLog;
  }

  public void setActionLog(ArrayList<Action> actionLog) {
    this.actionLog = actionLog;
  }

  public void addActionToActionLog(Action action) {
    this.actionLog.add(action);
  }

  public Action getLastAction() {
    return getActionLog().getLast();
  }

  public double getNextActionValue() {
    double actionValue = getActionValue();
    if (!getActionLog().isEmpty()) {
      actionValue += getLastAction().getActionValue();
    }

    return actionValue;
  }

  public double getActionValue() {
    double av = 10000.0 / getSpeed();
    return av;
  }
}
