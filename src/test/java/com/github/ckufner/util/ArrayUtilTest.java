package com.github.ckufner.util;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
class ArrayUtilTest {
    @Test
    void containsIntInIntArrayTest() {
        int[] arr = new int[]{1, 2, 3};

        assertTrue(ArrayUtil.contains(arr, 1));
        assertTrue(ArrayUtil.contains(arr, 2));
        assertTrue(ArrayUtil.contains(arr, 3));
        assertFalse(ArrayUtil.contains(arr, 0));
    }

    @ParameterizedTest
    @ArgumentsSource(value = AppendIntTestArgumentsProvider.class)
    void appendIntTest(int[] a, int[] b, int[] expected) {
        int[] actual = ArrayUtil.append(a, b);

        if (a != null && actual != null) assertNotEquals(a, actual);
        if (b != null && actual != null) assertNotEquals(b, actual);

        if (expected == null) {
            assertNull(actual);
        } else {
            assertArrayEquals(expected, actual);
        }
    }

    private static class AppendIntTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(null, null, null),
                    Arguments.of(new int[0], null, new int[0]),
                    Arguments.of(null, new int[0], new int[0]),
                    Arguments.of(new int[]{1}, new int[]{1}, new int[]{1, 1}),
                    Arguments.of(new int[]{1}, new int[]{2}, new int[]{1, 2})
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = AppendIntWithLengthTestArgumentsProvider.class)
    void appendIntWithLengthTest(int[] a, int[] b, int length, int[] expected) {
        int[] actual = ArrayUtil.append(a, b, length);

        if (a != null && actual != null) assertNotEquals(a, actual);
        if (b != null && actual != null) assertNotEquals(b, actual);

        if (expected == null) {
            assertNull(actual);
        } else {
            assertArrayEquals(expected, actual);
        }
    }

    private static class AppendIntWithLengthTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(null, null, 10, null),
                    Arguments.of(new int[0], null, 10, new int[0]),
                    Arguments.of(null, new int[0], 10, new int[0]),
                    Arguments.of(new int[]{1}, new int[]{1}, 10, new int[]{1, 1}),
                    Arguments.of(new int[]{1}, new int[]{2}, 0, new int[]{1}),
                    Arguments.of(new int[]{1}, new int[]{2}, 1, new int[]{1, 2}),
                    Arguments.of(new int[]{1}, new int[]{2}, 10, new int[]{1, 2}),
                    Arguments.of(null, new int[]{1, 2, 3}, 2, new int[]{1, 2})
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = AppendObjectTestArgumentsProvider.class)
    <T> void appendObjectTest(Class<T> clazz, T[] a, T[] b, T[] expected) {
        T[] actual = ArrayUtil.append(clazz, a, b);

        if (a != null && actual != null) assertNotEquals(a, actual);
        if (b != null && actual != null) assertNotEquals(b, actual);

        if (expected == null) {
            assertNull(actual);
        } else {
            assertArrayEquals(expected, actual);
        }
    }

    private static class AppendObjectTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(Object.class, null, null, null),
                    Arguments.of(String.class, new String[0], null, new String[0]),
                    Arguments.of(String.class, null, new String[0], new String[0]),
                    Arguments.of(String.class, new String[]{"1"}, new String[]{"1"}, new String[]{"1", "1"}),
                    Arguments.of(String.class, new String[]{"1"}, new String[]{"2"}, new String[]{"1", "2"})
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = AppendObjectWithLengthTestArgumentsProvider.class)
    <T> void appendObjectWithLengthTest(Class<T> clazz, T[] a, T[] b, int length, T[] expected) {
        T[] actual = ArrayUtil.append(clazz, a, b, length);

        if (a != null && actual != null) assertNotEquals(a, actual);
        if (b != null && actual != null) assertNotEquals(b, actual);

        if (expected == null) {
            assertNull(actual);
        } else {
            assertArrayEquals(expected, actual);
        }
    }

    private static class AppendObjectWithLengthTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(Object.class, null, null, 10, null),
                    Arguments.of(String.class, new String[0], null, 10, new String[0]),
                    Arguments.of(String.class, null, new String[0], 10, new String[0]),
                    Arguments.of(String.class, new String[]{"1"}, new String[]{"1"}, 10, new String[]{"1", "1"}),
                    Arguments.of(String.class, new String[]{"1"}, new String[]{"2"}, 0, new String[]{"1"}),
                    Arguments.of(String.class, new String[]{"1"}, new String[]{"2"}, 1, new String[]{"1", "2"}),
                    Arguments.of(String.class, new String[]{"1"}, new String[]{"2"}, 10, new String[]{"1", "2"}),
                    Arguments.of(String.class, null, new String[]{"1", "2", "3"}, 2, new String[]{"1", "2"})
            );
        }
    }

    @Test
    void getColumnOf2dIntArrayTest() {
        int[][] input = new int[][]{
                {11, 12, 13, 14},
                {21, 22, 23, 24},
                {31, 32, 33, 34}
        };

        int[] expected = new int[]{12, 22, 32};

        int[] actual = ArrayUtil.getColumn(1, input);

        assertNotNull(actual);
        assertEquals(expected.length, actual.length);
        assertNotSame(expected, actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void getColumnOf2dObjectArrayTest() {
        String[][] input = new String[][]{
                {"11", "12", "13", "14"},
                {"21", "22", "23", "24"},
                {"31", "32", "33", "34"}
        };

        String[] expected = new String[]{"12", "22", "32"};

        String[] actual = ArrayUtil.getColumn(String.class, 1, input);

        assertNotNull(actual);
        assertEquals(expected.length, actual.length);
        assertNotSame(expected, actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void copyIntArrayTest() {
        int length = new Random().nextInt(100) + 1;

        int[] expectedArray = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            expectedArray[i] = random.nextInt(100);
        }

        int[] actualArray = ArrayUtil.copyArray(expectedArray);

        assertNotNull(actualArray);
        assertNotEquals(expectedArray, actualArray);
        assertNotSame(expectedArray, actualArray);

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void copyIntArrayWithLengthTest() {
        Random random = new Random();
        int length = random.nextInt(100) + 1;
        int expectedLength = random.nextInt(length);

        int[] expectedArray = new int[length];

        for (int i = 0; i < expectedLength; i++) {
            expectedArray[i] = random.nextInt(100);
        }

        int[] actualArray = ArrayUtil.copyArray(expectedArray, expectedLength);

        assertNotNull(actualArray);

        assertNotEquals(expectedArray, actualArray);
        assertNotSame(expectedArray, actualArray);
        assertEquals(expectedLength, actualArray.length);

        for (int i = 0; i < expectedLength; i++) {
            assertEquals(expectedArray[i], actualArray[i]);
        }
    }

    @Test
    void copyObjectArrayTest() {
        int length = new Random().nextInt(100) + 1;
        String[] expectedArray = new String[length];
        for (int i = 0; i < length; i++) {
            expectedArray[i] = "" + Math.random() + "_" + System.currentTimeMillis();
        }

        String[] actualArray = ArrayUtil.copyArray(String.class, expectedArray);

        assertNotNull(actualArray);
        assertNotEquals(expectedArray, actualArray);
        assertNotSame(expectedArray, actualArray);

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void copyObjectArrayWithLengthTest() {
        Random random = new Random();
        int length = random.nextInt(100) + 1;
        int expectedLength = random.nextInt(length);

        String[] expectedArray = new String[length];
        for (int i = 0; i < expectedLength; i++) {
            expectedArray[i] = "" + Math.random() + "_" + System.currentTimeMillis();
        }

        String[] actualArray = ArrayUtil.copyArray(String.class, expectedArray, expectedLength);

        assertNotNull(actualArray);

        assertNotEquals(expectedArray, actualArray);
        assertNotSame(expectedArray, actualArray);
        assertEquals(expectedLength, actualArray.length);

        for (int i = 0; i < expectedLength; i++) {
            assertEquals(expectedArray[i], actualArray[i]);
        }
    }

    @Test
    void copyInt2dArrayTest() {
        int[][] expected = new int[][]{
                {1},
                {2, 3},
                {4, 5, 6},
                {7, 8, 9, 10}
        };

        int[][] actual = ArrayUtil.copy2dArray(expected);

        assertNotEquals(expected, actual);
        assertNotSame(expected, actual);

        assertEquals(expected.length, actual.length);

        for (int row = 0; row < expected.length; row++) {
            assertEquals(expected[row].length, actual[row].length);

            for (int col = 0; col < expected[row].length; col++) {
                assertEquals(expected[row][col], actual[row][col]);
            }

            assertArrayEquals(expected[row], actual[row]);
        }
    }

    @Test
    void copyObject2dArrayTest() {
        Object[][] expected = new Object[][]{
                {"1"},
                {"2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9", "10"}
        };

        Object[][] actual = ArrayUtil.copy2dArray(Object.class, expected);

        assertNotEquals(expected, actual);
        assertNotSame(expected, actual);

        assertEquals(expected.length, actual.length);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, actual[i].length);
            assertArrayEquals(expected[i], actual[i]);
        }
    }

    @Test
    void getShuffledIntTest() {
        int[] expected = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] actual = ArrayUtil.getShuffled(expected);

        assertNotNull(actual);
        assertNotEquals(expected, actual);
        assertNotSame(expected, actual);
        assertEquals(expected.length, actual.length);

        for (int i = 0; i < expected.length; i++) {
            assertTrue(ArrayUtil.contains(actual, expected[i]));
        }
    }

    @Test
    void intPopTest() {
        int[] input = ArrayUtil.getShuffled(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        int expected = input[0];

        ArrayUtil.IntArrayPopResult popResult = ArrayUtil.pop(input);
        assertNotNull(popResult);

        assertEquals(expected, popResult.getPoppedValue());
        assertEquals(input.length - 1, popResult.getArray().length);

        for (int i = 0; i < popResult.getArray().length; i++) {
            assertEquals(input[i + 1], popResult.getArray()[i]);
        }
    }

    @Test
    void intArrayAsHashSetTest() {
        int[] arr = new int[]{1, 2, 3, 3, 4};
        HashSet<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        HashSet<Integer> actual = ArrayUtil.asHashSet(arr);

        assertNotNull(actual);
        assertNotSame(expected, actual);
        assertEquals(expected, actual);
    }

    @Test
    void intArrayAsArrayListTest() {
        int[] arr = new int[]{1, 2, 3, 3, 4};
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(3);
        expected.add(4);

        ArrayList<Integer> actual = ArrayUtil.asArrayList(arr);

        assertNotNull(actual);
        assertNotSame(expected, actual);
        assertEquals(expected, actual);
    }
}