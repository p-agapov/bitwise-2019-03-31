package com.epam.practice.mask;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class BitPermissions implements Permissions {

    private final Type type;
    private int mask;

    public BitPermissions(Type type) {
        this.type = type;
        mask = type.getDefaultPermissions();
    }

    @Override
    public void add(Permission... permissions) throws SecurityException {
        if (Stream.of(permissions).anyMatch(permission -> !type.isAvailable(permission))) {
            throw new SecurityException();
        }
        for (Permission permission : permissions) {
            mask |= permission.getFlag();
        }
    }

    @Override
    public void remove(Permission...permissions) throws NoSuchElementException {
        if (Arrays.stream(permissions).noneMatch(curr -> (mask & curr.getFlag()) == curr.getFlag())) {
            throw new NoSuchElementException();
        }
        for (Permission permission : permissions) {
            mask &= ~permission.getFlag();
        }
    }

    @Override
    public boolean has(Permission permission, Permission... other) {
        if ((mask & permission.getFlag()) != permission.getFlag()) {
            return false;
        }
        return Arrays.stream(other).noneMatch(curr -> (mask & curr.getFlag()) != curr.getFlag());
    }

    @Override
    public Permissions.Type getType() {
        return type;
    }

    @Override
    public void merge(Permissions other) throws IllegalArgumentException {
        if (other.getType() != this.type) {
            throw new IllegalArgumentException();
        }
        Arrays.stream(Permission.values())
              .filter(other::has)
              .forEach(this::add);
    }
}
