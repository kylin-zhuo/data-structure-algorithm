package com.kylin.ds;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import com.kylin.ds.model.Trie;
import com.kylin.ds.model.graph.Graph;
import com.kylin.ds.model.graph.UGraph;
import com.kylin.ds.service.Dijsktra;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DijkstraTest {

    private Graph graph;

    private Dijsktra dij;

    // @BeforeAll
    // public void init() {
    //     graph = new UGraph();
    // }
    
    @Test
    public void testDijsktraShortestPath1() {
        int vertices = 6;
        graph = new UGraph(vertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        
        dij = new Dijsktra(graph);
        // int[] dist1 = dij.getMinDistances(0);
        // assertArrayEquals(dist1, new int[] {0, 4, 3, 6, 8, 14});

        int[] dist2 = dij.getMinDistances(3);
        assertArrayEquals(dist2, new int[] {6, 2, 3, 0, 2, 8});
    }
    
}

