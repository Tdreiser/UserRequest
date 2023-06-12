package ru.shlokov.UserRequest.model.enums;

/**
 * @author Shlokov Andrey
 */
public enum Permission {
    REQUEST_SHOW_EXISTS_LIST("request:showExistsList"),
    REQUEST_SHOW_SENT_LIST("request:showSentList"),
    REQUEST_ADD("request:add"),
    REQUEST_ACCEPT("request:accept"),
    REQUEST_SEND("request:send"),
    REQUEST_EDIT_DRAFT_LIST("request:editDraftList"),
    REQUEST_DECIDE("request:decide"),
    USER_SET_OPERATOR("user:setOperator"),
    USER_SHOW_ALL("user:showAll");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
