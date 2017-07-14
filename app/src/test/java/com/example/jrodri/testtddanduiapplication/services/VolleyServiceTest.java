package com.example.jrodri.testtddanduiapplication.services;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jrodri on 11/7/17.
 */
public class VolleyServiceTest {

    @Test
    public void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = VolleyService.class.getDeclaredConstructors();

        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
    }

    @Test
    public void testGetInstanceReturnANotNullVolleyService(){
        VolleyService volleyService = VolleyService.getInstance(null);

        assertNotNull(volleyService);
    }

}