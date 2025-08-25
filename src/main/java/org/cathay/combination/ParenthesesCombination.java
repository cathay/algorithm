package org.cathay.combination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParenthesesCombination {

    public static void main(String[] args) {
//        System.out.println(solution2());
//        System.out.println(solutionN(3, new HashMap<>()));
        System.out.println(solutionN(4));

        List<String> a = new ArrayList<>(List.of(
                "()()()()", "(()()())", "()(()())",
                "(()())()", "((()()))", "()()(())", "()(())()", "(()(()))",
                "(())()()", "((())())", "()((()))", "((()))()", "(((())))"
        ));

        List<String> b = new ArrayList<>(List.of(
                "(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()"
        ));

        Collections.sort(a);
        Collections.sort(b);
        System.out.println(a);
        System.out.println(b);
    }

    public static List<String> solution1() {
        return List.of("()");
    }

    public static List<String> solution2() {
        List<String> result = new ArrayList<>();
        for (String s : solution1()) {
            result.add("()" + s);
            result.add("(" + s + ")");
        }
        return result;
    }

    public static List<String> solutionN(int n) {
        if (n == 1) return solution1();
        if (n == 2) return solution2();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            List<String> part1 = solutionN(i + 1);
            for (String s1 : part1) {
                List<String> part2 = solutionN(n - 1 - i);
                for (String s2 : part2) {
                    String combined = s1 + s2;
                    if (!result.contains(combined)) {
                        result.add(combined);
                    }
                }
            }
        }

        for (String s : solutionN(n - 1)) {
            String combined = "(" + s + ")";
            if (!result.contains(combined)) {
                result.add(combined);
            }
        }
        return result;
    }
}
