package com.github.ckufner.puzzles.sudoku;

//TODO: test
//TODO: implement all strategies from https://www.sudopedia.org/wiki/Solving_Technique
//TODO: Knuth's Algorithm X

import com.github.ckufner.IntTuple;
import com.github.ckufner.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

interface SudokuStrategyUtil {
    static void applyPuzzleSolvingStrategies(int[][] puzzle) {
        if (fullHouse(puzzle)) applyPuzzleSolvingStrategies(puzzle);
        if (nakedSingle(puzzle)) applyPuzzleSolvingStrategies(puzzle);
        if (hiddenSingle(puzzle)) applyPuzzleSolvingStrategies(puzzle);
        if (twoOutOfThree(puzzle)) applyPuzzleSolvingStrategies(puzzle);
    }

    static boolean fullHouse(int[][] puzzle) {
        for (int i = 0; i < 9; i++) {
            if (fullHouseForRow(puzzle, i)) return true;
            if (fullHouseForColumn(puzzle, i)) return true;
            if (fullHouseForSector(puzzle, i)) return true;
        }

        return false;
    }

    static boolean fullHouseForRow(int[][] puzzle, int row) {
        int[] correspondingRow = puzzle[row];

        ArrayList<Integer> candidateValues = SudokuUtil.getCandidateList();

        int designatedPos = -1;

        for (int currentColumnIndex = 0; currentColumnIndex < 9; currentColumnIndex++) {
            if (correspondingRow[currentColumnIndex] == 0) designatedPos = currentColumnIndex;

            candidateValues.remove(Integer.valueOf(correspondingRow[currentColumnIndex]));
        }

        if (candidateValues.size() == 1 && designatedPos != -1) {
            puzzle[row][designatedPos] = candidateValues.get(0);
            return true;
        }

        return false;
    }

    static boolean fullHouseForColumn(int[][] puzzle, int column) {
        int[] candidateColumn = SudokuUtil.getColumn(puzzle, column);

        ArrayList<Integer> candidateValues = SudokuUtil.getCandidateList();

        int designatedPos = -1;

        for (int currentRowIndex = 0; currentRowIndex < 9; currentRowIndex++) {
            if (candidateColumn[currentRowIndex] == 0) designatedPos = currentRowIndex;

            candidateValues.remove(Integer.valueOf(candidateColumn[currentRowIndex]));
        }

        if (candidateValues.size() == 1 && designatedPos != -1) {
            puzzle[designatedPos][column] = candidateValues.get(0);
            return true;
        }

        return false;
    }

    static boolean fullHouseForSector(int[][] puzzle, int sector) {
        int[] candidateSector = SudokuUtil.getSector(puzzle, sector);

        ArrayList<Integer> candidateValues = SudokuUtil.getCandidateList();

        int designatedPos = -1;

        for (int currentPosition = 0; currentPosition < 9; currentPosition++) {
            if (candidateSector[currentPosition] == 0) designatedPos = currentPosition;

            candidateValues.remove(Integer.valueOf(candidateSector[currentPosition]));
        }

        if (candidateValues.size() == 1 && designatedPos != -1) {
            IntTuple cellPosition = SudokuUtil.getGridPositionForSectorAndPosition(sector, designatedPos);
            puzzle[cellPosition.getItem1()][cellPosition.getItem2()] = candidateValues.get(0);
            return true;
        }

        return false;
    }

    static boolean hiddenSingle(int[][] puzzle) {
        for (int i = 0; i < 9; i++) {
            if (nakedSingleForRow(puzzle, i)) return true;
            if (nakedSingleForColumn(puzzle, i)) return true;
            if (nakedSingleForSector(puzzle, i)) return true;
        }

        return false;
    }

    static boolean nakedSingle(int[][] puzzle) {
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

    static boolean nakedSingleForRow(int[][] puzzle, int rowPosition) {
        ArrayList<Integer>[] listOfCandidates = getListOfCandidatesForNakedSingle(
                SudokuUtil.getCandidateValuesOfRow(puzzle, rowPosition)
        );

        return nakedSingle(
                listOfCandidates,
                (columnPosition, value) -> {
                    puzzle[rowPosition][columnPosition] = value;
                }
        );
    }

    static boolean nakedSingleForColumn(int[][] puzzle, int columnPosition) {
        ArrayList<Integer>[] listOfCandidates = getListOfCandidatesForNakedSingle(
                SudokuUtil.getCandidateValuesOfColumn(puzzle, columnPosition)
        );

        return nakedSingle(
                listOfCandidates,
                (rowPosition, value) -> {
                    puzzle[rowPosition][columnPosition] = value;
                }
        );
    }

    static boolean nakedSingleForSector(int[][] puzzle, int sector) {
        ArrayList<Integer>[] listOfCandidates = getListOfCandidatesForNakedSingle(
                SudokuUtil.getCandidateValuesOfSector(puzzle, sector)
        );

        return nakedSingle(
                listOfCandidates,
                (candidatePosition, value) -> {
                    IntTuple gridPosition = SudokuUtil.getGridPositionForSectorAndPosition(sector, candidatePosition);
                    puzzle[gridPosition.getItem1()][gridPosition.getItem2()] = value;
                }
        );
    }

    private static boolean nakedSingle(
            ArrayList<Integer>[] listOfCandidates,
            BiConsumer<Integer, Integer> nakedSingleConsumer
    ) {
        boolean foundNakedSingle = false;

        for (int candidatePosition = 0; candidatePosition < 9; candidatePosition++) {
            List<Integer> candidateList = listOfCandidates[candidatePosition];

            if (candidateList.size() != 1) continue;

            nakedSingleConsumer.accept(candidatePosition, candidateList.get(0));

            foundNakedSingle = true;
        }

        return foundNakedSingle;
    }

    private static ArrayList<Integer>[] getListOfCandidatesForNakedSingle(List<int[]> candidates) {
        ArrayList<Integer>[] listOfCandidates = new ArrayList[9];
        for (int position = 0; position < 9; position++) {
            listOfCandidates[position] = ArrayUtil.asArrayList(candidates.get(position));
        }

        List<Integer> candidateValues = SudokuUtil.getCandidateList();
        List<Integer> valuesToRemove = new ArrayList<>();

        for (Integer candidateValue : candidateValues) {
            int counter = 0;

            if (listOfCandidates[0].contains(candidateValue)) counter++;
            if (listOfCandidates[1].contains(candidateValue)) counter++;
            if (listOfCandidates[2].contains(candidateValue)) counter++;
            if (listOfCandidates[3].contains(candidateValue)) counter++;
            if (listOfCandidates[4].contains(candidateValue)) counter++;
            if (listOfCandidates[5].contains(candidateValue)) counter++;
            if (listOfCandidates[6].contains(candidateValue)) counter++;
            if (listOfCandidates[7].contains(candidateValue)) counter++;
            if (listOfCandidates[8].contains(candidateValue)) counter++;

            if (counter > 1) valuesToRemove.add(candidateValue);
        }

        for (Integer valueToRemove : valuesToRemove) {
            listOfCandidates[0].remove(valueToRemove);
            listOfCandidates[1].remove(valueToRemove);
            listOfCandidates[2].remove(valueToRemove);
            listOfCandidates[3].remove(valueToRemove);
            listOfCandidates[4].remove(valueToRemove);
            listOfCandidates[5].remove(valueToRemove);
            listOfCandidates[6].remove(valueToRemove);
            listOfCandidates[7].remove(valueToRemove);
            listOfCandidates[8].remove(valueToRemove);
        }

        return listOfCandidates;
    }

    static boolean twoOutOfThree(int[][] puzzle) {
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
