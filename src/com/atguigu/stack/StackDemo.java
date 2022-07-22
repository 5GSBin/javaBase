package com.atguigu.stack;

public class StackDemo {
    public static void main(String[] args) {

    }
}

//定义Stack，一个Stack对象就是一个栈
class Stack{

    private StackNode top = new StackNode();   //定义top为栈顶，




}

//定义StackNode，一个StackNode对象就是一个节点
class StackNode{
    public int val;
    public StackNode next;

    public StackNode(int val){
        this.val = val;
    }
    public StackNode(){

    }

}
