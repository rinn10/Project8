package edu.ttap.graphs;

import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.Queue;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A generic, weighted, undirected graph where nodes are represented by strings.
 */
public class Graph {

    private Map<String, List<String>> graph;
    private Map<String, Map<String, Integer>> weights;

    /**
     * Constructs a graph from a list of graph entries.
     * 
     * @param entries the entries of the graph; each entry is one edge
     */
    public Graph(List<GraphEntry> entries) {
        graph = new HashMap<>();
        weights = new HashMap<>();

        for (int i = 0; i < entries.size(); i++) {
            GraphEntry entry = entries.get(i);
            String src = entry.src();
            String dest = entry.dest();
            int weight = entry.weight();

            if (!graph.containsKey(src))
                graph.put(src, new ArrayList<>());

            if (!graph.containsKey(dest))
                graph.put(dest, new ArrayList<>());

        }

    }

    /**
     * @param n the name of the node to check for
     * @return true if the graph contains a node with the given name, false
     *         otherwise
     */
    public boolean contains(String n) {
        return graph.containsKey(n);
    }

    /**
     * @param src the source node
     * @param dst the destination node
     * @return the weight of (src, dst) if it exists, or an empty Optional
     *         otherwise
     */
    public Optional<Integer> getWeight(String src, String dst) {
        // TODO: implement me!
        return Optional.empty();
    }

    /**
     * @param start the node to begin the search, assumed to be in the graph
     * @return a list of nodes of the graph obtained via a depth-first traversal
     *         beginning at the starting node.
     */
    public List<String> collectDepthFirst(String start) {
        Stack<String> stack = new Stack<>();
        Set<String> visitedNode = new HashSet<>();
        List<String> answer = new ArrayList<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            String checkNode = stack.pop();
            if (!visitedNode.contains(checkNode)) {
                answer.add(checkNode);
                visitedNode.add(checkNode);
                for (String adj : graph.get(checkNode)) {
                    stack.push(adj);
                }
            }
        }
        return answer;
    }

    /**
     * @param start the node to begin the search, assumed to be in the graph
     * @return a list of nodes of the graph obtained via a breadth-first traversal
     *         beginning at the starting node.
     */
    public List<String> collectBreadthFirst(String start) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visitedNode = new HashSet<>();
        List<String> answer = new ArrayList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            String checkNode = queue.poll();
            if (!visitedNode.contains(checkNode)) {
                answer.add(checkNode);
                visitedNode.add(checkNode);
                for (String adj : graph.get(checkNode)) {
                    queue.add(adj);
                }
            }
        }
        return answer;
    }

    /**
     * Derives a minimum spanning tree of the graph using Prim's algorithm
     * 
     * @param start the starting node for this search
     * @return a list of edges that form a minimum spanning tree of the graph
     */
    public List<Edge> deriveMST(String start) {
        Set<String> visitedV = new HashSet<>();
        List<Edge> visitedE = new ArrayList<>();
        Map<String, String> vertexEdge = new HashMap<>();
        visitedV.add(start);
        while(XX) {
            String checkV = get.visitedV();
            int minE = 0;
            getWeight(checkV, adj);
        }
        return null;
    }
}

/*
A mapping from each vertex v in the graph to the minimum weight edge from 
the vertices visited so far to v. Initially, there are no such mappings in the graph.

Beginning with the start vertex:
Add the start vertex to the visited collection.
Update the vertex-edge mapping with the start vertex:
For each vertex incident to the start vertex, add a binding from that vertex to the edge connecting it and the start vertex to the map.
While there is a vertex that does not appear in the visited collection:
Choose an unvisited vertex v in the mapping whose corresponding edge has minimum weight among all the unvisited vertices. Add that edge to the visited edge collection.
Add v to the visited collection.
Update the vertex-edge mapping with v:
For each vertex u incident to v, add/update the binding with the edge (u,v) if it does not exist yet or is smaller than the current edge for u. */