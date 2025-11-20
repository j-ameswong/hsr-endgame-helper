package sim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sim.gamemodes.GameMode;

public class Sim {
  private ArrayList<Unit> units;
  private ArrayList<Action> actionLog;
  private List<Action> lastActions;
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
    // Battle begins, get turns sorted by AV
    do {
      refreshLastActions();
      System.out.println("--------------------------\n" + getLastActions());
      for (Action a : lastActions) {
        // Turn starts
        Unit currentUnit = a.getUnit();
        if (currentUnit.getNextActionValue() < getTotalActionValue()) {
          Action newAction = currentUnit.generateAction(ActionType.ULTIMATE);
          ActionHandler.execute(newAction);
          actionWasTaken = true;
        } else {
          actionWasTaken = false;
        }
      }
    } while (actionWasTaken);

    // List all turns
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

  public ArrayList<Action> getActionLog() {
    return this.actionLog;
  }

  public void addToActionLog(Action a) {
    getActionLog().add(a);
  }

  public List<Action> getLastActions() {
    return this.lastActions;
  }

  public void refreshLastActions() {
    this.lastActions = getRefreshedLastActions();
  }

  public List<Action> getRefreshedLastActions() {
    return new ArrayList<>(getUnits()
        .stream()
        .map(unit -> unit.getLastAction())
        .toList());
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
