package ru.mpei;

import java.util.*;


public class TripletDeque<T> implements Containerable, Deque<T> {
    private Container<T> firstContainer;
    private Container<T> lastContainer;
    private int size = 1000;
    private int length;

    public TripletDeque(int size){
        this.size = size;
    }

    public TripletDeque(){}


    @Override
    public void addFirst(Object o) {
        if (firstContainer != null && firstContainer.getContainer()[4] != null && !firstContainer.getContainer()[4].getClass().equals(o.getClass())){
            return;
        } else if (o == null){
            throw new NullPointerException();
        }

        if (this.firstContainer == null && this.lastContainer == null) {
            this.firstContainer = this.lastContainer =  new Container<T>();
            length += 1;
        }
        if (firstContainer.hasEmptyPlace()){
            firstContainer.addToContainerToRight((T) o);
        } else {
            Container<T> newContainer = new Container<>();
            newContainer.setNext(this.firstContainer);
            this.firstContainer.setPrev(newContainer);
            firstContainer = newContainer;
            firstContainer.addToContainerToRight((T) o);
            length++;
        }

    }

    @Override
    public void addLast(Object o) {
        if (firstContainer != null && firstContainer.getContainer()[4] != null && !firstContainer.getContainer()[4].getClass().equals(o.getClass())){
            return;
        } else if (o == null){
            throw new NullPointerException();
        }

        if (this.firstContainer == null && this.lastContainer == null) {
            this.firstContainer = this.lastContainer = new Container<T>();
            length++;
        }

        if (lastContainer.hasEmptyPlace()){
            lastContainer.addToContainerToLeft((T) o);
        } else {
            Container<T> newLastContainer = new Container<>();
            this.lastContainer.setNext(newLastContainer);
            newLastContainer.setPrev(this.lastContainer);
            this.lastContainer = newLastContainer;
            this.lastContainer.addToContainerToLeft((T) o);
        }
    }

    @Override
    public boolean offerFirst(Object o) {
        if (firstContainer != null && firstContainer.getContainer()[4]!= null && !firstContainer.getContainer()[4].getClass().equals(o.getClass())){
            return false;
        } else if (o == null){
            throw new NullPointerException();
        }

        if (this.firstContainer == null && this.lastContainer == null) {
            this.firstContainer = this.lastContainer =  new Container<T>();
            length += 1;
        }
        if (firstContainer.hasEmptyPlace()){
            firstContainer.addToContainerToRight((T) o);
            return true;
        }
        return false;
    }

    @Override
    public boolean offerLast(Object o) {
        if (firstContainer != null && firstContainer.getContainer()[4]!= null && !firstContainer.getContainer()[4].getClass().equals(o.getClass())){
            return false;
        } else if (o == null){
            throw new NullPointerException();
        }

        if (this.firstContainer == null && this.lastContainer == null) {
            this.firstContainer = this.lastContainer =  new Container<T>();
            length += 1;
        }
        if (lastContainer.hasEmptyPlace()){
            lastContainer.addToContainerToLeft((T) o);
            return true;
        }
        return false;
    }

    @Override
    public T removeFirst() {
        if (this.firstContainer.isEmpty() && firstContainer != null){
            this.firstContainer.getNext().setPrev(null);
            this.firstContainer = this.firstContainer.getNext();
        }
        return removeFromRight(this.firstContainer);
    }

    @Override
    public T removeLast() {
        if (this.lastContainer.isEmpty() && lastContainer != null){
            this.lastContainer.getPrev().setNext(null);
            this.lastContainer = this.lastContainer.getPrev();
        }
        return removeFromLeft(this.lastContainer);
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
        if (this.firstContainer.getNext() != null && this.firstContainer.isEmpty()){
            this.firstContainer.getNext().setPrev(null);
            this.firstContainer = this.firstContainer.getNext();
        } else if (this.firstContainer.isEmpty() && this.firstContainer.getNext() == null){
            throw new ContainerEmptyException();
        }
        return getLastElementFromRight(this.firstContainer);
    }

    @Override
    public T getLast() {
        if (this.lastContainer.isEmpty() && this.lastContainer.getPrev() != null){
            this.lastContainer.getPrev().setNext(null);
            this.lastContainer = this.lastContainer.getPrev();
        } else if (this.lastContainer.isEmpty() && this.lastContainer.getPrev() == null){
            throw new ContainerEmptyException();
        }
        if (this.firstContainer.getNext()!= null && this.firstContainer.getNext().equals(this.lastContainer)){
            return getLastElementFromRight(this.lastContainer);
        }
        return getLastElementFromLeft(this.lastContainer);
    }

