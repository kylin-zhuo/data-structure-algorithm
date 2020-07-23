package com.kylin.ds.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

public class DijkstraTreeSet {
	
    static class Edge {
    	
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    
    static class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencylist;

        @SuppressWarnings("unchecked")
		Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i <vertices ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist[source].addFirst(edge);

            edge = new Edge(destination, source, weight);
            adjacencylist[destination].addFirst(edge); //for undirected graph
        }

        public void dijkstra_GetMinDistances(int sourceVertex){

            boolean[] settled = new boolean[vertices];
            
            //distance used to store the distance of vertex from a source
            int [] distance = new int[vertices];
            
            Arrays.fill(distance, Integer.MAX_VALUE);
            
            //Initialize priority queue
            TreeSet<int[]> treeSet = new TreeSet<>(new Comparator<int[]>() {
            	public int compare(int[] x, int[] y) {
            		return x[0] - y[0];
            	}
            });
            
            //create the pair for for the first index, 0 distance 0 index
            distance[sourceVertex] = 0;
            int[] p0 = new int[] {0, sourceVertex};
           
            //add it to tree set
            treeSet.add(p0);

            //while priority queue is not empty
            while(!treeSet.isEmpty()){
            	
                //extract the min distance
                int[] current = treeSet.pollFirst();

                //extracted vertex
                int currentVertex = current[1];
                
                if(!settled[currentVertex]) {
                	
                	settled[currentVertex] = true;

                    //iterate through all the adjacent vertices and update the keys
                    LinkedList<Edge> edges = adjacencylist[currentVertex];
                    
                    for (Edge edge : edges) {
                    	
//                        Edge edge = edges.get(i);
                        int dest = edge.destination;
                        
                        if (!settled[dest]) {
                        	
                            int newKey =  distance[currentVertex] + edge.weight ;
                            
                            int currentKey = distance[dest];
                            
                            if (newKey < currentKey){
                            	int [] p = new int[] {newKey, dest};
                                treeSet.add(p);
                                distance[dest] = newKey;
                            }
                        }
                    }
                }
            }
            //print Shortest Path Tree
            printDijkstra(distance, sourceVertex);
        }

        public void printDijkstra(int[] distance, int sourceVertex){
        	
            System.out.println("Dijkstra Algorithm: (Adjacency List + TreeSet)");
            for (int i = 0; i <vertices ; i++) {
                System.out.println("Source Vertex: " + sourceVertex + " to vertex " +   + i +
                        " distance: " + distance[i]);
            }
        }
        
        public static void main(String[] args) {
            int vertices = 6;
            Graph graph = new Graph(vertices);
            graph.addEdge(0, 1, 1);
            graph.addEdge(0, 2, 6);
            graph.addEdge(1, 2, 2);
            graph.addEdge(1, 3, 7);
            graph.addEdge(1, 4, 8);
            graph.addEdge(2, 3, 3);
            graph.addEdge(2, 4, 9);
            graph.addEdge(3, 4, 4);
            graph.addEdge(3, 5, 10);
            graph.addEdge(4, 5, 5);
            graph.dijkstra_GetMinDistances(0);
        }
    }
}