package dev.canhdominich.core.exceptions.hierarchy;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public final class CommonExceptions {

    private CommonExceptions() {
    }

    public static String describe(Action action) {
        try {
            action.run();
            return "no exception";
        } catch (Throwable thrown) {
            return thrown.getClass().getSimpleName() + ": " + thrown.getMessage();
        }
    }

    public static List<String> catalogue() {
        String nothing = null;
        int[] data = new int[2];
        Object text = "text";
        List<String> out = new ArrayList<>();
        out.add("divide by zero           : " + describe(() -> System.out.print(10 / (data.length - 2))));
        out.add("null reference           : " + describe(() -> nothing.length()));
        out.add("array index              : " + describe(() -> data[2] = 1));
        out.add("bad cast                 : " + describe(() -> System.out.print((Integer) text)));
        out.add("parse a bad number       : " + describe(() -> Integer.parseInt("12x")));
        out.add("parse null               : " + describe(() -> Integer.parseInt(null)));
        out.add("string index             : " + describe(() -> "abc".substring(5)));
        out.add("list index               : " + describe(() -> List.of().get(0)));
        out.add("immutable list           : " + describe(() -> List.of(1).add(2)));
        out.add("empty iterator           : " + describe(() -> new ArrayList<String>().iterator().next()));
        out.add("requireNonNull with text : " + describe(() -> Objects.requireNonNull(nothing, "name is required")));
        out.add("bad capacity             : " + describe(() -> new ArrayList<String>(-1)));
        out.add("missing file (checked)   : " + describe(() -> new FileInputStream("/no/such/file.txt")));
        return out;
    }
}
