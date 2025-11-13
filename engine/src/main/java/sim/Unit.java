package sim;

import java.util.ArrayList;
import java.time.LocalTime;

public class Unit {
  private String name;
  private double speed;
  private Boolean hasEagle;
  private Boolean hasDDD;
  private int supLevelDDD;
  private ArrayList<Action> actionHistory;
  private double currentActionValue;

  public Unit(String name, double speed, Boolean hasEagle, Boolean hasDDD, int supLevelDDD) {
    this.name = name;
    this.speed = speed;
    this.hasEagle = hasEagle;
    this.hasDDD = hasDDD;
    this.supLevelDDD = supLevelDDD;

    this.actionHistory = new ArrayList<>();
    this.currentActionValue = 10000.0 / speed;
  }

  public Unit(double speed) {
    this("Unit_" + String.valueOf(LocalTime.now().hashCode()),
        speed,
        false,
        false,
        0);

    this.actionHistory = new ArrayList<>();
    this.currentActionValue = 10000.0 / this.speed;
  }

  public void takeAction() {
    Action action = new Action(this, getCurrentActionValue());
    addActionToActionHistory(action);

    double newAV = getCurrentActionValue() + getActionValueToNextAction();
    setCurrentActionValue(newAV);
  }

  public String getSummary() {
    String info = "Character: " + getName() + "\n" +
        "Speed: " + getSpeed() + "\n" +
        "AV: " + getCurrentActionValue() + "\n";

    return info;
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

  public double getCurrentActionValue() {
    return currentActionValue;
  }

  public void setCurrentActionValue(double currentActionValue) {
    this.currentActionValue = currentActionValue;
  }

  public double getActionValueToNextAction() {
    double av = 10000.0 / getSpeed();
    return av;
  }
}
