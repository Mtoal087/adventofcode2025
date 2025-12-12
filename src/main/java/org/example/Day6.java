package org.example;

import java.util.Arrays;
import java.util.List;

public class Day6 {
    private final String[][] grid;
    private long total = 0L;

    public Day6(List<String> lines) {
        this.grid = GridParser.toGrid(lines);
    }

    public void run() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

//        System.out.println("Total: " + total);
    }
}

class GridParser {
    public static String[][] toGrid(List<String> lines) {
        // collect non-empty rows
        java.util.List<String[]> rows = new java.util.ArrayList<>();

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;
            rows.add(line.split("\\s+"));
        }

        if (rows.isEmpty()) {
            return new String[0][0];
        }

        int numRows = rows.size();
        int numCols = rows.get(0).length;

        // optional: enforce rectangle
        for (int r = 1; r < numRows; r++) {
            if (rows.get(r).length != numCols) {
                throw new IllegalArgumentException(
                        "Non-rectangular input: row " + r + " has " + rows.get(r).length +
                                " cols, expected " + numCols
                );
            }
        }

        String[][] grid = new String[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            grid[r] = rows.get(r);
        }

        return grid;
    }
}
