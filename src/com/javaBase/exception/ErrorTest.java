package com.javaBase.exception;

/**
 * java��������޷����������--Error   ������Դ�ľ���jvmϵͳ�ڲ�����
 */

public class ErrorTest {

    public static void main(String[] args) {
        //1.ջ�����java.lang.StackOverflowError
//        main(args);
        //2.�����:java.lang.OutOfMemoryError: Java heap space----OOM
        Integer[] arr = new Integer[1024*1024*1024];
    }

}
