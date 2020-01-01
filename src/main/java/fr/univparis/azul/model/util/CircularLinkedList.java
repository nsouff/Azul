package fr.univparis.azul.model.util;

import java.util.Iterator;

public class CircularLinkedList<E> {
	private class Node<E> {
		private E element;
		private Node<E> next;

		private Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		private Node(E e) {
			element = e;
		}

		private void setNext(Node<E> n) {
			next = n;
		}

	}
	Node<E> first;

	public void add(E element) {
		if (first == null) {
			first = new Node<E>(element);
			first.setNext(first);
			return;
		}
		Node<E> it = first;
		while (it.next != first) {
			it = it.next;
		}
		Node<E> last = new Node<E>(element, first);
		it.setNext(last);
	}

	public void setFirst(E element) {
		Node<E> it = first;
		while(it.element != element && it.next != first) {
			it = it.next;
		}
		if (it.element != element)
			throw new IllegalArgumentException("Element is not in the CircularLinkedList");
		else 
			first = it;
	}

	public boolean isEmpty() {return (first == null);}

	public E getFirst() {return first.element;}

	public Iterator<E> iterator() {
		Iterator<E> it = new Iterator<E>() {
			Node<E> actual = null;

			@Override
			public boolean hasNext() {throw new UnsupportedOperationException();}

			@Override
			public E next() {
				if (actual == null) actual = first;
				else actual = actual.next;
				return actual.element;
			}
		};
		return it;
	}

}
