package com.atguigu.stack;

public class StackDemo {
    public static void main(String[] args) {

    }
}

//����Stack��һ��Stack�������һ��ջ
class Stack{

    private StackNode top = new StackNode();   //����topΪջ����




}

//����StackNode��һ��StackNode�������һ���ڵ�
class StackNode{
    public int val;
    public StackNode next;

    public StackNode(int val){
        this.val = val;
    }
    public StackNode(){

    }

}
