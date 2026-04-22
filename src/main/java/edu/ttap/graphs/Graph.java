package edu.ttap.graphs;

import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.Map;
import java.util.Set;
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
     * @param entries the entries of the graph; each entry is one edge
     */
    public Graph(List<GraphEntry> entries) {
        graph= new HashMap<>();
        weights= new HashMap<>();

        for(int i=0; i<entries.size(); i++)
        {
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
     * otherwise
     */
    public boolean contains(String n) {
        return graph.containsKey(n);
    }

    /**
     * @param src the source node
     * @param dst the destination node
     * @return the weight of (src, dst) if it exists, or an empty Optional
     * otherwise
     */
    public Optional<Integer> getWeight(String src, String dst) {
        // TODO: implement me!
        return Optional.empty();
    }

    /**
     * @param start the node to begin the search, assumed to be in the graph
     * @return a list of nodes of the graph obtained via a depth-first traversal
     * beginning at the starting node.
     */
    public List<String> collectDepthFirst(String start) {
        Stack<String> stackNode = new Stack<>();
        Set<String> visitedNode = new Set<>();
        List<String> answer = new ArrayList<>();
        stackNode.push(start);
        while(stackNode!=null) {
            String checkNode = stackNode.pop();
            boolean visited = false;
            for(int i=0; i<visitedNode.length(); i++) {
            if(checkNode==visitedNode<i>) {
                visited = true;
                }
            }
            if(visited == false) {
                answer.add(checkNode);
                visitedNode.add(checkNode);
            }
            stackNode.push(Graph(checkNode));
        }
        return null;
    }

    /**
     * @param start the node to begin the search, assumed to be in the graph
     * @return a list of nodes of the graph obtained via a breadth-first traversal
     * beginning at the starting node.
     */
    public List<String> collectBreadthFirst(String start) {
        // TODO: implement me!
        return null;
    }

    /**
     * Derives a minimum spanning tree of the graph using Prim's algorithm
     * @param start the starting node for this search
     * @return a list of edges that form a minimum spanning tree of the graph
     */
    public List<Edge> deriveMST(String start) {
        // TODO: implement me!
        return null;
    }
}