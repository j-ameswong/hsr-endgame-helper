package com.hoyoverse.hsr_combat_simulator.components;

import com.hoyoverse.hsr_combat_simulator.model.Action;
import com.hoyoverse.hsr_combat_simulator.service.CombatEngine;

public interface BattleObserver {
    void onEvent(Action action, CombatEngine CombatEngine);
}
