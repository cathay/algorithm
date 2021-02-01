package org.cathay.datastructure;

public class Pair<U, V> {
    public final U first;       // first field of a Pair
    public final V second;      // second field of a Pair

    // Constructs a new Pair with specified values
    private Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    // Factory method for creating a Typed Pair immutable instance
    public static <U, V> Pair<U, V> of(U a, V b) {
        // calls private constructor
        return new Pair<>(a, b);
    }
}