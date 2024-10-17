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
        if (!first.getContainer().getClass().equals(o.getClass())){
            return;
        }
    }

    @Override
    public void addLast(Object o) {
        if (!first.getContainer().getClass().equals(o.getClass())){
            return;
        }
        if (this.first == null && this.last == null){
            this.first = new Container<T>();
            this.last = new Container<T>();
            this.first.setNext(last);
            this.last.setPrev(first);

            if (last.hasEmptyPlace()){
                last.addToContainer((T) o);
            } else {
                Container<T> newLastContainer = new Container<>();
                this.last.setNext(newLastContainer);
                newLastContainer.setPrev(this.last);
                last = newLastContainer;
                last.addToContainer((T) o);
            }
        }
    }

    @Override
    public boolean offerFirst(Object o) {
        return false;
    }

    @Override
    public boolean offerLast(Object o) {
        return false;
    }

    @Override
    public Object removeFirst() {
        return null;
    }

    @Override
    public Object removeLast() {
        return null;
    }

    @Override
    public Object pollFirst() {
        return null;
    }

    @Override
    public Object pollLast() {
        return null;
    }

    @Override
    public Object getFirst() {
        return null;
    }

    @Override
    public Object getLast() {
        return null;
    }

    @Override
    public Object peekFirst() {
        return null;
    }

    @Override
    public Object peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
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
}
