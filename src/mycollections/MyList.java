package mycollections;

public interface MyList<T> {

    void add(T t);

    void remove(int index);

    void clear();

    int size();

    T get(int index);

}
