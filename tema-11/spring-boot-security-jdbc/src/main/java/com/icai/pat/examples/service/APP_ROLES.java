package com.icai.pat.examples.service;

import java.util.HashMap;
import java.util.Map;

public enum APP_ROLES {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String abbreviation;

    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<String, APP_ROLES> lookup = new HashMap<String, APP_ROLES>();

    static {
        for (APP_ROLES d : APP_ROLES.values()) {
            lookup.put(d.getAbbreviation(), d);
        }
    }

    private APP_ROLES(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static APP_ROLES get(String abbreviation) {
        return lookup.get(abbreviation);
    }
}
