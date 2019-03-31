package com.epam.practice.mask;

import java.util.NoSuchElementException;

public interface Permissions {

    static Permissions getDefault(Type type) {
        throw new UnsupportedOperationException();
    }

    void add(Permission...permissions) throws SecurityException;

    void remove(Permission...permissions) throws NoSuchElementException;

    boolean has(Permission permission, Permission...other);

    /**
     * @param other permissions that need to be added.
     * @throws IllegalArgumentException mismatch types of current and other permissions.
     */
    void merge(Permissions other) throws IllegalArgumentException;

    enum Type {
        GUEST,
        EDITOR,
        ADMIN
    }
}
