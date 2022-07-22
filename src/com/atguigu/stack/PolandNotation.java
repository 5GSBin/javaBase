package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        //(30+4)*5-6 => 3 4 + 5 * 6 -
        //说明：为了方便，将表达式每一个元素用符号隔开
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1.将表达式放到ArrayList中
        //2.将ArrayList传入一个方法，用栈辅助该方法完成计算
        try {
            List<String> list = getListString(suffixExpression);
            System.out.println("rpnList =" + list);
            int res = calculate(list);
            System.out.println("res = " + res);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //将一个逆波兰表达式的每一个元素，一次放入到ArrayList中
    public static List<String> getListString(String expression){
        String[] split = expression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for(String ele: split){
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> list){
        //创建一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历 list
        for (String item: list){
            //使用正则匹配字符串的方式进行判断
            if(item.matches("\\d+")){   //判断如果是数
                //入栈
                stack.push(item);
            }else{  //如果是字符
                //出两个数先
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1 / num2;
                }else{
                    throw new RuntimeException("运算符有误");
                }
                //有计算，则结果需要重新入栈
                stack.push(res+"");
            }
        }
        //最后栈中留有一个结果，就是表达式的值
        return Integer.parseInt(stack.pop());
    }
}
