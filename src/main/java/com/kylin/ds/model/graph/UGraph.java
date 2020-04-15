package com.kylin.ds.model.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Undirected Graph
 * 
 */
public class UGraph implements Graph {

    int N = 0;
    int E = 0;
    Map<Integer, List<Edge>> adjacentList = new HashMap<>();

    // public UGraph() {
    //     N = 0;
    //     E = 0;
    //     adjacentList = new HashMap<>();
    // }

    public UGraph(int n) {
        this.N = n;
        for (int i = 0; i < n; i++) {
            adjacentList.put(i, new LinkedList<Edge>());
        }
    }
    
    @Override
    public void addEdge(int source, int destination, int weight) {
        if (!adjacentList.containsKey(source)) {
            adjacentList.put(source, new LinkedList<Edge>());
        }
        adjacentList.get(source).add(new Edge(source, destination, weight));
        if (!adjacentList.containsKey((destination))) {
            adjacentList.put(destination, new LinkedList<Edge>());
        }
        adjacentList.get(destination).add(new Edge(destination, source, weight));
    }

    @Override
    public int shortestDistance(int source, int destination) {
        return 0;
    }

    @Override
    public int N() {
        return N;
    }

    @Override
    public int E() {
        return E;
    }

    public Map<Integer, List<Edge>> getAdjacentList() {
        return adjacentList;
    }
    
}