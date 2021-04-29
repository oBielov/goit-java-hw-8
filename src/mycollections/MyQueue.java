package mycollections;

import java.util.Arrays;

public class MyQueue<T> implements MyQueueInt<T>{

    //init
    private int size = 0;
    private Object[] queue;


    public MyQueue(){
        this.queue = new Object[0];
    }
    public MyQueue(Object[] queue){
        this.queue = queue;
    }


    //services
    private void removeAndTrim(Object[] array, int index){
        if(index == 0){
            array = Arrays.copyOfRange(array, 1, array.length);
            return;
        }
        Object[] a1 = Arrays.copyOfRange(array, 0, index);
        Object[] a2 = Arrays.copyOfRange(array, index + 1, array.length);
        array = Arrays.copyOf(a1, a1.length + a2.length);
        System.arraycopy(a2, 0, array, a1.length, a2.length);
    }

    private Object[] scale(int newCapacity) {
        return queue = Arrays.copyOf(queue, newCapacity);

    }

    private Object[] scale() {
        return scale(size + 1);
    }

    private void add(Object value, Object[] array) {
        if (size == array.length){
            array = scale();
        }
        array[size] = value;
        size++;
    }


    //business
    @Override
    public void remove(int index) {
        if(index > size) throw new IndexOutOfBoundsException("Illegal index");
        removeAndTrim(queue, index);
        size--;
    }

    @Override
    public void clear() {
        queue = new Object[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        return (T)queue[0];
    }

    public T poll() {
        T elem = (T)queue[0];
        queue = Arrays.copyOfRange(queue, 1, queue.length);
        size--;
        return elem;
    }

    public void add(Object value) {
        add(value, queue);
    }

}
