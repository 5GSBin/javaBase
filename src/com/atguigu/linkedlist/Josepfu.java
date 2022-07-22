package com.atguigu.linkedlist;

public class Josepfu {

    public static void main(String[] args) {
        //测试构建和遍历环形链表
        CircleSingleLinkedList circleSingleLinkedList= new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);
        circleSingleLinkedList.showBoy();

        //测试小孩出圈
        circleSingleLinkedList.countBoy(10,20,125);
    }

}

//创建一个环形的单向链表
class CircleSingleLinkedList{

    //创建一个first节点，当前没有编号
    private Boy first = null;
    //添加小孩节点，构建成一个环形链表
    public void addBoy(int nums){
        //判断值是否有效
        if (nums < 1){
            System.out.println("nums的值不正确!");
            return;
        }
        //定义一个辅助指针，帮助构建环形链表
        Boy curBoy = null;
        //用for循环来创建环形链表--往里面加节点
        for (int i = 1; i <= nums; i++){
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1){
                first = boy;
                first.setNext(first);      //构成环
                curBoy = first;     //让curBoy指向第一个小孩
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy(){
        //判断链表是否为空
        if(first == null){
            System.out.println("没有任何小孩~~");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩的编号 %d \n",curBoy.getId());
            if (curBoy.getNext() == first){ //说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();  //让curBoy往后移一位
        }
    }

    //根据用户的输入，计算小孩出圈的顺序

    /**
     *
     * @param startId   表示开始的编号
     * @param countNum  表示出圈规则，数的次数
     * @param nums  一共有多少个小孩
     */
    public void countBoy(int startId, int countNum, int nums){
        //数据校验
        if (first == null || startId < 1 || startId > nums || countNum <1){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //将指针指向链表的最后一个节点
        while (true){
            if(helper.getNext() == first){  //说明helper指向了最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，需要将first和helper移动startId-1次
        for (int i = 0; i < startId -1; i++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //小孩报数，first和helper指针移动countNum-1次，完成出圈
        //循环出圈，直到只剩最后一个节点
        while(true){
            if (first == helper){   //说明圈中只有一个节点
                break;
            }
            //先移动，后出圈
            for (int i = 0; i < countNum - 1; i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //循环后，first就是要出圈的小孩
            System.out.printf("小孩%d出圈\n",first.getId());
            //节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getId());
    }

}

//创建一个Boy类，表示一个节点
class Boy{
    private int id; //编号
    private Boy next;   //指向下一个节点，默认为null

    public Boy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}


