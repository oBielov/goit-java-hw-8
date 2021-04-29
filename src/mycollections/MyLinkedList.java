package mycollections;

public class MyLinkedList<T> implements MyList<T> {

    int size = 0;
    private Node<T> first;
    private Node<T> last;


    private static class Node<T>{
        MyLinkedList.Node<T> previous;
        T current;
        MyLinkedList.Node<T> next;

        Node(MyLinkedList.Node<T> previous, T current, MyLinkedList.Node<T> next){
            this.previous = previous;
            this.current = current;
            this.next = next;
        }
    }

    private Node<T> getNodeAtIndex(int index){
        Node<T> temp;
        if (index < size/2){
            temp = first;
            for(int i = 0; i<index; i++){
                temp = temp.next;
            }
        }
        else{
            temp = last;
            for(int i = size-1; i>index; i--){
                temp = temp.previous;
            }
        }
        return temp;
    }



    @Override
    public void add(T t) {
        MyLinkedList.Node<T> l = last;
        MyLinkedList.Node<T> newNode = new MyLinkedList.Node<>(l, t, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        System.out.println("Node added: " + newNode.current);
        size++;
    }

    @Override
    public void remove(int index) {
        if (index > size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> tmp = getNodeAtIndex(index);
        Node<T> next = tmp.next;
        Node<T> previous = tmp.previous;

        if (next == null){
            previous.next = null;
        }
        else if (previous == null){
            next.previous = null;
            first = next;
        }
        else{
            previous.next = next;
            next.previous = previous;
        }
        size--;

    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        return getNodeAtIndex(index).current;
    }
}
