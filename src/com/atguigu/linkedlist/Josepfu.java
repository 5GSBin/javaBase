package com.atguigu.linkedlist;

public class Josepfu {

    public static void main(String[] args) {
        //���Թ����ͱ�����������
        CircleSingleLinkedList circleSingleLinkedList= new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);
        circleSingleLinkedList.showBoy();

        //����С����Ȧ
        circleSingleLinkedList.countBoy(10,20,125);
    }

}

//����һ�����εĵ�������
class CircleSingleLinkedList{

    //����һ��first�ڵ㣬��ǰû�б��
    private Boy first = null;
    //���С���ڵ㣬������һ����������
    public void addBoy(int nums){
        //�ж�ֵ�Ƿ���Ч
        if (nums < 1){
            System.out.println("nums��ֵ����ȷ!");
            return;
        }
        //����һ������ָ�룬����������������
        Boy curBoy = null;
        //��forѭ����������������--������ӽڵ�
        for (int i = 1; i <= nums; i++){
            //���ݱ�Ŵ���С���ڵ�
            Boy boy = new Boy(i);
            //����ǵ�һ��С��
            if (i == 1){
                first = boy;
                first.setNext(first);      //���ɻ�
                curBoy = first;     //��curBoyָ���һ��С��
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //������ǰ�Ļ�������
    public void showBoy(){
        //�ж������Ƿ�Ϊ��
        if(first == null){
            System.out.println("û���κ�С��~~");
            return;
        }
        //��Ϊfirst���ܶ������������Ȼʹ��һ������ָ����ɱ���
        Boy curBoy = first;
        while(true){
            System.out.printf("С���ı�� %d \n",curBoy.getId());
            if (curBoy.getNext() == first){ //˵���Ѿ��������
                break;
            }
            curBoy = curBoy.getNext();  //��curBoy������һλ
        }
    }

    //�����û������룬����С����Ȧ��˳��

    /**
     *
     * @param startId   ��ʾ��ʼ�ı��
     * @param countNum  ��ʾ��Ȧ�������Ĵ���
     * @param nums  һ���ж��ٸ�С��
     */
    public void countBoy(int startId, int countNum, int nums){
        //����У��
        if (first == null || startId < 1 || startId > nums || countNum <1){
            System.out.println("����������������������");
            return;
        }
        //����һ������ָ�룬�������С����Ȧ
        Boy helper = first;
        //��ָ��ָ����������һ���ڵ�
        while (true){
            if(helper.getNext() == first){  //˵��helperָ�������С���ڵ�
                break;
            }
            helper = helper.getNext();
        }
        //С������ǰ����Ҫ��first��helper�ƶ�startId-1��
        for (int i = 0; i < startId -1; i++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //С��������first��helperָ���ƶ�countNum-1�Σ���ɳ�Ȧ
        //ѭ����Ȧ��ֱ��ֻʣ���һ���ڵ�
        while(true){
            if (first == helper){   //˵��Ȧ��ֻ��һ���ڵ�
                break;
            }
            //���ƶ������Ȧ
            for (int i = 0; i < countNum - 1; i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //ѭ����first����Ҫ��Ȧ��С��
            System.out.printf("С��%d��Ȧ\n",first.getId());
            //�ڵ��Ȧ
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("�������Ȧ�е�С�����%d\n",first.getId());
    }

}

//����һ��Boy�࣬��ʾһ���ڵ�
class Boy{
    private int id; //���
    private Boy next;   //ָ����һ���ڵ㣬Ĭ��Ϊnull

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


