package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacityCollection = (int) Math.ceil(collection.size() / 0.75);
        int maxCapacity = Math.max(16, capacityCollection);
        map = new HashMap<>(maxCapacity);

        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        if (map.containsKey(e))
            return false;
        else {
            map.put(e, PRESENT);
            return true;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    @Override
    public Object clone() {
        try {
            AmigoSet<E> clone = (AmigoSet<E>) super.clone();
            clone.map = (HashMap<E, Object>) map.clone();

            return clone;
            
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        int capacity = in.readInt();
        float loadFactor = in.readFloat();
        int size = in.readInt();

        this.map = new HashMap<>(capacity, loadFactor);

        for (int i = 0; i < size; i++) {
            this.map.put((E) in.readObject(), PRESENT);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        Integer capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        Float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");

        out.defaultWriteObject();
        out.writeInt(capacity);
        out.writeFloat(loadFactor);
        out.writeInt(size());

        for (E e : map.keySet()) {
            out.writeObject(e);
        }
    }
}
