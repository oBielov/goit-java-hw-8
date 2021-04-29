package mycollections;

import java.util.Arrays;

public class MyStack<T> implements MyQueueInt<T>{

    //init
    private int size = 0;
    private Object[] stack;

    public MyStack(){
        this.stack = new Object[0];
    }

    public MyStack(Object[] stack){
        this.stack = stack;
    }

    //service
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
        return stack = Arrays.copyOf(stack, newCapacity);

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
    public void push(Object value){
        add(value, stack);
    }

    @Override
    public void remove(int index) {
        removeAndTrim(stack, index);
    }

    @Override
    public void clear() {
        stack = new Object[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        return (T)stack[stack.length - 1];
    }

    public T pop(){
        T elem = (T)stack[stack.length - 1];
        stack = Arrays.copyOfRange(stack, 0, stack.length - 1);
        size--;
        return elem;
    }
}
