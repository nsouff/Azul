package fr.univparis.azul.model.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SortedLinkedList<E> implements List<E> {
  private LinkedList<E> list;
  private Comparator<E> comp;

  public SortedLinkedList(Comparator<E> c) {
    list = new LinkedList<E>();
    comp = c;
  }

  public boolean add(E e) {
    int i = 0;

    while( i < list.size() && comp.compare(list.get(i),e) < 0 ) {
      i++;
    }

    list.add(i,e);
    return true;
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

  public E set(int index, E e) {throw new UnsupportedOperationException();}

  public void clear() {list.clear();}

  public void add(int index, E e) {throw new UnsupportedOperationException();}

  public boolean addAll(int index, Collection<? extends E> c) {throw new UnsupportedOperationException();}

  public boolean addAll(Collection<? extends E> c) {throw new UnsupportedOperationException();}

  public boolean containsAll(Collection<?> c) {throw new UnsupportedOperationException();}

  public int indexOf(Object o) {throw new UnsupportedOperationException();}

  public int lastIndexOf(Object o) {throw new UnsupportedOperationException();}

  public ListIterator<E> listIterator() {throw new UnsupportedOperationException();}

  public ListIterator<E> listIterator(int index) {throw new UnsupportedOperationException();}

  public boolean removeAll(Collection<?> c) {
    boolean b = true;
    for (Object o : c) b &= list.remove(o);
    return b;
  }

  public boolean retainAll(Collection<?> c) {throw new UnsupportedOperationException();}

  public List<E> subList(int fromIndex, int toIndex) {throw new UnsupportedOperationException();}

  public Object[] toArray() {throw new UnsupportedOperationException();}

  public <T> T[] toArray(T[] a) {throw new UnsupportedOperationException();}

}
