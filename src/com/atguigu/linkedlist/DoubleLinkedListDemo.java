package com.atguigu.linkedlist;

/**
 * 双向链表的实例：遍历、添加、修改、删除
 */

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");
        //创建节点
        HeroNode1 heroNode1 = new HeroNode1(1,"宋江","及时雨");
        HeroNode1 heroNode2 = new HeroNode1(2,"卢俊义","玉麒麟");
        HeroNode1 heroNode3 = new HeroNode1(3,"吴用","智多星");
        HeroNode1 heroNode4 = new HeroNode1(4,"林冲","豹子头");
        //创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);

        doubleLinkedList.list();
        System.out.println();

        //修改
        doubleLinkedList.update(new HeroNode1(4,"林冲","狮子头"));
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();
        System.out.println();

        //删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();

        //第二种添加
        System.out.println();
        System.out.println("第二种添加测试");
        HeroNode1 heroNode5 = new HeroNode1(1,"binge","wudi");
        HeroNode1 heroNode6 = new HeroNode1(6,"jiandi","youchou");
        doubleLinkedList.add1(heroNode6);
        doubleLinkedList.add1(heroNode5);
        doubleLinkedList.list();


    }

}

//创建一个双向链表类
class DoubleLinkedList{

    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode1 head = new HeroNode1(0,"","");

     //返回头节点
    public HeroNode1 getHead(){
        return head;
    }

    //遍历显示列表
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空!");
            return;
        }
        HeroNode1 temp = head.next;
        while(true){
            System.out.println(temp);
            temp = temp.next;
            if(temp==null){
                break;
            }
        }

    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode1 heroNode1){
        HeroNode1 temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //temp指向了最后
        //形成一个双向列表
        temp.next = heroNode1;
        heroNode1.pre = temp;
    }

    //修改一个节点的内容
    public void update(HeroNode1 heroNode1){
        if(head.next == null){
            System.out.println("链表为空！");
            return;
        }
        HeroNode1 temp = head.next;
        boolean flag = false;
        while(true){
            if(temp.id == heroNode1.id){
                flag = true;
                break;
            }
            temp = temp.next;
            if(temp == null){
                break;
            }
        }
        if(flag){
            temp.name = heroNode1.name;
            temp.nickName = heroNode1.nickName;
        }else{
            System.out.println("查无此id，无法修改");
        }
    }

    //删除一个节点的的内容
    public void del(int id){
        if(head.next == null){
            System.out.println("链表为空！");
            return;
        }
        HeroNode1 temp = head.next;
        boolean flag = false;
        while(true) {
            if (temp.id == id) {
                flag = true;
                break;
            }
            temp = temp.next;
            if(temp == null){
                break;
            }
        }
        if(flag){
            temp.pre.next = temp.next;
            if(temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.println("查无此id");
        }
    }

    //第二种添加：根据编号大小，若存在则不添加
    public void add1(HeroNode1 heroNode1){
        if(head.next == null){
            return;
        }
        HeroNode1 temp = head.next;
        boolean flag = false;
        while(true){
            if(temp.id == heroNode1.id){
                System.out.println("该英雄已经存在！无法添加");
                return;
            }
            if(temp.id > heroNode1.id){
                break;
            }
            if(temp.next == null){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = heroNode1;
            heroNode1.pre = temp;
        }else {
            temp.pre.next = heroNode1;
            heroNode1.pre = temp.pre;
            heroNode1.next = temp;
            temp.pre = heroNode1;
        }
    }
}

//定义HeroNode1，每一个HeroNode1对象就是一个节点
class HeroNode1{

    public int id;
    public String name;
    public String nickName;
    public HeroNode1 next;  //指向下一个节点
    public HeroNode1 pre;   //指向前一个节点

    public HeroNode1(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode1() {
    }

    @Override
    public String toString() {
        return "HeroNode1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
