package com.hoyoverse.hsr_combat_simulator.model.enums;

public enum ActionType {
    BASIC("Basic attack"),
    SKILL("Use skill"),
    ULTIMATE("Cast ultimate"),
    ENTER_BATTLE("Character enters battle");

    private String description;

    private ActionType(String description) {
        // TODO: energy, action advance amount(?), target team/single
        this.description = description;
    }

    public String toString() {
        return description;
    }
}
