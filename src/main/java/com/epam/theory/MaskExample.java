package com.epam.theory;

public class MaskExample {

    public static void main(String[] args) {
        int mask = 0b000000011;
        int flag = 0b10;

        flag = ~flag;
        System.out.println(Integer.toBinaryString(flag & mask));
    }
}
