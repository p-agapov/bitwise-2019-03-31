package com.epam.practice.mask;

import java.util.NoSuchElementException;

import static com.epam.practice.mask.Permission.*;

public interface Permissions {

    static Permissions getDefault(Type type) {
        return new BitPermissions(type);
    }

    void add(Permission... permissions) throws SecurityException;

    void remove(Permission... permissions) throws NoSuchElementException;

    boolean has(Permission permission, Permission... other);

    Type getType();

    /**
     * @param other permissions that need to be added.
     * @throws IllegalArgumentException mismatch types of current and other permissions.
     */
    void merge(Permissions other) throws IllegalArgumentException;

    enum Type {
        GUEST(VIEW_ARTICLE, VIEW_PRODUCT),
        EDITOR(VIEW_ARTICLE, EDIT_ARTICLE, VIEW_PRODUCT, EDIT_PRODUCT),
        ADMIN(VIEW_ARTICLE, EDIT_ARTICLE, VIEW_PRODUCT, EDIT_PRODUCT, CHANGE_PERMISSIONS);

        private final int defaultPermissions;

        Type(Permission...permissions) {
            int defaultPermissions = 0;
            for (Permission permission : permissions) {
                defaultPermissions |= permission.getFlag();
            }
            this.defaultPermissions = defaultPermissions;
        }

        public int getDefaultPermissions() {
            return defaultPermissions;
        }

        public boolean isAvailable(Permission permission) {
            return (defaultPermissions & permission.getFlag()) == permission.getFlag();
        }
    }
}
