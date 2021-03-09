package com.github.ckufner.puzzles.sudoku;

public interface SudokuParser {
    static int[][] parse(String input) {
        if (input == null) throw new IllegalArgumentException("input must not be null");
        if (input.length() != 81) throw new IllegalArgumentException("input is not 81 characters");

        int[][] result = new int[9][];
        result[0] = new int[9];

        int row = 0;
        int column = 0;

        for (int i = 0; i < input.length(); i++) {
            char curChar = input.charAt(i);

            if (curChar == '.') curChar = '0';

            if (curChar < 48 || curChar > 57) throw new IllegalArgumentException("char '" + curChar + "' at position " + i + " is invalid");

            result[row][column++] = curChar - 48;

            if (column == 9 && row != 8) {
                result[++row] = new int[9];
                column = 0;
            }
        }

        if (!SudokuUtil.isPuzzleValid(result, false)) throw new IllegalArgumentException("puzzle is invalid");

        return result;
    }
}
