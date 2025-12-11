package org.example;

import java.util.List;

public class Day4 {
    private List<String> lines;
    private  int numRows, numCols;
    private int totalRolls = 0;
    private final char[][] map;

    public Day4(List<String> lines) {
        this.lines = lines;
        this.numRows = lines.size();
        this.numCols = lines.getFirst().length();
        this.map = new char[numRows][numCols];

        for (int r = 0; r < numRows; r++) {
            map[r] = lines.get(r).toCharArray();
        }
    }

    private int findAdjacentRolls(int row, int col) {
        int rolls = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;

                int nr = row + dr;
                int nc = col + dc;

                if (nr < 0 || nr >= numRows || nc < 0 || nc >= numCols) continue;

                if (map[nr][nc] == '@') rolls++;
            }
        }
        return rolls;
    }

    public void run() {
        while (true) {
            boolean[][] toRemove = new boolean[numRows][numCols];
            int removedThisRound = 0;

            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    if (map[r][c] != '@') continue;

                    int adjacent = findAdjacentRolls(r, c);
                    if (adjacent < 4) {
                        toRemove[r][c] = true;
                    }
                }
            }

            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    if (toRemove[r][c]) {
                        map[r][c] = '.';
                        removedThisRound++;
                    }
                }
            }

            if (removedThisRound == 0) {
                break;
            }

            totalRolls += removedThisRound;
        }

        System.out.println("Total: " + totalRolls);

//        for (char[] row : map) {
//            for (char col : row) {
//                System.out.print(col);
//            }
//            System.out.println(row);
//        }
    }
}
