package com.example.finalproject.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testDefaultConstructor() {
        Product p = new Product();
        assertNull(p.getName());
    }

    @Test
    public void testParameterizedConstructor() {
        Product p = new Product("Apple", 1.50, 100);
        assertEquals("Apple", p.getName());
        assertEquals(1.50, p.getPrice());
        assertEquals(Integer.valueOf(100), p.getQuantity());
    }

    // functionality test 1
    @Test
    public void testUpdateQuantity() {
        Product p = new Product("Apple", 1.50, 10);
        p.setQuantity(5);
        assertEquals(Integer.valueOf(5), p.getQuantity());
    }

    // functionality test 2
    @Test
    public void testPriceUpdate() {
        Product p = new Product("Apple", 1.50, 10);
        p.setPrice(2.00);
        assertEquals(2.00, p.getPrice());
    }

    // functionality test 3 (Logic if logic exists, e.g. toString or custom)
    @Test
    public void testNameUpdate() {
        Product p = new Product("Apple", 1.50, 10);
        p.setName("Pear");
        assertEquals("Pear", p.getName());
    }
}
