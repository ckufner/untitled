package com.github.ckufner.util;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("UnitTest")
class GenericsUtilTest {
    @Test
    void arrayOfTest() {
        int expectedDimension = new Random().nextInt(100) + 1;

        String[] array = GenericsUtil.arrayOf(String.class, expectedDimension);

        assertNotNull(array);
        assertEquals(expectedDimension, array.length);
    }

    @Test
    void arrayOf2dTest() {
        int dim1 = new Random().nextInt(100) + 1;
        int dim2 = new Random().nextInt(100) + 1;

        String[][] array = GenericsUtil.arrayOf(String.class, dim1, dim2);

        assertNotNull(array);

        assertEquals(dim1, array.length);
        for (int i = 0; i < array.length; i++) {
            assertEquals(dim2, array[i].length);
        }
    }
}
