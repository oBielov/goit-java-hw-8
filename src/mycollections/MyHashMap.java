package mycollections;

import java.util.Arrays;
import java.util.Objects;


public class MyHashMap<K,V> implements MyMap<K,V>{

    private int size;

    private Node<K, V>[] shuffle;
    private final int DEFAULT_CAPACITY = 16;

    public MyHashMap(){
        this.shuffle = (Node<K,V>[])new Node[DEFAULT_CAPACITY];
    }

    //Service methods

    private int genHash(Object key) {
        return (key == null) ? 0 : key.hashCode();
    }

    private int indexFor(int h, int length)
    {
        return h & (length - 1);
    }

    private Node<K,V>[] resize(){
        int newCap = shuffle.length*2;
        Node <K, V>[] newShuffle = Arrays.copyOf(shuffle, newCap);
        for (Node<K,V> node : newShuffle){
            int index = indexFor(node.hash, newShuffle.length);
            newShuffle[index] = node;
        }
        return newShuffle;
    }


    private boolean hasNext(Node<K, V> node){
        return node.next != null;
    }

    static class Node<K, V> implements MyMap.Pair<K, V>{

        final int hash;
        final K key;
        V value;
        MyHashMap.Node<K,V> next;

        Node(int hash, K key, V value, MyHashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey(){
            return key;
        }

        public final V getValue(){
            return value;
        }

        public final String toString(){
            return key + "=" + value;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (obj instanceof MyMap.Pair) {
                MyMap.Pair<?,?> e = (MyMap.Pair<?,?>)obj;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }


    //business
    @Override
    public void put(K key, V value) {
        int hash = genHash(key);
        int index = indexFor(hash, shuffle.length);
        while (index > shuffle.length){
            shuffle = resize();
        }
        if(shuffle[index] == null) {
            shuffle[index] = new Node<>(hash, key, value, null);
        }
        else if(shuffle[index] != null){
            Node<K, V> current = shuffle[index];
            Node<K,V> newNode = new Node<>(hash, key, value, null);
            if(current.key == newNode.key || current.key.equals(newNode.key)){
                if(hasNext(current)){
                    newNode.next = current.next;
                }
                shuffle[index] = newNode;
            }
            else{
                if(hasNext(shuffle[index])){
                    Node <K, V> c = shuffle[index];
                    Node <K, V> n = shuffle[index].next;
                    do{
                        if(n.key == newNode.key || n.key.equals(newNode.key)){
                            c.next = newNode;
                            newNode.next = n.next;
                            n = newNode;
                        }else if(n.next == null){
                            n.next = newNode;
                            break;
                        }
                        else{
                            c = n;
                            n = n.next;
                        }
                    }while(hasNext(n));

                }
            }
        }
        size++;
    }

    @Override
    public void remove(K key) {
        int hash = genHash(key);
        int index = indexFor(hash, shuffle.length);

        if (shuffle[index] != null) {
            if (hash == shuffle[index].hash || shuffle[index].key.equals(key)) {
                shuffle[index] = null;
            }
            else if (hasNext(shuffle[index])) {
                Node<K, V> c = shuffle[index];
                Node<K, V> n = shuffle[index].next;
                do {
                    if (hash == n.hash || n.key.equals(key)) {
                        c.next = n.next;
                        break;
                    } else if (n.next == null) {
                        System.out.println("No such key to remove");
                    } else {
                        c = n;
                        n = n.next;
                    }
                } while (hasNext(n));
            }
        }
        size --;
    }

    @Override
    public void clear() {
        shuffle = (Node<K,V>[])new Node[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int hash = genHash(key);
        int index = indexFor(hash, shuffle.length);
        if(shuffle[index] != null){
            if (hash == shuffle[index].hash || shuffle[index].key.equals(key)){
                return shuffle[index].value;
            }
            else if(hasNext(shuffle[index])){
                Node<K, V> n = shuffle[index].next;
                do{
                    if (hash == n.hash || n.key.equals(key)){
                        return n.value;
                    }
                    else if (n.next == null){
                        return null;
                    }
                    else{
                        n = n.next;
                    }
                }while(hasNext(n));
            }
        }
        return null;
    }


}