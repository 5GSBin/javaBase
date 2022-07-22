package com.atguigu.testdemo;

public class test2 {
    public static void main(String[] args) {
        try {
            System.out.println();
        }catch (Exception a){

        }
        int a = 2;
        if(a>0 && (a++)>1){
            System.out.println(a);
        }else{
            System.out.println(1);
        }
    }
}
