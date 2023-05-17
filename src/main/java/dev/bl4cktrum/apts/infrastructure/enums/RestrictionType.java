package dev.bl4cktrum.apts.infrastructure.enums;

public enum RestrictionType {
    POLYGON,
    CIRCLE,
    PASSIVITY;

    public static final RestrictionType[] values = values();
    public static RestrictionType get(int ordinal) { return values[ordinal]; }
}
