package fr.univparis.azul.util;

import java.util.Comparator;
import java.util.LinkedList;

public class SortedLinkedList<E> {
    LinkedList<E> list;
    Comparator<E> comp;

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

}
