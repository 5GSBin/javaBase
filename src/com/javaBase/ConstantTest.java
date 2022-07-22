package com.javaBase;

public class ConstantTest {

    public static final int F_A = 3;

    public static void main(String[] args) {
        final int A = 1;
        int b = 2;
        int c = b;
        System.out.println(A);
        System.out.println(b);
        System.out.println(c);
        b = 1;
        System.out.println();
        System.out.println(A);
        System.out.println(b);
        System.out.println(A==b);
        System.out.println(c);

    }
}
