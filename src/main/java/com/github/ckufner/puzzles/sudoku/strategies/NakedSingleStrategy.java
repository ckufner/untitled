package com.github.ckufner.puzzles.sudoku.strategies;

import com.github.ckufner.puzzles.sudoku.SudokuUtil;

//TODO: test
public interface NakedSingleStrategy {
    static boolean applyStrategy(int[][] puzzle) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                int[] remainingCandidates = SudokuUtil.getCandidateValuesOfCell(puzzle, row, column);
                if (remainingCandidates.length != 1) continue;

                puzzle[row][column] = remainingCandidates[0];

                return true;
            }
        }

        return false;
    }
}
