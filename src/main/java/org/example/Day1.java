package org.example;

import java.util.List;

public class Day1 {
    private List<String> input;
    private int curr = 50;
    private int password = 0;

    public Day1(List<String> input) {
        this.input = input;
    }

    private Rotation splitRotation(String s) {
        s = s.trim();
        char letter = s.charAt(0);
        int number = Integer.parseInt(s.substring(1));
        return new Rotation(letter, number);
    }

    private void rotate(char dir, int amt) {
        if (dir == 'L') {
            amt *= -1;
        }

        int fullRotations = Math.abs((int)Math.floor(amt / 100));
        amt %= 100;


        if (curr != 0) {
            curr += amt;
            if (curr > 100 || curr < 0) {
                password++;
            }
        } else {
            curr += amt;
        }
        if (curr < 0) {
            curr += 100;
        } else if (curr > 99) {
            curr -= 100;
        }

        if (curr == 0) {
            password++;
        }
        password += fullRotations;
    }


    public void run() {
        for (String line : input) {
            Rotation r = splitRotation(line);
            char dir = r.getDirection();
            int amt = r.getAmount();
            rotate(dir, amt);
//            System.out.println(curr + " " + password);
//            break;
        }

        System.out.println("The password is: " + password);
//        System.out.println(curr);
    }
}


class Rotation {
    private final char direction;
    private final int amount;

    public Rotation(char direction, int amount) {
        this.direction = direction;
        this.amount = amount;
    }

    public char getDirection() {
        return direction;
    }

    public int getAmount() {
        return amount;
    }
}