package ru.mpei;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NewCustomIterator<T> implements Iterator<T> {
    private int currentIndex = 0;
    private Container<T> currentContainer;
    TripletDeque tripletDeque;

    public <T> NewCustomIterator(TripletDeque<T> tripletDeque) {
        this.tripletDeque = tripletDeque;
        currentContainer = this.tripletDeque.getFirstContainer();
    }

    @Override
    public boolean hasNext() {
        if (this.currentContainer != null && currentIndex == currentContainer.getSize() ) {
            this.currentContainer = this.currentContainer.getNext();
            this.currentIndex = 0;
        }
        if (currentContainer !=null && (currentContainer.hasNext() || currentContainer.equals(this.tripletDeque.getLastContainer())) ) {
            if (currentContainer.hasEmptyPlace()) {
                return currentContainer.getContainer()[this.currentIndex++] != null;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public T next() {
        if (this.currentContainer == null){
            throw new NoSuchElementException();
        }
        return currentContainer.getContainer()[currentIndex++];
    }
}
