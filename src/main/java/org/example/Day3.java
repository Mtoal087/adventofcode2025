package org.example;

import java.util.ArrayList;
import java.util.List;

public class Day3 {
    List<String> banks = new ArrayList<String>();
    long total = 0L;

    public Day3 (List<String> banks) {
        this.banks = banks;
    }

//    PART 1
//    private int findLargest(List<Integer> bank) {
//        int largest = 0;
//        int len = bank.size();
//        for (int start = 0; start < len; start++) {
//            int first = bank.get(start) * 10;
//            if (first < largest) {continue;}
//            for (int end = len - 1; end > start; end--) {
//                int second = bank.get(end);
//                int sum = first + second;
//                if (sum > largest) {
//                    largest = sum;
//                }
//            }
//        }
//        return largest;
//    }

//    PART 2
    private long findLargest(List<Integer> bank) {
        List<Integer> stack = new ArrayList<>();
        int len = bank.size();
        int targetLen = 12;
        int toRemove = len - targetLen;

        for (int i = 0; i < len; i++) {
            int digit = bank.get(i);
            while (!stack.isEmpty() && toRemove > 0
                && stack.getLast() < digit
                && (stack.size() - 1 + (len - i)) >= targetLen) {
                stack.removeLast();
                toRemove--;
            }

            if (stack.size() < targetLen) {
                stack.add(digit);
            } else {
                toRemove--;
            }
        }

        long largest = 0L;
        for (int digit : stack) {
            largest = largest * 10 + digit;
        }

        return largest;
    }

    public void run() {
        for (String bank : banks) {
            List<Integer> currRow = new ArrayList<>();
            for (String num : bank.split("")) {
                currRow.add(Integer.parseInt(num));
            }
            long result = findLargest(currRow);
            System.out.println(result);
            total +=  result;
        }
        System.out.println("Total: " + total);
    }
}
