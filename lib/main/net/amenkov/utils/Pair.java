package net.amenkov.utils;

/**
 * Created by dev_il on 29.05.15.
 */
public class Pair<T extends Number & Comparable<T>, S extends Number & Comparable<S>>
        implements Comparable<Pair> {
    public T first;
    public S second;

    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }

    public static <T extends Number, S extends Number> Pair makePair(T first, S second) {
        return new Pair(first, second);
    }

    @Override
    public int compareTo(Pair o) {
        if (this.first.equals(o.first)) {
            return second.compareTo((S) o.second);
        }

        return first.compareTo((T) o.first);
    }
}
