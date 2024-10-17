package ru.mpei;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

@SuppressWarnings("ALL")
public class TripletDeque<T> implements Containerable, Deque<T>{
    private Container<T> first;
    private Container<T> last;
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
        if (first != null && !first.getContainer()[4].getClass().equals(o.getClass())){
            return;
        } else if (o == null){
            throw new NullPointerException();
        }

        if (this.first == null && this.last == null) {
            this.first = this.last =  new Container<T>();
            length += 1;
        }
        if (first.hasEmptyPlace()){
            first.addToContainerToRight((T) o);
        } else {
            Container<T> newContainer = new Container<>();
            newContainer.setNext(this.first);
            this.first.setPrev(newContainer);
            first = newContainer;
            first.addToContainerToRight((T) o);
            length++;
        }

    }

    @Override
    public void addLast(Object o) {
        if (first != null && !first.getContainer()[4].getClass().equals(o.getClass())){
            return;
        } else if (o == null){
            throw new NullPointerException();
        }

        if (this.first == null && this.last == null) {
            this.first = this.last = new Container<T>();
            length++;
        }

        if (last.hasEmptyPlace()){
            last.addToContainerToLeft((T) o);
        } else {
            Container<T> newLastContainer = new Container<>();
            this.last.setNext(newLastContainer);
            newLastContainer.setPrev(this.last);
            this.last = newLastContainer;
            this.last.addToContainerToLeft((T) o);
        }
    }

    @Override
    public boolean offerFirst(Object o) {
        if (first != null && !first.getContainer()[4].getClass().equals(o.getClass())){
            return false;
        } else if (o == null){
            throw new NullPointerException();
        }

        if (this.first == null && this.last == null) {
            this.first = this.last =  new Container<T>();
            length += 1;
        }
        if (first.hasEmptyPlace()){
            first.addToContainerToRight((T) o);
            return true;
        }
        return false;
    }

    @Override
    public boolean offerLast(Object o) {
        if (first != null && !first.getContainer()[4].getClass().equals(o.getClass())){
            return false;
        } else if (o == null){
            throw new NullPointerException();
        }

        if (this.first == null && this.last == null) {
            this.first = this.last =  new Container<T>();
            length += 1;
        }
        if (last.hasEmptyPlace()){
            last.addToContainerToLeft((T) o);
            return true;
        }
        return false;
    }

    @Override
    public T removeFirst() {
        if (this.first.isEmpty()){
            this.first.getNext().setPrev(null);
            this.first = this.first.getNext();
        }
        return removeFromLeft(this.first);
    }

    @Override
    public T removeLast() {
        if (this.last.isEmpty()){
            this.last.getPrev().setNext(null);
            this.last = this.last.getPrev();
        }
       return removeFromRight(this.last);
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
//        return removeFromMiddle(this.first, o);
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
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
    public T pop() {
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
        throw new UnsupportedOperationException();
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

    private T removeFromRight(Container<T> t){
        if (t.getContainer()[t.getSize() - 1] != null){
            T lastElement = t.getContainer()[t.getSize() - 1];
            t.getContainer()[t.getSize() - 1] = null;
            return lastElement;
        } else {
            for (int i = 0; i < t.getSize(); i++){
                if (t.getContainer()[i] == null){
                    T returnValue = t.getContainer()[i - 1];
                    t.getContainer()[i-1] = null;
                    return returnValue;
                }
            }
        }
        return null;
    }

    private T removeFromLeft(Container<T> t){
        if (t.getContainer()[0] != null){
            T lastElement = t.getContainer()[0];
            t.getContainer()[0] = null;
            return lastElement;
        } else {
            for (int i = t.getSize() - 1; i >= 0; i--){
                if (t.getContainer()[i] == null){
                    T returnValue = t.getContainer()[i+1];
                    t.getContainer()[i+1] = null;
                    return returnValue;
                }
            }
        }
        return null;
    }

//    private boolean removeFromMiddle(Container<T> t, Object elementToRemove){
//
//        if (elementToRemove != null){
//            elementToRemove = (T)elementToRemove;
//        }
//
//        if (t.isEmpty()){
//            return false;
//        } else {
//            if (elementToRemove.equals(t.getContainer()[t.getSize() - 1])){
//                t.getContainer()[t.getSize() - 1] = null;
//                return true;
//            } else {
//                for (int i = 0; i < t.getSize(); i++) {
//                    if (elementToRemove.equals(t.getContainer()[i])) {
//                        int la = 0;
//                        for (int j = i; t.getContainer()[j] != null; j++, la++) {
//                            if (j > 4){
//                                break;
//                            }
////                            t.getContainer()[j] = t.getContainer()[j + 1];
//                        }
//                        t.getContainer()[la - 1] = null;
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }

    public void printDeque() {
       Container<T> node = first;
       while (node != null) {
           System.out.print("[");
           for (int i = 0; i < 5; i++) {
               System.out.print(node.getContainer()[i] + " ");
           }
           System.out.print("] ");
           node = node.getNext();
       }
    }
}
