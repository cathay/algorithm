package org.cathay.datastructure;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
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
