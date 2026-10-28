import java.util.HashSet;
import java.util.Set;

public class NoHash {
    public static void main(String[] args) {
        Set<Card> set = new HashSet<>();
        set.add(new Card("An"));
        System.out.println(set.contains(new Card("An")));
    }
}

class Card {
    private final String owner;

    Card(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Card other && owner.equals(other.owner);
    }
}
