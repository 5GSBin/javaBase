package com.atguigu.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode heroNode1 = new HeroNode(1,"�ν�","��ʱ��");
        HeroNode heroNode2 = new HeroNode(2,"¬����","������");
        HeroNode heroNode3 = new HeroNode(3,"����","�Ƕ���");
        HeroNode heroNode4 = new HeroNode(4,"�ֳ�","����ͷ");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.list();
        System.out.println();

//        //���Ե���������Ч�ڵ�ĸ���
//        System.out.println("��Ч�Ľڵ����"+getLength(singleLinkedList.getHead()));
//
//        //���Ե�����k���ڵ�
//        System.out.println("�����ڵ�=" + findLastIndexNode(singleLinkedList.getHead(),2));
//
        //��������ת
        reverseList(singleLinkedList.getHead());
//        reverseList(singleLinkedList);
        singleLinkedList.list();

//        System.out.println("�����ӡ");
//        reversePrint(singleLinkedList.getHead());


    }

    //ʹ��ջ��ʵ�������ӡ������
    public static void reversePrint(HeroNode head){
        if(head.next==null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //�������нڵ����ջ��
        while(cur!=null){
            stack.add(cur);
            cur = cur.next;
        }
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }


    //��������ת
    public static void reverseList(HeroNode head){
        if(head.next== null || head.next.next == null){
            return;
        }
        //����һ�������ڵ�
        HeroNode cur = head.next;
        HeroNode next = null;//ָ��ǰ�ڵ����һ���ڵ�
        HeroNode reserverHead = new HeroNode();
        //������������
        while(cur!=null){
            next = cur.next;
            cur.next = reserverHead.next;
            reserverHead.next = cur;
            cur = next;
        }

        head.next = reserverHead.next;
        return;

    }

    //���ҵ������еĵ�����k���ڵ�
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //�������Ϊ��
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

    //��������ȡ��������Ľڵ���Ч�����������ͷ�ڵ����������ͳ��ͷ�ڵ㣩

    /**
     *
     * @param head �����ͷ�ڵ�
     * @return ���صľ�����Ч�ڵ�ĸ���
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


//����SingleLinkedList �������ǵ�Ӣ��
class SingleLinkedList{

    //�ȳ�ʼ��һ��ͷ�ڵ�,ͷ�ڵ㲻Ҫ��,�Ҳ�����κ�����
    private HeroNode head = new HeroNode();

    public HeroNode getHead() {
        return head;
    }

    //��ӽڵ㵽�����б���
    public void add(HeroNode heroNode){
        //1.�ҵ���ǰ��������ڵ�,ͷ�ڵ㲻�ܶ���������Ҫһ����������temp
        HeroNode temp = head;
        //���������
        while(true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        //�˳�ѭ�������tempָ�������һ���ڵ�

        //2.�����Ľڵ��next��ָ���µĽڵ�
        temp.next = heroNode;
    }

    //�޸Ľڵ���Ϣ������no������޸�
    public void update(HeroNode newHeroNode){
        //�ж��Ƿ�Ϊ��
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

    //ɾ���ڵ�
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

    //��ʾ����
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

    //�ڶ��� ����������˳����ӣ����Ѵ��ڣ�����ӣ�����ʾ
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;//��־����ı���Ƿ����
        while (true){
            if(temp.next == null){//˵��temp�Ѿ�����������
                break;
            }
            if(temp.next.no > heroNode.no){//��ʱλ��ȷ��
                break;
            }else if(temp.next.no == heroNode.no){
                flag = true;//˵����ų���
                break;
            }
            temp = temp.next;
        }

        //Ҫ�ж��Ƿ����
        if(flag){
            System.out.printf("the %d is existed!!",heroNode.no);
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }


}

//����HeroNode��ÿһ��HeroNode�������һ���ڵ�
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//ָ����һ���ڵ�

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
