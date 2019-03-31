package com.epam.practice.mask;

public enum Permission {
    VIEW_ARTICLE(1),
    EDIT_ARTICLE(2),

    VIEW_PRODUCT(4),
    EDIT_PRODUCT(8),

    CHANGE_PERMISSIONS(16);

    private final int flag;

    Permission(int flag) {

        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }
}
