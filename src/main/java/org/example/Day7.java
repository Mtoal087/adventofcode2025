package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day7 {

    // Small helper type for beam positions
    private record Pos(int r, int c) {}
    private record StepResult(long[] nextCounts, long endedTimelines) {}

    private final List<String> lines;
    private final int rows;
    private final int cols;
    private final char[][] grid;

    private long totalTimelines = 0L;

    public Day7(List<String> lines) {
        this.lines = lines;
        this.rows = lines.size();
        this.cols = lines.stream().mapToInt(String::length).max().orElse(0);
        this.grid = buildGrid();
    }

    // 1) Build the char grid from input lines
    private char[][] buildGrid() {
        char[][] g = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            String line = lines.get(r);
            for (int c = 0; c < cols; c++) {
                g[r][c] = (c < line.length()) ? line.charAt(c) : '.';
            }
        }
        return g;
    }

    // 2) Find the starting S position
    private Pos findStart() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 'S') {
                    return new Pos(r, c);
                }
            }
        }
        throw new IllegalStateException("No 'S' found in grid");
    }

//    PART 1
//    // 3) Initialize the first beam(s) under S
//    private Set<Pos> initBeams(Pos start) {
//        Set<Pos> beams = new HashSet<>();
//        int sRow = start.r();
//        int sCol = start.c();
//        if (sRow + 1 < rows) {
//            beams.add(new Pos(sRow + 1, sCol));
//        }
//        return beams;
//    }
//
//    // 4) Process one simulation step: move all beams down one row
//    //    and update totalSplits when a beam hits a splitter '^'.
//    private Set<Pos> stepBeams(Set<Pos> currentBeams) {
//        Set<Pos> nextBeams = new HashSet<>();
//
//        for (Pos p : currentBeams) {
//            int r = p.r();
//            int c = p.c();
//            int nr = r + 1;
//
//            // Beam leaves the grid
//            if (nr >= rows) {
//                continue;
//            }
//
//            char cell = grid[nr][c];
//
//            if (cell == '.') {
//                // Beam just continues downward
//                nextBeams.add(new Pos(nr, c));
//            } else if (cell == '^') {
//                // Beam is split
//                totalSplits++;
//
//                int leftC = c - 1;
//                int rightC = c + 1;
//
//                if (leftC >= 0 && leftC < cols) {
//                    nextBeams.add(new Pos(nr, leftC));
//                }
//                if (rightC >= 0 && rightC < cols) {
//                    nextBeams.add(new Pos(nr, rightC));
//                }
//            } else if (cell == 'S') {
//                // Treat S as empty if ever encountered again (unlikely)
//                nextBeams.add(new Pos(nr, c));
//            } else {
//                // Any other char: treat as empty space by default
//                nextBeams.add(new Pos(nr, c));
//            }
//        }
//
//        return nextBeams;
//    }
//
//    // 5) Run the full simulation until no beams are left
//    private void simulate() {
//        Pos start = findStart();
//        Set<Pos> beams = initBeams(start);
//
//        while (!beams.isEmpty()) {
//            beams = stepBeams(beams);
//        }
//    }

//    PART 2
// 3) Initialize the timeline counts just below S
//    returns counts for the row where the particle starts moving
    private long[] initTimelines(Pos start) {
        long[] counts = new long[cols];
        int sRow = start.r();
        int sCol = start.c();

        if (sRow + 1 < rows) {
            counts[sCol] = 1L;    // one timeline at the cell just below S
        }
        return counts;
    }

    // 4) Perform a single downward step for all timelines at a given row
    private StepResult stepTimelines(int currentRow, long[] currCounts) {
        int nextRow = currentRow + 1;

        // If next row is past the bottom, all current timelines terminate
        if (nextRow >= rows) {
            long ended = sumCounts(currCounts);
            return new StepResult(new long[cols], ended);
        }

        long[] nextCounts = new long[cols];
        long endedTimelines = 0L;

        for (int c = 0; c < cols; c++) {
            long ways = currCounts[c];
            if (ways == 0) continue;

            char cell = grid[nextRow][c];

            if (cell == '^') {
                // Split: each timeline becomes two
                int leftC = c - 1;
                int rightC = c + 1;

                if (leftC >= 0 && leftC < cols) {
                    nextCounts[leftC] += ways;
                }
                if (rightC >= 0 && rightC < cols) {
                    nextCounts[rightC] += ways;
                }
            } else {
                // Normal space (., S, etc): just move straight down
                nextCounts[c] += ways;
            }
        }

        return new StepResult(nextCounts, endedTimelines);
    }

    // 5) Helper to check if there are any active timelines on a row
    private boolean hasActive(long[] counts) {
        for (long v : counts) {
            if (v != 0L) return true;
        }
        return false;
    }

    // 6) Helper to sum counts (used when timelines exit past the bottom)
    private long sumCounts(long[] counts) {
        long sum = 0L;
        for (long v : counts) sum += v;
        return sum;
    }

    // 7) Run the full simulation
    private void simulate() {
        Pos start = findStart();
        long[] currCounts = initTimelines(start);
        int currentRow = start.r();

        while (hasActive(currCounts)) {
            StepResult step = stepTimelines(currentRow, currCounts);
            totalTimelines += step.endedTimelines();
            currCounts = step.nextCounts();
            currentRow++;

            if (currentRow >= rows) {
                // any remaining counts have just exited
                totalTimelines += sumCounts(currCounts);
                break;
            }
        }
    }

    // 6) Public entry point
    public void run() {
        simulate();
        System.out.println("Total splits: " + totalTimelines);
    }
}
