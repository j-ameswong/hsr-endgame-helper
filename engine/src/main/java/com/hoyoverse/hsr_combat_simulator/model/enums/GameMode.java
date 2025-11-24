package com.hoyoverse.hsr_combat_simulator.model.enums;

public enum GameMode {
    // Planned endgame implementations
    MEMORY_OF_CHAOS("Memory of Chaos", 150, 100),
    ANOMALY_ARBITRATION("Anomaly Arbitration", 300, 200),
    // For Apoc, set first cycle to full star clear
    APOCALYPTIC_SHADOW("Apocalyptic Shadow", 700, 250), // TODO: Check if this is right
    PURE_FICTION("Pure Fiction", 150, 100);

    public final double firstCycleActionValue;
    public final double subsequentCyclesActionValue;
    public final String name;

    private GameMode(String name, double firstCycle, double subsCycle) {
        this.name = name;
        this.firstCycleActionValue = firstCycle;
        this.subsequentCyclesActionValue = subsCycle;
    }
}
