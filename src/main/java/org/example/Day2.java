package org.example;

import java.util.ArrayList;
import java.util.List;

public class Day2 {
    private List<String> values;
    private Long total = Long.parseLong("0");

    public Day2 (List<String> values) {
        this.values = values;
    }

    private List<String> getRange(String range) {
        String[] parts = range.split("-");
        List<String> result = new ArrayList<>();
        result.add(parts[0]);
        result.add(parts[1]);
        return result;
    }

//    PART 1
//    private List<Long> findInvalidIds(String start, String end) {
//        List<Long> result = new ArrayList<>();
//        long startNum = Long.parseLong(start);
//        long endNum = Long.parseLong(end);
//        for (long i = startNum; i <= endNum; i++) {
//            String curr = Long.toString(i);
//            int len = curr.length();
//            if (len % 2 == 0) {
//                int half = len / 2;
//                String firstHalfOfCurr =  curr.substring(0, half);
//                String secondHalfOfCurr = curr.substring(half);
//                if (firstHalfOfCurr.equals(secondHalfOfCurr)) {result.add(i);}
//            }
//        }
//        return result;
//    }

//    PART2
    private List<Long> findInvalidIds(String start, String end) {
        List<Long> result = new ArrayList<>();

        long startNum = Long.parseLong(start);
        long endNum = Long.parseLong(end);

        // i = the current id being looked at
        for (long i = startNum; i <= endNum; i++) {
            String curr = Long.toString(i);
            if (isInvalid(curr)) {result.add(i);}
        }

        return result;
    }

//    PART 2
    private boolean isInvalid(String id) {
        int len = id.length();
        for (int j = 1; j <= len/2; j++) {
            if (len % j != 0) {
                continue;
            }

            String block = id.substring(0, j);
            boolean allMatch = true;

            for (int pos = j; pos < len; pos += j) {
                if (!id.regionMatches(pos, block, 0, j)) {
                    allMatch = false;
                    break;
                }
            }

            if (allMatch) {
                return true;
            }
        }

        return false;
    }

    public void run() {
        for (String value : values) {
            List<String> range = getRange(value);
            String start = range.get(0);
            String end = range.get(1);
            List<Long> invalidIds = findInvalidIds(start, end);
            for (long id : invalidIds) {
//                System.out.println(id);
                total += id;
            }
//            System.out.println("Total: " + total);
//            System.out.println("Next");
        }
        System.out.println("Total: " + total);
    }
}
