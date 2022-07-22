package com.atguigu.stack;

import java.util.ArrayList;

public class Calculator {
    public static void main(String[] args) {
        //��ɱ��ʽ����
        String expression = "70+20*6-4";
        //��������ջ ��ջ ����ջ
        ArrayStack1 numStack = new ArrayStack1(20);
        ArrayStack1 operStack = new ArrayStack1(20);
        //������Ҫ����ر���
        int index = 0;  //����ɨ��
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';  //ÿ��ɨ��������ַ�char�����浽ch
        String keepNum = "";
        //��ʼwhileѭ��ɨ��expression
        while(true) {
            //���εõ�expression��ÿһ���ַ�
            ch = expression.substring(index, index + 1).charAt(0);
            //�ж��Ƿ�Ϊ�����
            if(operStack.isOper(ch)) {  //�������
                //�жϷ���ջ�����Ƿ�Ϊ��
                if (!operStack.isEmpty()) { //��Ϊ��
                    //��һ��ջ������������뵱ǰ������Ƚϣ�����ǰ��������ȼ����ڻ�С��ջ��������������ջ��ȡ�������ӷ���ջȡһ�����������
                    //�����꣬��ǰ�������ջ��������ֵ��ջ
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();  //��������
                        num2 = numStack.pop();  //������
                        res = operStack.cal(num2,num1,operStack.pop()); //����
                        operStack.push(ch); //�����ջ
                        numStack.push(res); //������ջ
                    } else{ //��ǰ��������ȼ�����ջ�������
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            }else{//���ɨ�赥������ֱ������ջ,������Ϊ��λ��������£������
                //��Ҫ�ж��Ƿ�Ϊ��λ������λ������ջ����λ������һ��һ��ƴ�Ӻ���ջ
                keepNum += ch; //��ɨ���������ֵ������
                //��������һλ����ֱ����ջ
                if(index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) { //��λ��
                        //���ַ���תΪ���֣���ջ
                        numStack.push(Integer.parseInt(keepNum));
                        //���ַ�����գ�����
                        keepNum = "";
                    }
                }
            }
            //����index�ж�ɨ�浽����
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        while(true){
            //����ջΪ�գ������
            if(operStack.isEmpty()){
                break;
            }
            num2 = numStack.pop();
            num1 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("���ʽ%s = %d",expression,res);
    }
}

//����һ��ArrayStack1��ʾջ
class ArrayStack1{
    private int maxSize;    //ջ�Ĵ�С
    private int[] stack;    //���飬����ģ��ջ�����ݾͷ��ڸ�������
    private int top = -1;   //top��ʾջ������ʼ��Ϊ-1
    //������
    public ArrayStack1(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    //����һ�����������Է��ص�ǰջ����ֵ�����ǲ���pop
    public int peek(){
        return  stack[top];
    }
    //ջ��
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //ջ��
    public boolean isEmpty(){
        return top == -1;
    }
    //��ջ-push
    public void push(int value){
        //�ж�ջ�Ƿ���
        if(isFull()){
            System.out.println("ջ����");
            return;
        }
        top++;
        stack[top] = value;
    }
    //��ջ-pop
    public int pop(){
        //�ж�ջ�Ƿ��
        if(isEmpty()){
            throw new RuntimeException("ջ��");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //��ʾջ�����[����ջ],��Ҫ��ջ����ʼ��ʾ����
    public void list(){
        if(isEmpty()){
            System.out.println("ջ�գ�û�����ݣ�");
            return;
        }
        for(int i = top; i >= 0; i--){
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
    //��������������ȼ������ȼ��ǳ���Ա�Լ���ȷ���ģ����ȼ������ֱ�ʾ
    //����Խ�����ȼ�Խ��
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        } else if(oper == '+' || oper == '-'){
            return 0;
        } else{
            return -1;  //�ٶ�Ŀǰ�ı��ʽֻ�� + - * / ������
        }
    }
    //�ж��ǲ���һ�������
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    //���㷽��
    public int cal(int num1, int num2, int oper){
        int res = 0;    //res ���ڴ�ż���Ľ��
        switch (oper){
            //��Ҫע�⣬���浯����������Ϊ���������������ȵ���������Ϊ������ӽ�ȥ������
            //��Ϊջ�Ǻ���ȳ�
            //num1ӦΪ�ȵ������� num2ӦΪ���浯������
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 *num2;
                break;
            case '/':
                res = num1 / num2;
                break;
        }
        return res;
    }
}
