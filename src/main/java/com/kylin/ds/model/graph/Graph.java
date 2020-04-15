package com.kylin.ds.model.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Graph {

    public int N(); // number of Vertices

    public int E(); // number of Edges

    Map<Integer, List<Edge>> adjacentList = new HashMap<>();  // adjacent list

    public void addEdge(int source, int destination, int weight);

    public int shortestDistance(int source, int destination);

    public Map<Integer, List<Edge>> getAdjacentList();
    
}