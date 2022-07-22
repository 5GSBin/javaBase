package com.atguigu.queue;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';   //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("s:show");
            System.out.println("e:exit");
            System.out.println("a:add");
            System.out.println("g:get");
            System.out.println("h:head");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("input：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("get data:%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.printf("head data：%d\n",head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }

        }
        System.out.println("exit....");
    }

}

//使用数组模拟数列-编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列构造器
    public ArrayQueue(int arrMaxSiza){
        maxSize = arrMaxSiza;
        arr = new int[maxSize];
        front = -1;//指向队列头部，分析出front是指向队列头的前一个位置
        rear = -1;//指向队列尾部，指向队列尾的数据（就是队列的最后一个数据）

    }

    //判断队列是否为满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("isfull,can't add");
            return;
        }

        rear++;
        arr[rear] = n;

    }

    //获取队列数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("isempty,can't get data");
        }

        front++;//front后移，让其指向第一个数据
        return arr[front];
    }

    //显示队列所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("is empty,can't data");
        }
        for(int i = 0; i < arr.length; i++){
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("is empty,can't data");
        }
        return arr[front+1];
    }

}
