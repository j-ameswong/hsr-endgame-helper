package com.hoyoverse.hsr_combat_simulator.components;

import org.springframework.stereotype.Component;

import com.hoyoverse.hsr_combat_simulator.model.Action;
import com.hoyoverse.hsr_combat_simulator.model.enums.ActionType;
import com.hoyoverse.hsr_combat_simulator.service.CombatEngine;

@Component
public class DanceDanceDance implements BattleObserver {
    @Override
    public void onEvent(Action action, CombatEngine combatEngine) {
        // If action is ult, advance actions
        if (action.getType() == ActionType.ULTIMATE) {
            // TODO
        }
    }
}
