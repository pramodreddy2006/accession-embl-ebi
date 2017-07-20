package com.ebi.accession.linkedlist;

public class Node {

	public int value;
	public Node next;

	public Node(int value) {
		this.value = value;
		this.next = null;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		Node other = (Node) object;
		Node cur = this;
		while (other != null && cur != null) {
			if (other.value != cur.value) {
				return false;
			}
			other = other.next;
			cur = cur.next;
		}
		if (other == null && cur == null) {
			return true;
		}
		return false;
	}

}
