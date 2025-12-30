package com.hoyoshills.avcalc.services;

import com.hoyoshills.avcalc.dto.Breakpoint;
import com.hoyoshills.avcalc.dto.BreakpointRequest;
import com.hoyoshills.avcalc.dto.BreakpointResponse;
import com.hoyoshills.avcalc.dto.CalcRequest;
import com.hoyoshills.avcalc.dto.CalcResponse;
import com.hoyoshills.avcalc.dto.Turn;
import com.hoyoshills.avcalc.dto.enums.ActionType;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class SimulationService {

    // private record ActionAdvance(double actionAdvanceAmount) {}
    public CalcResponse calculateActions(CalcRequest request) {
        double speed = request.speed();
        double maxAv = request.maxAv();

        double baseAv = 10000.0 / speed;

        // Turn queue
        PriorityQueue<Turn> turns =
                new PriorityQueue<>(Comparator.comparingDouble(Turn::actionValue));
        List<Turn> responseTurns = new ArrayList<>();

        List<Turn> actionAdvancePoints =
                List.of(
                        // new Turn(20.0, ActionType.ADVANCE),
                        // new Turn(40.0, ActionType.ADVANCE),
                        // new Turn(100.0, ActionType.ADVANCE),
                        new Turn(145.0, ActionType.ADVANCE), new Turn(145.0, ActionType.ADVANCE));

        turns.addAll(actionAdvancePoints);

        // Add first turn to turns
        // Should check for vonwacq here
        Turn trackedTurn = new Turn(baseAv, ActionType.NORMAL);
        turns.add(trackedTurn);

        // loop until next action exceeds the max sim av
        while (!turns.isEmpty()) {
            Turn currentTurn = turns.poll();
            if (currentTurn.actionValue() > maxAv) break;

            if (currentTurn == trackedTurn) {
                responseTurns.add(trackedTurn);

                // Generate next turn and track it
                trackedTurn = new Turn(trackedTurn.actionValue() + baseAv, ActionType.NORMAL);
                turns.add(trackedTurn);
            } else if (currentTurn.actionType() == ActionType.ADVANCE) {
                // Remove and add new advanced turn
                turns.remove(trackedTurn);

                // next action = baseAv - (baseAv * actionAdvance (%))
                double nextAv =
                        Math.max(
                                currentTurn.actionValue(),
                                (trackedTurn.actionValue() - baseAv * 0.24));

                trackedTurn = new Turn(nextAv, ActionType.NORMAL);
                turns.add(trackedTurn);
            }
        }

        return new CalcResponse(request.unitName(), responseTurns.stream().toList());
    }

    public BreakpointResponse calculateBreakpoints(BreakpointRequest request) {
        final double DDD_AA_AMOUNT = 0.24;
        final double EAGLE_AA_AMOUNT = 0.25;

        double speed = request.speed();
        double maxAv = request.maxAv();
        int numActions = request.numActions();
        int numDDD = request.numDDD();
        int numEagle = request.numEagle();

        // Number of actions with current speed
        int currentActions =
                (int)
                        Math.floor(
                                (speed * maxAv
                                                + (DDD_AA_AMOUNT * numDDD * 10000)
                                                + (EAGLE_AA_AMOUNT * numEagle * 10000))
                                        / 10000);
        // Check breakpoints starting from prev action
        int startActions = Math.max(1, currentActions - 1);

        List<Breakpoint> breakpoints = new ArrayList<>();

        // Assuming no wasted action advances
        // Calculate one below/above numAction range to get bounds
        for (int i = startActions; i <= numActions + 1; i++) {
            double distRequired = (i * 10000) - (DDD_AA_AMOUNT * 10000) - (EAGLE_AA_AMOUNT * 10000);
            double reqSpeed = Math.max(0, distRequired / maxAv);

            breakpoints.add(new Breakpoint(i, reqSpeed));
        }

        return new BreakpointResponse(breakpoints);
    }
}
