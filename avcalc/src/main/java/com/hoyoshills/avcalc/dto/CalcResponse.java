package com.hoyoshills.avcalc.dto;

import java.util.List;

public record CalcResponse(String unitName, List<ActionOccurrence> actions) {
}
