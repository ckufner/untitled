package com.github.ckufner.puzzles.sudoku.strategies;

//TODO: implement all strategies from https://www.sudopedia.org/wiki/Solving_Technique
//TODO: Knuth's Algorithm X

public interface SudokuStrategiesUtil {
    static void applyStrategies(int[][] puzzle) {
        if (FullHouseStrategy.applyStrategy(puzzle)
                || NakedSingleStrategy.applyStrategy(puzzle)
                || HiddenSingleStrategy.applyStrategy(puzzle)
                || TwoOutOfThreeStrategy.applyStrategy(puzzle)) {
            applyStrategies(puzzle);
        }
    }
}
