package com.kylin.ds.model.graph;

import java.util.List;
import java.util.Map;

public class Graph<T> {

    int N;  // number of nodes
    int E;  // number of edges
    Map<T, List<T>> graph;  // adjacent list

    public Graph() {

    }

}