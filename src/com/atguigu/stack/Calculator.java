package com.atguigu.stack;

import java.util.ArrayList;

public class Calculator {
    public static void main(String[] args) {
        //完成表达式运算
        String expression = "70+20*6-4";
        //创建两个栈 数栈 符号栈
        ArrayStack1 numStack = new ArrayStack1(20);
        ArrayStack1 operStack = new ArrayStack1(20);
        //定义需要的相关变量
        int index = 0;  //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';  //每次扫描出来的字符char，保存到ch
        String keepNum = "";
        //开始while循环扫描expression
        while(true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断是否为运算符
            if(operStack.isOper(ch)) {  //是运算符
                //判断符号栈里面是否为空
                if (!operStack.isEmpty()) { //不为空
                    //看一眼栈顶的运算符，与当前运算符比较，若当前运算符优先级等于或小于栈顶运算符，则从数栈中取两数，从符号栈取一个运算符运算
                    //操作完，当前运算符入栈，计算后的值入栈
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();  //被操作数
                        num2 = numStack.pop();  //操作数
                        res = operStack.cal(num2,num1,operStack.pop()); //计算
                        operStack.push(ch); //入符号栈
                        numStack.push(res); //入数字栈
                    } else{ //当前运算符优先级大于栈顶运算符
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            }else{//如果扫描单个数字直接入数栈,在数字为多位数的情况下，会出错。
                //需要判断是否为多位数，个位数则入栈，多位数，则一个一个拼接后入栈
                keepNum += ch; //将扫描出来的数值保存先
                //如果是最后一位，则直接入栈
                if(index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) { //个位数
                        //将字符串转为数字，入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //将字符串清空！！！
                        keepNum = "";
                    }
                }
            }
            //根据index判断扫面到哪里
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        while(true){
            //符号栈为空，则结束
            if(operStack.isEmpty()){
                break;
            }
            num2 = numStack.pop();
            num1 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式%s = %d",expression,res);
    }
}

//定义一个ArrayStack1表示栈
class ArrayStack1{
    private int maxSize;    //栈的大小
    private int[] stack;    //数组，数组模拟栈，数据就放在该数组中
    private int top = -1;   //top表示栈定，初始化为-1
    //构造器
    public ArrayStack1(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    //增加一个方法，可以返回当前栈顶的值，但是不是pop
    public int peek(){
        return  stack[top];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈-push
    public void push(int value){
        //判断栈是否满
        if(isFull()){
            System.out.println("栈满！");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈-pop
    public int pop(){
        //判断栈是否空
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈的情况[遍历栈],需要从栈顶开始显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，没有数据！");
            return;
        }
        for(int i = top; i >= 0; i--){
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
    //返回运算符的优先级，优先级是程序员自己来确定的，优先级用数字表示
    //数字越大优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        } else if(oper == '+' || oper == '-'){
            return 0;
        } else{
            return -1;  //假定目前的表达式只有 + - * / 这四种
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    //计算方法
    public int cal(int num1, int num2, int oper){
        int res = 0;    //res 用于存放计算的结果
        switch (oper){
            //需要注意，后面弹出来的数，为被操作的数，而先弹出来的数为后面添加进去的数。
            //因为栈是后进先出
            //num1应为先弹出来的 num2应为后面弹出来的
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
