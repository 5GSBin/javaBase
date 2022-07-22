package com.binge.easy;

public class huiwen {

    public static void main(String[] args) {
        int x = 1234554321;
        boolean is = isPalindrome(x);
        System.out.println(is);

    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        StringBuffer sb = new StringBuffer(String.valueOf(x));
        StringBuffer re = sb.reverse();
        if (re.toString().equals(String.valueOf(x))) {
            return true;
        }
        return false;
    }

}

