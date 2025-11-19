package sim;

import java.util.ArrayList;
import java.util.List;

public class Unit {
  private String name;
  private double speed;
  private Boolean hasEagle;
  private Boolean hasDDD;
  private int supLevelDDD;
  private Sim sim;

  public Unit(String name, double speed, Boolean hasEagle, Boolean hasDDD, int supLevelDDD) {
    this.name = name;
    this.speed = speed;
    this.hasEagle = hasEagle;
    this.hasDDD = hasDDD;
    this.supLevelDDD = supLevelDDD;
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

  public Action generateAction(ActionType actionType) {
    Action action;
    if (getActionLog().isEmpty()) {
      action = new Action(this, getActionValue(), actionType);
    } else {
      action = new Action(this, getLastAction(), actionType);
    }

    System.out.println("Generating action for unit " + getName());
    return action; // TODO: Add enum for types of actions, to trigger AA and amount etc...
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

  // List of actions reference actionLog in Sim()
  public List<Action> getActionLog() {
    if (getSim().getActionLog().isEmpty()) {
      return new ArrayList<>();
    } else {
      return getSim().getActionLog().stream()
          .filter(action -> action.getUnit().equals(this))
          .toList();
    }
  }

  public Action getLastAction() {
    return getActionLog().getLast();
  }

  public double getNextActionValue() {
    double actionValue = getActionValue();
    if (!getActionLog().isEmpty() &&
        !getLastAction().getActionType().equals(ActionType.ENTER_BATTLE)) {
      return actionValue + getLastAction().getActionValue();
    } else {
      return actionValue;
    }
  }

  public double getActionValue() {
    double av = 10000.0 / getSpeed();
    return av;
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

  public Sim getSim() {
    return this.sim;
  }

  public void setSim(Sim sim) {
    this.sim = sim;
  }
}
