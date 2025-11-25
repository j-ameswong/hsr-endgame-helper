package com.hoyoverse.hsr_combat_simulator.service;

import org.springframework.stereotype.Service;

import com.hoyoverse.hsr_combat_simulator.model.Action;
import com.hoyoverse.hsr_combat_simulator.model.Unit;
import com.hoyoverse.hsr_combat_simulator.model.enums.ActionType;

@Service
public class ActionService {
    public ActionService() {
    }

    public void execute(Action action, CombatEngine engine) {
        Unit unit = action.getUnit();

        System.out.println("Processing: " + action);

        // Notify startOfTurn listeners

        switch (action.getType()) {
            case SKILL:
                //
                break;
            case ULTIMATE:
                //
                break;
            case BASIC:
                //
                break;
        }

        // unit.getObservers... i.e. process all events
    }
}
