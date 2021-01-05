package org.cathay.datastructure;

public class Merge2SortedList {

    public static void main(String[] args) {
        Merge2SortedList solution = new Merge2SortedList();
        //ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(solution.mergeTwoLists(l1, l2));

        ListNode l3 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l4 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(solution.mergeTwoLists(l3, l4));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if(l1 == null) return l2;
        if(l2 ==null) return l1;

        ListNode pointer1 = l1;
        ListNode pointer2 = l2;
        ListNode previousNode = null;
        ListNode currentNode;
        ListNode head = null;

        while (pointer1 != null && pointer2 != null) {
            currentNode = pointer1.val <= pointer2.val ? pointer1 : pointer2;
            if (head == null) head = currentNode;

            if (pointer1.val <= pointer2.val) {
                pointer1 = pointer1.next;
            } else {
                pointer2 = pointer2.next;
            }
            if (previousNode != null) previousNode.next = currentNode;
            previousNode = currentNode;
        }

        if (pointer1 != null) {
            previousNode.next = pointer1;
        }

        if (pointer2 != null) {
            previousNode.next = pointer2;
        }
        return head;
    }
}
