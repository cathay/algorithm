package org.cathay.combination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterPhoneNumberCombination {

    public static void main(String[] args) {
        System.out.println(combine("23"));
        System.out.println(combine("234"));
    }

    static List<String> combine(String digits) {
        Map<String, String> phoneNumberToLetters = Map.of(
                "1", "",
                "2", "abc",
                "3", "def",
                "4", "ghi",
                "5", "jkl",
                "6", "mno",
                "7", "pqrs",
                "8", "tuv",
                "9", "wxyz",
                "0", " "
        );

        List<String> result = new ArrayList<>();

        for (int i = 0; i < digits.length(); i++) {
            String letters = phoneNumberToLetters.get(String.valueOf(digits.charAt(i)));
            result = combination(result, letters);
        }
        return result;
    }

    private static List<String> combination(final List<String> inputA,
                                            final String letters) {

        List<String> result = new ArrayList<>();
        if (inputA.isEmpty()) {
            for (char c: letters.toCharArray()) {
                result.add(String.valueOf(c));
            }
            return result;
        }


        for (String item : inputA) {
            for (int i = 0; i < letters.length(); i++) {
                result.add(item + letters.charAt(i));
            }
        }
        return result;
    }

}
