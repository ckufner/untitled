package com.github.ckufner.util;

import lombok.Value;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public interface ArrayUtil {
    static boolean contains(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) return true;
        }
        return false;
    }

    static int[] getColumn(int column, int[][] array) {
        int bufSize = 10;
        int usedBuf = 0;
        int[] tmp = new int[bufSize];

        int[] result = null;

        for (int i = 0; i < array.length; i++) {
            int value = array[i][column];

            tmp[usedBuf++] = value;
            if (usedBuf == bufSize) {
                result = ArrayUtil.append(result, tmp);
                usedBuf = 0;
            }
        }

        if (usedBuf > 0) {
            result = ArrayUtil.append(result, tmp, usedBuf);
        }

        return result;
    }

    static <T> T[] getColumn(Class<T> clazz, int column, T[][] array) {
        int bufSize = 10;
        int usedBuf = 0;
        T[] tmp = GenericsUtil.arrayOf(clazz, bufSize);

        T[] result = null;

        for (int i = 0; i < array.length; i++) {
            T value = array[i][column];

            tmp[usedBuf++] = value;
            if (usedBuf == bufSize) {
                result = ArrayUtil.append(clazz, result, tmp);
                usedBuf = 0;
            }
        }

        if (usedBuf > 0) {
            result = ArrayUtil.append(clazz, result, tmp, usedBuf);
        }

        return result;
    }

    static int[] copyArray(int[] array) {
        return copyArray(array, array == null ? 0 : array.length);
    }

    static int[] copyArray(int[] array, int length) {
        if (array == null) return null;

        int actualLength = Math.min(array.length, length);

        int[] dest = new int[actualLength];
        System.arraycopy(array, 0, dest, 0, actualLength);

        return dest;
    }

    static <T> T[] copyArray(Class<T> clazz, T[] src) {
        return copyArray(clazz, src, src == null ? 0 : src.length);
    }

    static <T> T[] copyArray(Class<T> clazz, T[] src, int length) {
        if (src == null) return null;

        int actualLength = Math.min(src.length, length);

        T[] dest = GenericsUtil.arrayOf(clazz, actualLength);
        System.arraycopy(src, 0, dest, 0, actualLength);

        return dest;
    }

    static int[][] copy2dArray(int[][] src) {
        int[][] dest = new int[src.length][];

        for (int i = 0; i < src.length; i++) {
            dest[i] = new int[src[i].length];
            System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
        }

        return dest;
    }

    static <T> T[][] copy2dArray(Class<T> clazz, T[][] src) {
        T[][] dest = GenericsUtil.arrayOf(clazz, src.length, 0);

        for (int i = 0; i < src.length; i++) {
            dest[i] = GenericsUtil.arrayOf(clazz, src[i].length);
            System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
        }

        return dest;
    }

    static int[] append(int[] a, int[] b) {
        return append(a, b, b == null ? 0 : b.length);
    }

    static int[] append(int[] a, int[] b, int length) {
        if (a == null && b == null) return null;
        if (a == null) return ArrayUtil.copyArray(b, length);
        if (b == null) return ArrayUtil.copyArray(a, length);

        int appendMaxLength = Math.min(b.length, length);

        int[] result = new int[a.length + appendMaxLength];

        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, appendMaxLength);

        return result;
    }

    static <T> T[] append(Class<T> clazz, T[] a, T[] b) {
        return append(clazz, a, b, b == null ? 0 : b.length);
    }

    static <T> T[] append(Class<T> clazz, T[] a, T[] b, int length) {
        if (a == null && b == null) return null;
        if (a == null) return ArrayUtil.copyArray(clazz, b, length);
        if (b == null) return ArrayUtil.copyArray(clazz, a, length);

        int appendMaxLength = Math.min(b.length, length);
        T[] result = GenericsUtil.arrayOf(clazz, a.length + appendMaxLength);

        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, appendMaxLength);

        return result;
    }

    /**
     * Fisher–Yates shuffle
     * optimized Richard Durstenfeld version
     * <p>
     * -- To shuffle an array a of n elements (indices 0..n-1):
     * for i from n−1 downto 1 do
     * j ← random integer such that 0 ≤ j ≤ i
     * exchange a[j] and a[i]
     *
     * @param arr
     * @return
     */
    static int[] getShuffled(int[] arr) {
        int[] result = new int[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);

        shuffle(result);
        return result;
    }

    /**
     * Fisher–Yates shuffle
     * optimized Richard Durstenfeld version
     * <p>
     * -- To shuffle an array a of n elements (indices 0..n-1):
     * for i from n−1 downto 1 do
     * j ← random integer such that 0 ≤ j ≤ i
     * exchange a[j] and a[i]
     *
     * @param arr
     * @return
     */
    static void shuffle(int[] arr) {
        Random random = new Random();

        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            int exchange = arr[j];
            arr[j] = arr[i];
            arr[i] = exchange;
        }
    }

    static HashSet<Integer> asHashSet(int[] array) {
        HashSet<Integer> hashSet = new HashSet<>();

        for (int value : array) {
            hashSet.add(value);
        }

        return hashSet;
    }

    static ArrayList<Integer> asArrayList(int[] array) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int value : array) {
            arrayList.add(value);
        }

        return arrayList;
    }

    @Value(staticConstructor = "of")
    class IntArrayPopResult {
        int poppedValue;
        int[] array;
    }

    static IntArrayPopResult pop(int[] array) {
        int poppedValue = array[0];
        int[] newArray = new int[array.length - 1];
        System.arraycopy(array, 1, newArray, 0, newArray.length);

        return IntArrayPopResult.of(poppedValue, newArray);
    }
}
