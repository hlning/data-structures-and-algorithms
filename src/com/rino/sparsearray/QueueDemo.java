package com.rino.sparsearray;

/**
 * @author Huilong.Ning
 * @description 数组实现队列
 * @date 2021/6/1 21:50
 */

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * @author Huilong.Ning
 * @description 数组实现普通队列
 * @date 2021/6/1 21:54
 */
class NormalQueue {
    //队头
    int front;
    //队尾
    int rear;
    //最大容量
    int maxSize;
    //数组
    Object[] objects;

    public NormalQueue(int maxSize) {
        front = -1;
        rear = -1;
        this.maxSize = maxSize;
        objects = new Object[maxSize];
    }

    public boolean isFull() {
        return rear + 1 == maxSize;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 添加数据到队列
     *
     * @param data
     */
    public void add(Object data) {
        if (isFull()) {
            System.out.println("队列已满，不能添加");
            return;
        }
        objects[++rear] = data;
    }


    /**
     * 出队列
     *
     * @return
     */
    public Object get() throws Exception {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取");
        }
        return objects[++front];
    }

    /**
     * 打印队列
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空，无法打印");
            return;
        }
        System.out.println("队列数据为：");
        for (int i = 0; i < objects.length; i++) {
            System.out.print(objects[i] + "\t");
        }
        System.out.println();
    }

    public Object head() throws Exception {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取");
        }
        return objects[front + 1];
    }
}

class AnnulusQueue {
    //队头
    int front;
    //队尾
    int rear;
    //最大容量
    int maxSize;
    //数组
    Object[] objects;

    public AnnulusQueue(int maxSize) {
        front = 0;
        rear = 0;
        this.maxSize = maxSize;
        objects = new Object[maxSize + 1];
    }

    public boolean isFull() {
        //
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 添加数据到队列
     *
     * @param data
     */
    public void add(Object data) {
        if (isFull()) {
            System.out.println("队列已满，不能添加");
            return;
        }
        objects[rear] = data;
        rear = (rear + 1) % maxSize;
    }


    /**
     * 出队列
     *
     * @return
     */
    public Object get() throws Exception {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取");
        }
        Object o = objects[front];
        front = (front + 1) % maxSize;
        return o;
    }

    /**
     * 打印队列
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空，无法打印");
            return;
        }
        System.out.println("队列数据为：");
        for (int i = 0; i < objects.length; i++) {
            System.out.print(objects[i] + "\t");
        }
        System.out.println();
    }

    public Object head() throws Exception {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取");
        }
        return objects[front + 1];
    }
}

public class QueueDemo {
    public static void main(String[] args) {
        System.out.println("请输入：1.数组实现普通队列  2.数组实现环形队列");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("请输入容量大小：");
                int maxSize1 = scanner.nextInt();
                NormalQueue normalQueue = new NormalQueue(maxSize1);
                boolean flag1 = true;
                while (flag1) {
                    System.out.println("请输入操作：1.添加数据到队列  2.出队列 3.打印队列 4.显示队头数据 5.退出");
                    switch (scanner.nextInt()) {
                        case 1:
                            System.out.println("请输入数据：");
                            normalQueue.add(scanner.next());
                            break;
                        case 2:
                            try {
                                System.out.println(normalQueue.get() + "出队列");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3:
                            normalQueue.show();
                            break;
                        case 4:
                            try {
                                System.out.println("队头数据：" + normalQueue.head());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 5:
                            flag1 = false;
                            break;
                        default:
                            break;
                    }
                }
                break;
            case 2:
                System.out.println("请输入容量大小：");
                int maxSize2 = scanner.nextInt();
                AnnulusQueue annulusQueue = new AnnulusQueue(maxSize2);
                boolean flag2 = true;
                while (flag2) {
                    System.out.println("请输入操作：1.添加数据到队列  2.出队列 3.打印队列 4.显示队头数据 5.退出");
                    switch (scanner.nextInt()) {
                        case 1:
                            System.out.println("请输入数据：");
                            annulusQueue.add(scanner.next());
                            break;
                        case 2:
                            try {
                                System.out.println(annulusQueue.get() + "出队列");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3:
                            annulusQueue.show();
                            break;
                        case 4:
                            try {
                                System.out.println("队头数据：" + annulusQueue.head());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 5:
                            flag2 = false;
                            break;
                        default:
                            break;
                    }
                }
                break;
            default:
                break;
        }
        System.out.println("程序退出");
    }
}
