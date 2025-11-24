package com.hoyoverse.hsr_combat_simulator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hoyoverse.hsr_combat_simulator.model.Action;
import com.hoyoverse.hsr_combat_simulator.model.Unit;
import com.hoyoverse.hsr_combat_simulator.model.enums.ActionType;
import com.hoyoverse.hsr_combat_simulator.model.enums.GameMode;

public class CombatEngine {
    private ArrayList<Unit> units;
    private ArrayList<Action> actionLog;
    private List<Action> lastActions;
    private ActionService actionHandler;
    private GameMode gameMode;
    private int numCycles;
    private double totalActionValue;

    public CombatEngine(ArrayList<Unit> units, GameMode gameMode, int numCycles) {
        this.units = units;
        this.gameMode = gameMode;
        this.numCycles = numCycles;
        this.actionLog = new ArrayList<>();

        this.totalActionValue = gameMode.firstCycleActionValue +
                (numCycles * gameMode.subsequentCyclesActionValue);
    }

    // default simulator
    public CombatEngine() {
        ArrayList<Unit> units = new ArrayList<>();
        units.addAll(Arrays.asList(
                new Unit("Blade", 134, 0),
                new Unit("Sparkle", 160, 0)));

        this(units, GameMode.MEMORY_OF_CHAOS, 1);
    }

    public void run() {
        // Initialize
        actionHandler = new ActionService();
        System.out.println(this);
        // Load each unit's first action

        boolean actionWasTaken = false;
        // Battle begins, get turns sorted by AV
        do {
            System.out.println("--------------------------\n" + getLastActions());
            for (Action a : lastActions) {
                // Turn starts
                Unit currentUnit = a.getUnit();
                if (true) {
                    actionWasTaken = true;
                }
            }
        } while (actionWasTaken);

        // List all turns
        for (Action a : getActionLog()) {
            System.out.println("- " + a);
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

    public List<Action> getLastActions() {
        return this.lastActions;
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
