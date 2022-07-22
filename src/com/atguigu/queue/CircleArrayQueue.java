package com.atguigu.queue;

import java.util.Scanner;

public class CircleArrayQueue {

    public static void main(String[] args) {
        //����һ������
        CircleArray queue = new CircleArray(4);
        char key = ' ';   //�����û�����
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s:show");
            System.out.println("e:exit");
            System.out.println("a:add");
            System.out.println("g:get");
            System.out.println("h:head");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("input��");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("get data:%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.printf("head data��%d\n", head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }

        }
        System.out.println("exit....");
    }

}

//ʹ������ģ������-��дһ��ArrayQueue��
class CircleArray{
    private int maxSize;//��ʾ������������
    private int front;//����ͷ
    private int rear;//����β
    private int[] arr;//���������ڴ�����ݣ�ģ�����

    //�������й�����
    public CircleArray(int arrMaxSiza){
        maxSize = arrMaxSiza;
        arr = new int[maxSize];
        front = 0;//ָ�����ͷ����������front��ָ�����ͷ��ǰһ��λ��
        rear = 0;//ָ�����β����ָ�����β�����ݣ����Ƕ��е����һ�����ݣ�

    }

    //�ж϶����Ƿ�Ϊ��
    public boolean isFull(){
        return (rear+1) % maxSize == front;
    }

    //�ж϶����Ƿ�Ϊ��
    public boolean isEmpty(){
        return rear == front;
    }

    //������ݵ�����
    public void addQueue(int n){
        //�ж϶����Ƿ���
        if(isFull()){
            System.out.println("isfull,can't add");
            return;
        }
        //ֱ�ӽ����ݼ���
        arr[rear] = n;
        //��rear���ƣ�������뿼��ȡģ
        rear = (rear+1) % maxSize;

    }

    //��ȡ�������ݣ�������
    public int getQueue(){
        //�ж϶����Ƿ�Ϊ��
        if(isEmpty()){
            //ͨ���׳��쳣
            throw new RuntimeException("isempty,can't get data");
        }
        //������Ҫ������front��ָ����еĵ�һ��Ԫ��
        //�Ȱ�front��Ӧ��ֵ���浽��ʱ�ı���
        //��front����
        //����ʱ����ı�������
        int value = arr[front];
        front = (front+1)%maxSize;

        return value;

    }

    //��ʾ������������
    public void showQueue(){
        //����
        if(isEmpty()){
            System.out.println("is empty,can't data");
        }
        for(int i = front; i < front+size(); i++){
            System.out.printf("arr[%d] = %d\n", i%maxSize, arr[i%maxSize]);
        }
    }

    //��ʾ���е�ͷ���ݣ�ע�ⲻ��ȡ������
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("is empty,can't data");
        }
        return arr[front];
    }

    //�����ǰ���е���Ч����
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

}
