package mycollections;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T>{

    private int size = 0;
    private Object[] array;
    private final int DEFAULT_CAPACITY = 10;

    public MyArrayList(){
        this.array = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity){
        this.array = new Object[capacity];
    }


    @Override
    public void add(T value) {
        if(size == 0){
            array[size] = value;
            size++;
        }
        else{
            if(size() > array.length - 1){
                array = Arrays.copyOf(array, array.length * 2);
            }
            array[size] = value;
            size++;

        }
    }

    @Override
    public void remove(int index) {
        if(index > array.length) throw new IndexOutOfBoundsException("Index out of bounds");
        array[index] = null;
        size --;
    }

    @Override
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if(index > array.length) throw new IndexOutOfBoundsException("Out of bounds");
        return (T)(array[index]);
    }
}
