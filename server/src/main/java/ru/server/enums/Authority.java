package ru.server.enums;

public final class Authority {
    private final static String DEFAULT_PREFIX = "ROLE_";
    public static final String CAN_ENTER = DEFAULT_PREFIX +"CAN_ENTER";
    public static final String CAN_VIEW_ROLES = DEFAULT_PREFIX + "CAN_VIEW_ROLES";
    public static final String CAN_MANAGE_USERS = DEFAULT_PREFIX + "CAN_MANAGE_USERS";
    public static final String CAN_EDIT_ARTICLES = DEFAULT_PREFIX + "CAN_EDIT_ARTICLES";
    public static final String CAN_MANAGE_COMPLEXES = DEFAULT_PREFIX + "CAN_MANAGE_COMPLEXES";
    public static final String CAN_EDIT_FLATS = DEFAULT_PREFIX + "CAN_EDIT_FLATS";
    public static final String CAN_EDIT_METROS = DEFAULT_PREFIX + "CAN_EDIT_METROS";
    public static final String CAN_EDIT_DISTRICTS = DEFAULT_PREFIX + "CAN_EDIT_DISTRICTS";
}
