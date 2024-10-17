package ru.mpei;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

@SuppressWarnings("ALL")
public class TripletDeque<T> implements Containerable, Deque {
    private Container<T> last;
    private Container<T> first;
    private int size = 1000;
    private int length;

    public TripletDeque(int size){
        this.size = size;
    }

    public TripletDeque(){}


    @Override
    public Object[] getContainerByIndex(int cIndex) {
        return new Object[0];
    }

    @Override
    public void addFirst(Object o) {
//        if (first != null && !first.getContainer().getClass().equals(o.getClass())){
//            return;
//        }

        if (this.first == null && this.last == null) {
            this.first = new Container<T>();
            this.last = new Container<T>();
            this.first.setNext(last);
            this.last.setPrev(first);
            length += 2;
        }
        if (first.hasEmptyPlace()){
            first.addToContainer((T) o);
        } else {
            Container<T> newLastContainer = new Container<>();
            newLastContainer.setNext(this.first);
            this.first.setPrev(newLastContainer);
            first = newLastContainer;
            first.addToContainer((T) o);
            length++;
        }

    }

    @Override
    public void addLast(Object o) {
//        if (first != null && !first.getContainer().getClass().equals(o.getClass())){
//            return;
//        }
        if (this.first == null && this.last == null) {
            this.first = new Container<T>();
            this.last = new Container<T>();
            this.first.setNext(last);
            this.last.setPrev(first);
            length += 2;
        }
//        if (this.last == null){
//            this.last = new Container<T>();
//            this.first.setNext(last);
//            this.last.setPrev(first);
//            length++;
//        }
        if (last.hasEmptyPlace()){
            last.addToContainer((T) o);
        } else {
            Container<T> newLastContainer = new Container<>();
            this.last.setNext(newLastContainer);
            newLastContainer.setPrev(this.last);
            this.last = newLastContainer;
            this.last.addToContainer((T) o);
        }
    }

    @Override
    public boolean offerFirst(Object o) {
        addFirst(o);
        for (int i = 0; i < this.first.getSize(); i++){
            if (this.first.getContainer()[i].equals((T) o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean offerLast(Object o) {
        addLast(o);
        for (int i = 0; i < this.last.getSize(); i++){
            if (this.last.getContainer()[i].equals((T) o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public T removeFirst() {
        removeFromEnd(this.first);
        return (T) getLastElementFromContainer(this.first);
    }

    @Override
    public T removeLast() {
        removeFromEnd(this.last);
        return (T) getLastElementFromContainer(this.last);
    }

    @Override
    public T pollFirst() {
        return removeFirst();
    }

    @Override
    public T pollLast() {
        return removeLast();
    }

    @Override
    public T getFirst() {
        return getLastElementFromContainer(this.first);
    }

    @Override
    public T getLast() {
        return getLastElementFromContainer(this.last);
    }

    @Override
    public T peekFirst() {
        return getLastElementFromContainer(this.first);
    }

    @Override
    public T peekLast() {
       return getLastElementFromContainer(this.last);
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return removeFromMiddle(this.first, o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return removeFromMiddle(this.last, o);
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public Object remove() {
        return null;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public Object element() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public void push(Object o) {

    }

    @Override
    public Object pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (o instanceof Container){

        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public Iterator descendingIterator() {
        return null;
    }

//    private T removeLastElementFromContainer(Container<T> t){
//        T lastElementOfContainer = t.getContainer()[t.getSize() - 1];
//        if (lastElementOfContainer != null){
//            t.getContainer()[t.getSize() - 1] = null;
//            return (T) lastElementOfContainer;
//        } else {
//            for (int i = 0; i < t.getSize(); i++) {
//                if (t.getContainer()[i] == null) {
//                    T returnValue = t.getContainer()[i - 1];
//                    t.getContainer()[i-1] = null;
//                    return (T) returnValue;
//                }
//            }
//        }
//        return null;
//    }


    private T getLastElementFromContainer(Container<T> t){
        if (t.isEmpty()){
            throw new ContainerEmptyException();
        }
        T lastElementOfContainer = t.getContainer()[t.getSize() - 1];
        if (lastElementOfContainer != null){
            return (T) lastElementOfContainer;
        } else {
            for (int i = 0; i < t.getSize(); i++) {
                if (t.getContainer()[i] == null) {
                    T returnValue = t.getContainer()[i - 1];
                    return (T) returnValue;
                }
            }
        }
        return null;
    }

    private void removeFromEnd(Container<T> t){
        if (t.isEmpty()){
            throw new ContainerEmptyException();
        }
        if (t.getContainer()[t.getSize() - 1] != null){
            t.getContainer()[t.getSize() - 1] = null;
        } else {
            for (int i = 0; i < t.getSize(); i++){
                if (t.getContainer()[i] == null){
                    t.getContainer()[i - 1] = null;
                }
            }
        }
    }

    private boolean removeFromMiddle(Container<T> t, Object elementToRemove){
        if (elementToRemove != null){
            elementToRemove = (T)elementToRemove;
        }

        if (t.isEmpty()){
            return false;
        } else {
            if (elementToRemove.equals(t.getContainer()[t.getSize() - 1])){
                t.getContainer()[t.getSize() - 1] = null;
                return true;
            } else {
                for (int i = 0; i < t.getSize(); i++) {
                    if (elementToRemove.equals(t.getContainer()[i])) {
                        int la = 0;
                        for (int j = i; t.getContainer()[j] != null; j++, la++) {
                            t.getContainer()[j] = t.getContainer()[j + 1];
                        }
                        t.getContainer()[la - 1] = null;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void printDeque() {
       Container<T> node = first;
       while (node != null) {
           for (int i = 0; i < 5; i++) {
               System.out.print(node.getContainer()[i] + " ");
           }
           System.out.println();
           node = node.getNext();
       }
    }
}
