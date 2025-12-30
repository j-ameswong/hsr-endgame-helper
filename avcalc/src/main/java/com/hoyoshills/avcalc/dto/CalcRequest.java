package com.hoyoshills.avcalc.dto;

import java.util.List;

public record CalcRequest(
        String unitName, double speed, double maxAv, List<Turn> actionAdvancePoints) {}
