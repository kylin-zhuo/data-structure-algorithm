package com.kylin.ds.leetcode.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Solution46 {
    
    List<List<Integer>> res;
    int[] nums;
    int N;
    
    public List<List<Integer>> permute(int[] nums) {
        
        init(nums);
        for (int n : nums) {
            backtrack(n, new HashSet<Integer>(), new LinkedList<Integer>());
        }
        if (res.size() == 0) {
            res.add(new LinkedList<Integer>());
        }
        return res;
    }
    
    void init(int[] nums) {
        res = new ArrayList<List<Integer>>();
        this.nums = nums;
        this.N = nums.length;
    }
    
    void backtrack(int num, Set<Integer> seen, LinkedList<Integer> path) {
        seen.add(num);
        path.addLast(num);
        if (path.size() == N) {
            res.add(new LinkedList<Integer>(path));
        }
        for (int n : nums) {
            if (n != num && !seen.contains(n)) {
                backtrack(n, seen, path);
            }
        }
        seen.remove(num);
        path.removeLast();
    }
    
    
}