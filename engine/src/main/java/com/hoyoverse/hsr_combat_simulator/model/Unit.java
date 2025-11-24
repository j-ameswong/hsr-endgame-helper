package com.hoyoverse.hsr_combat_simulator.model;

public class Unit {
    private String name;
    private double speed;
    private double currentAV;

    public Unit(String name, double speed, double currentAV) {
        this.name = name;
        this.speed = speed;
        this.currentAV = currentAV;
    }

    public String getSummary() {
        String summary = "Character " + getName() +
                " has " + getSpeed() + " SPD";

        return summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getCurrentAV() {
        return currentAV;
    }

    public void setCurrentAV(double currentAV) {
        this.currentAV = currentAV;
    }
}
