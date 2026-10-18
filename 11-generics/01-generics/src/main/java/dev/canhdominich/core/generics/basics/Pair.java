package dev.canhdominich.core.generics.basics;

public record Pair<A, B>(A first, B second) {

    public Pair<B, A> swapped() {
        return new Pair<>(second, first);
    }
}
