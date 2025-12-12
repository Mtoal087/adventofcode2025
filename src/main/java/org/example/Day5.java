package org.example;

import java.util.ArrayList;
import java.util.List;

public class Day5 {
    private final List<String> lines;
    private final List<Range> ranges = new ArrayList<>();
    private final List<Long> ids = new ArrayList<>();
//    private int count = 0;
    private List<Long> allIds = new ArrayList<>();
    record Range (long start, long end){};

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
                ids.add(Long.parseLong(line));
            } else {
                String[] parts = line.split("-");
                long start = Long.parseLong(parts[0]);
                long end = Long.parseLong(parts[1]);
                ranges.add(new Range(start, end));
            }
        }
    }

//    PART 1
//    private int inRange(long id) {
//        // 0 -> false, 1 -> true
//        for (int i = 0; i < ranges.size(); i += 2) {
//            if (id >= ranges.get(i) && id <= ranges.get(i+ 1)) {
//                return 1;
//            }
//        }
//        return 0;
//    }

//    PART 2
    private long countAllIdsInRanges() {
        if (ranges.isEmpty()) return 0L;

        ranges.sort((a,b) -> Long.compare(a.start(), b.start()));

        long total = 0L;
        long currStart = ranges.get(0).start();
        long currEnd = ranges.get(0).end();

        for (int i = 1; i < ranges.size(); i++) {
            Range r = ranges.get(i);
            if (r.start() <= currEnd + 1) {
                currEnd = Math.max(currEnd, r.end());
            } else {
                total += (currEnd - currStart + 1);
                currStart = r.start();
                currEnd = r.end();
            }
        }

        total += (currEnd - currStart + 1);
        return total;
    }

    public void run() {
//        for (long id : ids) {
//            System.out.println(id);
//            count += inRange(id);
//        }
//        System.out.println(ranges);
//        System.out.println(count);

        long allAmt = countAllIdsInRanges();
//        System.out.println(allIds);
        System.out.println(allAmt);
    }
}
