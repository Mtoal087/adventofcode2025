package org.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> readFile(String fileName) { // for line separated values
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

//    private static List<String> readFile(String fileName) { // for comma separated values
//        try {
//            String line = Files.readString(Path.of(fileName));
//            List<String> lines = new ArrayList<>();
//            String[] parts = line.split(",");
//            for (String part : parts) {
//                lines.add(part.trim());
//            }
//            return lines;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void main(String[] args) {
        List<String> lines = readFile("test.txt");
//        List<String> lines = readFile("input.txt");

//        for (String line : lines){
//            System.out.println(line);
//        }

        Day7 day = new Day7(lines);
        day.run();
    }
}