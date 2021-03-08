package com.github.ckufner.util;

import java.lang.reflect.Array;

public interface GenericsUtil {
    static <T> T[] arrayOf(Class<T> clazz, int dimension) {
        return (T[]) Array.newInstance(clazz, dimension);
    }

    static <T> T[][] arrayOf(Class<T> clazz, int dim1, int dim2) {
        return (T[][]) Array.newInstance(clazz, dim1, dim2);
    }
}
