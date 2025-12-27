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

        double baseAv = 10000.0 / speed; // initially with base av
        double nextAv = 10000.0 / speed;

        PriorityQueue<Turn> turns = new PriorityQueue<>(
                Comparator.comparingDouble(Turn::actionValue)
            );
        double globalAv = nextAv;

        // Temporary
        class Thing {
            public int count = 0;
            public List<Double> actionAdvance = List.of(0.25, 0.25, 0.0);
        }
        Thing thing = new Thing();

        // loop until next action exceeds the max sim av
        while (true) {
            // turns.add(new Turn(globalAv));
            turns.add(new Turn(globalAv));

            // probably apply advance logic here
            // next action = baseAv - (baseAv * actionAdvance (%))
            nextAv = baseAv - (baseAv * thing.actionAdvance.get(thing.count++));

            globalAv += nextAv;
            if (globalAv > maxAv) break;
        }

        return new CalcResponse(request.unitName(), turns.stream().toList());

    }

    public BreakpointResponse calculateBreakpoints(BreakpointRequest request) {
        double speed = request.speed();
        double maxAv = request.maxAv();
        int numActions = request.numActions();

        List<Breakpoint> breakpoints = new ArrayList<>();

        int currentActions = (int) Math.floor((speed * maxAv) / 10000.0);
        double reqSpeed = (double) (currentActions * 10000.0) / maxAv;
        while (currentActions <= numActions) {
            breakpoints.add(new Breakpoint(currentActions++, reqSpeed));
            reqSpeed = (double) (currentActions * 10000.0) / maxAv;
        }

        return new BreakpointResponse(breakpoints);
    }
}
