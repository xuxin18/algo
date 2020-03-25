package c5_array;

/**
 * @author xuxin
 * @date 2020/1/8 17:37
 * @description
 */
public class GenericArray<T> {
    private T[] data;
    //数组中元素的个数
    private int size;

    // 根据传入容量，构造数组
    public GenericArray(int capacity){
        data = (T[]) new Object[capacity];
        size = 0;
    }

    // 无参构造函数，默认容量为 10
    public GenericArray(){
        this(10);
    }

    // 获取数组容量
    public int getCapacity(){
        return data.length;
    }

    // 获取当前元素个数
    public int count(){
        return size;
    }

    // 判断数组是否为 空
    public boolean isEmpty(){
        return size == 0;
    }

    // 修改 index 位置的元素
    public void set(int index, T e){
        checkIndex(index);
        data[index] = e;
    }

    // 获取对应 index 位置的元素
    public T get(int index){
        checkIndex(index);
        return data[index];
    }

    // 查看数组是否包含元素e
    public boolean contains(T e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    // 获取对应元素的下标, 未找到，返回 -1
    public int find(T e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    // 在 index 位置，插入元素e, 时间复杂度 O(m+n)
    public void add(int index, T e){
        checkIndex(index);
        // 如果当前元素的个数等于数组容量，则将数组扩容为原来的2倍
        if (size == data.length){
            resize(2*data.length);
        }

        for (int i = size-1; i >= index; i--) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    // 向数组头部插入元素
    public void addFirst(T e){
        add(0,e);
    }

    // 向数组尾部插入元素
    public void addLast(T e){
        add(size,e);
    }

    // 删除 index 位置的元素并返回
    public T remove(int index){
        checkIndex(index);
        T ret = data[index];
        for (int i = index+1; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;

        // 缩容
        if (size == data.length/4 && data.length/2 != 0){
            resize(data.length/2);
        }

        return ret;
    }

    // 删除第一个元素
    public T removeFirst(){
        return remove(0);
    }

    // 删除末尾元素
    public T removeLast(){
        return remove(size-1);

    }
    // 从数组中删除指定元素
    public void removeElement(T e){
        int index = find(e);
        if (index != -1){
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array size(元素个数) = %d, capacity（容量） = %d \n", size, data.length));
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size-1){
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    //扩容方法，时间复杂度为 O(n)
    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private void checkIndex(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("操作失败，index必须为在 0（包含0）-size 之间");
        }
    }
}