    @Override
    public T peekFirst() {
        if (this.firstContainer == null && this.lastContainer == null){
            return null;
        }
        return getFirst();
    }

    @Override
    public T peekLast() {
        if (this.firstContainer == null && this.lastContainer == null){
            return null;
        }
        return getLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (firstContainer != null && firstContainer.getContainer()[4]!= null && !firstContainer.getContainer()[4].getClass().equals(o.getClass())){
            return false;
        } else if (o == null){
            throw new NullPointerException();
        }

        T objectToRemove = (T) o;
        Container<T> needContainer = this.firstContainer;
        boolean result = false;
        while (needContainer != null){
            for (int i = 0; i < needContainer.getSize(); i++){
                if (objectToRemove.equals(needContainer.getContainer()[i])){
                    shifToRight(needContainer, i);
                    result = true;
                    break;
                }
            }

            if (result == true){
                break;
            }
            needContainer = needContainer.getNext();
        }
        while (needContainer != null){
            if (needContainer.getPrev() != null && needContainer.getContainer()[0] == null) {
                needContainer.getContainer()[0] = needContainer.getPrev().getContainer()[4];
                shifToRight(needContainer.getPrev(),4);
            }
            needContainer = needContainer.getPrev();
        }
        return result;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (firstContainer != null && firstContainer.getContainer()[4]!= null && !firstContainer.getContainer()[4].getClass().equals(o.getClass())){
            return false;
        } else if (o == null){
            throw new NullPointerException();
        }

        T objectToRemove = (T) o;
        Container<T> needContainer = this.lastContainer;
        boolean result = false;
        while (needContainer != null){
            for (int i = 0; i < needContainer.getSize(); i++){
                if (objectToRemove.equals(needContainer.getContainer()[i])){
                    shiftToLeft(needContainer, i);
                    result = true;
                    break;
                }
            }
            if (result == true){
                break;
            }
            needContainer = needContainer.getPrev();
        }
        while (needContainer != null){
            if (needContainer.getNext() != null && needContainer.getContainer()[4] == null) {
                needContainer.getContainer()[4] = needContainer.getPrev().getContainer()[0];
                shiftToLeft(needContainer.getPrev(),0);
            }
            needContainer = needContainer.getNext();
        }
        return result;
    }

    @Override
    public boolean add(T t) {
        if (!this.contains(t)){
            this.addFirst(t);
            return true;
        }
        return false;
    }

    @Override
    public boolean offer(T t) {
        return offerFirst(t);
    }

    public T remove() {
        if (this.firstContainer == null && this.lastContainer == null){
            throw new ContainerEmptyException();
        }
        T t = removeLast();
        if (this.lastContainer.isEmpty() && lastContainer != null){
            this.lastContainer.getPrev().setNext(null);
            this.lastContainer = this.lastContainer.getPrev();
        }
        return t;
    }


    @Override
    public T poll() {
        if (this.firstContainer == null && this.lastContainer == null){
            return null;
        }
        return removeFirst();
    }

