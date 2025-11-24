package com.hoyoverse.hsr_combat_simulator.service;

import com.hoyoverse.hsr_combat_simulator.model.Action;
import com.hoyoverse.hsr_combat_simulator.model.Unit;
import com.hoyoverse.hsr_combat_simulator.model.enums.ActionType;

public class ActionService {
    public ActionService() {
    }

    public void execute(Action a) {
        // If action advance is involved
        Unit unit = a.getUnit();
        if (a.getActionType().equals(ActionType.ULTIMATE)) {
            double actionAdvance = 0.0;
        }
        if (a.getActionType().equals(ActionType.ULTIMATE)) {
            // for (Action act : actions.stream()
            // .filter(thisAction -> thisAction.getActionValue() > a.getActionValue())
            // .toList()) {
            // System.out.println("Would execute DDD on " + act);
            // }
        }
    }
}
