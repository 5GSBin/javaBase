package com.atguigu.sparsearray;

public class SparsrArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11 * 11
        //0表示空，1表示黑，2表示蓝
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        //输出原始的二维数组
        for(int[] row:chessArr1){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println("");
        }
        //将二维数组转稀疏数组的思想：
        //1.先遍历二维数组 得到非零数据的个数
        int sum = 0;
        for(int i=0; i<11; i++){
            for (int j=0; j<11;j++){
                if(chessArr1[i][j]!=0){
                    sum+=1;
                }
            }
        }

//        System.out.println("sum="+sum);

        //创建对应的稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        int count = 0;
        for(int i=0; i<11; i++){
            for (int j=0; j<11;j++){
                if(chessArr1[i][j]!=0){
                    count+=1;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        System.out.println("");

        //输出稀疏数组的形式
        for(int[] row:sparseArr){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println("");
        }

        System.out.println("");

        //将稀疏数组恢复成二维数组

        //读取稀疏数组第一行创建二维数组
        int[][] chessArr2 =new int[sparseArr[0][0]][sparseArr[0][1]];

        //打印初始数组
        for(int[] row:chessArr2){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println("");
        }

        System.out.println("");

        //再读后几行数据，并赋值
        for(int i = 1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for(int[] row:chessArr2){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println("");
        }

    }

}
