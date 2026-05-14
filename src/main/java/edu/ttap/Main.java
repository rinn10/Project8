package edu.ttap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * The driver for our lab on lists.
 */
public class Main {

    /**
     * Main method that analyzes machine connectivity for different configurations.
     *
     * @param args Command-line arguments
     * @throws FileNotFoundException If data files cannot be found
     */
    public static void main(String[] args) throws FileNotFoundException {

        List<String> machines = readMachines();

        String[] configs = {"A", "B", "C", "D", "E"};

        for (String config : configs) {
            Map<String, List<String>> graph = readGraph(config, machines);
            Set<String> bestReachable = new HashSet<>();
            String bestStart = "";

            for (String machine : machines) {
                Set<String> reachable = bfs(graph, machine);
                if (reachable.size() > bestReachable.size()) {
                    bestReachable = reachable;
                    bestStart = machine;
                }
            }

            System.out.println("Configuration " + config);
            System.out.println("Best start: " + bestStart);
            System.out.println("Reachable count: " + bestReachable.size() 
                + " / " + machines.size());
            System.out.println("Missing machines:");

            for (String machine : machines) {
                if (!bestReachable.contains(machine)) {
                    System.out.println(machine);
                }
            }
            System.out.println();
        }
    }

    /**
     * Reads the list of machines from the data file.
     *
     * @return A list of machine names
     * @throws FileNotFoundException If the machines data file cannot be found
     */
    public static List<String> readMachines() throws FileNotFoundException {
        List<String> machines = new ArrayList<>();
        Scanner file = new Scanner(new File("data/mathlan-machines.txt"));

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();
            if (!line.isEmpty()) {
                machines.add(line);
            }
        }
        file.close();
        return machines;
    }

    /**
     * Reads the graph data for a given configuration.
     *
     * @param config The configuration letter (A-E)
     * @param machines The list of machines to include in the graph
     * @return A map representing the graph with adjacency lists
     * @throws FileNotFoundException If the graph data file cannot be found
     */
    public static Map<String, List<String>> readGraph(String config, List<String> machines)
            throws FileNotFoundException {
        Map<String, List<String>> graph = new HashMap<>();

        for (String machine : machines) {
            graph.put(machine, new ArrayList<>());
        }

        Scanner file = new Scanner(new File("data/mathlan." + config + ".data"));
        while (file.hasNextLine()) {
            String line = file.nextLine().trim();

            if (!line.isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String src = parts[0].trim();
                    String dest = parts[1].trim();

                    if (graph.containsKey(src) && graph.containsKey(dest)) {
                        graph.get(src).add(dest);
                        graph.get(dest).add(src);
                    }
                }
            }
        }
        file.close();
        return graph;
    }

    /**
     * Do a breadth-first search to find all reachable nodes from a starting node.
     *
     * @param graph The graph represented as an adjacency list
     * @param start The starting node for the BFS
     * @return A set of all nodes reachable from the start node
     */
    public static Set<String> bfs(Map<String, List<String>> graph, String start) {

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (!visited.contains(current)) {
                visited.add(current);
                for (String neighbor : graph.get(current)) {
                    queue.add(neighbor);
                }
            }
        }
        return visited;
    }
}