package com.hoyoverse.hsr_combat_simulator.model;

import com.hoyoverse.hsr_combat_simulator.model.enums.ActionType;

public class Action {
    private final Unit unit;

    private final ActionType type;
    private double actionValue;

    public Action(Unit unit, double actionValue, ActionType type) {
        this.unit = unit;
        this.actionValue = actionValue;
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

    public ActionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "<" + getUnit().getName() + " " + getType() + " at " + String.format("%.2f", getActionValue())
                + ">";
    }
}
