package ru.mpei;

@SuppressWarnings("ALL")
public class Container<T> {
    private Container<T> next = null;
    private Container<T> prev = null;
    private T[] container;
    private int size = 5;

    public Container(int size){
        this.size = size;
        container = (T[]) new Object[size];
    }

    public Container(){
        container = (T[]) new Object[size];
    }

    public Container<T> getNext() {
        return next;
    }

    public void setNext(Container<T> next) {
        this.next = next;
    }

    public Container<T> getPrev() {
        return prev;
    }

    public void setPrev(Container<T> prev) {
        this.prev = prev;
    }

    public T[] getContainer() {
        return container;
    }

    public void setContainer(T[] container) {
        this.container = container;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean hasEmptyPlace(){
        for (int i = 0; i < container.length; i++){
            if (container[i] == null){
                return true;
            }
        }
        return false;
    }

    public boolean addToContainer(T t){
        for (int i = 0; i < container.length; i++){
            if (container[i] == null){
                container[i] = t;
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(){
        return container[0] == null;
    }
}
