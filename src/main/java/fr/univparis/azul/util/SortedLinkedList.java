package fr.univparis.azul.util;

import java.lang.Iterable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class SortedLinkedList<E> implements Iterable<E> {
    private LinkedList<E> list;
    private Comparator<E> comp;

    public SortedLinkedList(Comparator<E> c) {
	list = new LinkedList<E>();
	comp = c;
    }

    public void add(E e) {
	int i = 0;
	
	while( i < list.size() && comp.compare(list.get(i),e) < 0 ) {
	    i++;
	}
	
	list.add(i,e);
    }

    public E get(int index) {
	return list.get(index);
    }

    public boolean isEmpty() {
	return list.isEmpty();
    }

    public int size() {
	return list.size();
    }

    public E remove(int index) {
	return list.remove(index);
    }

    public boolean remove(Object o) {
	return list.remove(o);
    }

    public boolean contains(Object o) {
	return list.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
	return list.iterator();
    }
}
