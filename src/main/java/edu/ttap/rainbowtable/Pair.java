package edu.ttap.rainbowtable;

/**
 * An immutable, generic pair of values.
 */
public record Pair<T, U>(T first, U second) { 
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
}