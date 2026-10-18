package dev.canhdominich.core.oop.overview;

public interface Channel {

    String send(String to, String text);

    int cost();

    boolean accepts(String address);
}
