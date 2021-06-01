package com.rino.sparsearray;

/**
 * @author Administrator
 * @description 稀疏数组
 * <p>
 * 介绍：当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
 * <p>
 * 使用场景：五子棋
 * <p>
 * 二维数组转稀疏数组思路
 * 1.遍历原始的二维数组，得到有效数据的个数sum
 * 2.根据sum就可以创建稀疏数组sparseArr int[sum+1][3]
 * 3.将二维数组的小树数据存入到稀疏数组
 * <p>
 * 稀疏数组转二维数组
 * 1.先读取稀疏数组的第一行，创建原始的二维数组
 * 2.读取后面的行，赋值给二维数组
 * @date 2021/6/1 17:16
 */
class SparseArray {
    private Object[][] objectArr;

    public SparseArray(int x, int y) {
        objectArr = new Object[x][y];
    }

    /**
     * 二维数组赋值
     *
     * @param x
     * @param y
     * @param value
     */
    public void setValue(int x, int y, Object value) {
        try {
            objectArr[x][y] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            //直接打印，不做处理了
            System.out.println("数组越界");
        }
    }

    /**
     * 二维数组转稀疏数组
     *
     * @return
     */
    public Object[][] convertSparseArray() {
        if (objectArr == null) return null;
        //从二维数组中获取有效数据个数
        int sum = 0;
        for (int i = 0; i < objectArr.length; i++) {
            for (int j = 0; j < objectArr[i].length; j++) {
                if (objectArr[i][j] != null) sum++;
            }
        }
        //创建稀疏数组
        Object[][] spareseArr = new Object[sum + 1][3];
        //第一行写入二维数组有多少行，多少列，多少个有效数据
        spareseArr[0][0] = objectArr.length;
        spareseArr[0][1] = objectArr[0].length;
        spareseArr[0][2] = sum;
        //第二行开始写入有效数据的行列值
        int clo = 1;
        for (int i = 0; i < objectArr.length; i++) {
            for (int j = 0; j < objectArr[i].length; j++) {
                if (objectArr[i][j] == null) continue;
                spareseArr[clo][0] = i;
                spareseArr[clo][1] = j;
                spareseArr[clo][2] = objectArr[i][j];
                clo++;
            }
        }
        return spareseArr;
    }

    /**
     * 稀疏数组转换为二维数组
     *
     * @param spareseArr
     * @return
     */
    public Object[][] convertObjectArray(Object[][] spareseArr) {
        //获取第一行
        int x = (int) spareseArr[0][0];
        int y = (int) spareseArr[0][1];
        //创建二维数组
        Object[][] objectArr = new Object[x][y];
        //读取稀疏数组，将数据写入到二维数组
        for (int i = 1; i < spareseArr.length; i++) {
            objectArr[(int) spareseArr[i][0]][(int) spareseArr[i][1]] = spareseArr[i][2];
        }
        return objectArr;
    }
}

public class SparseAarrayDemo {
    public static void main(String[] args) {
        SparseArray objectArr = new SparseArray(10, 10);
        objectArr.setValue(0, 0, 10);
        objectArr.setValue(0, 1, 5);
        objectArr.setValue(1, 3, 23);
        objectArr.setValue(4, 5, 13);
        objectArr.setValue(6, 6, 12);
        objectArr.setValue(1, 7, 62);
        objectArr.setValue(3, 3, 77);

        Object[][] sparseArr = objectArr.convertSparseArray();
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                System.out.print(sparseArr[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("====================================");
        Object[][] newObjectArr = objectArr.convertObjectArray(sparseArr);
        for (int i = 0; i < newObjectArr.length; i++) {
            for (int j = 0; j < newObjectArr[i].length; j++) {
                System.out.print(newObjectArr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
