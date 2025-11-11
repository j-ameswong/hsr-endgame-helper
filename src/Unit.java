package src;

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
    String info = "";
    return info;
  }
}
