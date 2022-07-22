package com.atguigu.testdemo;

public class test1 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        System.out.println(head.val);
        System.out.println(head.next);
        head = head.next;
        System.out.println(head);
//        head.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//
//        ListNode last = head;
//        ListNode temp = head;
//        while(temp != null){
//            System.out.println(temp.val+"-->");
//            temp = temp.next;
//            if (temp.next !=null & temp.val == 2){
//                head = temp;
//                System.out.println(last.val);
//                break;
//            }
//        }


    }

}

class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


