package com.kylin.ds.leetcode.solutions;

import java.util.Arrays;

class Solution741 {
    
    int[][][] memo;
    int[][] grid;
    int n;
    
    public int cherryPickup(int[][] grid) {
        
        /*
        Equivalent: two people walking from (0,0) to (m-1,n-1) to pick.
        the position they are standing are: (i1, j1) and (i2, j2)
        There is a constraint that: i1+j1 = i2+j2 (imagine they set off at the same time)
        
        Dynamic programming, state:
        dp[i1][j1][j2] - the most number of cherries obtained by two people starting at
        (i1,j1) and (i1+j1-j2, j2), walking downwards toward (m-1,n-1).
        
        if grid[i1][j1] and grid[i2][j2] are not horns:
            dp[i1][j1][j2] = (grid[i1][j1] + grid[i2][j2]) + 
                            max(dp[i1+1][j1][j2], dp[i1][j1+1][j2], dp[i1+1][j1][j2+1], dp[i1][j1+1][j2+1])
        avoid double count when (i1,j1) == (i2,j2)
        
        */
        
        this.grid = grid;
        n = grid.length;
        memo = new int[n][n][n];
        
        for (int[][] mem : memo) for (int[] me : mem) Arrays.fill(me, Integer.MIN_VALUE);
        return Math.max(0, dp(0, 0, 0));
        
    }
    
    int dp(int i1, int j1, int j2) {
        int i2 = i1 + j1 - j2;
        if (i1 == n || i2 == n || j1 == n || j2 == n | grid[i1][j1] == -1 || grid[i2][j2] == -1) {
            return Integer.MIN_VALUE / n;
        }
        else if (i1 == n-1 && j1 == n-1) {
            return grid[n-1][n-1];
        } else if (memo[i1][j1][j2] != Integer.MIN_VALUE) {
            return memo[i1][j1][j2];
        } else {
            int ans = grid[i1][j1];
            if (j1 != j2) {
                ans += grid[i2][j2];
            }
            ans += Math.max(Math.max(dp(i1+1, j1, j2), dp(i1, j1+1, j2)), 
                            Math.max(dp(i1+1, j1, j2+1), dp(i1, j1+1, j2+1)));
            memo[i1][j1][j2] = ans;
            return ans;
        }
    }
}