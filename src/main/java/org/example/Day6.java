package org.example;

import java.util.ArrayList;
import java.util.List;

public class Day6 {
    private final List<String> lines;
    private final int rows;
    private final int cols;
    private final char[][] grid;

    public Day6(List<String> lines) {
//        this.grid = Grid.toGrid(lines);
        this.lines = lines;
        this.rows = lines.size();
        this.cols = lines.stream().mapToInt(String::length).max().orElse(0);

        // build char to grid, padding with spaces
        this.grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            String line = lines.get(r);
            for (int c = 0; c < cols; c++) {
                grid[r][c] = (c < line.length()) ? line.charAt(c) : ' ';
            }
        }
    }

//    PART 1
//    private long doMath(String[] problem) {
//        boolean add = true;
//        String op = problem[problem.length - 1];
//
//        long answer = 0L;
//        if (!op.equals("+")) {
//            add = false;
//            answer = 1L;
//        }
//
//        for (int i = 0; i < problem.length - 1; i++) {
//            if (add) {
//                answer += Long.parseLong(problem[i]);
//            } else {
//                answer *= Long.parseLong(problem[i]);
//            }
//        }
//        return answer;
//    }

//    PART 2
    private long cephalopodMath() {
        boolean[] separator = new boolean[cols];
        for (int c = 0; c < cols; c++) {
            boolean allSpaces = true;
            for (int r = 0; r < rows; r++) {
                if (grid[r][c] != ' ') {
                    allSpaces = false;
                    break;
                }
            }
            separator[c] = allSpaces;
        }

        List<int[]> segments = new ArrayList<>();
        int c = 0;
        while (c < cols) {
            while (c < cols && separator[c]) c++;
            if (c >= cols) break;

            int start = c;
            while ( c < cols && !separator[c]) c++;
            int end = c - 1;

            segments.add(new int[] {start, end});
        }

        int opRow = rows -1;
        long total = 0L;

        for (int[] seg : segments) {
            int startCol =  seg[0];
            int endCol =  seg[1];

            char op = '?';
            for (int col = startCol; col <= endCol; col++) {
                char ch = grid[opRow][col];
                if (ch == '+' || ch == '*') {
                    op = ch;
                    break;
                }
            }
            if (op == '?') continue;

            List<Long> numbers = new ArrayList<>();
            for (int col = endCol; col >= startCol; col--) {
                StringBuilder sb = new StringBuilder();
                for (int row = 0; row < opRow; row++) {
                    char ch = grid[row][col];
                    if (Character.isDigit(ch)) {
                        sb.append(ch);
                    }
                }
                if (sb.length() > 0) {
                    numbers.add(Long.parseLong(sb.toString()));
                }
            }

            if (numbers.isEmpty()) {
                continue;
            }

            long value;
            if (op == '+') {
                value = 0L;
                for (long n : numbers) {
                    value += n;
                }
            } else {
                value = 1L;
                for (long n : numbers) {
                    value *= n;
                }
            }

            total += value;
        }
        return total;
    }

    public void run() {
//        for (int i = 0; i < grid.length; i++) {
//            long answer = doMath(grid[i]);
//            for (int j = 0; j < grid[0].length; j++) {
//                System.out.print(grid[i][j] + " ");
//            }
//            System.out.println();
//            System.out.println(answer);
//            total = total + answer;
//        }

        long answer = cephalopodMath();
        System.out.println("Total: " + answer);
    }
}


//class Grid {
//    private static String[][] transposeGrid(String[][] grid) {
//        String[][] transposedGrid = new String[grid[0].length][grid.length];
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                transposedGrid[j][i] = grid[i][j];
//            }
//        }
//        return transposedGrid;
//    }
//
//    public static String[][] toGrid(List<String> lines) {
//        // collect non-empty rows
//        List<String[]> rows = new ArrayList<>();
//
//        for (String line : lines) {
//            line = line.trim();
//            if (line.isEmpty()) continue;
//            rows.add(line.split("\\s+"));
//        }
//
//        if (rows.isEmpty()) {
//            return new String[0][0];
//        }
//
//        int numRows = rows.size();
//        int numCols = rows.get(0).length;
//
//        // optional: enforce rectangle
//        for (int r = 1; r < numRows; r++) {
//            if (rows.get(r).length != numCols) {
//                throw new IllegalArgumentException(
//                        "Non-rectangular input: row " + r + " has " + rows.get(r).length +
//                                " cols, expected " + numCols
//                );
//            }
//        }
//
//        String[][] grid = new String[numRows][numCols];
//        for (int r = 0; r < numRows; r++) {
//            grid[r] = rows.get(r);
//        }
//
//        return transposeGrid(grid);
//    }
//}
