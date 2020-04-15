package com.kylin.ds.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import com.kylin.ds.model.graph.Edge;
import com.kylin.ds.model.graph.Graph;

public class Dijsktra {

    Graph graph;
    int n;

    class State {
        int node;
        int value;
        State (int node, int value) {
            this.node = node;
            this.value = value;
        }
        // boolean equals(State s) {
        //     return this.value == s.value && this.node == s.node;
        // }
    }

    // static class NodeComparator implements Comparable<State> {
    //     @Override
    //     public int compare(State s1, State s2) {
    //         return s1.value - s2.value;
    //     }
    // }

    public Dijsktra(Graph graph) {
        this.graph = graph;
        this.n = graph.N();
    }

    public int[] getMinDistances(int source) {
        boolean[] settled = new boolean[n];
        int[] distance = new int[n];
        
        // initialized all distances to infinity
        Arrays.fill(distance, Integer.MAX_VALUE);

        TreeSet<State> indexSet = new TreeSet<>(new Comparator<State>() {
            @Override
            public int compare(State s1, State s2) {
                return s1.value - s2.value;
            }
        });
        distance[source] = 0;
        State sourceIndex = new State(source, 0);
        indexSet.add(sourceIndex);

        while (!indexSet.isEmpty()) {
            State minDistIndex = indexSet.pollFirst();
            int curnode = minDistIndex.node;
            System.out.println(curnode + Arrays.toString(distance));
            if (!settled[curnode]) {
                settled[curnode] = true;
                // iterate the adjacent nodes
                List<Edge> neighbors = graph.getAdjacentList().get(curnode);
                for (Edge nei : neighbors) {
                    int neinode = nei.getDestination();
                    int weight = nei.getWeight();
                    if (!settled[neinode]) {
                        int d1 = distance[curnode] + weight;
                        int d2 = distance[neinode];
                        if (d1 < d2) {
                            State newState = new State(neinode, d1);
                            indexSet.add(newState);
                            distance[neinode] = d1;
                        } 
                    }
                }

            }

        }

        return distance;
    }

}