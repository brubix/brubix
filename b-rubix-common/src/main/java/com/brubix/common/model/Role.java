package com.brubix.common.model;

/**
 * Created by Sanjaya on 27/01/18.
 */
public enum Role {
    SUPER_ADMIN("SUPER ADMINISTRATOR");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
