package com.example.nserver.security;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserPermisson {
    COMMAND_PRODUCT_ADD("product:add"),

    COMMAND_PRODUCT_REMOVE("product:remove"),

    COMMAND_PRODUCT_UPDATE("product:update"),

    COMMAND_PRODUCT_READ("product:read"),

    QUERY_PRODUCT_READ("query:read"),

    USER_READ("user:read"),

    USER_WRITE("user:write");


    private final String permission;

    public String getPermission() {
        return permission;
    }

}
