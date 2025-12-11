package org.example;

import java.util.ArrayList;
import java.util.List;

public class Day5 {
    private List<String> lines;
    private List<Integer> ranges = new ArrayList<>();
    private List<Integer> ids = new ArrayList<>();

    public Day5(List<String> lines) {
        this.lines = lines;
        parseInput();
    }

    private void parseInput() {
        boolean foundBlankRow = false;
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                foundBlankRow = true;
                continue;
            }

            if (foundBlankRow) {
                ids.add(Integer.parseInt(line));
            } else {
                ranges.add(Integer.parseInt(line.split("-")[0]));
                ranges.add(Integer.parseInt(line.split("-")[1]));
            }
        }
    }

    private boolean inRange(String id) {
        return false;
    }

    public void run() {
//        for (String id : ids) {
//
//        }
        System.out.println(ranges);
    }
}
