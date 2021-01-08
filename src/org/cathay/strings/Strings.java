package org.cathay.strings;

import java.util.*;
import java.util.stream.Collectors;

public class Strings {

    public static void main(String[] args) {
        Strings solution = new Strings();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("aab"));
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), left = 0, right = 0, ans = 0;
        if(n == 0)
            return 0;

        Map<Character, Integer> hm = new HashMap<>();
        while(right < n){
            char rch = s.charAt(right);

            if(!hm.containsKey(rch)){
                hm.put(rch, 1);

                ans = Math.max(ans, right-left+1);
                right++;
            }else{
                char lch = s.charAt(left);

                hm.remove(lch);
                left++;
            }
        }

        return ans;

    }
}
