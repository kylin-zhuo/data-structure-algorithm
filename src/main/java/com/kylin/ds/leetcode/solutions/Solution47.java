package com.kylin.ds.leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution47 {
    
    int[] nums;
    int n;
    boolean[] seen;
    List<List<Integer>> res;
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        
        init(nums);
        backtrack(new LinkedList<Integer>(), new boolean[n]);
        return res;
    }
    
    void init(int[] nums) {
        this.nums = nums;
        n = nums.length;
        seen = new boolean[n];
        Arrays.sort(nums);
        res = new ArrayList<List<Integer>>();
    }
    
    void backtrack(LinkedList<Integer> path, boolean[] seen) {
        if (path.size() == n) {
            res.add(new LinkedList<Integer>(path));
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (seen[i] || (i > 0 && !seen[i-1] && nums[i] == nums[i-1])) {
                continue;
            }
            path.addLast(nums[i]);
            seen[i] = true;
            backtrack(path, seen);
            seen[i] = false;
            path.removeLast();
        }
    }
    
    
    
}