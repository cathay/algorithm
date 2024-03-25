package org.cathay.datastructure;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public boolean isEndNode() {
        return next == null;
    }

    public ListNode getNext() {
        return next;
    }

    public int getVal() {
        return val;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public static void main(String[] args) {
        System.out.println(
                deleteDuplicates(
                        new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4))))))
                ));

        System.out.println(
                deleteDuplicates(
                        new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4))))))
        );

        System.out.println(
                deleteDuplicates(
                        new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3,
                                new ListNode(4, new ListNode(4, new ListNode(5))))))))
        );

        System.out.println(
                deleteDuplicates(
                        new ListNode(1, new ListNode(1))
                ));
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        while (isDuplicateNode(head)) {
            head = ignoreDuplicates(head);
        }

        if (head == null) return null;

        ListNode currentNode = head;
        ListNode previousNode = currentNode;
        while (currentNode != null) {
            if (isDuplicateNode(currentNode)) {
                currentNode = ignoreDuplicates(currentNode);
                previousNode.next = currentNode;
            } else {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
        }
        return head;
    }

    private static boolean isDuplicateNode(ListNode node) {
        if (node == null) return false;
        if (node.next == null) return false;
        return node.val == node.next.val;
    }

    private static ListNode ignoreDuplicates(ListNode node) {
        if (node == null) return null;
        ListNode nextNode = node.next;
        while (nextNode != null && node.val == nextNode.val) {
            nextNode = nextNode.next;
        }
        return nextNode;
    }

    public String toString() {
        List<Integer> values = new ArrayList<>();
        ListNode t = this;
        while (t != null) {
            values.add(t.val);
            t = t.next;
        }
        return values.toString();
    }
}
