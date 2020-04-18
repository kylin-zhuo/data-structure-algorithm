package com.kylin.ds.leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution1192 {
    
    int id = 0;
    List<Integer>[] graph;
    int n;
    int[] visited; // the visited time
    int[] low;
    List<List<Integer>> res;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        /*
        To find the critical connections (also called the bridges) in a graph, 
        it needs to find all the edges that are not part of a cycle.
        
        DFS, with tracking the visited time of each node.
        Starting from a certain graph node, if there are some of its neighbor nodes that can reach
        some nodes that were visted earlier, then this node must be in a cycle.
        */
        
        buildGraph(n, connections);
        init();
        dfs();
        return res;
    }
    
    void buildGraph(int n, List<List<Integer>> connections) {
        this.n = n;
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (List<Integer> c : connections) {
            int n1 = c.get(0);
            int n2 = c.get(1);
            graph[n1].add(n2);
            graph[n2].add(n1);
        }
    }
    
    void init() {
        visited = new int[n];
        low = new int[n];
        Arrays.fill(visited, -1);
        res = new ArrayList<List<Integer>>();
    }
    
    void dfs() {
        for (int i = 0; i < n; i++) {
            if (visited[i] == -1) {
                dfs(i, -1);
            }
        }
    }
    
    void dfs(int cur, int parent) {
        visited[cur] = low[cur] = ++id;
        for (int nei : graph[cur]) {
            if (nei == parent) {
                continue;
            }
            if (visited[nei] == -1) {
                dfs(nei, cur);
                low[cur] = Math.min(low[cur], low[nei]);
                if (low[nei] > visited[cur]) {
                    res.add(Arrays.asList(cur, nei));
                }
            }
            else {
                low[cur] = Math.min(low[cur], visited[nei]);
            }
        }
    }
    
    
}