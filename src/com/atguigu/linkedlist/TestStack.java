package com.atguigu.linkedlist;

import java.util.Stack;

//��ʾջ�Ļ���ʹ��
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        while(stack.size()>0){
            System.out.println(stack.pop());

        }
    }

}
