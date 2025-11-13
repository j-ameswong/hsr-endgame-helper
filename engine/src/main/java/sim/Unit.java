package sim;

import java.util.ArrayList;

public class Unit {
  private String name;
  private double speed;
  private Boolean hasEagle;
  private Boolean hasDDD;
  private int supLevelDDD;
  private ArrayList<Action> actionHistory;

  public Unit(String name, double speed, Boolean hasEagle, Boolean hasDDD, int supLevelDDD) {
    this.name = name;
    this.speed = speed;
    this.hasEagle = hasEagle;
    this.hasDDD = hasDDD;
    this.supLevelDDD = supLevelDDD;

    this.actionHistory = new ArrayList<>();
  }

  public Unit(double speed) {
    this("Unit_" + String.valueOf(Math.random()).substring(0, 10),
        speed,
        false,
        false,
        0);

    this.actionHistory = new ArrayList<>();
  }

  public void takeAction() {
    Action action;
    if (getActionHistory().isEmpty()) {
      action = new Action(this, getActionValueToNextAction());
    } else {
      action = new Action(this, getLastAction());
    }

    addActionToActionHistory(action);
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

  public Boolean getHasEagle() {
    return hasEagle;
  }

  public void setHasEagle(Boolean hasEagle) {
    this.hasEagle = hasEagle;
  }

  public Boolean getHasDDD() {
    return hasDDD;
  }

  public void setHasDDD(Boolean hasDDD) {
    this.hasDDD = hasDDD;
  }

  public int getSupLevelDDD() {
    return supLevelDDD;
  }

  public void setSupLevelDDD(int supLevelDDD) {
    this.supLevelDDD = supLevelDDD;
  }

  public ArrayList<Action> getActionHistory() {
    return actionHistory;
  }

  public void setActionHistory(ArrayList<Action> actionHistory) {
    this.actionHistory = actionHistory;
  }

  public void addActionToActionHistory(Action action) {
    this.actionHistory.add(action);
  }

  public Action getLastAction() {
    return getActionHistory().getLast();
  }

  public double getNextActionValue() {
    return getLastAction().getActionValue() + getActionValueToNextAction();
  }

  public double getActionValueToNextAction() {
    double av = 10000.0 / getSpeed();
    return av;
  }
}
