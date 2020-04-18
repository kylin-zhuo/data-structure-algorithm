package com.kylin.ds.leetcode.solutions;

class Solution1201 {
    
    public int nthUglyNumber(int n, int a, int b, int c) {
        
        /*
        Efficient Approach: The idea is to use Binary search. 
        Formula to calculate how many numbers from 1 to num are divisible by either a, b or c:
        
        (num / a) + (num / b) + (num / c) 
        – (num / lcm(a, b)) – (num / lcm(b, c)) – (num / lcm(a, c)) 
        + (num / lcm(a, b, c))
        */
        
        int lo = 1, hi = Integer.MAX_VALUE;
        int mid;
        while (lo < hi) {
            mid = lo + (hi - lo) /2;
            if (count(a, b, c, mid) < n) {
                lo = mid + 1;
            }
            else {
                hi = mid;
            }
        }
        return lo;
    }
    
    long gcd(long a, long b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
    
    long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    } 
    
    long count(long a, long b, long c, long num) {
        return ((num / a) + (num / b) + (num / c)
                - (num / lcm(a, b)) - (num / lcm(b, c)) - (num / lcm(a, c))
                + (num / lcm(a, lcm(b, c))));
    }
}