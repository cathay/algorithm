package org.cathay.datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeAlgorithms {

    public static List<List<Integer>> zizagTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) return List.of();
        if (root.left == null || root.right == null) List.of(List.of(root.val));

        Queue<TreeNode> visits = new ArrayDeque<>();
        visits.add(root);
        TreeNode curr;
        result.add(List.of(root.val));

        boolean fromTheRight = true;

        while (!visits.isEmpty()) {
            Queue<TreeNode> levelVisits = new ArrayDeque<>();
            curr = visits.poll();
            List<Integer> levelVals = new ArrayList<>();

            while (curr != null) {
                if (curr.left != null) {
                    levelVals.add(curr.left.val);
                    levelVisits.add(curr.left);
                }

                if (curr.right != null) {
                    levelVals.add(curr.right.val);
                    levelVisits.add(curr.right);
                }
                curr = visits.poll();
            }
            if (!levelVals.isEmpty()) {
                if (fromTheRight) {
                    Collections.reverse(levelVals);
                }
                result.add(levelVals);
            }
            fromTheRight = !fromTheRight;
            visits = levelVisits;
        }
        return result;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> visits = new Stack<>();

        TreeNode curr = root;

        // traverse the tree
        while (curr != null || visits.size() > 0) {

            /* Reach the left most Node of the
            curr Node */
            while (curr != null) {
                /* place pointer to a tree node on
                   the stack before traversing
                  the node's left subtree */
                visits.push(curr);
                curr = curr.left;
            }

            /* Current must be NULL at this point */
            curr = visits.pop();
            result.add(curr.val);
            System.out.print(curr.val + " ");

            /* we have visited the node and its
               left subtree.  Now, it's right
               subtree's turn */
            curr = curr.right;
        }

        return result;
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1,
//                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
//                new TreeNode(3, new TreeNode(6), new TreeNode(7))
//        );

//        TreeNode root = new TreeNode(1,
//                null,
//                new TreeNode(2, new TreeNode(3), null)
//        );


        TreeNode root = new TreeNode(0,
                new TreeNode(2,
                        new TreeNode(1,
                                new TreeNode(5),
                                new TreeNode(1))
                        , null),
                new TreeNode(4,
                        new TreeNode(3, null, new TreeNode(6)),
                        new TreeNode(-1, null, new TreeNode(8)))
        );
//        System.out.println(inorderTraversal(root));
        System.out.println(zizagTraversal(root));
    }
}