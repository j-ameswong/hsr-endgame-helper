package com.hoyoshills.avcalc.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

import com.hoyoshills.avcalc.dto.Turn;
import com.hoyoshills.avcalc.dto.Breakpoint;
import com.hoyoshills.avcalc.dto.BreakpointRequest;
import com.hoyoshills.avcalc.dto.BreakpointResponse;
import com.hoyoshills.avcalc.dto.CalcRequest;
import com.hoyoshills.avcalc.dto.CalcResponse;

@Service
public class SimulationService {

    // private record ActionAdvance(double actionAdvanceAmount) {}
    public CalcResponse calculateActions(CalcRequest request) {
        double speed = request.speed();
        double maxAv = request.maxAv();

        double baseAv = 10000.0 / speed;
        double nextAv = baseAv;

        PriorityQueue<Turn> turns = new PriorityQueue<>(
                Comparator.comparingDouble(Turn::actionValue)
            );
        double globalAv = nextAv;

        // loop until next action exceeds the max sim av
        while (true) {
            turns.add(new Turn(globalAv));

            // probably apply advance logic here
            // next action = baseAv - (baseAv * actionAdvance (%))
            nextAv = baseAv - (baseAv * 0.24);

            globalAv += nextAv;
            if (globalAv > maxAv) break;
        }

        return new CalcResponse(request.unitName(), turns.stream().toList());

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
        int currentActions = (int) Math.floor((speed * maxAv
                + (DDD_AA_AMOUNT * numDDD * 10000)
                + (EAGLE_AA_AMOUNT * numEagle * 10000))
                / 10000);
        // Check breakpoints starting from prev action
        int startActions = Math.max(1, currentActions - 1);

        List<Breakpoint> breakpoints = new ArrayList<>();

        // Assuming no wasted action advances
        // Calculate one below/above numAction range to get bounds
        for (int i = startActions; i <= numActions + 1; i++) {
            double distRequired = (i * 10000)
                    - (DDD_AA_AMOUNT * 10000) - (EAGLE_AA_AMOUNT * 10000);
            double reqSpeed = Math.max(0, distRequired / maxAv);

            breakpoints.add(new Breakpoint(i, reqSpeed));
        }

        return new BreakpointResponse(breakpoints);
    }
}
