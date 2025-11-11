package sim;

import java.util.ArrayList;

public class Unit {
  private String name;
  private double speed;
  private Boolean hasEagle;
  private Boolean hasDDD;
  private int supLevelDDD;
  private ArrayList<Double> actionHistory;
  private double currentAV;

  public Unit(String name, double speed, Boolean hasEagle, Boolean hasDDD, int supLevelDDD) {
    this.name = name;
    this.speed = speed;
    this.hasEagle = hasEagle;
    this.hasDDD = hasDDD;
    this.supLevelDDD = supLevelDDD;

    this.actionHistory = new ArrayList<>();
    this.currentAV = 10000.0 / speed;
  }

  public String getSummary() {
    String info = "Character: " + getName() + "\n" +
        "Speed: " + getSpeed() + "\n" +
        "AV: " + getCurrentAV() + "\n";

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

  public ArrayList<Double> getActionHistory() {
    return actionHistory;
  }

  public void setActionHistory(ArrayList<Double> actionHistory) {
    this.actionHistory = actionHistory;
  }

  public double getCurrentAV() {
    return currentAV;
  }

  public void setCurrentAV(double currentAV) {
    this.currentAV = currentAV;
  }
}
