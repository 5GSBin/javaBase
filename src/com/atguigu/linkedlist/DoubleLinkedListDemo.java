package com.atguigu.linkedlist;

/**
 * ˫�������ʵ������������ӡ��޸ġ�ɾ��
 */

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //����
        System.out.println("˫������Ĳ���");
        //�����ڵ�
        HeroNode1 heroNode1 = new HeroNode1(1,"�ν�","��ʱ��");
        HeroNode1 heroNode2 = new HeroNode1(2,"¬����","������");
        HeroNode1 heroNode3 = new HeroNode1(3,"����","�Ƕ���");
        HeroNode1 heroNode4 = new HeroNode1(4,"�ֳ�","����ͷ");
        //����һ��˫���������
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);

        doubleLinkedList.list();
        System.out.println();

        //�޸�
        doubleLinkedList.update(new HeroNode1(4,"�ֳ�","ʨ��ͷ"));
        System.out.println("�޸ĺ���������");
        doubleLinkedList.list();
        System.out.println();

        //ɾ��
        doubleLinkedList.del(3);
        System.out.println("ɾ������������");
        doubleLinkedList.list();

        //�ڶ������
        System.out.println();
        System.out.println("�ڶ�����Ӳ���");
        HeroNode1 heroNode5 = new HeroNode1(1,"binge","wudi");
        HeroNode1 heroNode6 = new HeroNode1(6,"jiandi","youchou");
        doubleLinkedList.add1(heroNode6);
        doubleLinkedList.add1(heroNode5);
        doubleLinkedList.list();


    }

}

//����һ��˫��������
class DoubleLinkedList{

    //�ȳ�ʼ��һ��ͷ�ڵ㣬ͷ�ڵ㲻Ҫ��������ž��������
    private HeroNode1 head = new HeroNode1(0,"","");

     //����ͷ�ڵ�
    public HeroNode1 getHead(){
        return head;
    }

    //������ʾ�б�
    public void list(){
        //�ж������Ƿ�Ϊ��
        if(head.next==null){
            System.out.println("����Ϊ��!");
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

    //���һ���ڵ㵽˫����������
    public void add(HeroNode1 heroNode1){
        HeroNode1 temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //tempָ�������
        //�γ�һ��˫���б�
        temp.next = heroNode1;
        heroNode1.pre = temp;
    }

    //�޸�һ���ڵ������
    public void update(HeroNode1 heroNode1){
        if(head.next == null){
            System.out.println("����Ϊ�գ�");
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
            System.out.println("���޴�id���޷��޸�");
        }
    }

    //ɾ��һ���ڵ�ĵ�����
    public void del(int id){
        if(head.next == null){
            System.out.println("����Ϊ�գ�");
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
            System.out.println("���޴�id");
        }
    }

    //�ڶ�����ӣ����ݱ�Ŵ�С�������������
    public void add1(HeroNode1 heroNode1){
        if(head.next == null){
            return;
        }
        HeroNode1 temp = head.next;
        boolean flag = false;
        while(true){
            if(temp.id == heroNode1.id){
                System.out.println("��Ӣ���Ѿ����ڣ��޷����");
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

//����HeroNode1��ÿһ��HeroNode1�������һ���ڵ�
class HeroNode1{

    public int id;
    public String name;
    public String nickName;
    public HeroNode1 next;  //ָ����һ���ڵ�
    public HeroNode1 pre;   //ָ��ǰһ���ڵ�

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
