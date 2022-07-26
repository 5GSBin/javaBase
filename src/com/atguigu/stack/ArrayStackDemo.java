package com.atguigu.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        //测试一下ArrayStack是否正确
        //先创建一个ArrayStack对象-->栈
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;    //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show：表示显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：表示添加数据到栈(入栈)");
            System.out.println("pop：表示从栈取出数据(出栈)");
            System.out.print("请输入你的选择：");
            key = scanner.next();
            switch(key){
                case "show":
                    arrayStack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                case "push":
                    System.out.print("请输入要添加的数据：");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.printf("移除的数据为：%d", arrayStack.pop());
                        System.out.println();
                    }catch (Exception a){
                        System.out.println(a.getMessage());
                    }
                    break;
            }
        }
        System.out.println("程序退出！");

    }

}

//定义一个ArrayStack表示栈
class ArrayStack{
    private int maxSize;    //栈的大小
    private int[] stack;    //数组，数组模拟栈，数据就放在该数组中
    private int top = -1;   //top表示栈定，初始化为-1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
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
}
