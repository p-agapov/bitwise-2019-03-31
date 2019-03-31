package com.epam.practice.mask;

import com.epam.practice.mask.Permissions.Type;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PermissionsTest {

    @Test
    void has() {
        Permissions guest = Permissions.getDefault(Type.GUEST);
        assertTrue(guest.has(Permission.VIEW_ARTICLE));
        assertTrue(guest.has(Permission.VIEW_PRODUCT));
        assertFalse(guest.has(Permission.EDIT_PRODUCT));
        assertFalse(guest.has(Permission.EDIT_ARTICLE));
        assertFalse(guest.has(Permission.CHANGE_PERMISSIONS));
    }

    @Test
    void addAndRemove() {
        Permissions editor = Permissions.getDefault(Type.EDITOR);
        assertTrue(editor.has(Permission.EDIT_PRODUCT));

        editor.remove(Permission.EDIT_PRODUCT);
        assertFalse(editor.has(Permission.EDIT_PRODUCT));

        assertThrows(NoSuchElementException.class, () -> editor.remove(Permission.EDIT_PRODUCT));

        editor.add(Permission.EDIT_PRODUCT);
        assertTrue(editor.has(Permission.EDIT_PRODUCT));

        assertThrows(SecurityException.class, () -> editor.add(Permission.CHANGE_PERMISSIONS));
    }

    @Test
    void merge() {
        Permissions disabledEditor = Permissions.getDefault(Type.EDITOR);

        assertTrue(disabledEditor.has(Permission.EDIT_PRODUCT));

        disabledEditor.remove(Permission.EDIT_PRODUCT);
        assertFalse(disabledEditor.has(Permission.EDIT_PRODUCT));

        Permissions defaultEditor = Permissions.getDefault(Type.EDITOR);
        disabledEditor.merge(defaultEditor);
        assertTrue(defaultEditor.has(Permission.EDIT_PRODUCT));

        assertThrows(IllegalArgumentException.class, () -> disabledEditor.merge(Permissions.getDefault(Type.ADMIN)));
    }
}