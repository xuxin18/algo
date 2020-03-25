package c6_linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuxin
 * @date 2020/1/10 15:49
 * @description
 * 单链表的插入、删除、查找操作
 * 链表中存储的是 int 类型的数据
 */
public class SinglyLinkedList {
    private Node head = null;

    // 根据 value 来查找 链表中的结点
    public Node findByValue(int value){
        Node p = head;
        while (p != null && p.data != value){
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index){
        Node p = head;
        int pos = 0;
        while (p != null && pos != index){
            p = p.next;
            System.out.println(pos);
            pos++;
            System.out.println(pos);
        }
        return p;
    }

    public void insertToHead(Node newNode){
        if (head == null){
            head = newNode;
        }else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertToHead(int value){
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    public void insertTail(int value){
        Node newNode = new Node(value, null);
        if (head == null){
            head = newNode;
        }else {
            Node q = head;
            while (q.next != null){
                q = q.next;
            }
            q.next = newNode;
        }
    }

    public void insertAfter(Node p, Node newNode){
        if (p == null){
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertAfter(Node p, int value){
        Node newNode = new Node(value, null);
        insertAfter(p,newNode);
    }

    public void insertBefore(Node p, Node newNode){
        if (p == null){
            return;
        }
        if (head == p){
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while (q != null && q.next != p){
            q = q.next;
        }
        if (q == null){
            return;
        }
        newNode.next = p;
        q.next = newNode;
    }

    public void insertBefore(Node p, int value){
        Node newNode = new Node(value, null);
        insertBefore(p, newNode);
    }

    public void deleteByNode(Node p){
        if (p == null || head == null){
            return;
        }
        if (p == head){
            head = head.next;
            return;
        }

        Node q = head;
        while (q != null && q.next != p){
            q = q.next;
        }
        if (q == null){
            return;
        }
        q.next = q.next.next;
    }

    public void deleteByValue(int value){
        if (head == null){
            return;
        }
        Node p = head;
        //记录目标Node的前一个Node
        Node q = null;
        while (p != null && p.data != value){
            q = p;
            p = p.next;
        }
        if (p == null){
            return;
        }
        if (q == null){
            // 如果目标Node刚好为 head
            head = head.next;
        }else {
            q.next = q.next.next;
        }
    }
    /*
     * 使用 快慢指针 来实现判断单链表是否为回文链表
     *      方式一：用数组存储链表的前半段的值
     */

    public boolean isPalindromeByArray(Node p){
        if (p == null){
            return false;
        }
        Node fast = p;
        Node slow = p;
        if (p.next == null){
            return true;
        }

        /*
            找到中间结点，同事用数组逆序插左侧元素并保存
            nodeList.add(0, slow.data); 在指定位置插入元素，原位置及之后的依次向右移动一位。
        */
        List<Integer> nodeList = new ArrayList<Integer>();
        nodeList.add(0,slow.data);
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            nodeList.add(0,slow.data);
        }

        // 针对 链表中的元素个数为偶数的情况
        if (fast.next != null){
            slow = slow.next;
        }

        Node cur = slow;
        int i = 0;
        while (cur != null){
            if (cur.data != nodeList.get(i)){
                return false;
            }
            cur = cur.next;
            i++;
        }
        return true;
    }

    public static class Node{
        private int data;

        private Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

        public int getData(){
            return data;
        }
    }
}
