package com.github.ckufner.puzzles.sudoku.strategies;

import com.github.ckufner.IntTuple;
import com.github.ckufner.puzzles.sudoku.SudokuUtil;
import com.github.ckufner.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public interface HiddenSingleStrategy {
    static boolean applyStrategy(int[][] puzzle) {
        for (int i = 0; i < 9; i++) {
            if (hiddenSingleForRow(puzzle, i)) return true;
            if (hiddenSingleForColumn(puzzle, i)) return true;
            if (hiddenSingleForSector(puzzle, i)) return true;
        }

        return false;
    }

    static boolean hiddenSingleForRow(int[][] puzzle, int rowPosition) {
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

    static boolean hiddenSingleForColumn(int[][] puzzle, int columnPosition) {
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

    static boolean hiddenSingleForSector(int[][] puzzle, int sector) {
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
}
