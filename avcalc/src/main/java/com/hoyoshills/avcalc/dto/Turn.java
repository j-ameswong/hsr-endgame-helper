package com.hoyoshills.avcalc.dto;

import com.hoyoshills.avcalc.dto.enums.ActionType;

public record Turn(double actionValue, double actionAdvance, ActionType actionType) {
}
