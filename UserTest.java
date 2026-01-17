package com.example.finalproject.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testDefaultConstructor() {
        User user = new User();
        assertNull(user.getId());
    }

    @Test
    public void testParameterizedConstructor() {
        User user = new User("admin", "password", Role.ADMIN);
        assertEquals("admin", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    public void testSetters() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("123");
        user.setRole(Role.USER);

        assertEquals("testuser", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }
}
