package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {
        //�ȶ���һ���沨�����ʽ
        //(30+4)*5-6 => 3 4 + 5 * 6 -
        //˵����Ϊ�˷��㣬�����ʽÿһ��Ԫ���÷��Ÿ���
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //˼·
        //1.�����ʽ�ŵ�ArrayList��
        //2.��ArrayList����һ����������ջ�����÷�����ɼ���
        try {
            List<String> list = getListString(suffixExpression);
            System.out.println("rpnList =" + list);
            int res = calculate(list);
            System.out.println("res = " + res);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //��һ���沨�����ʽ��ÿһ��Ԫ�أ�һ�η��뵽ArrayList��
    public static List<String> getListString(String expression){
        String[] split = expression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for(String ele: split){
            list.add(ele);
        }
        return list;
    }

    //��ɶ��沨�����ʽ������
    public static int calculate(List<String> list){
        //����һ��ջ����
        Stack<String> stack = new Stack<>();
        //���� list
        for (String item: list){
            //ʹ������ƥ���ַ����ķ�ʽ�����ж�
            if(item.matches("\\d+")){   //�ж��������
                //��ջ
                stack.push(item);
            }else{  //������ַ�
                //����������
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
                    throw new RuntimeException("���������");
                }
                //�м��㣬������Ҫ������ջ
                stack.push(res+"");
            }
        }
        //���ջ������һ����������Ǳ��ʽ��ֵ
        return Integer.parseInt(stack.pop());
    }
}
