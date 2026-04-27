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

            graph.get(src).add(dest);
            graph.get(dest).add(src);

            if (!weights.containsKey(src))
                weights.put(src, new HashMap<>());

            if (!weights.containsKey(dest))
                weights.put(dest, new HashMap<>());

            weights.get(src).put(dest, weight);
            weights.get(dest).put(src, weight);
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
        if (weights.containsKey(src) && weights.get(src).containsKey(dst)) {
            return Optional.of(weights.get(src).get(dst));
        }
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
        Map<String, Edge> vertexEdge = new HashMap<>();
        visitedV.add(start);
        for (String neighbor : graph.get(start)) {
            int weight = getWeight(start, neighbor).get();
            vertexEdge.put(neighbor, new Edge(start, neighbor));
        }

        while (visitedV.size() != graph.size()) {
            int minWeight = Integer.MAX_VALUE;
            String minVert = null;
            for (String vert : vertexEdge.keySet()) {
                if (!visitedV.contains(vert)) {
                    int weight = getWeight(vertexEdge.get(vert).src(), vert).get();
                    if (weight < minWeight) {
                        minWeight = weight;
                        minVert = vert;
                    }
                }
            }
            visitedE.add(vertexEdge.get(minVert));
            visitedV.add(minVert);

            for (String neighbor : graph.get(minVert)) {
                if (!visitedV.contains(neighbor)) {
                    if (!vertexEdge.containsKey(neighbor)) {
                        vertexEdge.put(neighbor, new Edge(minVert, neighbor));
                    } else {
                        int oldWeight = getWeight(vertexEdge.get(neighbor).src(), neighbor).get();
                        int newWeight = getWeight(minVert, neighbor).get();
                        if (newWeight < oldWeight) {
                            vertexEdge.put(neighbor, new Edge(minVert, neighbor));
                        }
                    }
                }
            }
        }
        return visitedE;
    }
}
/*
 * Part 5: Applying Graph Algorithms
 * 
 * DFS:
 * Question: Starting from one node, what nodes can I reach if I keep
 * going as deep as possible?
 * Answer: DFS would visit nodes by going along one path fully before
 * backtracking and exploring
 * other paths.
 * 
 * BFS:
 * Question: Starting from one node, what nodes are closest to it?
 * Answer: BFS would visit nodes level by level, so it shows which nodes
 * are 1 step away, then 2 steps away, and so on.
 * 
 * Prim's Algorithm:
 * Question: What is the cheapest way to connect all nodes in the graph?
 * Answer: Prim’s algorithm would return a set of edges that connects all
 * nodes with the smallest total weight.
 */