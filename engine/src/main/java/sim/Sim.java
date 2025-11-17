package sim;

import java.util.ArrayList;
import java.util.Arrays;

import sim.gamemodes.*;

public class Sim {
  private ArrayList<Unit> units;
  private ArrayList<Action> actionLog;
  private GameMode gameMode;
  private int numCycles;
  private double totalActionValue;

  public Sim(ArrayList<Unit> units, GameMode gameMode, int numCycles) {
    this.units = units;
    this.gameMode = gameMode;
    this.numCycles = numCycles;

    this.totalActionValue = gameMode.firstCycleActionValue +
        (numCycles * gameMode.subsequentCyclesActionValue);
  }

  // default simulator
  public Sim() {
    ArrayList<Unit> units = new ArrayList<>();
    units.addAll(Arrays.asList(
        new Unit(133.4), new Unit(160, true, false)));

    this(units, GameMode.MEMORY_OF_CHAOS, 1);
  }

  public void run() {
    // Initialize
    // Load each unit's first action
    unitsEnterBattle();

    ArrayList<Action> actionLog = getActionLog();
    Boolean wasChanged;
    do {
      wasChanged = false;
      for (Action a : actionLog) {
        // Turn starts
        if (a.getUnit().getNextActionValue() < getTotalActionValue()) {
          ActionType actionType;
          if (a.getUnit().hasDDD()) {
            actionType = ActionType.ULTIMATE;
            // for (int i = actionLog.indexOf(a) + 1; i < actionLog.size(); i++) {
            // System.out.println("Advance action: " + actionLog.get(i));
            // }
          } else {
            actionType = ActionType.BASIC;
          }

          a.getUnit().takeAction(actionType);
          wasChanged = true;
        }

        // Turn ends
        if (true) {

        } // if have enough energy...
      }

      for (Action a : getActionLog()) {
        System.out.println(a);
      }
      System.out.println("--------------------------");
    } while (wasChanged);

    // List all actions
    // for (Action a : getActionLog()) {
    // System.out.println(a);
    // }
  }

  public void unitsEnterBattle() {
    for (Unit u : getUnits()) {
      if (u.getNextActionValue() < getTotalActionValue()) {
        u.takeAction(ActionType.ENTER_BATTLE);

        System.out.println(u.getActionLog().get(0));
      }
    }
  }

  public ArrayList<Unit> getUnits() {
    return units;
  }

  public void setUnits(ArrayList<Unit> units) {
    this.units = units;
  }

  public GameMode getGameMode() {
    return gameMode;
  }

  public void setGameMode(GameMode gameMode) {
    this.gameMode = gameMode;
  }

  public int getNumCycles() {
    return numCycles;
  }

  public void setNumCycles(int numCycles) {
    this.numCycles = numCycles;
  }

  public double getTotalActionValue() {
    return totalActionValue;
  }

  public void setTotalActionValue(double totalActionValue) {
    this.totalActionValue = totalActionValue;
  }

  public ArrayList<Action> getActionLog() {
    return this.actionLog;
  }

  public void addToActionLog(Action a) {
    getActionLog().add(a);
  }
}
