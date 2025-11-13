package sim;

import java.util.ArrayList;
import sim.gamemodes.*;

public class Sim {
  private ArrayList<Unit> units;
  // TODO: make gameMode enum with AV baked in
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
}
