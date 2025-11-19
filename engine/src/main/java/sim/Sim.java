package sim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import sim.gamemodes.GameMode;

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
    this.actionLog = new ArrayList<>();

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

    boolean wasChanged = false;
    List<Action> pendingActions = new ArrayList<>(getActionLog()
        .stream()
        .sorted(new ActionComparator())
        .toList());
    // Battle begins, get turns sorted by AV
    do {
      List<Action> toRemove = new ArrayList<>();
      for (Action a : pendingActions) {
        // Turn starts
        Unit currentUnit = a.getUnit();
        if (currentUnit.getNextActionValue() < getTotalActionValue()) {
          toRemove.add(currentUnit.takeAction(ActionType.BASIC));
          wasChanged = true;
        } else {
          wasChanged = false;
        }

        // ActionType actionType;
        // if (a.getUnit().hasDDD()) {
        // actionType = ActionType.ULTIMATE;
        // // for (int i = actionLog.indexOf(a) + 1; i < actionLog.size(); i++) {
        // // System.out.println("Advance action: " + actionLog.get(i));
        // // }
        // } else {
        // actionType = ActionType.BASIC;
        // }
        //
        // a.getUnit().takeAction(actionType);

        // Turn ends
        if (true) {

        } // if have enough energy...
      }
      pendingActions.removeAll(toRemove);
    } while (wasChanged);

    // List all actions
    for (Action a : getActionLog()) {
      System.out.println(a);
    }
  }

  public void unitsEnterBattle() {
    for (Unit u : getUnits()) {
      System.out.println("Setting up character " + u.getName() + "...");
      u.setSim(this);
      if (u.getNextActionValue() < getTotalActionValue()) {
        u.takeAction(ActionType.ENTER_BATTLE);
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

  // public double getFirstActionValue

  public List<Action> getPendingActions(double actionValue) {
    return getActionLog().stream()
        .filter(action -> action.getActionValue() > actionValue)
        .sorted(new ActionComparator())
        .toList();
  }

  public void addToActionLog(Action a) {
    getActionLog().add(a);
  }
}