    @Override
    public T element() {
        if (this.firstContainer == null && this.lastContainer == null){
            throw new ContainerEmptyException();
        }
        return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public boolean addAll(Collection c) {
        Object[] objectOfCollection = c.toArray();
        for (int i = 0; i < objectOfCollection.length; i++){
            if (!this.add((T) objectOfCollection[i])){
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        this.firstContainer = this.lastContainer = null;
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
        if (this.firstContainer.hasEmptyPlace()){
            offerFirst((T) o);
        }
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        if (this.firstContainer == null && this.lastContainer == null){
            throw new ContainerEmptyException();
        }
        boolean result = removeFirstOccurrence(o);
        if (this.firstContainer.isEmpty() && firstContainer != null){
            this.firstContainer.getNext().setPrev(null);
            this.firstContainer = this.firstContainer.getNext();
        }
        return result;
    }

    @Override
    public boolean containsAll(Collection c) {
        Container<T> first = this.firstContainer;
        while (first.hasNext()) {
            for (int i = 0; i < first.getSize() - 1; i++){
                if (!c.contains(first.getContainer()[i])){
                    return false;
                }
            }
            first = first.getNext();
        }
        for (int i = 0; i < lastContainer.getSize() - 1; i++){
            if (!c.contains(lastContainer.getContainer()[i])){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        if (firstContainer != null && firstContainer.getContainer()[4]!= null && !firstContainer.getContainer()[4].getClass().equals(o.getClass())){
            return false;
        } else if (o == null){
            throw new NullPointerException();
        }
        Container<T> first = this.firstContainer;
        while (first.hasNext()) {
            for (int i = 0; i < first.getSize(); i++){
                if (first.getContainer()[i] != null && first.getContainer()[i].equals((T)o)){
                    return true;
                }
            }
            first = first.getNext();
        }
        for (int i = 0; i < first.getSize() - 1; i++){
            if (this.lastContainer.getContainer()[i].equals((T)o)){
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < this.firstContainer.getSize(); i++){
            if (this.firstContainer.getContainer()[i] != null){
                return false;
            }
        }
        for (int i = 0; i < this.lastContainer.getSize(); i++){
            if (this.lastContainer.getContainer()[i] != null){
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator iterator() {
        return new NewCustomIterator(this);
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



    private T getLastElementFromLeft(Container<T> t){
        if (t.getContainer()[t.getContainer().length - 1] != null){
            T lastElement = t.getContainer()[t.getContainer().length - 1];
            return lastElement;
        } else {
            for (int i = 0; i < t.getContainer().length; i++){
                if (t.getContainer()[i] == null && i != 0){
                    T returnValue = t.getContainer()[i-1];
                    return returnValue;
                }
            }
        }
        return null;
    }

    private T getLastElementFromRight(Container<T> t){
        if (t.getContainer()[0] != null){
            T lastElement = t.getContainer()[0];
            return lastElement;
        } else {
            for (int i = t.getSize() - 1; i >= 0; i--){
                if (t.getContainer()[i] == null && i != t.getSize() - 1){
                    T returnValue = t.getContainer()[i+1];
                    return returnValue;
                }
            }
        }
        return null;
    }

    private T removeFromRight(Container<T> t){
        if (t.getContainer()[0] != null){
            T lastElement = t.getContainer()[0];
            t.getContainer()[0] = null;
            return lastElement;
        } else {
            for (int i = t.getSize() - 1; i >= 0; i--){
                if (t.getContainer()[i] == null && i != t.getSize() - 1){
                    T returnValue = t.getContainer()[i+1];
                    t.getContainer()[i+1] = null;
                    return returnValue;
                }
            }
        }
        return null;
    }

    private T removeFromLeft(Container<T> t){
        if (t.getContainer()[t.getSize()- 1] != null){
            T lastElement = t.getContainer()[t.getSize()- 1];
            t.getContainer()[t.getSize()-1] = null;
            return lastElement;
        } else {
            for (int i = 0; i < t.getSize(); i++){
                if (t.getContainer()[i] == null && i != 0){
                    T returnValue = t.getContainer()[i-1];
                    t.getContainer()[i-1] = null;
                    return returnValue;
                }
            }
        }
        return null;
    }

    private void shiftToLeft(Container<T> t, int shiftFrom){
        for (int i = shiftFrom; i < t.getSize() - 1; i++){
            t.getContainer()[i] = t.getContainer()[i + 1];
        }

        if (t.getContainer()[t.getSize() - 1] != null){
            t.getContainer()[t.getSize() - 1] = null;
        }
    }

    private void shifToRight(Container<T> t, int shiftTo){
        for (int i = shiftTo; i > 0; i--){
            t.getContainer()[i] = t.getContainer()[i - 1];

        }
        if (t.getContainer()[0] != null){
            t.getContainer()[0] = null;
        }
    }

    @Override
    public Object[] getContainerByIndex(int cIndex) {
        Container<T> checker = this.firstContainer;
        while (cIndex > 0){
            checker = checker.getNext();
            cIndex--;
        }
        if (checker != null && checker.getContainer() != null){
            return checker.getContainer();
        } else {
            return null;
        }
    }

    public Container<T> getFirstContainer() {
        return firstContainer;
    }

    public Container<T> getLastContainer() {
        return lastContainer;
    }

    public int getLength() {
        return length;
    }

}
