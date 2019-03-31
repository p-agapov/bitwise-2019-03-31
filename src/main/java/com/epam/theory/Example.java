package com.epam.theory;

public class Example {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(-Integer.MAX_VALUE);

        System.out.println(Integer.MIN_VALUE);
        System.out.println(-Integer.MIN_VALUE);


        int value = ((2 & 0xFF) << 24) |
                ((3 & 0xFF) << 16) |
                ((4 & 0xFF) << 8)  |
                ((5 & 0xFF) << 0);
    }
}
