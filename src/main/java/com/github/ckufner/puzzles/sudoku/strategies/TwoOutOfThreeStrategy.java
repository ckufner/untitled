package com.github.ckufner.puzzles.sudoku.strategies;

import com.github.ckufner.puzzles.sudoku.SudokuUtil;
import com.github.ckufner.util.ArrayUtil;

public interface TwoOutOfThreeStrategy {
    static boolean applyStrategy(int[][] puzzle) {
        if (twoOutOfThreeForRows(puzzle)) return true;
        if (twoOutOfThreeForColumns(puzzle)) return true;

        return false;
    }

    static boolean twoOutOfThreeForRows(int[][] puzzle) {
        for (int startRowIndex = 0; startRowIndex < 3; startRowIndex++) {
            int row1Position = startRowIndex * 3 + 0;
            int row2Position = startRowIndex * 3 + 1;
            int row3Position = startRowIndex * 3 + 2;

            int[] row1 = puzzle[row1Position];
            int[] row2 = puzzle[row2Position];
            int[] row3 = puzzle[row3Position];

            if (twoOutOfThreeSubGroupProcessing(puzzle, row1, row1Position, true, row2, row3)) return true;
            if (twoOutOfThreeSubGroupProcessing(puzzle, row2, row2Position, true, row1, row3)) return true;
            if (twoOutOfThreeSubGroupProcessing(puzzle, row3, row3Position, true, row1, row2)) return true;
        }

        return false;
    }

    static boolean twoOutOfThreeForColumns(int[][] puzzle) {
        for (int startColumnIndex = 0; startColumnIndex < 3; startColumnIndex++) {
            int column1Position = startColumnIndex * 3 + 0;
            int column2Position = startColumnIndex * 3 + 1;
            int column3Position = startColumnIndex * 3 + 2;

            int[] column1 = ArrayUtil.getColumn(column1Position, puzzle);
            int[] column2 = ArrayUtil.getColumn(column2Position, puzzle);
            int[] column3 = ArrayUtil.getColumn(column3Position, puzzle);

            if (twoOutOfThreeSubGroupProcessing(puzzle, column1, column1Position, false, column2, column3)) return true;
            if (twoOutOfThreeSubGroupProcessing(puzzle, column2, column2Position, false, column1, column3)) return true;
            if (twoOutOfThreeSubGroupProcessing(puzzle, column3, column3Position, false, column1, column2)) return true;
        }

        return false;
    }

    private static boolean twoOutOfThreeSubGroupProcessing(
            int[][] puzzle,
            int[] candidate,
            int candidatePosition,
            boolean candidatePositionIsRow,
            int[] other1,
            int[] other2
    ) {
        for (int subGroup = 0; subGroup < 3; subGroup++) {
            int value1Position = subGroup * 3 + 0;
            int value2Position = subGroup * 3 + 1;
            int value3Position = subGroup * 3 + 2;

            int value1 = candidate[value1Position];
            int value2 = candidate[value2Position];
            int value3 = candidate[value3Position];

            if (value1 == 0 && value2 == 0 && value3 == 0) continue;
            if (value1 != 0 && value2 != 0 && value3 != 0) continue;

            int candidateSubGroupPosition = -1;

            if (value1 != 0 && value2 != 0) candidateSubGroupPosition = value3Position;
            else if (value1 != 0 && value3 != 0) candidateSubGroupPosition = value2Position;
            else if (value2 != 0 && value3 != 0) candidateSubGroupPosition = value1Position;
            else continue;

            int designatedRow = candidatePositionIsRow ? candidatePosition : candidateSubGroupPosition;
            int designatedColumn = candidatePositionIsRow ? candidateSubGroupPosition : candidatePosition;

            int[] candidateValues = SudokuUtil.getCandidateValuesOfCell(puzzle, designatedRow, designatedColumn);

            for (int candidateValue : candidateValues) {
                if (ArrayUtil.contains(other1, candidateValue) && ArrayUtil.contains(other2, candidateValue)) {
                    puzzle[designatedRow][designatedColumn] = candidateValue;
                    return true;
                }
            }
        }

        return false;
    }
}
