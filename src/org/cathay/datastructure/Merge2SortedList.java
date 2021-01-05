package org.cathay.datastructure;

public class Merge2SortedList {

    public static void main(String[] args) {
        Merge2SortedList solution = new Merge2SortedList();
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        solution.mergeTwoLists(l1, l2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode pointer1 = l1;
        ListNode pointer2 = l2;
        ListNode tempNode;
        ListNode previousNode = l1;
        while (pointer1 != null && pointer2 != null) {
            if (pointer1.val <= pointer2.val) {
                previousNode = pointer1;
                pointer1 = pointer1.next;
            } else {
                tempNode = pointer2.next;
                previousNode.next = pointer2;
                pointer2.next = pointer1;
                pointer2 = tempNode;
            }
        }

        return l1;
    }
}
