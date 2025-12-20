package com.hoyoshills.avcalc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hoyoshills.avcalc.dto.ActionOccurrence;
import com.hoyoshills.avcalc.dto.Breakpoint;
import com.hoyoshills.avcalc.dto.BreakpointRequest;
import com.hoyoshills.avcalc.dto.BreakpointResponse;
import com.hoyoshills.avcalc.dto.CalcRequest;
import com.hoyoshills.avcalc.dto.CalcResponse;

@Service
public class SimulationService {

    public CalcResponse calculateActions(CalcRequest request) {
        double speed = request.speed();
        double maxAv = request.maxAv();

        double baseAv = 10000.0 / speed;

        List<ActionOccurrence> actions = new ArrayList<>();
        double currentTotalAv = baseAv;
        int count = 1;

        // loop until next action exceeds the max sim av
        while (currentTotalAv < maxAv) {
            actions.add(new ActionOccurrence(count++, currentTotalAv));
            currentTotalAv += baseAv;
        }

        return new CalcResponse(request.unitName(), actions);

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
