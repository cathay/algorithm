package org.cathay.datastructure.list;

import org.cathay.datastructure.ListNode;

public class ReverseLinkedList {

    public static ListNode reverse(ListNode list) {
        ListNode result = null;

        ListNode current = list;

        while (current != null) {
            // first item
            if (result == null) {
                result = new ListNode(current.getVal());
                current = current.getNext();
                continue;
            }

            ListNode insertNode = new ListNode(current.getVal());
            insertNode.setNext(result);

            current = current.getNext();
            result = insertNode;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode n =
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));

        System.out.println(reverse(n));
    }
}
