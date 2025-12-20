package com.hoyoverse.hsr_combat_simulator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoyoverse.hsr_combat_simulator.model.Action;
import com.hoyoverse.hsr_combat_simulator.model.Unit;
import com.hoyoverse.hsr_combat_simulator.model.enums.ActionType;
import com.hoyoverse.hsr_combat_simulator.model.enums.GameMode;

@Service
public class CombatEngine {
    private PriorityQueue<Unit> actionQueue;
    @Autowired
    private ActionService actionService;
    private double globalAV = 0;
    private Scanner scanner = new Scanner(System.in);

    // default simulator
    public void initializeBattle(List<Unit> units) {
        this.actionService = new ActionService();
        this.actionQueue = new PriorityQueue<>(
                Comparator.comparingDouble(Unit::getCurrentAV));
        this.actionQueue.addAll(units);
    }

    public void tick() {
        // Init
        System.out.println(this);

        if (actionQueue.isEmpty())
            return;

        // Get next acting unit
        Unit activeUnit = actionQueue.poll();

        // Set simulator AV to this unit's AV
        setGlobalAV(activeUnit.getCurrentAV());

        System.out.println("Basic, Skill, or Ult: (B/S/C) ");
        // placeholders
        Action action = new Action(activeUnit, getGlobalAV(),
                ActionType.ULTIMATE);

        // Execute action
        actionService.execute(action, this);

        // Queue next action
        activeUnit.setCurrentAV(getGlobalAV() +
                10000.0 / activeUnit.getSpeed());
        actionQueue.add(activeUnit);
    }

    public PriorityQueue<Unit> getActionQueue() {
        return actionQueue;
    }

    public double getGlobalAV() {
        return globalAV;
    }

    public void setGlobalAV(double globalAV) {
        this.globalAV = globalAV;
    }

    @Override
    public String toString() {
        return "Simulator Setup: \n" +
                "> Units: " + getActionQueue() + "\n" +
                "> Global Sim AV: " + getGlobalAV() +
                ".\n.\n.\n.";
    }
}
