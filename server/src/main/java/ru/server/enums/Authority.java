package ru.server.enums;

public final class Authority {
    private final static String DEFAULT_PREFIX = "ROLE_";
    public static final String CAN_ENTER = DEFAULT_PREFIX +"CAN_ENTER";
    public static final String CAN_VIEW_ROLES = DEFAULT_PREFIX + "CAN_VIEW_ROLES";
    public static final String CAN_MANAGE_USERS = DEFAULT_PREFIX + "CAN_MANAGE_USERS";
}
