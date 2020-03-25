package c5_array;

/**
 * @author xuxin
 * @date 2020/1/8 14:54
 * @description
 *      数组的插入、删除、按照下标随机访问操作
 *      数组中的数据是 int 类型的
 */
public class Array {
    // 定义整型数据 data 保存数据
    private int data[];
    // 定义数组长度
    private int n;
    // 定义数组中实际的元素个数
    private int count;

    public Array(int capacity) {
        this.data = new int[capacity];
        this.n = capacity;
        // 刚开始一个数都没存，所以为0
        this.count = 0;
    }

    // 根据索引，查找数据中的元素并返回
    public int find(int index){
        if (index < 0 || index > count){
            return -1;
        }else {
            return data[index];
        }
    }

    // 插入元素
    public boolean insert(int index, int value){
        if (count == n){
            System.out.println("数组空间已满，没有可插入的位置");
            return false;
        }

        if (index < 0 || index > n){
            System.out.println("插入位置越界");
            return false;
        }

        for (int i = count; i > index ; --i) {
            data[i] = data[i-1];
        }
        data[index] = value;
        ++count;
        return true;
    }

    // 根据索引删除数组中的元素
    public boolean delete(int index){
        if (index < 0 || index >= count-1){
            return false;
        }

        for (int i = index+1; i<count; i++){
            data[i-1] = data[i];
        }
        --count;
        return true;
    }

    public void printAll(){
        for (int i = 0; i < count; i++) {
            System.out.print(data[i] + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array array = new Array(5);
        array.printAll();
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        array.printAll();
        System.out.println(array.count);
        array.insert(3, 11);
    }
}
