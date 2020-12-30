package org.cathay.palindrome;

import org.cathay.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PseudoPalindromeTree {

    public static void main(String[] args) {
        TreeNode tree1 = new TreeNode(2,
                new TreeNode(3,
                        new TreeNode(3),
                        new TreeNode(1)),
                new TreeNode(1,
                        null,
                        new TreeNode(1))
        );

        TreeNode tree2 = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        PseudoPalindromeTree solution = new PseudoPalindromeTree();
        //System.out.println(solution.pseudoPalindromicPaths(tree2));
        System.out.println(solution.pseudoPalindromicPaths(tree1));
    }

    public int pseudoPalindromicPaths(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        int[] count = new int[1];
        pseudoPalindromicPaths(root, values, count);
        return count[0];
    }

    private void pseudoPalindromicPaths(TreeNode node, List<Integer> values, int[] counts) {

        if (node == null) {
            return;
        }

        values.add(node.val);

        if (node.right == null && node.left == null) {
            if (isPalindromic(values)) {
                counts[0] += 1;
            }
        } else {
            pseudoPalindromicPaths(node.left, values, counts);
            pseudoPalindromicPaths(node.right, values, counts);
        }
        values.remove(values.size() - 1);
    }

    private boolean isPalindromic(List<Integer> values) {
        System.out.println(values);
        Map<Integer, Long> occurences = values.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        long coundOdds = occurences.entrySet().stream()
                .filter(e -> e.getValue() % 2 == 1)
                .count();
        System.out.println(coundOdds <= 1);
        return coundOdds <= 1;
    }
}
