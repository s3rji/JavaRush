package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    LinkedList<Entry<String>> queue;
    int size = 0;


    public CustomTree() {
        this.root = new Entry<>("root");
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

    public String getParent(String s) {
        getAllEntries();
        getActualEntries();

        Entry<String> entry;
        while (!queue.isEmpty()) {
            entry = queue.removeFirst();
            if (entry.elementName.equals(s)) {
                return entry.parent.elementName;
            }
        }

        return null;
    }

    @Override
    public boolean add(String s) {
        getAllEntries();

        Entry<String> entry;

        while (!queue.isEmpty()) {
            entry = queue.pollLast();
            if (!queue.isEmpty() && queue.peekLast().isAvailableToAddChildren())
                continue;

            if (entry.leftChild == null) {
                entry.leftChild = new Entry<>(s);
                entry.leftChild.parent = entry;
                entry.availableToAddLeftChildren = false;
                size++;
                break;
            }

            if (entry.rightChild == null) {
                entry.rightChild = new Entry<>(s);
                entry.rightChild.parent = entry;
                entry.availableToAddRightChildren = false;
                size++;
                break;
            }

        }

        return true;
    }

    public void getAllEntries() {
        queue = new LinkedList<>();
        LinkedList<Entry<String>> subQueue = new LinkedList<>();
        queue.add(root);
        subQueue.add(root);

        Entry<String> entry;
        while (!subQueue.isEmpty()) {
            entry = subQueue.removeFirst();

            if (entry.leftChild != null) {
                queue.add(entry.leftChild);
                subQueue.add(entry.leftChild);
            }
            if (entry.rightChild != null) {
                queue.add(entry.rightChild);
                subQueue.add(entry.rightChild);
            }
        }
    }

    public void getActualEntries() {
        if (queue.getFirst() == root) queue.pollFirst();
    }


    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) throw new UnsupportedOperationException();

        boolean result = false;
        String target = (String) o;

        getAllEntries();
        getActualEntries();

        while (!queue.isEmpty()) {
            if (queue.peekFirst().elementName.equals(target)) {
                Entry<String> parent = queue.peekFirst().parent;

                if (parent.leftChild == queue.peekFirst()) {
                    parent.leftChild = null;
                    parent.availableToAddLeftChildren = true;
                }

                if (parent.rightChild == queue.peekFirst()) {
                    parent.rightChild = null;
                    parent.availableToAddRightChildren = true;
                }

                result = true;
            }
            queue.pollFirst();
        }

        getAllEntries();
        getActualEntries();
        this.size = queue.size();

        return result;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }
}
