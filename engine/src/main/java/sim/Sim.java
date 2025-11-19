package sim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sim.gamemodes.GameMode;

public class Sim {
  private ArrayList<Unit> units;
  private ArrayList<Action> actionLog;
  private List<Action> pendingActions;
  private ActionHandler actionHandler;
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
        new Unit("Blade", 134, true, false, 0),
        new Unit("Sparkle", 160, true, true, 5)));

    this(units, GameMode.MEMORY_OF_CHAOS, 1);
  }

  public void run() {
    // Initialize
    actionHandler = new ActionHandler(this);
    System.out.println(this);
    // Load each unit's first action
    unitsEnterBattle();

    boolean actionWasTaken = false;
    this.pendingActions = getPendingActions(-1);
    // Battle begins, get turns sorted by AV
    List<Action> toAdd = new ArrayList<>();
    do {
      System.out.println("--------------------------\n" + pendingActions);
      for (Action a : pendingActions) {
        // Turn starts
        Unit currentUnit = a.getUnit();
        // System.out.println("Next turn for " + currentUnit.getName() + " at " +
        if (currentUnit.getNextActionValue() < getTotalActionValue()) {
          Action newAction = currentUnit.generateAction(ActionType.ULTIMATE);
          ActionHandler.execute(newAction);
          toAdd.add(newAction);
          actionWasTaken = true;
        } else {
          actionWasTaken = false;
        }
      }
      if (actionWasTaken) {
        pendingActions = new ArrayList<>(toAdd);
      } else {
        pendingActions = new ArrayList<>();
      }

      toAdd = new ArrayList<>();
      // System.out.println("Current list of pending actions: " +
      // getPendingActions());
    } while (actionWasTaken);

    // List all actions
    for (Action a : getActionLog()) {
      System.out.println("- " + a);
    }
  }

  public void unitsEnterBattle() {
    for (Unit u : getUnits()) {
      System.out.println("> Setting up character " + u.getName() + "...");
      u.setSim(this);
      if (u.getNextActionValue() < getTotalActionValue()) {
        ActionHandler.execute(u.generateAction(ActionType.ENTER_BATTLE));
      }
    }

    System.out.println("\n");
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

  public List<Action> getPendingActions() {
    return this.pendingActions;
  }

  public void addToPendingActions(Action a) {
    getPendingActions().add(a);
  }

  public ArrayList<Action> getActionLog() {
    return this.actionLog;
  }

  // public double getFirstActionValue

  public List<Action> getPendingActions(double actionValue) {
    return new ArrayList<>(getActionLog().stream()
        .filter(action -> action.getActionValue() > actionValue)
        .sorted(new ActionComparator())
        .toList());
  }

  public void addToActionLog(Action a) {
    getActionLog().add(a);
  }

  @Override
  public String toString() {
    return "Simulator Setup: \n" +
        "> Units: " + getUnits() + "\n" +
        "> Gamemode: " + getGameMode().name + "\n" +
        "> Cycle count: " + getNumCycles() + " (" + getTotalActionValue() + "AV)" +
        ".\n.\n.\n.";
  }
}
