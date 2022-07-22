package com.atguigu.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode heroNode1 = new HeroNode(1,"宋江","及时雨");
        HeroNode heroNode2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode heroNode3 = new HeroNode(3,"吴用","智多星");
        HeroNode heroNode4 = new HeroNode(4,"林冲","豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.list();
        System.out.println();

//        //测试单链表中有效节点的个数
//        System.out.println("有效的节点个数"+getLength(singleLinkedList.getHead()));
//
//        //测试倒数第k个节点
//        System.out.println("倒数节点=" + findLastIndexNode(singleLinkedList.getHead(),2));
//
        //测试链表反转
        reverseList(singleLinkedList.getHead());
//        reverseList(singleLinkedList);
        singleLinkedList.list();

//        System.out.println("逆序打印");
//        reversePrint(singleLinkedList.getHead());


    }

    //使用栈，实现逆序打印单链表
    public static void reversePrint(HeroNode head){
        if(head.next==null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表中节点放入栈中
        while(cur!=null){
            stack.add(cur);
            cur = cur.next;
        }
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }


    //将单链表反转
    public static void reverseList(HeroNode head){
        if(head.next== null || head.next.next == null){
            return;
        }
        //定义一个辅助节点
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点的下一个节点
        HeroNode reserverHead = new HeroNode();
        //遍历，并工作
        while(cur!=null){
            next = cur.next;
            cur.next = reserverHead.next;
            reserverHead.next = cur;
            cur = next;
        }

        head.next = reserverHead.next;
        return;

    }

    //查找单链表中的倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //如果链表为空
        if(head.next==null){
            return null;
        }
        int length = getLength(head);
        if(length < index && index <=0){
            return null;
        }
        HeroNode cur = head.next;
        for(int i=0; i< length - index;i++){
            cur = cur.next;
        }
        return cur;

    }

    //方法：获取到单链表的节点有效个数（如果带头节点的链表，需求不统计头节点）

    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if(head.next==null){
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while(cur!=null){
            length += 1;
            cur = cur.next;
        }
        return length;
    }



}


//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList{

    //先初始化一个头节点,头节点不要动,且不存放任何数据
    private HeroNode head = new HeroNode();

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单项列表中
    public void add(HeroNode heroNode){
        //1.找到当前链表的最后节点,头节点不能动，所以需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历到最后
        while(true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        //退出循环则表明temp指向了最后一个节点

        //2.将最后的节点的next域指向新的节点
        temp.next = heroNode;
    }

    //修改节点信息，根据no编号来修改
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("it's empty!!");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while(true){
            if(temp==null){
                break;
            }
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }else if(temp.no < newHeroNode.no){
                temp = temp.next;
            }else {
                break;
            }
        }
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            System.out.println("it's no this one!!!");
        }
    }

    //删除节点
    public void delete(int no){
        if(head.next == null){
            System.out.println("it's empty");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
            if(temp.next == null){
                break;
            }
        }
        if(flag) {
            temp.next = temp.next.next;
        }else {
            System.out.println("it's no this one!!");
        }
    }

    //显示链表
    public void list(){
        if(head.next==null){
            System.out.println("this linkedList is empty!!");
            return;
        }

        HeroNode temp = head.next;
        while (true){
            System.out.println(temp);
            temp = temp.next;
            if(temp == null){
                break;
            }
        }

    }

    //第二种 ，根据排名顺序添加，若已存在，则不添加，并提示
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;//标志加入的编号是否存在
        while (true){
            if(temp.next == null){//说明temp已经在链表的最后
                break;
            }
            if(temp.next.no > heroNode.no){//此时位置确定
                break;
            }else if(temp.next.no == heroNode.no){
                flag = true;//说明编号成立
                break;
            }
            temp = temp.next;
        }

        //要判断是否存在
        if(flag){
            System.out.printf("the %d is existed!!",heroNode.no);
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }


}

//定义HeroNode，每一个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    public HeroNode(int hNo,String hName,String hNickName){
        this.no = hNo;
        this.name = hName;
        this.nickName = hNickName;
        this.next = null;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public HeroNode() {
    }
}
