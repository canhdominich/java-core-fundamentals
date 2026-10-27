import java.util.ArrayList;
import java.util.List;

public class Hosts {
    static final List<String> HOSTS = new ArrayList<>(List.of("a.example"));

    public static void main(String[] args) {
        HOSTS.add("b.example");
        System.out.println(HOSTS);
    }
}
