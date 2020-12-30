package org.cathay.palindrome;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        String max = "";
        char[] chars = s.toCharArray();

        if (isIndeticalChar(chars)) return s;

        for (int i = 0; i < chars.length; i++) {
            int j = chars.length - 1;
            while (j >= i) {
                String palindromic = isPalindromic(chars, i, j);
                if (max.length() <= palindromic.length()) max = palindromic;
                j--;
            }
        }
        return max;
    }

    String isPalindromic(char[] chars, int start, int end) {
        if (start == end) return Character.toString(chars[start]);
        int i = start;
        int j = end;
        while (i < end && j > start) {
            if (chars[i] != chars[j]) return "";
            i++;
            j--;
        }
        int length = end - start + 1;
        char[] result = new char[length];
        System.arraycopy(chars, start, result, 0, length);
        return new String(result);
    }

    boolean isIndeticalChar(char[] chars) {
        char c = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != c) return false;
        }
        return true;
    }

}
