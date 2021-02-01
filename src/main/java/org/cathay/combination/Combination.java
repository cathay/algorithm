package org.cathay.combination;

import java.util.*;

/**
 * FIXME: Example 1
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * <p>
 * FIXME: Example 2
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * FIXME: Example 3
 * Input: candidates = [1], target = 2
 * Output: []
 */
public class Combination {

    public static void main(String[] args) {
        Combination solution = new Combination();
//        System.out.println(solution.combinationSum(
//                new int[]{2, 3, 6, 7},
//                7
//        ));
        System.out.println(solution.combinationSum(
                new int[]{2, 3, 5},
                8
        ));
//        System.out.println("......");
//        System.out.println(solution.combinationSum(
//                new int[]{2,7,6,3,5,1},
//                9
//        ));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);
        int i = candidates.length - 1;
        while (i >= 0 && candidates[i] > target) i--;

        if (i < 0) return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();

        if (candidates[i] == target) result.add(Collections.singletonList(target));
        while (i >= 0 && candidates[i] == target) i--;

        if (i < 0) return result;

        int[] filter = new int[i + 1];
        System.arraycopy(candidates, 0, filter, 0, filter.length);

        dfs(filter, 0, target, result, new ArrayList<>());
        return new ArrayList<>(new HashSet<>(result));
    }

    private void dfs(int[] filter,
                     int index,
                     int target,
                     List<List<Integer>> result,
                     ArrayList<Integer> track) {

        if (target < 0) return;

        if (target == 0) {
            Collections.sort(track);
            result.add(new ArrayList<>(track));
            return;
        }

        for (int i = index; i < filter.length; i++) {
            track.add(filter[i]);
            dfs(filter, i, target - filter[i], result, track);
            track.remove(track.size() - 1);
        }
    }
}
