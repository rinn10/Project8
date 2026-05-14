package edu.ttap;

import edu.ttap.graphs.Graph;
import edu.ttap.graphs.GraphEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args)
            throws FileNotFoundException {

        List<String> machines = readMachines();

        String[] configs = {"A", "B", "C", "D", "E"};

        for (String config : configs) {
            Graph g = readGraph(config);

            String start = firstMachineInGraph(g, machines);
            List<String> reachable = g.collectBreadthFirst(start);

            System.out.println("Configuration " + config);
            System.out.println("Start: " + start);
            System.out.println("Reachable count: " + reachable.size()
                    + " / " + machines.size());

            Set<String> reachableSet = new HashSet<>(reachable);

            System.out.println("Missing machines:");
            for (String machine : machines) {
                if (!reachableSet.contains(machine)) {
                    System.out.println(machine);
                }
            }

            System.out.println();
        }
    }

    public static List<String> readMachines()
            throws FileNotFoundException {

        List<String> machines = new ArrayList<>();

        Scanner file =
            new Scanner(new File("data/mathlan-machines.txt"));

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();

            if (!line.isEmpty()) {
                machines.add(line);
            }
        }

        file.close();
        return machines;
    }

    public static Graph readGraph(String config)
            throws FileNotFoundException {

        List<GraphEntry> entries = new ArrayList<>();

        Scanner file =
            new Scanner(new File("data/mathlan."
                    + config + ".data"));

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();

            if (!line.isEmpty()) {
                String[] parts = line.split("\\s+");

                if (parts.length >= 2) {
                    String src = parts[0];
                    String dest = parts[1];

                    entries.add(new GraphEntry(src, dest, 1));
                }
            }
        }

        file.close();
        return new Graph(entries);
    }

    public static String firstMachineInGraph(
            Graph g,
            List<String> machines) {

        for (String machine : machines) {
            if (g.contains(machine)) {
                return machine;
            }
        }

        return machines.get(0);
    }
}