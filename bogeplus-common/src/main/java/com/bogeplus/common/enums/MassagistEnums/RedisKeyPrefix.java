package com.bogeplus.common.enums.MassagistEnums;

public enum RedisKeyPrefix {
    ASSIGNED_MASSAGISTS("ASSIGNED_MASSAGISTS:"),
    ASSIGNED_ITEMS("ASSIGNED_ITEMS:"),
    UNASSIGNED_MASSAGISTS("UNASSIGNED_MASSAGISTS:"),
    UNASSIGNED_ITEMS("UNASSIGNED_ITEMS:");
    private String prefix;
    RedisKeyPrefix(String prefix) {
        this.prefix = prefix;
    }
}
