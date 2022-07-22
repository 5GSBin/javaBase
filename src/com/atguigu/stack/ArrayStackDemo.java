package com.atguigu.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        //����һ��ArrayStack�Ƿ���ȷ
        //�ȴ���һ��ArrayStack����-->ջ
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;    //�����Ƿ��˳��˵�
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show����ʾ��ʾջ");
            System.out.println("exit���˳�����");
            System.out.println("push����ʾ������ݵ�ջ(��ջ)");
            System.out.println("pop����ʾ��ջȡ������(��ջ)");
            System.out.print("���������ѡ��");
            key = scanner.next();
            switch(key){
                case "show":
                    arrayStack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                case "push":
                    System.out.print("������Ҫ��ӵ����ݣ�");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.printf("�Ƴ�������Ϊ��%d", arrayStack.pop());
                        System.out.println();
                    }catch (Exception a){
                        System.out.println(a.getMessage());
                    }
                    break;
            }
        }
        System.out.println("�����˳���");

    }

}

//����һ��ArrayStack��ʾջ
class ArrayStack{
    private int maxSize;    //ջ�Ĵ�С
    private int[] stack;    //���飬����ģ��ջ�����ݾͷ��ڸ�������
    private int top = -1;   //top��ʾջ������ʼ��Ϊ-1

    //������
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
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
}
