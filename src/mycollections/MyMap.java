package mycollections;

public interface MyMap<K,V> {

    void put(K key, V value);

    void remove(K key);

    void clear();

    int size();

    V get(K key);

    interface Pair<K, V> {

        boolean equals(Object o);

        int hashCode();

        K getKey();

        V getValue();
    }
}
