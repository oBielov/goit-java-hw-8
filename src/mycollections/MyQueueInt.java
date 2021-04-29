package mycollections;

public interface MyQueueInt<T> {

    void remove(int index);

    void clear();

    int size();

    T peek();

}
