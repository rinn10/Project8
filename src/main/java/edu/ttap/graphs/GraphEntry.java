package edu.ttap.graphs;

/**
 * An entry in a graph data file, capturing the source node, destination node,
 * and weight of the edge between them.
 */
public record GraphEntry(String src, String dest, int weight) { 
    private String src;
    private String dest;
    private int weight;

    public GraphEntry(String src, String dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public String src() {
        return src;
    }

    public String dest() {
        return dest;
    }

    public int weight() {
        return weight;
    }
}