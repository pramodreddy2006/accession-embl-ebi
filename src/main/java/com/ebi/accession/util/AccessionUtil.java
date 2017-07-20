package com.ebi.accession.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ebi.accession.linkedlist.Node;

public class AccessionUtil {

	/**
	 * @param elements
	 *            - Array of unordered accession numbers.
	 * @return - List with ordered elements converting the consecutive accession
	 *         numbers to ranges
	 * @throws Exception
	 * 
	 */
	public static List<String> getOrderedRangeList(String[] elements) throws Exception {
		trimAndOrder(elements);
		List<String> resultList = generateRanges(elements, elements.length);
		return resultList;
	}

	/**
	 * @param elements
	 *            - Array of unordered accession numbers.
	 * 
	 *            Accession numbers are trimmed and sorted.
	 * 
	 */
	private static void trimAndOrder(String[] elements) {
		for (int i = 0; i < elements.length; i++) {
			elements[i] = elements[i].trim();
		}
		Arrays.sort(elements);
	}

	/**
	 * @param elements
	 *            - Array of ordered/sorted accession numbers.
	 * @param n
	 *            - total number of elements
	 * 
	 * @return - List with formatted elements converting the consecutive
	 *         accession numbers to ranges
	 * @throws Exception
	 * 
	 */
	private static List<String> generateRanges(String[] elements, int n) throws Exception {
		List<String> sol = new ArrayList<String>();
		int i = 0;
		int preIndex = getIndex(elements[i]);
		String prePrefix = elements[i].substring(0, preIndex);
		String preNumber = elements[i].substring(preIndex);
		Node preNode = getLinkedList(preNumber);
		while (i < n) {
			int j = i;
			while (j < n - 1) {
				int curIndex = getIndex(elements[j + 1]);
				String curPrefix = elements[j + 1].substring(0, curIndex);
				String curNumber = elements[j + 1].substring(curIndex);
				Node curNode = getLinkedList(curNumber);
				boolean isNext = validate(prePrefix, curPrefix, preNumber, curNumber, preNode, curNode);
				prePrefix = curPrefix;
				preNumber = curNumber;
				preNode = curNode;
				if (isNext) {
					j++;
				} else {
					break;
				}
			}
			if (!elements[i].equals(elements[j])) {
				sol.add(elements[i] + "-" + elements[j]);
			} else {
				sol.add(elements[i]);
			}
			i = j + 1;
		}
		return sol;
	}

	/**
	 * @param substring
	 *            - Digit sequence in an accession number
	 * 
	 * @return - LinkedList Node formed in reverse order of digit sequence
	 * 
	 * @throws Exception
	 */
	private static Node getLinkedList(String substring) throws Exception {
		Node linkedList = null;
		for (int i = 0; i < substring.length(); i++) {
			int dig = substring.charAt(i) - '0';
			if (dig < 0 || dig > 9) {
				throw new Exception("Invalid Accession Number");
			}
			Node newNode = new Node(dig);
			newNode.next = linkedList;
			linkedList = newNode;
		}
		return linkedList;
	}

	/**
	 * @param x
	 *            - accession number
	 * 
	 * @return - Index at which the Letters end in an accession number
	 */
	private static int getIndex(String x) {
		int i = 0;
		while (i < x.length()
				&& ((x.charAt(i) >= 'A' && x.charAt(i) <= 'Z') || (x.charAt(i) >= 'a' && x.charAt(i) <= 'z'))) {
			i++;
		}
		return i;
	}

	/**
	 * @param prePrefix
	 *            - ASCII letters of previous accession number
	 * @param curPrefix
	 *            - ASCII letters of current accession number
	 * @param preNumber
	 *            - Digits of previous accession number
	 * @param curNumber
	 *            - Digits of current accession number
	 * @param preNode
	 *            - List denoting the digits of previous
	 * @param curNode
	 *            - List denoting the digits of current
	 * 
	 * @return - true if two accession numbers are same or they are consecutive
	 *         - false otherwise
	 * 
	 */
	private static boolean validate(String prePrefix, String curPrefix, String preNumber, String curNumber,
			Node preNode, Node curNode) {
		if (!prePrefix.equals(curPrefix)) {
			return false;
		}
		if (preNumber.length() != curNumber.length()) {
			return false;
		}
		if (preNumber.equals(curNumber)) {
			return true;
		}
		addOne(preNode);
		if (preNode.equals(curNode)) {
			return true;
		}
		return false;
	}

	/**
	 * @param list
	 * 
	 *            adds 1 to linked list
	 * 
	 */
	private static void addOne(Node list) {
		int carry = 1;
		while (carry == 1 && list != null) {
			carry = (list.value + 1) / 10;
			list.value = (list.value + 1) % 10;
			list = list.next;
		}
	}

}
