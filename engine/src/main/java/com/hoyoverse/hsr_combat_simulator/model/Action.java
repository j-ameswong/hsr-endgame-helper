package com.hoyoverse.hsr_combat_simulator.model;

import com.hoyoverse.hsr_combat_simulator.model.enums.ActionType;

public class Action {
    private final Unit unit;

    private final ActionType type;
    private double actionValue;
    private Action prevAction;
    private Action nextAction;

    public Action(Unit unit, double actionValue, ActionType type) {
        this.unit = unit;
        this.actionValue = actionValue;
        this.type = type;
    }

    public Action(Unit unit, Action prevAction, ActionType type) {
        this.unit = unit;
        this.prevAction = prevAction;
        prevAction.setNextAction(this);
        this.type = type;
    }

    public Unit getUnit() {
        return unit;
    }

    public double getActionValue() {
        return actionValue;
    }

    public void setActionValue(double actionValue) {
        this.actionValue = actionValue;
    }

    public Action getPrevAction() {
        return prevAction;
    }

    public void setPrevAction(Action prevAction) {
        this.prevAction = prevAction;
    }

    public Action getNextAction() {
        return nextAction;
    }

    public void setNextAction(Action nextAction) {
        this.nextAction = nextAction;
    }

    public ActionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "<" + getUnit().getName() + " " + getType() + " at " + String.format("%.2f", getActionValue())
                + ">";
    }
}
